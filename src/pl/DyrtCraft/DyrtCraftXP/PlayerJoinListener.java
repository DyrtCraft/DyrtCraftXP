package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

	public PlayerJoinListener(DyrtCraftXP dyrtCraftXP) {}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setHelmet(null);
		e.getPlayer().getInventory().setChestplate(null);
		e.getPlayer().getInventory().setLeggings(null);
		e.getPlayer().getInventory().setBoots(null);
		
		e.getPlayer().getInventory().addItem(new ItemStack (Material.EMERALD, 1));
	}
	
}
