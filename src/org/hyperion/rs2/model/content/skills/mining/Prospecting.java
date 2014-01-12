package org.hyperion.rs2.model.content.skills.mining;

import org.hyperion.rs2.action.impl.InspectAction;
import org.hyperion.rs2.model.Location;
import org.hyperion.rs2.model.Player;

public class Prospecting extends InspectAction {

	/**
	 * Constructs a new <code>Prospecting</code> <code>Action</code>.
	 * 
	 * @param player
	 *            The <code>Player</code> to construct the <code>Action</code> for.
	 * @param location
	 *            The <code>Location</code> of the <code>Action</code>.
	 * @param ore
	 *            The <code>Ore</code> we're prospecting.
	 */
	public Prospecting(Player player, Location location, Ore ore) {
		super(player, location);
		this.ore = ore;
	}
	
	/**
	 * The <code>Ore</code> we're prospecting.
	 */
	private final Ore ore;

	@Override
	public void init() {
		player.getActionSender().sendMessage("You examine the rock for ores...");
	}

	@Override
	public long getInspectDelay() {
		return 2000;
	}

	@Override
	public void giveRewards(Player player) {
		player.getActionSender().sendMessage("This rock contains "+ ore.getOre().getDefinition().getName() +".");
	}

}
