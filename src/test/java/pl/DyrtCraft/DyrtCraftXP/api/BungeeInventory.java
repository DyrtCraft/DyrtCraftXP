package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

import pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory;

/**
 * @author TheMolkaPL
 * @since Alpha 1.1.4
 */
public class BungeeInventory {

	DyrtCraftXP plugin;
	private static TeleportInventory ti;
	
	public BungeeInventory(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	/**
	 * @author TheMolkaPL
	 * @since A
	 * @return Inventory TeleportInventory
	 */
	public static Inventory getInventory() {
		return TeleportInventory.inv;
	}

	/**
	 * @param player Gracz dla ktorego ma zostac wyswietlone inventory
	 * @author TheMolkaPL
	 * @since Alpha 1.1.4
	 * @see TeleportInventory
	 * @see Bungee#connect(Player, String, String)
	 */
	public static void showInventory(Player player) {
		ti.show(player);
	}
	
}
