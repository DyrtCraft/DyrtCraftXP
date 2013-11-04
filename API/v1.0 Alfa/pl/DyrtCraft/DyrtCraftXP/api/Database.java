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
	
	/**
	 * Dodaj nowego gracza do bazy danych
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @param player Nick nowego gracza
	 */
	public static void createNewPlayer(String player) {
		String lastServer = plugin.getConfig().getString("nazwa-serwera");
		
		mySql.addNewPlayer(player, lastServer);
	}
	
	/**
	 * Zdobadz ostatni czas wylogowania z serwerowni
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @param player Nick gracza
	 * @return String Czas ostatniego wylogowania
	 */
	public static String getLastLogout(String player) {
		String lastLogout = mySql.getLastLogout(player);
		return lastLogout;
	}
	
	/**
	 * Zdobadz ostatni serwer wylogowania z serwerowni
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @param player Nick gracza
	 * @return String Nazwa serwera ostatniego wylogowania
	 */
	public static String getLastServer(String player) {
		String lastServer = mySql.getLastServer(player);
		return lastServer;
	}
	
	
	/**
	 * Zdobadz XP
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @return {@link XP}
	 */
	public static XP getXP() {
		return xp;
	}
	
	/**
	 * Ustaw ostatni czas wylogowania
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @param player Nick gracza
	 * @param time Czas wylogowania
	 */
	public static void setLastLogout(String player, String time) {
		mySql.setLastLogout(player, time);
	}
	
	/**
	 * Ustaw nazwe ostatniego serwera wylogowania
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @param player Nick gracza
	 * @param server Nazwa serwera wylogowania
	 */
	public static void setLastServer(String player, String server) {
		mySql.setLastServer(player, server);
	}
	
}
