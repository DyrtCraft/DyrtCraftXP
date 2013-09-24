package pl.DyrtCraft.DyrtCraftXP.api;

import org.bukkit.Location;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class Selection {

	DyrtCraftXP plugin;
	
	public Selection(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	public static Location getFirstSelect() {
		return null;
	}
	
	public static Location getSecondSelect() {
		return null;
	}
	
	public static void setFirstSelect(Location location) {
		getFirstSelect().setX(location.getX());
		getFirstSelect().setY(location.getY());
		getFirstSelect().setZ(location.getZ());
	}
	
	public static void setSecondSelect(Location location) {
		getSecondSelect().setX(location.getX());
		getSecondSelect().setY(location.getY());
		getSecondSelect().setZ(location.getZ());
	}
	
}
