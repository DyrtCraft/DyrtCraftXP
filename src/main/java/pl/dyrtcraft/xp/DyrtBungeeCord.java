package pl.dyrtcraft.xp;

import javax.annotation.Nonnull;

import org.bukkit.inventory.Inventory;

import pl.dyrtcraft.BungeeCord;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.xp.inv.TeleportInventory;

public class DyrtBungeeCord implements BungeeCord {
	
	@Override
	public Server getServer(@Nonnull String address) {
		if(address.equalsIgnoreCase("apocalypto")) {
			return Server.APOCALYPTO_HARDCORE;
		}
		if(address.equalsIgnoreCase("lobby")) {
			return Server.LOBBY;
		}
		if(address.equalsIgnoreCase("minez")) {
			return Server.MINEZ;
		}
		if(address.equalsIgnoreCase("paintball")) {
			return Server.PAINTBALL;
		}
		if(address.equalsIgnoreCase("pvp")) {
			return Server.PVP;
		}
		if(address.equalsIgnoreCase("skyblock")) {
			return Server.SKYBLOCK;
		} else {
			return Server.valueOf(address);
		}
	}
	
	// TODO HashMap?
	@Override
	public String getServerAddress(@Nonnull Server server) {
		if(server == Server.APOCALYPTO_HARDCORE) {
			return "apocalypto";
		}
		if(server == Server.LOBBY) {
			return "lobby";
		}
		if(server == Server.MINEZ) {
			return "minez";
		}
		if(server == Server.PAINTBALL) {
			return "paintball";
		}
		if(server == Server.PVP) {
			return "pvp";
		}
		if(server == Server.SKYBLOCK) {
			return "skyblock";
		} else {
			return server.toString().toLowerCase();
		}
	}
	
	@Override
	public Inventory getServerChooserInventory() {
		return TeleportInventory.inv;
	}
	
	// TODO HashMap?
	@Override
	public String getServerName(@Nonnull Server server) {
		if(server == Server.APOCALYPTO_HARDCORE) {
			return "Apocalypto - Hardcore";
		}
		if(server == Server.LOBBY) {
			return "Lobby";
		}
		if(server == Server.MINEZ) {
			return "MineZ";
		}
		if(server == Server.PAINTBALL) {
			return "Paintball";
		}
		if(server == Server.PVP) {
			return "PvP";
		}
		if(server == Server.SKYBLOCK) {
			return "SkyBlock";
		} else {
			return server.toString().toLowerCase();
		}
	}
	
}
