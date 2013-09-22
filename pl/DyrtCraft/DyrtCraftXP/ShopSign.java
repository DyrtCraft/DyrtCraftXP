package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShopSign implements Listener {

	DyrtCraftXP plugin;
	
	public ShopSign(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[XPShop]")) {
			if(!(e.getPlayer().isOp())) {
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
				e.setCancelled(true);
			} else {
				if(e.getLine(1).isEmpty() || e.getLine(2).isEmpty()) {
					e.getBlock().breakNaturally();
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "Bledne argumenty! Prawdlowy uklad:");
					e.getPlayer().sendMessage(ChatColor.RED + " [XPShop]");
					e.getPlayer().sendMessage(ChatColor.RED + " ID itemu");
					e.getPlayer().sendMessage(ChatColor.RED + " cena XP");
				} else {
					e.setLine(0, ChatColor.DARK_PURPLE + "Sklep XP");
					e.setLine(1, ChatColor.WHITE + e.getLine(1));
					e.setLine(2, ChatColor.WHITE + e.getLine(2) + "XP");
					DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " utworzyl tabliczke XPShop", 0);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase(ChatColor.DARK_PURPLE + "Sklep XP")) {
					//int id = s.getLine(1);
					//int xp = s.getLine(2);
					
					//XP.delXp(e.getPlayer(), xp, "Zakup itemu w sklepie XP");
					//e.getPlayer().getInventory().addItem(Material.getMaterial(id))
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
