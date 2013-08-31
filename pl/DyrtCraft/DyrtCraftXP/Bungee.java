package pl.DyrtCraft.DyrtCraftXP;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Bungee implements Listener{

	private static DyrtCraftXP pluginStatic;
	private static PlayerQuitEvent playerQuitEvent;
	
	public Bungee(DyrtCraftXP dyrtCraftXP) {
		pluginStatic=dyrtCraftXP;
	}
	
	/**
	 * Polacz klienta z innym serwerem
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.1.0
	 * 
	 * @param player Klient
	 * @param serverName Nazwa serwera do wyswietlenia
	 * @param serverAdress Nazwa serwera w Bungee (lower case)
	 * 
	 * @see Bungee#broadcastLeftMessage(PlayerQuitEvent, String)
	 */
	public static void connect(Player player, String serverName, String serverAdress) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		player.sendMessage(ChatColor.GRAY + "Przelaczanie na serwer  " + ChatColor.GOLD + serverName + ChatColor.GRAY + "...");
		try {
			dos.writeUTF("Connect");
			dos.writeUTF(serverAdress);
		} catch(IOException ex) {
			player.sendMessage(ChatColor.RED + "Ten serwer jest chwilowo offline :(");
		}
		//Bungee.broadcastLeftMessage(playerQuitEvent, player, serverName);
		player.sendPluginMessage(pluginStatic, "BungeeCord", baos.toByteArray());
	}
	
	/**
	 * Powiadom graczy
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.1.0
	 * 
	 * @param event PlayerQuitEvent
	 * @param serverName Nazwa serwera do wyswietlenia
	 * 
	 * @see Bungee#connect(Player, String, String)
	 */
	/*@EventHandler(priority=EventPriority.HIGHEST)
	public static void broadcastLeftMessage(PlayerQuitEvent event, Player player, String serverName) {
		String gracz = player.toString();
		
		String msg = pluginStatic.getConfig().getString("wiadomosc-1");
		msg.replaceAll("{GRACZ}", gracz);
		msg.replaceAll("{SERWER}", serverName);
		msg.replaceAll("&", "§");
		
		event.setQuitMessage(msg);
		pluginStatic.getLogger().log(null, event.getQuitMessage());
	}*/
	
}
