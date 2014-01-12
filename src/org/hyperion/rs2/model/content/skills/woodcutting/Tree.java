package org.hyperion.rs2.model.content.skills.woodcutting;

import java.util.HashMap;
import java.util.Map;

import org.hyperion.rs2.model.Item;

/**
 * An enumeration containing all the tree data needed for the <code>Woodcutting</code> <code>Action</code>.
 * 
 * @author Thomas
 *
 */
public enum Tree {

	/**
	 * The normal tree.
	 */
	NORMAL(new int[] { 1276, 1277, 1278, 1279, 1280, 1282, 1283, 1284, 1285, 1286, 1289, 1290, 1291, 1315, 1316, 1318, 1319, 1330, 1331, 1332, 1333, 1365, 1383, 1384, 3033, 3034, 
			3035, 3036, 3881, 3882, 3883, 5902, 5903, 5904 }, 1, 25, new Item(1511), 25, 100, 1342),
	/**
	 * The oak tree.	
	 */
	OAK(new int[] { 1281, 2037 }, 15, 37.5, new Item(1521), 14, 25, 1356),
	
	/**
	 * The willow tree.
	 */
	WILLOW(new int[] { 1308, 5551, 5552, 5553 }, 30, 67.5, new Item(1519), 14, 15, 7399),
	
	/**
	 * The maple tree.
	 */
	MAPLE(new int[] { 1307, 4677 }, 45, 100, new Item(1517), 59, 15, 1343),
	
	/**
	 * The yew tree.
	 */
	YEW(new int[] { 1309 }, 60, 175, new Item(1515), 100, 5, 7402),
	
	/**
	 * The magic tree.
	 */
	MAGIC(new int[] { 1306 }, 75, 250, new Item(1513), 200, 5, 7401);
	
	/**
	 * Constructs a new tree.
	 * 
	 * @param objects
	 *            The array of object ids for the type of tree.
	 * @param level
	 *            The level needed to chop the tree.
	 * @param xp
	 *            The xp given while chopping the tree.
	 * @param log
	 *            The log given while chopping the tree.
	 * @param respawnTime
	 *            The time needed for the tree to re-grow.
	 * @param stump
	 *            The trees stump id.
	 */
	private Tree(int[] objects, int level, double xp, Item log, int respawnTime, int decayChance, int stump) {
		this.objects = objects;
		this.level = level;
		this.xp = xp;
		this.log = log;
		this.respawnTime = respawnTime;
		this.decayChance = decayChance;
		this.stump = stump;
	}
	
	/**
	 * A mapping of all the <code>Tree</code>s.
	 */
	private static Map<Integer, Tree> trees = new HashMap<Integer, Tree>();
	
	/**
	 * Populates the <code>Tree</code> mapping.
	 */
	static {
		for(Tree tree : Tree.values()) {
			for(int object : tree.objects) {
				trees.put(object, tree);
			}
		}
	}
	
	/**
	 * Gets the tree type depending on the objects id.
	 * 
	 * @param object
	 *            The object id.
	 * @return The <code>Tree</code>.
	 */
	public static Tree forId(int object) {
		return trees.get(object);
	}
	
	/**
	 * Is the object a tree?
	 * 
	 * @param object
	 *            The object id.
	 * @return If the object is a tree <code>true</code>, if the object is not a tree <code>false</code>.
	 */
	public static boolean isTree(int object) {
		for(Tree tree : Tree.values()) {
			for (int i = 0; i < tree.objects.length; i++) {
				if (tree.objects[i] == object)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * An array of the object ids for the tree type.
	 */
	private int[] objects;
	
	/**
	 * The trees level.
	 */
	private int level;
	
	/**
	 * The xp given while chopping the tree.
	 */
	private double xp;
	
	/**
	 * The log given while chopping the tree.
	 */
	private Item log;
	
	/**
	 * The re-spawn time for the tree.
	 */
	private int respawnTime;
	
	/**
	 * The decayChance.
	 */
	private int decayChance;
	
	/**
	 * The trees stump id.
	 */
	private int stump;
	
	/**
	 * Gets the object ids of the tree type.
	 * @return The object ids.
	 */
	public int[] getObjects() {
		return objects;
	}
	
	/**
	 * Gets the trees level.
	 * @return The tree level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Gets the trees xp.
	 * @return The tree xp.
	 */
	public double getXP() {
		return xp;
	}
	
	/**
	 * Gets the trees log.
	 * @return The tree log.
	 */
	public Item getLog() {
		return log;
	}
	
	/**
	 * Gets the trees re-spawn time.
	 * @return The re-spawn time.
	 */
	public int getRespawnTimer() {
		return respawnTime;
	}
	
	/**
	 * Gets the decay chance.
	 * @return The decay chance.
	 */
	public int getDecayChance() {
		return decayChance;
	}
	
	/**
	 * Gets the trees stump.
	 * @return The tree stump.
	 */
	public int getStump() {
		return stump;
	}
}
