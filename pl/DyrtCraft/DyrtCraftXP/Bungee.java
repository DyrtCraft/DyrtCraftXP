package pl.DyrtCraft.DyrtCraftXP;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

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
	
	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {}
	
	/**
	 * Pobierz liczbe klientow na serwerze serverAddress
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.1.1
	 * 
	 * @param player Klient
	 * @param serverAddress Nazwa serwera w Bungee (lower case)
	 * @return
	 * 
	 * @see Bungee#connect(Player, String, String)
	 */
	/*public static int getOnlinePlayers(Player player, String serverAddress) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		DataInputStream in = new DataInputStream(in));
		
		try {
			out.writeUTF("PlayerCount");
			out.writeUTF(serverAddress);
		} catch(IOException ex) {}
		player.sendPluginMessage(pluginStatic, "BungeeCord", b.toByteArray());
		
		String server = in.readUTF();
		int playercount = in.readInt();
		
		return playercount;
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		plugin.getLogger().info("Otrzymano wiadomosc na kanale " + channel + " od " + player.getName() + " o tresci: " + message.toString());
	
		if (!(channel.equals("BungeeCord"))) {
			return;
		}
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(b));
		String subchannel;
		try {
			subchannel = in.readUTF();
			if(subchannel.equals("PlayerCount")) {
				String server = in.readUTF();
				int count = in.readInt();
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}*/
	
}
