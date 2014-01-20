package org.hyperion.rs2.model.npc;

import org.hyperion.rs2.model.Damage.HitType;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Location;
import org.hyperion.rs2.model.region.Region;
import org.hyperion.rs2.util.Misc;

/**
 * <p>Represents a non-player character in the in-game world.</p>
 * 
 * @author Thomas
 *
 */
public class NPC extends Entity {
	
	/**
	 * Constructs a new NPC.
	 * 
	 * @param npcId
	 *            The NPC's id.
	 */
	public NPC(int npcId) {
		this.npcId = npcId;
		this.def = NPCDefinition.forId(npcId);
	}
	
	/**
	 * The NPC's id.
	 */
	private int npcId;
	
	/**
	 * The NPC's definition.
	 */
	private NPCDefinition def;
	
	/**
	 * The minimum walking location for the npc.
	 */
	private Location minWalk = Location.create(0, 0, 0);
	
	/**
	 * The maximum walking location for the npc.
	 */
	private Location maxWalk = Location.create(0, 0, 0);;
	
	/**
	 * The facing of the npc.
	 */
	private String face;
	
	/**
	 * Does the npc walk?
	 */
	private boolean walk;
	
	/**
	 * Randomly walks the NPC.
	 */
	private void randomWalk() {
		if (!walk) {
			if (this.getFaceLocation().toString() != getFacingLocation(this.getLocation()).toString()) {
				this.face(getFacingLocation(this.getLocation()));
			}
		} else {
			 if (Misc.random(9) == 0) {
				 int x = minWalk.getX(), y = minWalk.getY(), width = maxWalk.getX()-minWalk.getX(), length = maxWalk.getY()-minWalk.getY();
				 int x1 = Misc.getRandom().nextInt(width), y1 = Misc.getRandom().nextInt(length);
				 this.getWalkingQueue().reset();
				 this.getWalkingQueue().addStep(x+x1, y+y1);
			 }
		}
	}
	
	/**
	 * Gets the facing location for the npc.
	 * 
	 * @param loc
	 *            The facing location for the npc.
	 * @return The location for the npc to face.
	 */
	public Location getFacingLocation(Location loc) {
		int x = loc.getX();
		int y = loc.getY();
		switch (face) {
			case "north":
				return Location.create(x, y + 1, loc.getZ());
			case "south":
				return Location.create(x, y - 1, loc.getZ());
			case "east":
				return Location.create(x + 1, y, loc.getZ());
			case "west":
				return Location.create(x - 1, y, loc.getZ());
			default:
				return Location.create(x, y - 1, loc.getZ());
		}
	}
	
	/**
	 * Sets the minimum walking location for the npc.
	 * 
	 * @param minWalk
	 *            The minimum walking location for the npc.
	 */
	public void setMinWalk(Location minWalk) {
		this.minWalk = minWalk;
	}
	
	/**
	 * Sets the maximum walking location for the npc.
	 * 
	 * @param maxWalk
	 *            The maximum walking location for the npc.
	 */
	public void setMaxWalk(Location maxWalk) {
		this.maxWalk = maxWalk;
	}
	
	/**
	 * Sets the facing for the npc.
	 * 
	 * @param face
	 *            The face direction for the npc.
	 */
	public void setFace(String face) {
		this.face = face;
	}
	
	/**
	 * Sets the walk boolean.
	 * 
	 * @param walk 
	 *            If the npc should walk <code>true<code>,
	 *            if npc shouldn't walk <code>false</code>.
	 */
	public void setWalk(boolean walk) {
		this.walk = walk;
	}
	
	/**
	 * Gets the NPC's id.
	 * @return The NPC's id.
	 */
	public int getId() {
		return npcId;
	}
	
	/**
	 * Gets the NPC's definitions.
	 * @return The NPC's definitions.
	 */
	public NPCDefinition getDef() {
		return def;
	}
	
	/**
	 * Gets the minimum walking location for the npc.
	 * @return The minimum walking location.
	 */
	public Location getMinWalk() {
		return minWalk;
	}
	
	/**
	 * Gets the maximum walking location for the npc.
	 * @return The maximum walking location.
	 */
	public Location getMaxWalk() {
		return maxWalk;
	}
	
	/**
	 * Should the npc walk?
	 * @return If the npc should walk <code>true<code>,
	 * if npc shouldn't walk <code>false</code>.
	 */
	public boolean shouldWalk() {
		return walk;
	}

	@Override
	public void process() {
		randomWalk();
	}

	@Override
	public void inflictDamage(int damage, HitType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void addToRegion(Region region) {
            region.addNpc(this);
    }

    @Override
    public void removeFromRegion(Region region) {
            region.removeNpc(this);
    }

	@Override
	public int getClientIndex() {
		return this.getIndex();
	}

}
