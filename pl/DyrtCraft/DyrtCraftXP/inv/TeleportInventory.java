package pl.DyrtCraft.DyrtCraftXP.inv;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import pl.DyrtCraft.DyrtCraftXP.Bungee;
import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class TeleportInventory implements Listener {

	DyrtCraftXP plugin;
	PlayerQuitEvent playerQuitEvent;
	static DyrtCraftXP pluginStatic;
	
	private static Inventory inv;
	private ItemStack hc, hs, sb, sg, quit, inne_ts, inne_www, inne_forum;
	
	public TeleportInventory(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
		
		inv = Bukkit.getServer().createInventory(null, 18, ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "DyrtCraft" + ChatColor.DARK_GRAY + " Wybierz serwer:");
		
		hc = createItem(DyeColor.RED, ChatColor.RED + "Hardcore", "§bSpróbuj oryginalnego Apokaliptycznego hardcore'a!");
		hs = createItem(DyeColor.GREEN, ChatColor.DARK_GREEN + "RPG", "§bRPG, frakcje i klasy!");
		sb = createItem(DyeColor.GRAY, ChatColor.GRAY + "SkyBlock", "§bGotowy(a) na SkyBlock w kosmosie?");
		sg = createItem(DyeColor.YELLOW, ChatColor.YELLOW + "§bSurvival Games", "§bSG z autorskimi mapami!");
		quit = createQuitItem("Serwer Lobby", "§bKliknij, aby powrócic na serwer Lobby");
		
		inne_ts = createItem("Nasz TeamSpeak 3", "§bdyrtcraft.pl");
		inne_www = createItem("Nasza strona WWW", "§bhttp://dyrtcraft.pl");
		inne_forum = createItem("Nasze forum", "§bhttp://dyrtcraft.pl/forum");
		
		inv.setItem(1, hc);
		inv.setItem(3, hs);
		inv.setItem(5, sb);
		inv.setItem(7, sg);
		inv.setItem(17, quit);
		
		inv.setItem(9, inne_ts);
		inv.setItem(10, inne_www);
		inv.setItem(11, inne_forum);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		// Otwieranie inv po naciœniêciu na tabliczkê
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if(e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();
			if(s.getLine(1).equalsIgnoreCase("Lista serwerów"))
			if(s.getLine(2).equalsIgnoreCase(ChatColor.UNDERLINE + "" + ChatColor.BOLD + "DyrtCraft")) {
				TeleportInventory.show(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void onPlaceSign(SignChangeEvent e) {
		// Zmiana tabliczki "[Servers]"
		if(e.getLine(0).equalsIgnoreCase("[Servers]")) {
			if(!(e.getPlayer().isOp())) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			} else {
				e.setLine(0, "");
				e.setLine(1, "Lista serwerów");
				e.setLine(2, ChatColor.UNDERLINE + "" + ChatColor.BOLD + "DyrtCraft");
				e.setLine(3, "");
				e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "Pomyslnie utworzono tabliczke!");
			}
		}
	}
	
	// Ksi¹¿ka
	private ItemStack createItem(String name, String adress) {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(adress));
		i.setItemMeta(im);
		return i;
	}
	
	// We³na
	private ItemStack createItem(DyeColor dc, String name, String name2) {
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("Kliknij, aby przejsc na serwer.", name2));
		i.setItemMeta(im);
		return i;
	}
	
	// Per³a endermana (teleport do lobby)
	private ItemStack createQuitItem(String name, String name2) {
		ItemStack q = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta im = q.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(name2));
		q.setItemMeta(im);
		return q;
		
	}
	
	/**
	 * Poka¿ okno inventory
	 * 
	 * @param p
	 */
	public static void show(Player player) {
		player.openInventory(inv);
	}
	
	// Przy klikaniu w inv
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
		// Hardcore
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hardcore")) {
			e.setCancelled(true);
			Bungee.connect(p, "Hardcore", "hardcore");
			e.getWhoClicked().closeInventory();
		}
		// Heros
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("RPG")) {
			e.setCancelled(true);
			Bungee.connect(p, "RPG", "rpg");
			e.getWhoClicked().closeInventory();
		}
		// SkyBlock
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("SkyBlock")) {
			e.setCancelled(true);
			Bungee.connect(p, "SkyBlock", "skyblock");
			e.getWhoClicked().closeInventory();
		}
		// Survival Games
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival Games")) {
			e.setCancelled(true);
			Bungee.connect(p, "Survival Games", "survivalgames");
			e.getWhoClicked().closeInventory();
		}
		// Lobby
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Serwer Lobby")) {
			e.setCancelled(true);
			Bungee.connect(p, "Lobby", "lobby");
			e.getWhoClicked().closeInventory();
		} else {
			e.setCancelled(true);
		}
		
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Nasz TeamSpeak 3")) {
			e.setCancelled(true);
		}
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Nasza strona WWW")) {
			e.setCancelled(true);
		}
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Nasze forum")) {
			e.setCancelled(true);
		}
	}

}
