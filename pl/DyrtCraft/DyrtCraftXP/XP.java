package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.entity.Player;

public final class XP {
	
	private DyrtCraftXP plugin;
	
	public XP(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
	}
	
	public void addXp(Player player, int xp, String powod) {
		/*int xp1 = plugin.getConfig().getInt("database." + player.getName());
		
		player.sendMessage(ChatColor.DARK_GREEN + "Zdobyles " + xp + " XP za: " + powod + ".");
		
		plugin.getConfig().set("database." + player.getName(), xp1+xp);
		plugin.saveConfig();*/
	}
	
	public void delXp(Player player, int xp, String powod) {
		/*int xp1 = plugin.getConfig().getInt("database." + player.getName());
		
		player.sendMessage(ChatColor.RED + "Straciles " + xp + " XP za: " + powod + ".");
		
		plugin.getConfig().set("database." + player.getName(), xp1-xp);
		plugin.saveConfig();*/
	}
	
	public int getXp(Player player) {
		/*int xp1 = plugin.getConfig().getInt("database." + player.getName());
		
		return xp1;*/
		return 0;
	}
	
	public void showXp(Player player) {
		/*int xp1 = plugin.getConfig().getInt("database." + player.getName());
		
		player.sendMessage(ChatColor.DARK_GREEN + "Twoja aktualna ilosc XP to " + xp1 + ".");*/
	}
	
}
