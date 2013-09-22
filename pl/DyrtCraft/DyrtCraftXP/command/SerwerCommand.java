package pl.DyrtCraft.DyrtCraftXP.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.api.Bungee;

public class SerwerCommand implements CommandExecutor {

	DyrtCraftXP plugin;
	
	public SerwerCommand(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		String cmd = command.getName();
		List<String> listaSerwerow = plugin.getConfig().getStringList("lista-serwerow");
		
		if(cmd.equalsIgnoreCase("serwer") || cmd.equalsIgnoreCase("serwery") || cmd.equalsIgnoreCase("server") || cmd.equalsIgnoreCase("servers")) {
			if(args.length==0) {
				sender.sendMessage(ChatColor.GOLD + "Lista dostepnych obecnie serwerów:");
				for(String l : listaSerwerow) {
					sender.sendMessage(l);
				}
				return true;
			}
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("-l") || args[0].equalsIgnoreCase("-list") || args[0].equalsIgnoreCase("-lista") || args[0].equalsIgnoreCase("-a") || args[0].equalsIgnoreCase("-all")) {
					sender.sendMessage(ChatColor.GOLD + "Lista dostepnych obecnie serwerów:");
					for(String l : listaSerwerow) {
						sender.sendMessage(l);
					}
					return true;
				}
				if(listaSerwerow.equals(args[0])) {
					Player p = (Player) sender;
					String serverAddress = plugin.getConfig().getString(args[0]);
					
					Bungee.connect(p, args[0], serverAddress);
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Serwer który zostal przez Ciebie podany nie istnieje!");
					sender.sendMessage(ChatColor.RED + "Aby zobaczyc liste serwerow wpisz /serwer -lista");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Zbyt duzo argumentów!");
				sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("serwer").getUsage());
				return true;
			}
		}
		return false;
	}
	
}
