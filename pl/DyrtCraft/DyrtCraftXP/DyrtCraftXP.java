package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DyrtCraftXP extends JavaPlugin implements Listener {

	private ServerInventory serverInventory;
	
	public void onEnable() {
		getLogger().info("Wlaczanie DyrtCraftXP v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
	
		saveDefaultConfig();
		
		//getCommand("dyrtcraftxp:version").setExecutor(new pl.DyrtCraft.DyrtCraftXP.DyrtcraftxpCommand(this));
		getCommand("xp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.XpCommand(this));
		
		getServer().getPluginManager().registerEvents(this, this);
		//getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.ServerInventory(this), this);
		
		serverInventory = new ServerInventory(this);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		/*if(getConfig().getBoolean("emerald", true)) {
			e.getAction();
			if(Action.RIGHT_CLICK_AIR != null) {
				e.getItem().getType();
				if(Material.EMERALD != null) {
					serverInventory.show(e.getPlayer());
				}
			}
			if(Action.RIGHT_CLICK_BLOCK != null) {
				e.getItem().getType();
				if(Material.EMERALD != null) {
					serverInventory.show(e.getPlayer());
				}
			}
		}*/
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if(e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();
			if(s.getLine(1).equalsIgnoreCase("Lista serwerów"))
			if(s.getLine(2).equalsIgnoreCase(ChatColor.UNDERLINE + "" + ChatColor.BOLD + "DyrtCraft")) {
				serverInventory.show(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void onPlaceSign(SignChangeEvent e) {
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

}
