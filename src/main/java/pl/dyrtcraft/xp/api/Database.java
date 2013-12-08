package pl.dyrtcraft.xp.api;

import pl.dyrtcraft.xp.DyrtCraftXP;
import pl.dyrtcraft.xp.MySQL;

/**
 * @since Alpha 1.1.5_2
 */
@Deprecated
public class Database {

	static DyrtCraftXP plugin;
	static MySQL mySql;
	static XP xp;
	
	@Deprecated
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
	@Deprecated
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
	@Deprecated
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
	@Deprecated
	public static String getLastServer(String player) {
		String lastServer = mySql.getLastServer(player);
		return lastServer;
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
	@Deprecated
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
	@Deprecated
	public static void setLastServer(String player, String server) {
		mySql.setLastServer(player, server);
	}
	
}
