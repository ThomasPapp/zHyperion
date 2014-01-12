package org.hyperion.rs2.model;

/**
 * Represents a single game object.
 * @author Graham Edgecombe
 *
 */
public class GameObject {

	/**
	 * The location.
	 */
	private Location location;
	
	/**
	 * The definition.
	 */
	private GameObjectDefinition definition;
	
	/**
	 * The type.
	 */
	private int type;
	
	/**
	 * The rotation.
	 */
	private int rotation;
	
	/**
	 * The objects new id.
	 */
	private int newId;
	
	/**
	 * The objects tick time. This is set to -1 by default; any objects set with -1 ticks will not update until requested.
	 */
	private int ticks = -1;
	
	/**
	 * Creates the game object.
	 * @param definition The definition.
	 * @param location The location.
	 * @param type The type.
	 * @param rotation The rotation.
	 */
	public GameObject(GameObjectDefinition definition, Location location, int type, int rotation) {
		this.definition = definition;
		this.location = location;
		this.type = type;
		this.rotation = rotation;
		ObjectHandler.getInstance().addObject(this);
	}
	
	public GameObject(GameObjectDefinition definition, Location location, int type, int rotation, int newId, int ticks) {
		this.definition = definition;
		this.location = location;
		this.type = type;
		this.rotation = rotation;
		this.newId = newId;
		this.ticks = ticks;
		ObjectHandler.getInstance().addObject(this);
	}
	
	/**
	 * Decreases an objects tick amount.
	 * 
	 * @param amount
	 *            The amount to decrease the ticks by.
	 */
	public void decreaseTicks(int amount) {
		this.ticks -= amount;
	}
	
	/**
	 * Gets the location.
	 * @return The location.
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Gets the definition.
	 * @return The definition.
	 */
	public GameObjectDefinition getDefinition() {
		return definition;
	}
	
	/**
	 * Gets the type.
	 * @return The type.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Gets the rotation.
	 * @return The rotation.
	 */
	public int getRotation() {
		return rotation;
	}
	
	/**
	 * Gets the objects new id.
	 * @return The objects new id.
	 */
	public int getNewId() {
		return newId;
	}
	 
	/**
	 * Gets the objects ticks.
	 * @return The objects ticks.
	 */
	public int getTicks() {
		return ticks;
	}

}
