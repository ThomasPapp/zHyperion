package org.hyperion.rs2.model;

import java.util.ArrayList;
import java.util.List;

import org.hyperion.rs2.util.Misc;

/**
 * Handles all the <code>GameObject</code>s in the game.
 * 
 * @author Thomas
 *
 */
public class ObjectHandler {

	/**
	 * A list of all the <code>GameObject</code>s.
	 */
	private static List<GameObject> objects = new ArrayList<GameObject>();
	
	/**
	 * A list of queued objects.
	 */
	private static List<GameObject> queuedObjects = new ArrayList<GameObject>();
	
	/**
	 * The <code>ObjectHandler</code> instance.
	 */
	private static ObjectHandler instance = new ObjectHandler();
	
	/**
	 * Processes all the <code>GameObject</code>s in the game.
	 */
	public void process() {
		for (GameObject object : objects) {
			if (object == null)
				continue;
			
			if (object.getTicks() > 0) {
				object.decreaseTicks(1);
			} else if (object.getTicks() == 0) {
				updateObject(object);
				queuedObjects.add(object);
			}
		}
		for (GameObject object : queuedObjects) {
			objects.remove(object);
		}
		queuedObjects.clear();
	}
	
	/**
	 * Updates a <code>GameObject</code>.
	 * 
	 * @param object
	 *            The <code>GameObject</code> to update.
	 */
	public void updateObject(GameObject object) {
		for (Player players : World.getWorld().getPlayers()) {
			if (players == null)
				continue;
			
			if (players.getLocation().getZ() == object.getLocation().getZ() && Misc.goodDistance(object.getLocation().getX(), object.getLocation().getY(), players.getLocation().getX(), players.getLocation().getY(), 60))
				players.getActionSender().sendObject(object.getNewId(), object.getLocation().getX(), object.getLocation().getY(), object.getLocation().getZ(), object.getRotation(), object.getType());
		}
	}
	
	/**
	 * Gets the <code>GameObject</code> according to it's location.
	 * 
	 * @param x
	 *            The objects X coordinate.
	 * @param y
	 *            The objects Y coordinate.
	 * @param z
	 *            The objects Z coordinate.
	 * @return The <code>GameObject</code>.
	 */
	public GameObject getObject(int x, int y, int z) {
		for (GameObject o : objects) {
			if (o.getLocation().getX() == x && o.getLocation().getY() == y && o.getLocation().getZ() == z) {
				return o;
			}
		}
		System.out.println("null");
		return null;
	}
	
	/**
	 * Places a <code>GameObject</code> into the <code>World</code>.
	 * 
	 * @param object
	 *            The <code>GameObject</code> to add.
	 */
	private void placeObject(GameObject object) {
		for (Player players : World.getWorld().getPlayers()) {
			if (players == null)
				continue;
			
			if (players.getLocation().getZ() == object.getLocation().getZ() && Misc.goodDistance(object.getLocation().getX(), object.getLocation().getY(), players.getLocation().getX(), players.getLocation().getY(), 60))
				players.getActionSender().sendObject(object.getDefinition().getId(), object.getLocation().getX(), object.getLocation().getY(), object.getLocation().getZ(), object.getRotation(), object.getType());
		}
	}
	
	/**
	 * Adds a <code>GameObject</code>.
	 * 
	 * @param object
	 *            The <code>GameObject</code> to add.
	 */
	public void addObject(GameObject object) {
		objects.add(object);
		placeObject(object);
	}
	
	/**
	 * Removes a <code>GameObject</code>.
	 * 
	 * @param object
	 *            The <code>GameObject</code> to remove.
	 */
	public void removeObject(GameObject object) { 
		updateObject(object);
		objects.remove(object);
	}
	
	/**
	 * Gets the <code>ObjectHandler</code>s instance.
	 * @return The <code>ObjectHandler</code>s instance.
	 */
	public static ObjectHandler getInstance() {
		return instance;
	}
	
}
