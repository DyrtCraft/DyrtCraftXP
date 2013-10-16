package pl.DyrtCraft.DyrtCraftXP.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.error.YAMLException;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftPlugin;
import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.api.BungeeInventory;

public class DcxpCommand implements CommandExecutor {

	private DyrtCraftXP plugin;

	public DcxpCommand(DyrtCraftXP dyrtCraftXP) {
		plugin=dyrtCraftXP;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {		
		if(label.equalsIgnoreCase("dcxp")) {
			if(args.length==0) {
				return erArg(sender, "Nie podano zadnego argumentu!");
			}
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("version")) {
					sender.sendMessage(ChatColor.GOLD + " >==========[ " + ChatColor.BOLD + ChatColor.AQUA + "DyrtCraftXP" + ChatColor.RESET + ChatColor.GOLD + " ]==========< ");
					sender.sendMessage(ChatColor.GOLD + "Autor: " + plugin.getDescription().getAuthors());
					sender.sendMessage(ChatColor.GOLD + " >==========[ " + ChatColor.BOLD + ChatColor.AQUA + "DyrtCraftXP" + ChatColor.RESET + ChatColor.GOLD + " ]==========< ");
					return true;
				}
				if(args[0].equalsIgnoreCase("inv") || args[0].equalsIgnoreCase("show")) {
					if(!(sender.isOp() || sender.hasPermission("dyrtcraft.xp.inv"))) {
						sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
						return true;
					}
					if(!(sender instanceof Player)) {
						plugin.getLogger().warning("Nie mozesz wykonac tej komendy z poziomu konsoli!");
						return true;
					}
					Player p = (Player) sender;
					p.sendMessage(ChatColor.GRAY + "Otwieranie inv z teleportami...");
					BungeeInventory.showInventory(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("portals")) {
					return erPortalsArg(sender);
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(!(sender.isOp() || sender.hasPermission("dyrtcraft.xp.reload"))) {
						sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
						return true;
					}
					// config.yml
					try {
						plugin.reloadConfig();
					} catch(YAMLException ex) {
						DyrtCraftPlugin.sendMsgToOp("Nastapil blad z plikiem config.yml (YAMLException)", 1);
					} catch(Exception ex) {
						DyrtCraftPlugin.sendMsgToOp("Nastapil blad z plikiem config.yml (Nie znaleziono pliku). Tworzenie 1 pliku dla Ciebie w folderze", 1);
						plugin.saveDefaultConfig();
					}
					sender.sendMessage(ChatColor.DARK_GREEN + "Pomyslnie przeladowano plik config.yml!");
					
					// portale.yml
					try {
						//plugin.reloadPortals();
					} catch(YAMLException ex) {
						DyrtCraftPlugin.sendMsgToOp("Nastapil blad z plikiem portale.yml (YAMLException)", 1);
					} catch(Exception ex) {
						DyrtCraftPlugin.sendMsgToOp("Nastapil blad z plikiem portale.yml (Nie znaleziono pliku). Tworzenie 1 pliku dla Ciebie w folderze", 1);
						//plugin.saveDefaultPortals();
					}
					sender.sendMessage(ChatColor.DARK_GREEN + "Pomyslnie przeladowano plik portale.yml!");
					return true;
				} else {
					return erArg(sender, "Podano bledy argument!");
				}
			}
			if(args.length==2) {
				if(args[0].equalsIgnoreCase("portals")) {
					if(args[1].equalsIgnoreCase("manage")) {
						List<String> lista = plugin.getConfig().getStringList("lista-portali");
						for(String l : lista) {
							sender.sendMessage(l);
						}
						return true;
					}
					return erPortalsArg(sender);
				} else {
					return erArg(sender, "Podano bledy argument!");
				}
			}
			if(args.length==3) {
				if(args[0].equalsIgnoreCase("portals")) {
					if(args[1].equalsIgnoreCase("delete")) {
						String serverName = args[2];
						
						if(plugin.getConfig().getStringList("lista-portali").equals(serverName)) {
							plugin.getConfig().getStringList("lista-portali").remove(serverName);
							
							plugin.getConfig().set(serverName, null);
							plugin.saveConfig();
							DyrtCraftPlugin.sendMsgToOp(sender.getName() + " usunal portal na serwer " + serverName, 0);
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Nie znaleziono portalu o nazwie \"" + serverName + "\"!");
							return true;
						}
					}
					return erPortalsArg(sender);
				} else {
					return erArg(sender, "Podano bledy argument!");
				}
			}
			if(args.length==4) {
				if(args[0].equalsIgnoreCase("portals")) {
					if(args[1].equalsIgnoreCase("create")) {
						String serverName = args[2];
						String serverAddress = args[3];
						
						plugin.getConfig().getStringList("lista-portali").add(serverName);
						
						//TODO
						plugin.getConfig().set("portale." + serverName + ".address", serverAddress);
						
						plugin.getConfig().set("portale." + serverName + "1.x", null);
						plugin.getConfig().set("portale." + serverName + "1.y", null);
						plugin.getConfig().set("portale." + serverName + "1.z", null);
						
						plugin.getConfig().set("portale." + serverName + "2.x", null);
						plugin.getConfig().set("portale." + serverName + "2.y", null);
						plugin.getConfig().set("portale." + serverName + "2.z", null);
						plugin.saveConfig();
						DyrtCraftPlugin.sendMsgToOp(sender.getName() + " utworzyl portal do serwera " + serverName, 0);
						return true;
					} else {
						return erPortalsArg(sender);
					}
				}
				return false;
			} else {
				return erArg(sender, "Zbyt duzo argumentów!");
			}
		}
		return false;
	}
	
	protected boolean erArg(CommandSender sender, String er) {
		sender.sendMessage(ChatColor.RED + er);
		sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("dcxp").getUsage());
		return true;
	}
	
	protected boolean erPortalsArg(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		sender.sendMessage(ChatColor.RED + "/dcxp portals create <nazwaSerwera>");
		sender.sendMessage(ChatColor.RED + "/dcxp portals delete <nazwaSerwera>");
		sender.sendMessage(ChatColor.RED + "/dcxp portals manage");
		sender.sendMessage(ChatColor.RED + "Wazne: Aby stworzyc portal na serwer, musi byc on dostepny pod /dcxp server manage");
		return true;
	}
	
}
