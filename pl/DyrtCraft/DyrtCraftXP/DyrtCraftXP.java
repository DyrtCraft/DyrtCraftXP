package pl.DyrtCraft.DyrtCraftXP;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class DyrtCraftXP extends JavaPlugin implements PluginMessageListener, Plugin {
	
	public void onEnable() {
		
		getLogger().info("Wlaczanie DyrtCraftXP v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		saveDefaultConfig();

		getCommand("xp").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.XpCommand(this));
		getCommand("lobby").setExecutor(new pl.DyrtCraft.DyrtCraftXP.command.LobbyCommand(this));
		
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.Bungee(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.TeleportInventory(this), this);
		getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.inv.LobbySign(this), this);
		
		//getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.xpadd.JoinedNewServer(this), this);
		//getServer().getPluginManager().registerEvents(new pl.DyrtCraft.DyrtCraftXP.xpadd.KilledMob(this), this);
	}
	
	public void onDisable() {
		saveConfig();
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		this.getLogger().info("Wyslano wiadomosc na kanale " + channel + " od " + player.getName() + " o tresci: " + message.toString());
	}

}
