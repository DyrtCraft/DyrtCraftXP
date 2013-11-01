package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.api.Bungee;

public class DyrtCraftXP extends JavaPlugin {
	
	static DyrtCraftXP instance;
	
	// Dane do SQL
	/* adres */ //String address = getConfig().getString("sql.address");
	/* login */ //String login = getConfig().getString("sql.login");
	/* haslo */ //String password = getConfig().getString("sql.password");
	// Koniec danych do SQL
	
	@Override
	public void onEnable() {
		getLogger().info("Ladowanie " + getDescription().getFullName() + "...");
		
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new Bungee(this));
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		saveDefaultConfig();
		
		registerCommands();
		registerListeners();
		
		//new MySQL(this, address, login, password);
		
		getLogger().info("Zaladowano " + getDescription().getFullName() + "!");
	}
	
	@Override
	public void onDisable() {
		saveConfig();
	}
	
	public static DyrtCraftXP getInstance() {
		return instance;
	}
	
	public void registerCommands() {
		getCommand("dcxp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.DcxpCommand(this));
		getCommand("xp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.XpCommand(this));
	}
	
	public void registerListeners() {
		getLogger().info("[DyrtCraftXP] Rejestrowanie listenerów...");
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.api.Bungee(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.LobbySign(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory(this), this);
		getLogger().info("[DyrtCraftXP] Zarejestrowano listenery!");
	}
	
}
