package pl.DyrtCraft.DyrtCraftXP.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.events.PlayerChangeServerEvent;

public class Bungee implements Listener, PluginMessageListener {

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
		PlayerChangeServerEvent event = new PlayerChangeServerEvent(player, serverName, serverAddress);
		pluginStatic.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled()) {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			player.sendMessage(event.getMessage());
			try {
				out.writeUTF("Connect");
				out.writeUTF(event.getServerAddress());
			} catch(IOException ex) {}
			//Bungee.broadcastLeftMessage(playerQuitEvent, player, serverName);
			pluginStatic.getLogger().info("Przelaczanie gracza " + player.getName() + " na serwer " + serverName + "...");
			player.sendPluginMessage(pluginStatic, "BungeeCord", b.toByteArray());
		}
	}

	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {}
	
}
