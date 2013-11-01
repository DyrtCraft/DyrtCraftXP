package pl.DyrtCraft.DyrtCraftXP.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLoseXPEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player playerPla;
	private String reasonStr;
	private int xpInt;
	
	public PlayerLoseXPEvent(Player player, String reason, int xp) {
		playerPla = player;
		reasonStr = reason;
		xpInt = xp;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public Player getPlayer() {
		return playerPla;
	}
	
	public String getReason() {
		return reasonStr;
	}
	
	public int getAmount() {
		return xpInt;
	}
	
	public void setReason(String reason) {
		reasonStr = reason;
	}
	
	public void setAmount(int xp) {
		xpInt = xp;
	}
	
}
