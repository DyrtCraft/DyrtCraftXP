package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.entity.Player;

public final class XPAPI {

	private static DyrtCraftXP plugin;
	
	public static int addXp(Player player, int xp) {
		return plugin.getConfig().getInt("data." + player);
	}
	
	public static void delXp(Player player, int xp) {
		
	}
	
	public static int getXp(Player player) {
		return plugin.getConfig().getInt("data." + player);
	}
	
	public static void setXp(Player player, int xp) {
		try {
			plugin.getConfig().set("data" + player, xp);
			plugin.saveConfig();
		} catch(Exception ex) {}
	}
	
}
