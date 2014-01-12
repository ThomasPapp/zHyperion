package org.hyperion.rs2.model.content.combat.hit;

/**
 * An enumeration containing the different hit types.
 * 
 * @author Thomas
 *
 */
public enum HitType {

	/**
	 * The block hit type.
	 */
	BLOCK(0), 
	
	/**
	 * The normal hit type.
	 */
	NORMAL(1), 
	
	/**
	 * The poison hit type.
	 */
	POISON(2), 
	
	/**
	 * The burn hit type.
	 */
	BURN(3);
	
	/**
	 * Constructs a hit type.
	 * 
	 * @param type
	 *            The hit type value.
	 */
	private HitType(int type) {
		this.type = type;
	}
	
	/**
	 * The hit type value.
	 */
	private int type;
	
	/**
	 * Gets the hit type value.
	 * @return The hit type value.
	 */
	public int getType() {
		return type;
	}
	
}
