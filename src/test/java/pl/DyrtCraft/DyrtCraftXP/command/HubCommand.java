package pl.DyrtCraft.DyrtCraftXP.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.CraftDyrt;
import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class HubCommand implements CommandExecutor {
	
	DyrtCraftXP plugin;
	String lobby = "lobby";
	
	public HubCommand(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Liczba argumentow - 0
		if(args.length == 0) {
			// Jezeli wyslano z konsoli
			if(!(sender instanceof Player)) {
				Bukkit.getLogger().warning("Nie mozesz wykonywac tego polecenia z poziomu konsoli!");
				return true;
			}
			// Jezeli wyslal gracz
			CraftDyrt.getBungeeCord().connect((Player) sender, "Lobby", lobby);
			return true;
		}
		// Liczba argumentow - 1
		if(args.length == 1) {
			// Argument 0: -all
			if(args[0].equalsIgnoreCase("-ALL")) {
				// Jezeli sender nie jest operatorem serwera
				if(!(sender.hasPermission("dyrtcraft.command.hub.kickall"))) {
					sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
					return true;
				}
				// Jezeli wyslano z konsoli
				if(!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Uzycie: /hub -ALL <nazwa serwera>");
					return true;
				}
				kickPlayers((Player) sender);
				return true;
			// Jezeli argument 0 to nie: -all
			} else {
				// Pobieranie gracza z listy graczy online
				Player gracz = Bukkit.getPlayer(args[0]);
				// Jezeli sender nie jest operatorem serwera
				if(!(sender.hasPermission("dyrtcraft.command.hub.player"))) {
					sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
					return true;
				}
				// Jezeli pobrany gracz nie jest online na serwerze
				if(gracz == null) {
		        	sender.sendMessage(ChatColor.RED + "Gracz \"" + args[0] + "\" nie jest obecnie na serwerze!");
		        	return true;
				}
				// Polacz z serwerem lobby
				gracz.sendMessage(ChatColor.GRAY + "Przelaczanie na serwer " + ChatColor.GOLD + "Lobby" + ChatColor.GRAY + "...");
				CraftDyrt.getBungeeCord().connect(gracz, "Lobby", lobby);
				return true;
			}
		// Liczba argumentow nie zostala spelniona
		} else {
			sender.sendMessage(ChatColor.RED + "Zbyt duzo argumentów!");
			sender.sendMessage(ChatColor.RED + "Uzycie: /lobby [-ALL|gracz]");
			return true;
		}
	}
	
	protected void kickPlayers(Player player) {
		// Wiadomosc do wysylacacego (operatora)
		player.sendMessage(ChatColor.RED + "Wyrzucanie wszystkich graczy z serwera...");
		for(Player gracze : Bukkit.getOnlinePlayers()) {
			// Polacz z serwerem lobby
			player.sendMessage(ChatColor.RED + "Wyrzucanie gracza " + gracze.getName() + "...");
			gracze.sendMessage(ChatColor.GRAY + "Przelaczanie na serwer " + ChatColor.GOLD + "Lobby" + ChatColor.GRAY + "...");
			CraftDyrt.getBungeeCord().connect(gracze, "Lobby", lobby);
			gracze.sendMessage(ChatColor.GOLD + "Zostales przeniesiony na serwer Lobby.");
			return;
		}
	}
	
}
