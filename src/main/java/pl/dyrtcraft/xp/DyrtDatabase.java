package pl.dyrtcraft.xp;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;

import pl.dyrtcraft.Database;

public class DyrtDatabase implements Database {
	
	@SuppressWarnings("deprecation")
	@Override
	public void createNew(@Nonnull Player player) {
		pl.dyrtcraft.xp.api.Database.createNewPlayer(player.getName());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void createNew(@Nonnull String name) {
		pl.dyrtcraft.xp.api.Database.createNewPlayer(name);
	}
	
}
