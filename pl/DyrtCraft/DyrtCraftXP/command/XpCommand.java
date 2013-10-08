package pl.DyrtCraft.DyrtCraftXP.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftPlugin;
import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.api.Database;
import pl.DyrtCraft.DyrtCraftXP.api.XP;

/**
 * Komendy zwiazane z doswiadczeniem DyrtCraftXP
 * 
 * @author TheMolkaPL
 * @see XP
 */
public class XpCommand implements CommandExecutor {
	
	public XpCommand(DyrtCraftXP dyrtCraftXP) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		// Komenda /xp
		if(label.equalsIgnoreCase("xp")) {
			// Liczba argumentow - 0
			if(args.length==0) {
				// Jezeli wyslano z konsoli
				if(!(sender instanceof Player)) {
					Bukkit.getLogger().warning("Nie mozesz wykonywac tego polecenia z poziomu konsoli!");
					return true;
				}
				// Jezeli wyslal gracz
				Player p = (Player) sender;
				// Pokaz ilosc XP
				sender.sendMessage(XP.showXp(p.toString()));
				return true;
			}
			// Liczba argumentow - 1
			if(args.length==1) {
				// Pobieranie gracza z listy graczy online
				Player gracz = Bukkit.getServer().getPlayer(args[0]);
				// Jezeli sender nie jest operatorem serwera
				if(!(sender.isOp())) {
					sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
					return true;
				}
				// Jezeli pobrany gracz nie jest online na serwerze
				if(gracz == null) {
		        	sender.sendMessage(ChatColor.RED + "Gracz \"" + args[0] + "\" nie jest obecnie na serwerze!");
		        	return true;
		        }
				// Jezeli wyslal gracz
				// Pokaz ilosc XP
		        sender.sendMessage(XP.showXp(gracz.toString()));
		        return true;
			}
			// Liczba argumentow - 2
			if(args.length==2) {
				// Jezeli sender nie jest operatorem serwera
				if(!(sender.isOp())) {
					sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
					return true;
				}
				// Argument 1: create
				if(args[1].equalsIgnoreCase("create")) {
					Database.createNewPlayer(args[0]);
					DyrtCraftPlugin.sendMsgToOp(sender.getName() + " stworzyl nowe konto dla gracza " + args[0], 1);
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Popelniono blad! Spróbuj jeszcze raz!");
					sender.sendMessage(ChatColor.RED + "/xp [gracz] [[create]del|give|set <liczba>]");
					return true;
				}
			}
			// Liczba argumentow - 3
			if(args.length==3) {
				// Jezeli sender nie jest operatorem serwera
				if(!(sender.isOp())) {
					sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
					return true;
				}
				// Argument 1: del
				if(args[1].equalsIgnoreCase("del")) {
					/*
					int xp = args[2];
					XP.delXp(args[0], xp, "Usunieto " + xp + " XP przez " + sender.getName());
					DyrtCraftPlugin.sendMsgToOp(sender.getName() + " usunal graczowi " + args[0] + xp + " XP", 1);
					return true;
					*/
				}
				// Argument 1: give
				if(args[1].equalsIgnoreCase("give")) {
					/*
					int xp = args[2];
					XP.addXp(args[0], xp, "Dodano " + xp + "XP przez " + sender.getName());
					DyrtCraftPlugin.sendMsgToOp(sender.getName() + " dodal graczowi " + args[0] + xp + " XP", 1);
					return true;
					*/
				}
				// Argument 1: set
				if(args[1].equalsIgnoreCase("set")) {
					/*
					int xp = args[2];
					XP.delXp(args[0], Integer.MAX_VALUE, "");
					XP.addXp(args[0], xp, "Ustawiono " + xp + "XP przez " + sender.getName());
					DyrtCraftPlugin.sendMsgToOp(sender.getName() + " ustawil graczowi " + args[0] + xp + " XP", 1);
					return true;
					*/
				// Zaden z argumentow nie zostal spelniony
				} else {
					sender.sendMessage(ChatColor.RED + "Popelniono blad! Spróbuj jeszcze raz!");
					sender.sendMessage(ChatColor.RED + "/xp [gracz] [[create]|del|give|set <liczba>]");
					return true;
				}
		    // Liczba argumentow nie zostala spelniona
			} else {
				sender.sendMessage(ChatColor.RED + "Popelniono blad! Spróbuj jeszcze raz!");
				sender.sendMessage(ChatColor.RED + "/xp [gracz] [[create]del|give|set <liczba>]");
				return true;
			}
		}
		// Jezeli komenda nie jest /xp
		return false;
	}
	
}
