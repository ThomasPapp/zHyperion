package org.hyperion.rs2.action.impl;

import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Player;

/**
 * <p>A harvesting action is an action where on item is transformed into
 * another, typically this is in skills such as smithing and crafting.</p>
 * 
 * <p>This class implements code related to all production-type skills, such as
 * dealing with the action itself, replacing the items and checking levels.</p>
 * 
 * <p>The individual crafting, smithing, and other skills implement
 * functionality specific to them such as random events.</p>
 * @author Graham Edgecombe
 *
 */
public abstract class ProducingAction extends Action {

	/**
	 * Creates the producing action.
	 * @param player The player to create the action for.
	 */
	public ProducingAction(Player player) {
		super(player, 0);
	}

	@Override
	public QueuePolicy getQueuePolicy() {
		return QueuePolicy.ALWAYS;
	}
	
	@Override
	public WalkablePolicy getWalkablePolicy() {
		return WalkablePolicy.NON_WALKABLE;
	}

	@Override
	public void execute() {
		if(this.getDelay() == 0) {
			this.setDelay(getProductionDelay());
			init();
			if (this.isRunning() && getAnimation() != null)
				player.playAnimation(getAnimation());
			this.cycles = getCycles();
			this.totalCycles = cycles;
		} else {
			cycles--;
			if (totalCycles == 1 && cycles == 0) {
				player.playAnimation(Animation.create(-1));
				this.stop();
				return;
			}
			if (!proceed()) {
				player.playAnimation(Animation.create(-1));
				this.stop();
				return;
			}
			if (player.getInventory().freeSlots() <= 0) {
				player.getActionSender().sendMessage("There is not enough space in your inventory.");
				this.stop();
				return;
			}
			for (int i = 0; i < getStartingItems().length; i++) {
				player.getInventory().remove(getStartingItems()[i]);
			}
			for (int i = 0; i < getProducedItems().length; i++) {
				player.getInventory().add(getProducedItems()[i]);
			}
			player.getSkills().addExperience(index(), getExperience());
			player.getActionSender().sendMessage(getRewardMessage());
			if (!proceed()) {
				player.playAnimation(Animation.create(-1));
				this.stop();
				return;
			}
			if(cycles == 0) {
				stop();
			} else {
				if (getAnimation() != null)
				player.playAnimation(getAnimation());
			}
		}
	}
	
	/**
	 * The number of cycles for the action to make.
	 */
	private int cycles;
	
	/**
	 * The total amount of cycles for the action to make.
	 */
	private int totalCycles;
	
	/**
	 * Gets the production delay.
	 * @return The delay between consecutive productions.
	 */
	public abstract long getProductionDelay();
	
	/**
	 * Gets the cycle amount for the action.
	 * @return The cycle amount.
	 */
	public abstract int getCycles();
	
	/**
	 * Can the action proceed?
	 * @return If yes <code>true</code>, if no <code>false</code>.
	 */
	public abstract boolean proceed();
	
	/**
	 * Called when the action is initialized.
	 */
	public abstract void init();
	
	/**
	 * Gets the producing animation.
	 * @return The producing animation.
	 */
	public abstract Animation getAnimation();
	
	/**
	 * An array of all the starting <code>Item</code>s.
	 * @return The starting <code>Item</code>s.
	 */
	public abstract Item[] getStartingItems();
	
	/**
	 * An array of all the <code>Item</code>s received when producing.
	 * @return The <code>Item</code>s received when producing.
	 */
	public abstract Item[] getProducedItems();
	
	/**
	 * Gets the reward message.
	 * @return The reward message.
	 */
	public abstract String getRewardMessage();
	
	/**
	 * Gets the experience.
	 * @return The experience.
	 */
	public abstract double getExperience();
	
	/**
	 * Gets the skill index.
	 * @return The skill index.
	 */
	public abstract int index();

}
