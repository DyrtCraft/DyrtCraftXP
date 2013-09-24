package pl.DyrtCraft.DyrtCraftXP.inv;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.api.Selection;

public class Select implements Listener {

	DyrtCraftXP plugin;
	
	public Select(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getItem().getType() == Material.WOOD_AXE) {			
				// Pierwsza pozycja
				if(Action.LEFT_CLICK_BLOCK != null) {
					Location a = e.getClickedBlock().getLocation();
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Pierwsza pozycja zaznaczona na " +
					"x:" + a.getBlockX() + ", y:" + a.getBlockY() + ", z:" + a.getZ() + "");
					Location loc1 = new Location(a.getWorld(), a.getBlockX(), a.getBlockY(), a.getBlockZ());
					Selection.setFirstSelect(loc1);
				}
				
				// Druga pozycja
				else if(Action.RIGHT_CLICK_BLOCK != null) {
					Location b = e.getClickedBlock().getLocation();
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Druga pozycja zaznaczona na " +
					"x:" + b.getBlockX() + ", y:" + b.getBlockY() + ", z:" + b.getZ() + "");
					Location loc2 = new Location(b.getWorld(), b.getBlockX(), b.getBlockY(), b.getBlockZ());
					Selection.setSecondSelect(loc2);
				} else {}
			}
		} catch(NullPointerException ex) {}
	}
	
}
