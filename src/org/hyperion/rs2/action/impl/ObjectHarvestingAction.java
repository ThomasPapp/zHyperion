package org.hyperion.rs2.action.impl;

import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.GameObject;
import org.hyperion.rs2.model.Player;

/**
 * Handles object harvesting actions.
 * 
 * @author Thomas
 *
 */
public abstract class ObjectHarvestingAction extends Action {

	/**
	 * Constructs a new <code>ObjectHarvestingAction</code>.
	 * 
	 * @param player
	 *            The player to construct the action for.
	 * @param object
	 *            The object to harvest from.
	 */
	public ObjectHarvestingAction(Player player, GameObject object) {
		super(player, 0);
		this.object = object;
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
		if (this.getDelay() == 0) {
			this.setDelay(getHarvestDelay());
			this.canHarvest();
		} else {
			if (!this.process()) {
				player.playAnimation(Animation.create(-1));
				this.stop();
				return;
			}
			this.init();
		}
	}
	
	/**
	 * The <code>GameObject we're harvesting.
	 */
	protected GameObject object;
	
	/**
	 * Gets the harvest delay.
	 * @return The harvest delay.
	 */
	public abstract long getHarvestDelay();
	
	/**
	 * Called when the action is initialized.
	 */
	public abstract void init();
	
	/**
	 * Can the player harvest?
	 * @return If the player can harvest; <code>true</code>, if the player can not harvest;
	 * <code>false</code>.
	 */
	public abstract boolean canHarvest();
	
	/**
	 * Process stuff in the action (i.e. animations).
	 * @return If the action can continue to process; <code>true</code>, if the action
	 * can not continue to process; <code>false</code>.
	 */
	public abstract boolean process();

}
