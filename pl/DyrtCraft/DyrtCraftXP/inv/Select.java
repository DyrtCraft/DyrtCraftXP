package pl.DyrtCraft.DyrtCraftXP.inv;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

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
					setFirstSelect(a.getX(), a.getY(), a.getZ());
				}
				
				// Druga pozycja
				else if(Action.RIGHT_CLICK_BLOCK != null) {
					Location b = e.getClickedBlock().getLocation();
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Druga pozycja zaznaczona na " +
					"x:" + b.getBlockX() + ", y:" + b.getBlockY() + ", z:" + b.getZ() + "");
					setSecondSelect(b.getX(), b.getY(), b.getZ());
				} else {}
			}
		} catch(NullPointerException ex) {}
	}
	
	public static Location getFirstSelect() {
		return null;
	}
	
	public static Location getSecondSelect() {
		return null;
	}
	
	public static void setFirstSelect(double x, double y, double z) {
		getFirstSelect().setX(x);
		getFirstSelect().setY(y);
		getFirstSelect().setZ(z);
	}
	
	public static void setSecondSelect(double x, double y, double z) {
		getSecondSelect().setX(x);
		getSecondSelect().setY(y);
		getSecondSelect().setZ(z);
		
	}
}
