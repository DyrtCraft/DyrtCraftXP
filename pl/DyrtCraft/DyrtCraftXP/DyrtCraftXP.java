package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.api.Bungee;

public class DyrtCraftXP extends JavaPlugin {
	
	static DyrtCraftXP instance;
	String authors = "TheMolkaPL";
	String version = "Alpha 1.1.5";
	
	@Override
	public void onEnable() {
		getLogger().info("Ladowanie DyrtCraftXP v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new Bungee(this));
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		saveDefaultConfig();
		
		registerCommands();
		registerListeners();
		
		getLogger().info("Zaladowano DyrtCraftXP wersja " + getDescription().getVersion());
	}
	
	@Override
	public void onDisable() {
		saveConfig();
	}
	
	public String getAuthors() {
		return authors;
	}
	
	public static DyrtCraftXP getInstance() {
		return instance;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void registerCommands() {
		getCommand("dcxp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.DcxpCommand(this));
		getCommand("lobby").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.LobbyCommand(this));
		getCommand("xp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.XpCommand(this));
	}
	
	public void registerListeners() {
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.api.Bungee(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.LobbySign(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory(this), this);
	}

}
