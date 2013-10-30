package pl.DyrtCraft.DyrtCraftXP.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class PlayerOpenTeleportInvEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private Inventory inventoryInv;
	private Player playerPla;
	
	public PlayerOpenTeleportInvEvent(Inventory inv, Player player) {
		inventoryInv = inv;
		playerPla = player;
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
	
	public Inventory getInventory() {
		return inventoryInv;
	}
	
	public Player getPlayer() {
		return playerPla;
	}
	
	public void setInventory(Inventory inv) {
		inventoryInv = inv;
	}
	
}
