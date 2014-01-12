package org.hyperion.rs2.model.content.skills.mining;

import java.util.HashMap;
import java.util.Map;

import org.hyperion.rs2.model.Item;

/**
 * Represents ore types.
 * 
 * @author Thomas
 *
 */
public enum Ore {

	/**
	 * The clay ore.
	 */
	CLAY(new int[] { 2108, 2109, 9711, 9712, 9713, 10949, 11189, 11190, 11191 }, 1, new Item(434), 5, 5),
	
	/**
	 * The copper ore.
	 */
	COPPER(new int[] { 2090, 2091, 3042, 9708, 9709, 9710, 11936, 11937, 11938, 11960, 11961, 11962 }, 1, new Item(436), 17.5, 10),
	
	/**
	 * The tin ore.
	 */
	TIN(new int[] { 2094, 2095, 3043, 9714, 9715, 9716, 11933, 11934, 11935, 11957, 11958, 11959 }, 1, new Item(438), 17.5, 10),
	
	/**
	 * The iron ore.
	 */
	IRON(new int[] { 2092, 2093, 9717, 9718, 9719, 11954, 11955, 11956 }, 15, new Item(440), 35, 16),
	
	/**
	 * The silver ore.
	 */
	SILVER(new int[] { 2100, 2101, 11186, 11187, 11188, 11948, 11949, 11950 }, 20, new Item(442), 40, 225),
	
	/**
	 * The coal ore.
	 */
	COAL(new int[] { 2096, 2097, 10948, 11930, 11931, 11932, 11963, 11964, 11965 }, 30, new Item(453), 50, 125),
	
	/**
	 * The gold ore.
	 */
	GOLD(new int[] { 2098, 2099, 9720, 9721, 9722, 11183, 11184, 11185, 11951, 11952, 11953 }, 40, new Item(444), 65, 225),
	
	/**
	 * The mithril ore.
	 */
	MITHRIL(new int[] { 2102, 2103, 11942, 11943, 11944, 11945, 11946, 11947 }, 80, new Item(447), 80, 425),
	
	/**
	 * The adamantite ore.
	 */
	ADAMANTITE(new int[] { 2104, 2105, 11939, 11940, 11941 }, 70, new Item(449), 95, 825),
	
	/**
	 * The runite ore.
	 */
	RUNITE(new int[] { 2106, 2107, 14859, 14860, 14861 }, 85, new Item(451), 125, 2600),
	
	/**
	 * The empty ore.
	 */
	EMPTY(new int[] { 10944,9723, 9724, 9725, 11555, 11552, 11553, 11554, 11557, 11556, 450, 451 }, 0, null, 0, 0);
	
	/**
	 * Constructs a new ore.
	 * 
	 * @param objects
	 *            The ores object id's.
	 * @param level
	 *            The ores level.
	 * @param ore
	 *            The ore given when mined.
	 * @param xp
	 *            The xp given once mined.
	 * @param respawnTime
	 *            The ores respawn time.
	 */
	private Ore(int[] objects, int level, Item ore, double xp, int respawnTime) {
		this.objects = objects;
		this.level = level;
		this.ore = ore;
		this.xp = xp;
		this.respawnTime = respawnTime;
	}
	
	/**
	 * A mapping of all the <code>Ore</code>s.
	 */
	private static Map<Integer, Ore> ores = new HashMap<Integer, Ore>();
	
	/**
	 * Populates the <code>Ore</code> mapping.
	 */
	static {
		for (Ore ore : Ore.values()) {
			for (int i : ore.objects)
				ores.put(i, ore);
		}
	}
	
	/**
	 * Gets the ore depending on it's object id.
	 * 
	 * @param id
	 *            The ores object id.
	 * @return The <code>Ore</code>.
	 */
	public static Ore forId(int id) {
		return ores.get(id);
	}
	
	/**
	 * An array of all the object id's for the ore.
	 */
	private int[] objects;
	
	/**
	 * The level needed to mine the ore.
	 */
	private int level;
	
	/**
	 * The ore given.
	 */
	private Item ore;
	
	/**
	 * The xp given once mined.
	 */
	private double xp;
	
	/**
	 * The ores respawn time.
	 */
	private int respawnTime;
	
	/**
	 * Gets all the ores objects id's.
	 * @return The ores object id's.
	 */
	public int[] getObjects() {
		return objects;
	}
	
	/**
	 * Gets the ores level.
	 * @return The level needed to mine the ore.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * The ore given once mined.
	 * @return The ore given once mined.
	 */
	public Item getOre() {
		return ore;
	}
	
	/**
	 * Gets the xp given once mined.
	 * @return The xp given once mined.
	 */
	public double getXp() {
		return xp;
	}
	
	/**
	 * Gets the ores respawn time.
	 * @return The ores respawn time.
	 */
	public int getRespawnTime() {
		return respawnTime;
	}
	
}
