package org.hyperion.rs2.model.npc;

import java.io.FileInputStream;
import java.util.List;

import org.hyperion.rs2.Constants;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.util.XStreamManager;

/**
 * Created by IntelliJ IDEA. User: vayken Date: 07/04/12 Time: 23:11 To change
 * this template use File | Settings | File Templates.
 */
public class NPCDefinition {
	
	public static void load() {
		try {
			@SuppressWarnings("unchecked")
			List<NPCDefinition> defs = (List<NPCDefinition>) XStreamManager.getXStream().fromXML(new FileInputStream(XStreamManager.PATH +"npcDefinitions.xml"));
			for (final NPCDefinition def : defs) {
				if (def.getId() >= Constants.MAX_NPC_ID) {
					break;
				}
				World.getWorld().getNpcDef()[def.getId()] = def;
	            def.attackBonus = (int) (def.combat);
	        	def.defenceMelee = (int) (def.combat / 2);
	        	def.defenceMage = (int) (def.combat);
	        	def.defenceRange = (int) (def.combat / 2);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static NPCDefinition forName(String name) {
		for (NPCDefinition d : World.getWorld().getNpcDef()) {
			if (d.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
				return d;
			}
		}
		return null;
	}

	public static NPCDefinition forId(int id) {
		NPCDefinition d = World.getWorld().getNpcDef()[id];
		if (d == null) {
			d = produceDefinition(id);
		}
		return d;
	}

	private int id;
	private String name, examine;
	private int respawn = 0, combat = 0, hitpoints = 1, maxHit = 0, size = 1, attackSpeed = 4000, attackAnim = 422, defenceAnim = 404, deathAnim = 2304, attackBonus = 20, defenceMelee = 20, defenceRange = 20, defenceMage = 20;

	private boolean attackable = false;
	private boolean aggressive = false;
	private boolean retreats = false;
	private boolean poisonous = false;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getExamine() {
		return examine;
	}

	public int getDeathAnim() {
		return deathAnim;
	}

	public int getBlockAnim() {
		return defenceAnim;
	}

	public int getAttackAnim() {
		return attackAnim;
	}

	public int getCombat() {
		return combat;
	}

	public int getSize() {
		return size;
	}

	public boolean isAggressive() {
		return aggressive;
	}

	public boolean retreats() {
		return retreats;
	}

	public boolean isPoisonous() {
		return poisonous;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public int getMaxhit() {
		return maxHit;
	}
	
	public void setMaxHit(int hit) {
		this.maxHit = hit;
	}

	public static NPCDefinition produceDefinition(int id) {
		final NPCDefinition def = new NPCDefinition();
		def.id = id;
		def.name = "NPC #" + def.id;
		def.examine = "It's an NPC.";
		return def;
	}

	public boolean isAttackable() {
		return attackable;
	}
	
}
