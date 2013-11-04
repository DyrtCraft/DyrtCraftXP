package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class DyrtCraftPlugin {
	
	public DyrtCraftPlugin(DyrtCraftXP dyrtCraftXP) {}
	
	/**
	 * Wyslij powiadomienie do operatora serwera
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.0.0
	 * @param wiadomosc Wiadomosc do operatorów
	 * @param priorytet Priorytet wiadomosci
	 */
	public static void sendMsgToOp(String wiadomosc, int priorytet) {
		for(Player op : Bukkit.getOnlinePlayers()) {
			if(op.isOp() || op.hasPermission("dyrtcraftxp.notify")) {
				try {
					if(priorytet == 0) {
						op.sendMessage(ChatColor.AQUA + "* " + wiadomosc);
						Bukkit.getLogger().info("REP: " + wiadomosc);
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
