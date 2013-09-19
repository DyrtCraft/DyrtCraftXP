package pl.DyrtCraft.DyrtCraftXP.inv;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import pl.DyrtCraft.DyrtCraftXP.Bungee;
import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class Portals implements Listener {

	DyrtCraftXP plugin;
	private Map<String, Boolean> statusData = new HashMap<String, Boolean>();
	
	public Portals(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(!this.statusData.containsKey(e.getPlayer().getName())){
			this.statusData.put(e.getPlayer().getName(), false);
		}
		Block block = p.getWorld().getBlockAt(p.getLocation());
		String data = block.getWorld().getName() + "#" + String.valueOf(block.getX()) + "#" + String.valueOf(block.getY()) + "#" + String.valueOf(block.getZ());
		
		if(plugin.portalData.containsKey(data)){
			if(!this.statusData.get(e.getPlayer().getName())){
				this.statusData.put(e.getPlayer().getName(), true);
				String serverName = plugin.portalData.get(data);
				String serverAddress = plugin.portalData.get(data);

				Bungee.connect(p, serverName, serverAddress);
			}
		} else {
			if(this.statusData.get(e.getPlayer().getName())){
				this.statusData.put(e.getPlayer().getName(), false);
			}
		}
	}
	
}
