package pl.DyrtCraft.DyrtCraftXP;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DyrtCraftXP extends JavaPlugin implements Plugin {
	
	public Map<String, String> portalData = new HashMap<String, String>();
	protected File portale1 = null;
	protected FileConfiguration portale2 = null;
	
	public void onEnable() {
		
		getLogger().info("Wlaczanie DyrtCraftXP v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new Bungee(this));
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		saveDefaultConfig();
		saveDefaultPortals();

		getCommand("xp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.XpCommand(this));
		
		getCommand("dcxp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.DcxpCommand(this));
		getCommand("lobby").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.LobbyCommand(this));
		getCommand("serwer").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.SerwerCommand(this));
		
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.Bungee(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.LobbySign(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.Portals(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.Select(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory(this), this);
		
		//getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.sql.XP(this), this);
	}
	
	public void onDisable() {
		saveConfig();
	}
	
	public FileConfiguration getPortale() {
		return portale2;
	}
	
	public void reloadPortals() {
		if(portale1 == null) {
			portale1 = new File(getDataFolder(), "portale.yml");
		}
		portale2 = YamlConfiguration.loadConfiguration(portale1);
		InputStream defConfigStream = getResource("portale.yml");
		
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			portale2.setDefaults(defConfig);
		}
	}
	
	public void saveDefaultPortals() {
		if(portale1 == null) {
			portale1 = new File(getDataFolder(), "portale.yml");
		} else {
			((Plugin) portale1).saveResource("portale.yml", false);
		}
	}
	
	public void savePortals() {
		if((portale2 == null) || (portale1 == null))
			return;
		try {
			.save(portale1);
		} catch (IOException ex) {
			getLogger().log(Level.SEVERE, "Nie moge odnalesc  " + portale1, ex);
		}
	}

}
