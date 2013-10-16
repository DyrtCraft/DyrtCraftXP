package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;
import pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory;

/**
 * @author TheMolkaPL
 * @since Alpha 1.1.4
 */
public class BungeeInventory {

	DyrtCraftXP plugin;
	TeleportInventory ti;
	
	public BungeeInventory(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}

	/**
	 * @param player Gracz dla ktorego ma zostac wyswietlone inventory
	 * @author TheMolkaPL
	 * @since Alpha 1.1.4
	 * @see TeleportInventory
	 * @see Bungee#connect(Player, String, String)
	 */
	public static void showInventory(Player player) {
		TeleportInventory.show(player);
	}
	
}
