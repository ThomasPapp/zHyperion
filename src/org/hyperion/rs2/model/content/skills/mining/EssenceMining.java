package org.hyperion.rs2.model.content.skills.mining;

import org.hyperion.rs2.action.impl.HarvestingAction;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Location;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Skills;

public class EssenceMining extends HarvestingAction {

	/**
	 * Constructs a new <code>EssenceMining</code> <code>Action</code>.
	 * 
	 * @param player
	 *            The <code>Player</code> to construct the <code>Action</code> for.
	 * @param location
	 *            The <code>Location</code> of the <code>Action</code>.
	 */
	public EssenceMining(Player player, Location location) {
		super(player, location);
	}
	
	/**
	 * The <code>Pick</code> we're using.
	 */
	private final Pick pick = getPick();
	
	/**
	 * Gets the <code>Pick</code> to use.
	 * @return The <code>Pick</code>.
	 */
	private Pick getPick() {
		for (Pick pick : Pick.values()) {
			if((player.getEquipment().contains(pick.getId()) || player.getInventory().contains(pick.getId())) && player.getSkills().getLevel(Skills.MINING) >= pick.getLevel()) {
				return pick;
			}
		}
		return null;
	}

	@Override
	public void init() {
		
		/*
		 * If the <code>Player</code> doesn't have a use-able <code>Pick</code> then we stop the action and send a message.
		 */
		if (pick == null) {
			player.getActionSender().sendMessage("You do not have a pickaxe that you can use.");
			this.stop();
			return;
		}
		
		/*
		 * If the <code>Player</code>s inventory is full then we stop the action and send a message.
		 */
		if (player.getInventory().freeSlots() <= 0) {
			player.getActionSender().sendMessage("Not enough space in your inventory.");
			this.stop();
			return;
		}
		
		/*	Plays the pick's <code>Animation</code>.	*/
		player.getActionSender().sendMessage("You swing your pick at the rock.");
	}

	@Override
	public long getHarvestDelay() {
		return pick.getSpeed();
	}

	@Override
	public int getCycles() {
		return 28;
	}

	@Override
	public Item getHarvestedItem() {
		return player.getSkills().getLevel(Skills.MINING) < 30 ? new Item(1436) : new Item(7936);
	}

	@Override
	public double getExperience() {
		return 5;
	}

	@Override
	public int getSkill() {
		return Skills.MINING;
	}

	@Override
	public Animation getAnimation() {
		return pick.getAnimation();
	}

	@Override
	public boolean getPeriodicRewards() {
		return true;
	}

}
