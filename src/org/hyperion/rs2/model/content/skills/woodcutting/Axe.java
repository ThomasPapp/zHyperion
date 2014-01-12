package org.hyperion.rs2.model.content.skills.woodcutting;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents types of axes.
 * @author Graham Edgecombe
 *
 */
public enum Axe {
	
	/**
	 * Rune axe.
	 */
	RUNE(1359, 41, 867),
	
	/**
	 * Adamant axe.
	 */
	ADAMANT(1357, 31, 869),
	
	/**
	 * Mithril axe.
	 */
	MITHRIL(1355, 21, 871),
	
	/**
	 * Black axe.
	 */
	BLACK(1361, 6, 873),
	
	/**
	 * Steel axe.
	 */
	STEEL(1353, 6, 875),
	
	/**
	 * Iron axe.
	 */
	IRON(1349, 1, 877),
	
	/**
	 * Bronze axe.
	 */
	BRONZE(1351, 1, 879);
	
	/**
	 * The id.
	 */
	private int id;
	
	/**
	 * The level.
	 */
	private int level;
	
	/**
	 * The animation.
	 */
	private int animation;
	
	/**
	 * A map of object ids to axes.
	 */
	private static Map<Integer, Axe> axes = new HashMap<Integer, Axe>();
	
	/**
	 * Gets a axe by an object id.
	 * @param object The object id.
	 * @return The axe, or <code>null</code> if the object is not a axe.
	 */
	public static Axe forId(int object) {
		return axes.get(object);
	}
	
	/**
	 * Populates the tree map.
	 */
	static {
		for(Axe axe : Axe.values()) {
			axes.put(axe.id, axe);
		}
	}
	
	/**
	 * Creates the axe.
	 * @param id The id.
	 * @param level The required level.
	 * @param animation The animation id.
	 */
	private Axe(int id, int level, int animation) {
		this.id = id;
		this.level = level;
		this.animation = animation;
	}
	
	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the required level.
	 * @return The required level.
	 */
	public int getRequiredLevel() {
		return level;
	}
	
	/**
	 * Gets the animation id.
	 * @return The animation id.
	 */
	public int getAnimation() {
		return animation;
	}
	
}
