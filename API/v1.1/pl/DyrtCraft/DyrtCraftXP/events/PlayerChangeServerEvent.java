package pl.DyrtCraft.DyrtCraftXP.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChangeServerEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private Player playerPla;
	private String serverStr;
	private String serverAddressStr;
	private String messageStr = ChatColor.GRAY + "Przelaczanie na serwer " + ChatColor.GOLD + serverStr + ChatColor.GRAY + "...";
	
	public PlayerChangeServerEvent(Player player, String server, String serverAddress) {
		playerPla = player;
		serverStr = server;
		serverAddressStr = serverAddress;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
	
	public String getMessage() {
		return messageStr;
	}
	
	public Player getPlayer() {
		return playerPla;
	}
	
	public String getServer() {
		return serverStr;
	}
	
	public String getServerAddress() {
		return serverAddressStr;
	}
	
	public void setMessage(String message) {
		messageStr = message;
	}
	
	public void setServer(String server) {
		serverStr = server;
	}
	
	public void setServerAddress(String serverAddress) {
		serverAddressStr = serverAddress;
	}
	
}
