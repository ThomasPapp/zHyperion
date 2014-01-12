package org.hyperion.rs2.model.content.skills.woodcutting;

import org.hyperion.rs2.action.impl.ObjectHarvestingAction;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.GameObject;
import org.hyperion.rs2.model.GameObjectDefinition;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Skills;
import org.hyperion.rs2.model.content.skills.SkillManager;
import org.hyperion.rs2.util.Misc;

/**
 * Handles the <code>Woodcutting</code> action.
 * 
 * @author Thomas
 *
 */
public class Woodcutitng extends ObjectHarvestingAction {

	/**
	 * Constructs a new <code>Woodcutting<code> <code>Action</code>.
	 * 
	 * @param player
	 *            The <code>Player</code> to construct the <code>Action</code> for.
	 * @param object
	 *            The <code>GameObject</code> we're harvesting.
	 */
	public Woodcutitng(Player player, GameObject object) {
		super(player, object);
	}
	
	/**
	 * The <code>Tree</code> we're chopping.
	 */
	private final Tree tree = Tree.forId(object.getDefinition().getId());
	
	/**
	 * The axe we're using.
	 */
	private Axe axe = getAxe();
	
	/**
	 * Gets the axe to use.
	 * @return The <code>Axe</code>.
	 */
	private Axe getAxe() {
		final int wc = player.getSkills().getLevel(Skills.WOODCUTTING);
		for(Axe axe : Axe.values()) {
			if((player.getEquipment().contains(axe.getId()) || player.getInventory().contains(axe.getId())) && wc >= axe.getRequiredLevel()) {
				return axe;
			}
		}
		return null;
	}

	@Override
	public long getHarvestDelay() {
		return 3200;
	}

	@Override
	public void init() {
		
		/*
		 * If the harvest is ready, then we harvest some logs.
		 */
		if (SkillManager.harvestCheck(player.getSkills().getLevel(Skills.WOODCUTTING), tree.getLevel(), 0)) {
			player.getInventory().add(tree.getLog());
			player.getSkills().addExperience(Skills.WOODCUTTING, tree.getXP());
			player.getActionSender().sendMessage("You get some "+ tree.getLog().getDefinition().getName() +".");
			
			/*
			 * If a random number from 0-100 is less-than or equal to the tree's decay chance then we spawn the stump.
			 */
			if (Misc.random(100) <= tree.getDecayChance()) {
				player.getActionSender().sendMessage("The tree has run out of logs.");
				new GameObject(new GameObjectDefinition(tree.getStump(), null, null, 1, 1, false, false, false), object.getLocation(), object.getType(), object.getRotation(), object.getDefinition().getId(), tree.getRespawnTimer());
				player.playAnimation(Animation.create(-1));
				this.stop();
				return;
			}
		}
	}

	@Override
	public boolean canHarvest() {
		
		/*
		 * If the <code>Tree</code>/<code>GameObject</code> is null, then we stop the action.
		 */
		if (object == null || tree == null) {
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code> doesn't have a use-able axe, then we stop the action and send a message.
		 */
		if (axe == null) {
			player.getActionSender().sendMessage("You need an axe to chop this tree.");
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code> doesn't have the woodcutting level needed to chop the tree, then we stop the action and send a message.
		 */
		if (player.getSkills().getLevel(Skills.WOODCUTTING) < tree.getLevel()) {
			player.getActionSender().sendMessage("You need a Woodcutting level of "+ tree.getLevel() +" to chop this tree.");
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code> doesn't have any free inventory slots, then we stop the action and send a message. 
		 */
		if (player.getInventory().freeSlots() <= 0) {
			player.getActionSender().sendMessage("Your inventory is too full to chop this tree.");
			this.stop();
			return false;
		}
		
		/*	Plays the axe's animation.	*/
		player.playAnimation(Animation.create(axe.getAnimation()));
		
		/*	Sends the <code>Woodcutting</code> starting message.	*/
		player.getActionSender().sendMessage("You swing your axe at the tree. ");
		return true;
	}

	@Override
	public boolean process() {
		
		/*
		 * If the <code>GameObject</code> has changed, then we stop the action. This is used for when the tree decays and turns into the stump.
		 */
		if (object == null) {
			player.playAnimation(Animation.create(-1));
			this.stop();
			return false;
		}
		/*
		 * If the <code>Player</code> doesn't have a use-able axe, then we stop the action and send a message.
		 */
		if (axe == null) {
			player.getActionSender().sendMessage("You need an axe to chop this tree.");
			player.playAnimation(Animation.create(-1));
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code> doesn't have any free inventory slots, then we stop the action and send a message. 
		 */
		if (player.getInventory().freeSlots() <= 0) {
			player.getActionSender().sendMessage("Your inventory is too full to chop this tree.");
			player.playAnimation(Animation.create(-1));
			this.stop();
			return false;
		}
		
		/*	Plays the axe's animation.	*/
		player.playAnimation(Animation.create(axe.getAnimation()));
		return true;
	}
	
}
