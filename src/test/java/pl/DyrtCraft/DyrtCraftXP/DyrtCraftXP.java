package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.api.Bungee;

public class DyrtCraftXP extends JavaPlugin {
	
	static DyrtCraftXP instance;
	String authors = "TheMolkaPL";
	String version = "Alpha 1.1.6 - SNAPSHOT BUILD 02";
	
	// Dane do SQL
	/* adres */ //String address = getConfig().getString("sql.address");
	/* login */ //String login = getConfig().getString("sql.login");
	/* haslo */ //String password = getConfig().getString("sql.password");
	// Koniec danych do SQL
	
	@Override
	public void onEnable() {
		getLogger().info("Ladowanie DyrtCraftXP v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new Bungee(this));
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		saveDefaultConfig();
		
		registerCommands();
		registerListeners();
		
		//new MySQL(this, address, login, password);
		
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
		getCommand("xp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.XpCommand(this));
	}
	
	public void registerListeners() {
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.api.Bungee(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.LobbySign(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory(this), this);
	}
	
}
