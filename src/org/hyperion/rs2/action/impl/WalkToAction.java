package org.hyperion.rs2.action.impl;

import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.model.Location;
import org.hyperion.rs2.model.Player;

/**
 * Handles walk to actions. This is normally used for actions such as object clicking 
 * or npc clicking.
 * 
 * @author Thomas
 *
 */
public abstract class WalkToAction extends Action {

	public WalkToAction(Player player) {
		super(player, 0);
	}

	@Override
	public QueuePolicy getQueuePolicy() {
		return QueuePolicy.NEVER;
	}

	@Override
	public WalkablePolicy getWalkablePolicy() {
		return WalkablePolicy.NON_WALKABLE;
	}

	@Override
	public void execute() {
		if(this.getDelay() == 0) {
			this.setDelay(getDelay());
		} else {
			if (player.getLocation().isWithinInteractionDistance(getDestination())) {
				init();
			}
		}
	}
	
	/**
	 * Called when the action is initialized.
	 */
	public abstract void init();
	
	/**
	 * Gets the <code>Action</code>s delay.
	 */
	public abstract long getDelay();
	
	/**
	 * Gets the <code>Location</code> of the destination.
	 * @return The <code>Location</code> of the destination.
	 */
	public abstract Location getDestination();

}
