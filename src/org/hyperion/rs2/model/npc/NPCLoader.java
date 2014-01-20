package org.hyperion.rs2.model.npc;

import java.io.FileInputStream;
import java.util.List;

import org.hyperion.rs2.Constants;
import org.hyperion.rs2.model.Location;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.util.XStreamManager;

/**
 * Manages the loading of NPC spawns.
 * 
 * @author Thomas
 *
 */
public class NPCLoader {

	/**
	 * The id of the npc.
	 */
	private int id;
	
	/**
	 * The location of the npc.
	 */
	private Location loc;
	
	/**
	 * The facing of the npc.
	 */
	private String face;
	
	/**
	 * Should the npc walk?
	 */
	private boolean walk;
	
	/**
	 * Loads the NPC spawns.
	 */
	public static void load() {
		try {
			@SuppressWarnings("unchecked")
			List<NPCLoader> spawns = (List<NPCLoader>) XStreamManager.getXStream().fromXML(new FileInputStream(XStreamManager.PATH +"npcSpawns.xml"));
			if (spawns.size() > Constants.MAX_NPCS)
				return;
			for (NPCLoader spawn : spawns) {
				NPC npc = new NPC(spawn.id);
				npc.setLocation(spawn.loc);
				npc.setFace(spawn.face);
				npc.setWalk(spawn.walk);
				npc.setMinWalk(Location.create(spawn.loc.getX() - Constants.NPC_WALK_DISTANCE, spawn.loc.getY() - Constants.NPC_WALK_DISTANCE, spawn.loc.getZ()));
				npc.setMaxWalk(Location.create(spawn.loc.getX() + Constants.NPC_WALK_DISTANCE, spawn.loc.getY() + Constants.NPC_WALK_DISTANCE, spawn.loc.getZ()));
				World.getWorld().register(npc);
				npc.face(npc.getFacingLocation(spawn.loc));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
