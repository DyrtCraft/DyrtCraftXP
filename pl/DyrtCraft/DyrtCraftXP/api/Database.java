package pl.DyrtCraft.DyrtCraftXP.api;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

/**
 * @since Alpha 1.1.5_2
 */
public class Database {

	DyrtCraftXP plugin;
	XP xp;
	
	public Database(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	public static void createNewPlayer(String player) {}
	
	public static String getLastLogin(String player) {
		return null;
	}
	
	public static String getLastServer(String player) {
		return null;
	}
	
	public XP getXP() {
		return xp;
	}
	
	public static void setLastLogin(String player) {}
	
	public static void setLastServer(String player) {}
	
}
