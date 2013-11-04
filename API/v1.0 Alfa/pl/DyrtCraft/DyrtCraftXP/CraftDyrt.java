package pl.DyrtCraft.DyrtCraftXP;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.api.API;
import pl.DyrtCraft.DyrtCraftXP.api.Bungee;
import pl.DyrtCraft.DyrtCraftXP.api.BungeeInventory;
import pl.DyrtCraft.DyrtCraftXP.api.Database;
import pl.DyrtCraft.DyrtCraftXP.api.DyrtCraftPlugin;
import pl.DyrtCraft.DyrtCraftXP.api.Kits;
import pl.DyrtCraft.DyrtCraftXP.api.XP;

public class CraftDyrt {
	
	private static API api;
	private static Bungee bungee;
	private static BungeeInventory bungeeInventory;
	private static Database database;
	private static DyrtCraftPlugin dyrtCratPlugin;
	private static JavaPlugin javaPlugin;
	private static Kits kits;
	private static XP xp;
	
	DyrtCraftXP plugin;
	
	public CraftDyrt(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	public static API getPluginAPI() {
		return api;
	}
	
	public static Bungee getBungeeCord() {
		return bungee;
	}
	
	public static BungeeInventory getBungeeInventory() {
		return bungeeInventory;
	}
	
	public static Database getDatabase() {
		return database;
	}
	
	public static DyrtCraftPlugin getDyrtCraft() {
		return dyrtCratPlugin;
	}
	
	public static Kits getKits() {
		return kits;
	}
	
	public static List<String> getPluginAuthors() {
		return javaPlugin.getDescription().getAuthors();
	}
	
	public static String getPluginFullName() {
		return "DyrtCraftXP v" + getPluginVersion() + " by " + getPluginAuthors();
	}
	
	public static String getPluginGitHub() {
		return "https://www.github.com/DyrtCraft/DyrtCraftXP";
	}
	
	public static String getPluginName() {
		return "DyrtCraftXP";
	}
	
	public static String getPluginVersion() {
		return javaPlugin.getDescription().getVersion();
	}
	
	public static String getPluginWebsite() {
		return javaPlugin.getDescription().getWebsite();
	}
	
	public static XP getXp() {
		return xp;
	}
	
}
