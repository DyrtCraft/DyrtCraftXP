package pl.DyrtCraft.DyrtCraftXP.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.sql.Database;

public class XpCommand implements CommandExecutor {

	private DyrtCraftXP plugin;
	private Database db;
	
	public XpCommand(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(label.equalsIgnoreCase("xp")) {
			if(args.length==0) {
				Player p = (Player) sender;
				
				if(sender instanceof Player) {
					Bukkit.getLogger().warning("Nie mozesz wykonywac tego polecenia z poziomu konsoli!");
					return true;
				}
				db.showXp(p);
				return true;
			}
			if(args.length==1) {
				Player gracz = Bukkit.getServer().getPlayer(args[0]);
				
				if(gracz == null) {
		        	sender.sendMessage(ChatColor.RED + "Gracz " + args[0] + " nie jest obecnie na serwerze!");
		        	return true;
		        }
		        db.showXp(gracz);
		        return true;
			} else {
				sender.sendMessage(ChatColor.RED + "/xp [gracz]");
				return true;
			}
		}
		return false;
	}
	
}
