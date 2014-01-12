package org.hyperion.rs2.model.content.skills.mining;

import org.hyperion.rs2.action.impl.ObjectHarvestingAction;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.GameObject;
import org.hyperion.rs2.model.GameObjectDefinition;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Skills;
import org.hyperion.rs2.model.content.skills.SkillManager;

/**
 * Handles the ore mining action.
 * 
 * @author Thomas
 *
 */
public class OreMining extends ObjectHarvestingAction {

	/**
	 * Constructs a new ore mining action.
	 * 
	 * @param player
	 *            The <code>Player</code> to construct the <code>Action</code> for.
	 * @param object
	 *            The <code>GameObject</code> we're harvesting.
	 */
	public OreMining(Player player, GameObject object) {
		super(player, object);
	}
	
	/**
	 * The <code>Ore</code> we're using.
	 */
	private final Ore ore = Ore.forId(object.getDefinition().getId());
	
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
	public long getHarvestDelay() {
		return 3200;
	}

	@Override
	public void init() {
		
		/*
		 * If the harvest is ready then we harvest the ore, spawn the empty rock, stop the <code>Player</code>s <code>Animation</code> and stop the <code>Action</code>.
		 */
		if (SkillManager.harvestCheck(player.getSkills().getLevel(Skills.MINING), ore.getLevel(), pick.getBonus())) {
			player.getInventory().add(ore.getOre());
			player.getSkills().addExperience(Skills.MINING, ore.getXp());
			player.getActionSender().sendMessage("You manage to mine some "+ ore.getOre().getDefinition().getName() +".");
			new GameObject(new GameObjectDefinition(emptyOre(object.getDefinition().getId()), null, null, 1, 1, false, false, false), object.getLocation(), object.getType(), object.getRotation(), object.getDefinition().getId(), ore.getRespawnTime());
			player.playAnimation(Animation.create(-1));
			this.stop();
			return;
		}
	}

	@Override
	public boolean canHarvest() {
		
		/*
		 * If the <code>GameObject</code>/<code>Ore</code> is null then we stop the action.
		 */
		if (object == null || ore == null) {
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code> doesn't have a use-able <code>Pick</code> then we stop the action and send a message.
		 */
		if (pick == null) {
			player.getActionSender().sendMessage("You do not have a pickaxe that you can use.");
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code> doesn't have the required level to mine the ore then we stop the action and send a message.
		 */
		if (player.getSkills().getLevel(Skills.MINING) < ore.getLevel()) {
			player.getActionSender().sendMessage("You need a Mining level of "+ ore.getLevel() +" to mine this ore.");
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code>s inventory is full then we stop the action and send a message.
		 */
		if (player.getInventory().freeSlots() <= 0) {
			player.getActionSender().sendMessage("Your inventory is too full to mine this ore.");
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Ore</code> is empty then we stop the action and send a message.
		 */
		if (ore == Ore.EMPTY) {
			player.getActionSender().sendMessage("There is currently no ores remaining in this rock.");
			this.stop();
			return false;
		}
		
		/* Plays the <code>Pick</code>s <code>Animation</code>.	*/
		player.playAnimation(pick.getAnimation());
		
		/*	Sends the starting message.	*/
		player.getActionSender().sendMessage("You swing your pick at the rock.");
		return true;
	}

	@Override
	public boolean process() {
		
		/*
		 * If the <code>GameObject</code> has changed then we stop the action and reset the <code>Player</code>s animation.
		 */
		if (object == null) {
			player.playAnimation(Animation.create(-1));
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code> doesn't have a use-able <code>Pick</code> then we stop the action and send a message and reset the <code>Player</code>s animation.
		 */
		if (pick == null) {
			player.getActionSender().sendMessage("You do not have a pickaxe that you can use.");
			player.playAnimation(Animation.create(-1));
			this.stop();
			return false;
		}
		
		/*
		 * If the <code>Player</code>s inventory is full then we stop the action and send a message.
		 */
		if (player.getInventory().freeSlots() <= 0) {
			player.getActionSender().sendMessage("Your inventory is too full to mine this ore.");
			player.playAnimation(Animation.create(-1));
			this.stop();
			return false;
		}
		
		/* Plays the <code>Pick</code>s <code>Animation</code>.	*/
		player.playAnimation(pick.getAnimation());
		return true;
	}
	
	/**
	 * Gets the empty ore object id.
	 * 
	 * @param object
	 *            The ores object id.
	 * @return The empty ore object id.
	 */
	private static int emptyOre(int object) {
		int[] ore1 = {9708, 9711, 9714, 9717, 9720};
		int[] ore2 = {9709, 9712, 9715, 9718, 9721};
		int[] ore3 = {9710, 9713, 9716, 9719, 9722};

		int[] ore4 = {11183, 11186, 11189, 11930, 11933, 11936, 11939, 11942, 11945, 11948, 11951, 11954, 11957, 11960, 11963};
		int[] ore5 = {11184, 11187, 11190, 11931, 11934, 11937, 11940, 11943, 11946, 11949, 11952, 11955, 11958, 11961, 11964};
		int[] ore6 = {11185, 11188, 11191, 11932, 11935, 11938, 11941, 11944, 11947, 11950, 11953, 11956, 11959, 11962, 11965};
		
		int[] ore7 = {14859};
		int[] ore8 = {14860};
		int[] ore9 = {14861};
		if (object == 10946 || object == 10948) {
			return 10944;
		}
		if (object == 10947 || object == 10949) {
			return 10945;
		}
		for (int i : ore1) {
			if (object == i) {
				return 9723;
			}
		}
		for (int i : ore2) {
			if (object == i) {
				return 9724;
			}
		}
		for (int i : ore3) {
			if (object == i) {
				return 9725;
			}
		}
		for (int i : ore4) {
			if (object == i) {
				return object >= 11945 ? 11555 : 11552;
			}
		}
		for (int i : ore5) {
			if (object == i) {
				return object >= 11945 ? 11556 : 11553;
			}
		}
		for (int i : ore6) {
			if (object == i) {
				return object >= 11945 ? 11557 : 11554;
			}
		}
		for (int i : ore7) {
			if (object == i) {
				return 14832;
			}
		}
		for (int i : ore8) {
			if (object == i) {
				return 14833;
			}
		}
		for (int i : ore9) {
			if (object == i) {
				return 14834;
			}
		}
		if (object % 2 == 0 || object == 3043) {
			return 450;
		} else {
			return 451;
		}
	}

}
