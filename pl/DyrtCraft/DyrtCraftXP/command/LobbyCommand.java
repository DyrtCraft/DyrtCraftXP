package pl.DyrtCraft.DyrtCraftXP.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.api.Bungee;

/**
 * Polacz sendera/wybranego gracza online/wszystkich graczy z serwerem lobby
 * 
 * @author TheMolkaPL
 * @see Bungee#connect(Player, String, String)
 */
public class LobbyCommand implements CommandExecutor {

	DyrtCraftXP plugin;
	PlayerQuitEvent playerQuitEvent;
	
	public LobbyCommand(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		// Komenda /lobby i /hub
		if(command.getName().equalsIgnoreCase("lobby") || command.getName().equalsIgnoreCase("hub")) {
			// Liczba argumentow - 0
			if(args.length == 0) {
				// Jezeli wyslano z konsoli
				if(!(sender instanceof Player)) {
					plugin.getLogger().warning("Nie mozesz wykonac tej komendy z poziomu konsoli!");
					return true;
				}
				// Jezeli wyslal gracz
				Player p = (Player) sender;
				// Polacz z serwerem lobby
				Bungee.connect(p, "Lobby", "lobby");
				return true;
			}
			// Liczba argumentow - 1
			if(args.length == 1) {
				// Argument 0: -all
				if(args[0].equalsIgnoreCase("-ALL")) {
					// Jezeli sender nie jest operatorem serwera
					if(!(sender.isOp())) {
						sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
						return true;
					}
					// For: gracze to wszyscy gracze na serwerze
					for(Player gracze : Bukkit.getOnlinePlayers()) {
						// Wiadomosc do wysylacacego (operatora)
						sender.sendMessage(ChatColor.RED + "Wyrzucanie wszystkich graczy z serwera...");
						// Polacz z serwerem lobby
						Bungee.connect(gracze, "Lobby", "lobby");
						return true;
					}
				// Jezeli argument 0 to nie: -all
				} else {
					// Pobieranie gracze z listy graczy online
					Player gracz = Bukkit.getServer().getPlayer(args[0]);
					// Jezeli sender nie jest operatorem serwera
					if(!(sender.isOp())) {
						sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
						return true;
					}
					// Jezeli pobrany gracz nie jest online na serwerze
					if(gracz == null) {
			        	sender.sendMessage(ChatColor.RED + "Gracz " + args[0] + " nie jest obecnie na serwerze!");
			        	return true;
			        }
					// Polacz z serwerem lobby
					Bungee.connect(gracz, "Lobby", "lobby");
				}
			// Liczba argumentow nie zostala spelniona
			} else {
				sender.sendMessage(ChatColor.RED + "Zbyt duzo argumentów!");
				sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("lobby").getUsage());
			}
		}
		return false;
	}

}
