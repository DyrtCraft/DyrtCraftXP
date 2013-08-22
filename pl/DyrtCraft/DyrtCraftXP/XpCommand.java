package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XpCommand implements CommandExecutor {

	public XpCommand(DyrtCraftXP dyrtCraftXP) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(label.equalsIgnoreCase("xp")) {
			if(args.length==0) {
				if(sender instanceof Player) {
					Bukkit.getLogger().warning("Nie mozesz wykonywac tego polecenia z poziomu konsoli!");
					return true;
				}
				if(!(sender.hasPermission("dyrtcraft.xp.pokaz"))) {
		        	sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
		        	return true;
		        }
				Player player = (Player) sender;
				player.sendMessage(ChatColor.GRAY + "Twoj poziom XP to " + XPAPI.getXp(player) + ".");
				return true;
			}
			if(args.length==1) {
				Player gracz = Bukkit.getServer().getPlayer(args[0]);
		        if(!(sender.hasPermission("dyrtcraft.xp.pokaz.inny"))) {
		        	sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
		        	return true;
		        }
				if(gracz == null) {
		        	sender.sendMessage(ChatColor.RED + "Gracz " + args[0] + " nie jest obecnie na serwerze!");
		        	return true;
		        }
		        XPAPI.getXp(gracz);
		        return true;
			}
			if(args.length==2) {
				sender.sendMessage(ChatColor.RED + "/xp [gracz] [del|give|set] [liczba]");
				return true;
			}
			if(args.length==3) {
				Player gracz = Bukkit.getServer().getPlayer(args[0]);
				if(!(sender.hasPermission("dyrtcraft.xp.pokaz.admin"))) {
		        	sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
		        	return true;
		        }
				if(args[1].equalsIgnoreCase("del")) {
					int xp = 1;
					XPAPI.delXp(gracz, xp);
					return true;
				}
				if(args[1].equalsIgnoreCase("give")) {
					int xp = 1;
					XPAPI.addXp(gracz, xp);
					return true;
				}
				if(args[1].equalsIgnoreCase("set")) {
					int xp = 1;
					XPAPI.delXp(gracz, xp);
					return true;
				}
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "/xp [gracz] [del|give|set] [liczba]");
				return true;
			}
		}
		return false;
	}
	
}
