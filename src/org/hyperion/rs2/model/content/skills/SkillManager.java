package org.hyperion.rs2.model.content.skills;

import java.util.Random;

/**
 * Handles global skill methods.
 * 
 * @author Thomas
 *
 */
public class SkillManager {

	/**
	 * Checks to see if the harvest is ready.
	 * 
	 * @param level 
	 *            The <code>player</code>s skill level.
	 * @param levelRequired 
	 *            The level needed to harvest.
	 * @param itemBonus
	 *            The harvesting item bonus.
	 * @return If the harvest is ready; <code>true</code>, if the harvest isn't ready; <code>false</code>.
	 */
	public static boolean harvestCheck(int level, int levelRequired, int itemBonus) {
		double chance = 0.0;
		double baseChance = Math.pow(10d-levelRequired/10d, 2d)/2d;
		chance = baseChance + ((level - levelRequired) / 2d) + (itemBonus / 10d);
		return chance >= (new Random().nextDouble() * 100.0);
	}
	
}
