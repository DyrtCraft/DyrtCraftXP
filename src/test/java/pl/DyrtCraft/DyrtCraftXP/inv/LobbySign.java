package pl.DyrtCraft.DyrtCraftXP.inv;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.api.Bungee;

public class LobbySign implements Listener {

	DyrtCraftXP plugin;
	PlayerQuitEvent playerQuitEvent;
	
	public LobbySign(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
	}
	
	@EventHandler
	public void onPlaceSign(SignChangeEvent e) {
		// Zmiana tabliczki "[Lobby]"
		if(e.getLine(0).equalsIgnoreCase("[Lobby]")) {
			if(!(e.getPlayer().isOp())) {
				e.setCancelled(true);
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			} else {
				e.setLine(0, ChatColor.UNDERLINE + "LOBBY");
				e.setLine(1, ChatColor.DARK_GREEN + "do");
				e.setLine(2, ChatColor.DARK_GREEN + "DyrtCraft");
				e.setLine(3, ChatColor.DARK_GREEN + "Lobby serwer");
				e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "Pomyslnie utworzono tabliczke!");
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if(e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();
			if(s.getLine(0).equalsIgnoreCase(ChatColor.UNDERLINE + "LOBBY"));
			if(s.getLine(1).equalsIgnoreCase(ChatColor.DARK_GREEN + "do"));
			if(s.getLine(2).equalsIgnoreCase(ChatColor.DARK_GREEN + "DyrtCraft"));
			if(s.getLine(3).equalsIgnoreCase(ChatColor.DARK_GREEN + "Lobby serwer")) {
				Bungee.connect(e.getPlayer(), "Lobby", "lobby");
			}
		}
	}
	
}
