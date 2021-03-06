package pl.DyrtCraft.DyrtCraftXP.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class Bungee implements Listener, PluginMessageListener{

	private static DyrtCraftXP pluginStatic;
	
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
	 * @param serverAddress Nazwa serwera w Bungee (lower case)
	 * 
	 * @see Bungee#broadcastLeftMessage(PlayerQuitEvent, String)
	 * @see Bungee#getOnlinePlayers(Player, String)
	 */
	public static void connect(Player player, String serverName, String serverAddress) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		player.sendMessage(ChatColor.GRAY + "Przelaczanie na serwer " + ChatColor.GOLD + serverName + ChatColor.GRAY + "...");
		try {
			out.writeUTF("Connect");
			out.writeUTF(serverAddress);
		} catch(IOException ex) {}
		//Bungee.broadcastLeftMessage(playerQuitEvent, player, serverName);
		pluginStatic.getLogger().info("Przelaczanie gracza " + player.getName() + " na serwer " + serverName + "...");
		player.sendPluginMessage(pluginStatic, "BungeeCord", b.toByteArray());
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
	@EventHandler(priority=EventPriority.HIGHEST)
	public static void broadcastLeftMessage(PlayerQuitEvent event, Player player, String serverName) {
		String gracz = player.toString();
		
		String msg = pluginStatic.getConfig().getString("wiadomosc-1");
		msg.replaceAll("{GRACZ}", gracz);
		msg.replaceAll("{SERWER}", serverName);
		msg.replaceAll("&", "�");
		
		event.setQuitMessage(msg);
		pluginStatic.getLogger().log(null, event.getQuitMessage());
	}
	
	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {}
	
}
