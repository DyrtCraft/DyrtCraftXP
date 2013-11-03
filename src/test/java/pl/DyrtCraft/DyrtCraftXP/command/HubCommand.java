package pl.DyrtCraft.DyrtCraftXP.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.api.Bungee;

public class HubCommand implements CommandExecutor {
	
	DyrtCraftXP plugin;
	String lobby = "lobby";
	
	public HubCommand(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("hub") || command.getName().equalsIgnoreCase("lobby")) {
			// Liczba argumentow - 0
			if(args.length == 0) {
				// Jezeli wyslano z konsoli
				if(!(sender instanceof Player)) {
					Bukkit.getLogger().warning("Nie mozesz wykonywac tego polecenia z poziomu konsoli!");
					return true;
				}
				// Jezeli wyslal gracz
				Bungee.connect((Player) sender, "Lobby", lobby);
				return true;
			}
			// Liczba argumentow - 1
			if(args.length == 1) {
				// Argument 0: -all
				if(args[0].equalsIgnoreCase("-ALL")) {
					// Jezeli sender nie jest operatorem serwera
					if(!(sender.hasPermission("dyrtcraft.hub.kickall"))) {
						sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
						return true;
					}
					kickPlayers(sender);
					return true;
				// Jezeli argument 0 to nie: -all
				} else {
					// Pobieranie gracza z listy graczy online
					Player gracz = Bukkit.getPlayer(args[0]);
					// Jezeli sender nie jest operatorem serwera
					if(!(sender.hasPermission("dyrtcraft.hub.player"))) {
						sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
						return true;
					}
					// Jezeli pobrany gracz nie jest online na serwerze
					if(gracz == null) {
			        	sender.sendMessage(ChatColor.RED + "Gracz \"" + args[0] + "\" nie jest obecnie na serwerze!");
			        	return true;
					}
					// Polacz z serwerem lobby
					sender.sendMessage(ChatColor.RED + "Wyrzucanie gracza " + gracz.getName() + "...");
					Bungee.connect(gracz, "Lobby", lobby);
					gracz.sendMessage(ChatColor.GOLD + "Zostales przeniesiony na serwer Lobby przez " + sender.getName() + ".");
					return true;
				}
			// Liczba argumentow nie zostala spelniona
			} else {
				sender.sendMessage(ChatColor.RED + "Zbyt duzo argumentów!");
				sender.sendMessage(ChatColor.RED + "Uzycie: /lobby [-ALL|gracz]");
				return true;
			}
		}
		return false;
	}
	
	protected void kickPlayers(CommandSender sender) {
		// Wiadomosc do wysylacacego (operatora)
		sender.sendMessage(ChatColor.RED + "Wyrzucanie wszystkich graczy z serwera...");
		for(Player gracze : Bukkit.getOnlinePlayers()) {
			// Polacz z serwerem lobby
			sender.sendMessage(ChatColor.RED + "Wyrzucanie gracza " + gracze.getName() + "...");
			Bungee.connect(gracze, "Lobby", lobby);
			gracze.sendMessage(ChatColor.GOLD + "Zostales przeniesiony na serwer Lobby.");
			return;
		}
	}
	
}
