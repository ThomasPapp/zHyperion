package org.hyperion.rs2.model.content.skills.mining;

import java.util.HashMap;
import java.util.Map;

import org.hyperion.rs2.model.Animation;

/**
 * Represents types of picks.
 * 
 * @author Thomas
 *
 */
public enum Pick {
	
	/**
	 * The rune pick.
	 */
	RUNE(1275, 41, Animation.create(624), 100, 3000),
	
	/**
	 * The adamant pick.
	 */
	ADAMANT(1271, 31, Animation.create(628), 80, 4000),
	
	/**
	 * The mithril pick.
	 */
	MITHRIL(1273, 21, Animation.create(629), 60, 6000),
	
	/**
	 * The steel pick.
	 */
	STEEL(1269, 6, Animation.create(627), 40, 7000),
	
	/**
	 * The iron pick.
	 */
	IRON(1267, 1, Animation.create(626), 20, 9000),

	/**
	 * The bronze pick.
	 */
	BRONZE(1265, 1, Animation.create(625), 0, 11000);
	
	/**
	 * Constructs a new pick.
	 * 
	 * @param id
	 *            The <code>Item</code> id of the pick.
	 * @param level
	 *            The level needed to use the pick.
	 * @param animation
	 *            The <code>Animation</code> of the pick.
	 * @param bonus
	 *            The mining bonus the pick gives.
	 * @param speed
	 *            The mining speed for the pick. Used for essence mining.
	 */
	private Pick(int id, int level, Animation animation, int bonus, int speed) {
		this.id = id;
		this.level = level;
		this.animation = animation;
		this.bonus = bonus;
		this.speed = speed;
	}
	
	/**
	 * A mapping of all the <code>Pick</code>s.
	 */
	private static Map<Integer, Pick> picks = new HashMap<Integer, Pick>();
	
	/**
	 * Populates the <code>Pick</code> mapping.
	 */
	static {
		for (Pick pick : Pick.values()) {
			picks.put(pick.id, pick);
		}
	}
	
	/**
	 * Gets the <code>Pick</code> depending on the <code>Item</code> id.
	 * 
	 * @param id
	 *            The <code>Item</code> id of the <code>Pick</code>.
	 * @return The <code>Pick</code>.
	 */
	public static Pick forId(int id) {
		return picks.get(id);
	}
	
	/**
	 * The <code>Item</code> id of the pick.
	 */
	private int id;
	
	/**
	 * The level needed to use the pick.
	 */
	private int level;
	
	/**
	 * The <code>Animation</code> of the pick.
	 */
	private Animation animation;
	
	/**
	 *  The mining bonus the pick gives.
	 */
	private int bonus;
	
	/**
	 * The mining speed for the pick. Used for essence mining.
	 */
	private int speed;
	
	/**
	 * Gets the <code>Item</code> id of the pick.
	 * @return The <code>Item</code> id of the pick.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the level needed to use the pick.
	 * @return The level needed to use the pick.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Gets the <code>Animation</code> of the pick.
	 * @return The <code>Animation</code> of the pick.
	 */
	public Animation getAnimation() {
		return animation;
	}
	
	/**
	 * Gets the mining bonus the pick gives.
	 * @return The mining bonus the pick gives.
	 */
	public int getBonus() {
		return bonus;
	}
	
	/**
	 * Gets the mining speed for the pick. Used for essence mining.
	 * @return The mining speed for the pick. Used for essence mining.
	 */
	public int getSpeed() {
		return speed;
	}
	
}
