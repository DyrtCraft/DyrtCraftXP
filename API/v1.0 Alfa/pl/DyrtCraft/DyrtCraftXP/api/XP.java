package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.MySQL;
import pl.DyrtCraft.DyrtCraftXP.events.PlayerGetXPEvent;
import pl.DyrtCraft.DyrtCraftXP.events.PlayerLoseXPEvent;

public class XP {

	static MySQL mySql;
	DyrtCraftXP plugin;
	
	public XP(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	public static void addXp(Player player, int xp, String powod) {
		PlayerGetXPEvent event = new PlayerGetXPEvent(player, powod, xp);
		Bukkit.getPluginManager().callEvent(event);
		
		int xpRazem = xp+mySql.getXP(player.toString());
		mySql.setXP(player.toString(), xpRazem);
		player.sendMessage(ChatColor.LIGHT_PURPLE + "Zdobyles " + xp +" XP za: " + powod + ".");
		player.sendMessage(XP.showXp(player.toString()));
	}
	
	public static boolean delXp(Player player, int xp, String powod) {
		PlayerLoseXPEvent event = new PlayerLoseXPEvent(player, powod, xp);
		Bukkit.getPluginManager().callEvent(event);
		
		// TODO Naprawic przy release
		/*int iloscXp = mySql.getXP(player.toString());
		if(iloscXp == xp || iloscXp < xp) {
			// Nie wystarczajacy liczba XP
			return false;
		}
		int xpRazem = mySql.getXP(player.toString())-xp;
		mySql.setXP(player.toString(), xpRazem);*/
		player.sendMessage(ChatColor.RED + "Straciles " + xp + " XP za: " + powod + ".");
		player.sendMessage(XP.showXp(player.toString()));
		return true;
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
