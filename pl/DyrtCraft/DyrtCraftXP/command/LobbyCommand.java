package pl.DyrtCraft.DyrtCraftXP.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.DyrtCraft.DyrtCraftXP.Bungee;
import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class LobbyCommand implements CommandExecutor {

DyrtCraftXP plugin;
PlayerQuitEvent playerQuitEvent;
	
	public LobbyCommand(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		Player p = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("lobby")) {
			/*if(!(p.hasPermission("dyrtcraft.xp.connect")) || (p.isOp())) {
				p.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
				return true;
			}*/
			if(!(sender instanceof Player)) {
				plugin.getLogger().warning("Nie mozesz wykonac tej komendy z poziomu konsoli!");
				return true;
			}
			Bungee.connect(p, "Lobby", "lobby");
			return true;
		}
		return false;
	}

}
