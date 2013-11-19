package pl.DyrtCraft.DyrtCraftXP;

import me.confuser.barapi.BarAPI;

import org.bukkit.plugin.java.JavaPlugin;

import pl.DyrtCraft.DyrtCraftXP.api.API;
import pl.DyrtCraft.DyrtCraftXP.api.Bungee;
import pl.DyrtCraft.DyrtCraftXP.command.DcxpCommand;
import pl.DyrtCraft.DyrtCraftXP.command.LobbyCommand;
import pl.DyrtCraft.DyrtCraftXP.command.XpCommand;

public class DyrtCraftXP extends JavaPlugin {
	
	private static DyrtCraftXP instance;
	
	// Dane do SQL
	/* adres */ //String address = getConfig().getString("sql.address");
	/* login */ //String login = getConfig().getString("sql.login");
	/* haslo */ //String password = getConfig().getString("sql.password");
	// Koniec danych do SQL
	
	@Override
	public void onEnable() {
		getLogger().info("Ladowanie " + getDescription().getFullName() + "...");
		long loadTime = System.currentTimeMillis();
		
		//******************************************************************************//
		
		getLogger().info("Implementacja API " + API.getAPIVersion() + " by " + API.getAPIAuthors());
		
		//******************************************************************************//
		
		getLogger().info("Ladowanie BarAPI v1.0 by confuser...");
		BarAPI bar = null;
		bar = new BarAPI();
		
		bar.loadBarApi();
		getLogger().info("Zaladowano BarAPI by confuser!");
		
		//******************************************************************************//
		
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new Bungee(this));
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		//******************************************************************************//
		
		saveDefaultConfig();
		registerCommands();
		registerListeners();
		
		//******************************************************************************//
		
		getLogger().info("Ladowanie klasy MySQL.class...");
		//new MySQL(this, address, login, password);
		
		//******************************************************************************//
		
		long finLoadTime = System.currentTimeMillis() - loadTime;
		getLogger().info("Zaladowano " + getDescription().getFullName() + " w " + finLoadTime + " ms!");
	}
	
	@Override
	public void onDisable() {
		saveConfig();
	}
	
	public static DyrtCraftXP getInstance() {
		return instance;
	}
	
	public void registerCommands() {
		getCommand("dcxp").setExecutor(new DcxpCommand(this));
		getCommand("lobby").setExecutor(new LobbyCommand(this));
		getCommand("xp").setExecutor(new XpCommand(this));
	}
	
	public void registerListeners() {
		getLogger().info("Rejestrowanie listenerów...");
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.api.Bungee(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.LobbySign(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory(this), this);
		
		getServer().getPluginManager().registerEvents(new BarAPI(), this);
		getLogger().info("Zarejestrowano listenery!");
	}
	
}
