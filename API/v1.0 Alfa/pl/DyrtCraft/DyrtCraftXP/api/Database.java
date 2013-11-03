package pl.DyrtCraft.DyrtCraftXP.api;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.MySQL;

/**
 * @since Alpha 1.1.5_2
 */
public class Database {

	static DyrtCraftXP plugin;
	static MySQL mySql;
	static XP xp;
	
	public Database(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	public static void createNewPlayer(String player) {
		String lastServer = plugin.getConfig().getString("nazwa-serwera");
		
		mySql.addNewPlayer(player, lastServer);
	}
	
	public static String getLastLogout(String player) {
		String lastLogout = mySql.getLastLogout(player);
		return lastLogout;
	}
	
	public static String getLastServer(String player) {
		String lastServer = mySql.getLastServer(player);
		return lastServer;
	}
	
	public static XP getXP() {
		return xp;
	}
	
	public static void setLastLogout(String player, String time) {
		mySql.setLastLogout(player, time);
	}
	
	public static void setLastServer(String player, String server) {
		mySql.setLastServer(player, server);
	}
	
}
