package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DyrtCraftXP extends JavaPlugin implements Listener {

	private ServerInventory serverInventory;
	
	public void onEnable() {
		getLogger().info("Wlaczanie DyrtCraftXP v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
	
		//getCommand("dyrtcraftxp:version").setExecutor(new pl.DyrtCraft.DyrtCraftXP.DyrtcraftxpCommand(this));
		getCommand("xp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.XpCommand(this));
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.ServerInventory(this), this);
		
		serverInventory = new ServerInventory(this);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		//if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		//serverInventory.show(e.getPlayer());
		e.getAction();
		if(Action.RIGHT_CLICK_AIR != null) {
			e.getItem().getType();
			if(Material.EMERALD != null) {
				serverInventory.show(e.getPlayer());
			}
		}
	}
	
}
