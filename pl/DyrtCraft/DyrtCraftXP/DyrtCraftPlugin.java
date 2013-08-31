package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DyrtCraftPlugin {
	
	private DyrtCraftXP plugin;
	
	public DyrtCraftPlugin(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
	}
	
	public static void sendMsgToOp(String wiadomosc, int priorytet) {
		for(Player op : Bukkit.getOnlinePlayers()) {
			if(op.isOp()) {
				if(priorytet == 0) {
					op.sendMessage(ChatColor.AQUA + "* " + wiadomosc);
				}
				if(priorytet == 1) {
					op.sendMessage(ChatColor.RED + "** " + wiadomosc + "!");
					Bukkit.getLogger().warning("REP: " + priorytet);
				} else {
					Bukkit.getLogger().warning("Nie rozpoznano priorytetu \"" + priorytet + "\"!");
				}
			}
		}
	}
	
}
