package org.hyperion.rs2.model.content.combat.hit;

import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.UpdateFlags;

/**
 * Manages combat hits.
 * 
 * @author Thomas
 *
 */
public class Hit {

	/**
	 * The attacker.
	 */
	private Entity attacker;
	
	/**
	 * The victim.
	 */
	private Entity victim;
	
	/**
	 * The damage the victim takes.
	 */
	private int damage;
	
	/**
	 * Displays a combat hit.
	 */
	public void displayHit() {
		UpdateFlags flags = victim.getUpdateFlags();
		HitType type = damage == 0 ? HitType.BLOCK : HitType.NORMAL;
	}
	
}
