package pl.DyrtCraft.DyrtCraftXP.api;

import pl.DyrtCraft.DyrtCraftXP.DyrtCraftXP;

public class API {
	
	DyrtCraftXP plugin;
	
	public API(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	/**
	 * Zdobadz liste autorów API
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @return String Lista autorów API
	 */
	public static String getAPIAuthors() {
		return "TheMolkaPL";
	}
	
	/**
	 * Zdobadz pelna wersje API
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @return String Pelna wersja (x.x)
	 */
	public static String getAPIVersion() {
		return getAPIVersionV1() + "." + getAPIVersionV2() + "_" + getAPIVersionV3();
	}
	
	/**
	 * Zdobadz wersje v1 API
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @return int Wersja v1 API (<b>x</b>.x_x)
	 */
	public static int getAPIVersionV1() {
		return 1;
	}
	
	/**
	 * Zdobadz wersje v2 API
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @return int Wersja v2 API (x.<b>x</b>_x)
	 */
	public static int getAPIVersionV2() {
		return 0;
	}
	
	/**
	 * Zdobadz wersje v3 API (Jezeli dostepne)
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @return int Wersja API (x.x_<b>x</b>)
	 */
	public static int getAPIVersionV3() {
		return 0;
	}
	
}
