package org.hyperion.rs2.packet;

import org.hyperion.rs2.action.impl.WalkToAction;
import org.hyperion.rs2.model.GameObject;
import org.hyperion.rs2.model.Location;
import org.hyperion.rs2.model.ObjectHandler;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.content.skills.mining.EssenceMining;
import org.hyperion.rs2.model.content.skills.mining.Ore;
import org.hyperion.rs2.model.content.skills.mining.OreMining;
import org.hyperion.rs2.model.content.skills.mining.Prospecting;
import org.hyperion.rs2.model.content.skills.woodcutting.Tree;
import org.hyperion.rs2.model.content.skills.woodcutting.Woodcutitng;
import org.hyperion.rs2.net.Packet;

/**
 * Object option packet handler.
 * @author Graham Edgecombe
 *
 */
public class ObjectOptionPacketHandler implements PacketHandler {
	
	/**
	 * Option 1 opcode.
	 */
	private static final int OPTION_1 = 132, OPTION_2 = 252;

	@Override
	public void handle(Player player, Packet packet) {
		switch(packet.getOpcode()) {
		case OPTION_1:
			handleOption1(player, packet);
			break;
		case OPTION_2:
			handleOption2(player, packet);
			break;
		}
	}

	/**
	 * Handles the option 1 packet.
	 * @param player The player.
	 * @param packet The packet.
	 */
	private void handleOption1(Player player, Packet packet) {
		final int x = packet.getLEShortA() & 0xFFFF;
		final int id = packet.getShort() & 0xFFFF;
		final int y = packet.getShortA() & 0xFFFF;
		final Location loc = Location.create(x, y, player.getLocation().getZ());
		final GameObject object = ObjectHandler.getInstance().getObject(loc.getX(), loc.getY(), loc.getZ());
		player.getActionSender().sendMessage("Object first click: "+ loc.toString() +" id: "+ id);
		player.getActionQueue().addAction(new WalkToAction(player) {

			@Override
			public void init() {
				player.face(loc);
				if (Tree.isTree(id)) {
					this.stop();
					player.getActionQueue().addAction(new Woodcutitng(player, object));
					return;
				}
				if (Ore.forId(id) != null) {
					this.stop();
					player.getActionQueue().addAction(new OreMining(player, object));
					return;
				}
				switch (id) {
					case 2491:
						this.stop();
						player.getActionQueue().addAction(new EssenceMining(player, loc));
						break;
				}
				this.stop();
			}

			@Override
			public long getDelay() {
				return 1000;
			}

			@Override
			public Location getDestination() {
				return loc;
			}
			
		});
	}
	
    /**
     * Handles the option 2 packet.
     * @param player The player.
     * @param packet The packet.
     */
    private void handleOption2(Player player, Packet packet) {        
        int id = packet.getLEShortA() & 0xFFFF;
        int y = packet.getLEShort() & 0xFFFF;
        int x = packet.getShortA() & 0xFFFF;
        Location loc = Location.create(x, y, player.getLocation().getZ());
        GameObject object = ObjectHandler.getInstance().getObject(loc.getX(), loc.getY(), loc.getZ());
		player.face(loc);
		if (Ore.forId(id) != null) {
			player.getActionQueue().addAction(new Prospecting(player, loc, Ore.forId(id)));
		}
    }


}
