package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DyrtCraftPlugin {
	
	public DyrtCraftPlugin(DyrtCraftXP dyrtCraftXP) {}
	
	public static void sendMsgToOp(String wiadomosc, int priorytet) {
		for(Player op : Bukkit.getOnlinePlayers()) {
			if(op.isOp()) {
				try {
					if(priorytet == 0) {
						op.sendMessage(ChatColor.AQUA + "* " + wiadomosc);
						Bukkit.getLogger().log(null, wiadomosc);
					}
					if(priorytet == 1) {
						op.sendMessage(ChatColor.RED + "** " + wiadomosc + "!");
						Bukkit.getLogger().warning("REP: " + priorytet);
					}
				} catch(Exception ex) {}
			}
		}
	}

}
