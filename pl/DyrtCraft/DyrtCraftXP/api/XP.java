package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.MySQL;

public class XP {

	static MySQL mySql;
	DyrtCraftXP plugin;
	
	public XP(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	public static void addXp(Player player, int xp, String powod) {
		int xpRazem = xp+mySql.getXP(player.toString());
		mySql.setXP(player.toString(), xpRazem);
		player.sendMessage(ChatColor.LIGHT_PURPLE + "Zdobyles " + xp +" XP za: " + powod + ".");
		player.sendMessage(XP.showXp(player.toString()));
	}
	
	public static void delXp(Player player, int xp, String powod) {
		int xpRazem = mySql.getXP(player.toString())+xp;
		mySql.setXP(player.toString(), xpRazem);
		player.sendMessage(ChatColor.RED + "Straciles " + xp + " XP za: " + powod + ".");
		player.sendMessage(XP.showXp(player.toString()));
	}
	
	public static int getXp(String player) {
		int xp1 = mySql.getXP(player);
		return xp1;
	}
	
	public static String showXp(String player) {
		int xp1 = mySql.getXP(player.toString());
		return ChatColor.LIGHT_PURPLE + "Aktualna ilosc XP gracza " + player + " to " + xp1 + ".";
	}
	
}
