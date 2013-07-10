package pl.DyrtCraft.DyrtCraftXP;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;

public class ServerInventory implements Listener {

	private Inventory inv;
	private ItemStack hc, sb, sg;
	
	public ServerInventory(Plugin plugin) {
		inv = Bukkit.getServer().createInventory(null, 9, ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "DyrtCraft" + ChatColor.GOLD + " Wybierz serwer:");
		
		hc = createItem(DyeColor.RED, ChatColor.RED + "Hardcore");
		sb = createItem(DyeColor.GRAY, ChatColor.GRAY + "SkyBlock");
		sg = createItem(DyeColor.YELLOW, ChatColor.YELLOW + "Survival Games");
		
		inv.setItem(2, hc);
		inv.setItem(4, sb);
		inv.setItem(6, sg);
		
		//Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	private ItemStack createItem(DyeColor dc, String name) {
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("Przejdz na serwer", name));
		i.setItemMeta(im);
		return i;
	}
	
	public void show(Player p) {
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
		if (e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Hardcore")) {
			e.setCancelled(true);
			p.performCommand("server hardcore");
			e.getWhoClicked().closeInventory();
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("SkyBlock")) {
			e.setCancelled(true);
			p.performCommand("server skyblock");
			e.getWhoClicked().closeInventory();
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival Games")) {
			e.setCancelled(true);
			p.performCommand("server survivalg");
			e.getWhoClicked().closeInventory();
		}
	}
}