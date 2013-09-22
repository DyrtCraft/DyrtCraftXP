package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class XP {

	DyrtCraftXP plugin;
	
	public XP(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	public static void addXp(Player player, int xp, String powod) {
		//int xp1 = get SQL
		player.sendMessage(ChatColor.LIGHT_PURPLE + "Zdobyles " + xp +" XP za: " + powod + ".");
	}
	
	public static void createNewPlayer(String Player) {
		// get SQL
	}
	
	public static void delXp(Player player, int xp, String powod) {
		//int xp1 = get SQL
		player.sendMessage(ChatColor.RED + "Straciles " + xp + " XP za: " + powod + ".");
	}
	
	public static int getXp(Player player) {
		//int xp1 = get SQL
		//return xp1;
		return 0;
	}
	
	public static void showXp(Player player) {
		//int xp1 = get SQL
		Object xp1 = null; // Do usuniecia
		player.sendMessage(ChatColor.LIGHT_PURPLE + "Twoja aktualna ilosc XP to " + xp1 + ".");
	}
	
}
