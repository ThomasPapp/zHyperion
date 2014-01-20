package org.hyperion.rs2.util;

import org.hyperion.rs2.model.npc.NPCDefinition;
import org.hyperion.rs2.model.npc.NPCLoader;

import com.thoughtworks.xstream.XStream;

/**
 * Manages the XStream class for XML files.
 * 
 * @author Thomas
 *
 */
public class XStreamManager {

	/**
	 * The <code>XStream</code> instance.
	 */
	private static final XStream xstream = new XStream();
	
	/**
	 * The main path for the XML files.
	 */
	public static final String PATH = "./data/xml/";
	
	/**
	 * Populates the XStream alias.
	 */
	static {
		xstream.alias("npcDefinition", NPCDefinition.class);
		xstream.alias("spawn", NPCLoader.class);
	}
	
	/**
	 * Loads the XML files.
	 */
	public static void loadFiles() {
		try {
			NPCDefinition.load();
			NPCLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the <code>XStream</code> class.
	 * @return The <code>XStream</code> class.
	 */
	public static XStream getXStream() {
		return xstream;
	}
	
}
