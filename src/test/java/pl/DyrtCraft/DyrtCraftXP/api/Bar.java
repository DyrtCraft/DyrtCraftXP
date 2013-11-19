package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.entity.Player;

import me.confuser.barapi.BarAPI;

/*
 * TODO Documentation
 */
/**
 * @author James Mortemore (confuser)
 * @since Alpha 1.6 - SNAPSHOT BUILD 09
 * 
 * @see #hasBar(Player)
 * @see #removeBar(Player)
 * @see #setHealth(Player, float)
 * @see #setMessage(Player, String)
 * @see #setMessage(Player, String, int)
 */
public class Bar {
	
	/**
	 * @author James Mortemore (confuser)
	 * @since Alpha 1.6 - SNAPSHOT BUILD 09
	 */
	public static void setMessage(Player player, String message) {
		BarAPI.setMessage(player, message);
	}
	
	/**
	 * @author James Mortemore (confuser)
	 * @since Alpha 1.6 - SNAPSHOT BUILD 09
	 */
	public static void setMessage(Player player, String message, float percent) {
		BarAPI.setMessage(player, message, percent);
	}
	
	/**
	 * @author James Mortemore (confuser)
	 * @since Alpha 1.6 - SNAPSHOT BUILD 09
	 */
	public static void setMessage(final Player player, String message, int seconds) {
		BarAPI.setMessage(player, message, seconds);
	}
	
	/**
	 * Czy posiada pasek
	 * 
	 * @author James Mortemore (confuser)
	 * @since Alpha 1.6 - SNAPSHOT BUILD 09
	 * 
	 * @param player Zapytanie do tego gracza
	 * @return true Jezeli posiada
	 */
	public static boolean hasBar(Player player) {
		return BarAPI.hasBar(player);
	}
	
	/**
	 * Usun pasek
	 * 
	 * @author James Mortemore (confuser)
	 * @since Alpha 1.6 - SNAPSHOT BUILD 09
	 * 
	 * @param player Gracz dla którego ma zostac usuniety pasek
	 */
	public static void removeBar(Player player) {
		BarAPI.removeBar(player);
	}
	
	/**
	 * Ustaw poziom naladowania paska
	 * 
	 * @author James Mortemore (confuser)
	 * @since Alpha 1.6 - SNAPSHOT BUILD 09
	 * 
	 * @param player Gracz dla którego ma zostac ustawiony poziom naladowania
	 * @param percent Poziom naladowania (float)
	 */
	public static void setHealth(Player player, float percent) {
		BarAPI.setHealth(player, percent);
	}
	
}
