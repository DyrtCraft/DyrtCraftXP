package pl.dyrtcraft.xp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.annotation.Nonnull;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.Member;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.events.PlayerChangeServerEvent;
import pl.dyrtcraft.xp.api.Database;
import pl.dyrtcraft.xp.api.XP;

@SuppressWarnings("deprecation")
public class DyrtMember implements Member {
	
	private DyrtCraftXP plugin = new DyrtCraftXP();
	private Player member;
	
	public DyrtMember(Player member) {
		this.member = member;
	}
	
	@Override
	public void addXp(@Nonnull int amount, @Nonnull String reason) {
		XP.addXp(member, amount, reason);
	}
	
	@Override
	public void connect(@Nonnull Server server) {
		PlayerChangeServerEvent event = new PlayerChangeServerEvent(member, server);
		Bukkit.getPluginManager().callEvent(event);
		
		if(!event.isCancelled()) {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			member.sendMessage(event.getMessage());
			try {
				out.writeUTF("Connect");
				out.writeUTF(DyrtCraft.getProxy().getServerAddress(event.getServer()));
			} catch(IOException ex) {}
			Bukkit.getLogger().info("Przelaczanie gracza " + member.getName() + " na serwer " + DyrtCraft.getProxy().getServerName(server) + "...");
			member.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
		}
	}
	
	@Override
	public boolean delXp(@Nonnull int amount, @Nonnull String reason) {
		XP.delXp(member, amount, reason);
		return false;
	}
	
	@Override
	public int getDeads() {
		return -1;
	}
	
	@Override
	public int getKills() {
		return -1;
	}
	
	@Override
	public SimpleDateFormat getLastLogoutTime() {
		// TODO
		return null;
	}
	
	@Override
	public String getLastServer() {
		return Database.getLastServer(member.getName());
	}
	
	@Override
	public int getXp() {
		return XP.getXp(member.getName());
	}
	
	@Override
	public boolean hasDragonBar() {
		return BarAPI.hasBar(member);
	}
	
	@Override
	public void removeDragon() {
		BarAPI.removeBar(member);
	}
	
	@Override
	public void setDragonLevel(@Nonnull float percent) {
		BarAPI.setHealth(member, percent);
	}
	
	@Override
	public void setDragonMessage(String message) {
		BarAPI.setMessage(member, message);
	}
	
	@Override
	public void setDragonMessage(String message, @Nonnull float percent) {
		BarAPI.setMessage(member, message, percent);
		
	}
	
	@Override
	public void setDragonMessage(String message, @Nonnull int seconds) {
		BarAPI.setMessage(member, message, seconds);
		
	}
	
	@Override
	public void setDeads(int deads) {
		
	}
	
	@Override
	public void setKills(int kills) {
		
	}
	
	@Override
	public void setLastLogoutTime(@Nonnull SimpleDateFormat time) {
		// TODO
	}
	
	@Override
	public void setLastServer(@Nonnull Server server) {
		Database.setLastServer(member.getName(), server.toString());
	}
	
	@Override
	public String showXp() {
		return XP.showXp(member.getName());
	}
	
}
