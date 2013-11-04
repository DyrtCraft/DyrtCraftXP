package pl.DyrtCraft.DyrtCraftXP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.DyrtCraft.DyrtCraftXP.api.Database;
import pl.DyrtCraft.DyrtCraftXP.api.DyrtCraftPlugin;
import pl.DyrtCraft.DyrtCraftXP.api.XP;

/**
 * Klasa do MySQL
 * @author TheMolkaPL, Ziszek
 * @see Database
 * @see XP
 */
public class MySQL {

	/*
	 * Tu w przyszlosci bedzie obsluga bazy danych do XP oraz innych możliwości! :D
	 * 
	 * Tabele:
	 * 
	 *  |	Nick	|	XP	|	Ostatie wylogowanie 	| Ostatni serwer |  Kills   |	Deads	|
	 *  |-----------|-------|---------------------------|----------------|----------|-----------|
	 *  |TheMolkaPL	|	54	|21 december 1963 9:27 AM	|	SkyBlock	 |	40		|	15		|
	 *  |  Ziszek   |	80	|25 december 1963 4:04 PM	|	Hardcore	 |	5		|	1		|
	 * 
	 */
	
	DyrtCraftXP plugin;
	Statement statement = null;
	PreparedStatement prep = null;
	ResultSet res = null;
	Connection connect1 = null;
	String login;
	String password;
	
	public MySQL(DyrtCraftXP dyrtCraftXP, String address, String login, String password) {
		plugin=dyrtCraftXP;
		try {
			connect(address, login, password);
		} catch (IllegalAccessException e) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Nie zaladowano MySQL, Zobacz konsole", 1);
		}
	}
	
	public void connect(String address, String login, String password) throws IllegalAccessException {
		this.login = login;
		this.password = password;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			DyrtCraftPlugin.sendMsgToOp("[SQL] Zaladowano sterownik MySQL (com.mysql.jdbc.Driver)", 1);
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			DyrtCraftPlugin.sendMsgToOp("[SQL] Nie zaladowano sterownika MySQL, Zobacz konsole", 1);
		} catch(InstantiationException ex) {
			ex.printStackTrace();
			DyrtCraftPlugin.sendMsgToOp("[SQL] Nie zaladowano sterownika MySQL, Zobacz konsole", 1);
		}
		
		try {
			connect1 = DriverManager.getConnection("jdbc:mysql://localhost:3306", login, password);
			DyrtCraftPlugin.sendMsgToOp("[SQL] Polaczono z baza danych MySQL", 1);
			createTables();
		} catch(SQLException ex) {
			ex.printStackTrace();
			DyrtCraftPlugin.sendMsgToOp("[SQL] Nie polaczono z MySQL, zobacz konsole", 1);
		}
	}
	
	public void createTables() {
		/*
		 * TODO: Tworzenie tabels
		 */
	}
	
	public void addNewPlayer(String player, String lastServer) {
		try {
			statement = connect1.createStatement();
			
			statement.execute("INSERT INTO DyrtCraftXP1(NICK, XP, LASTLOGOUT, LASTSERVER, KILLS, DEADS) VALUES('" + player + "', 0, 'Online', '" + lastServer + "', 0, 0)");
			
			DyrtCraftPlugin.sendMsgToOp("[SQL] Dodano nowego gracza " + player + " do tabeli DyrtCraftXP1", 1);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z dodaniem nowego gracza do tabeli DyrtCraftXP1", 1);
			ex.printStackTrace();
		}
	}
	
	public int getDeads(String player) {
		try {
			prep = connect1.prepareStatement("SELECT DEADS FROM DyrtCraftXP1 WHERE NICK='" + player + "'");
			res = prep.executeQuery();
			return res.getInt(1);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z pobraniem deads od gracza " + player, 1);
			ex.printStackTrace();
			return -1;
		}
	}
	
	public int getKills(String player) {
		try {
			prep = connect1.prepareStatement("SELECT KILLS FROM DyrtCraftXP1 WHERE NICK='" + player + "'");
			res = prep.executeQuery();
			return res.getInt(1);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z pobraniem kills od gracza " + player, 1);
			ex.printStackTrace();
			return -1;
		}
	}
	
	public String getLastLogout(String player) {
		try {
			prep = connect1.prepareStatement("SELECT LASTLOGOUT FROM DyrtCraftXP1 WHERE NICK='" + player + "'");
			res = prep.executeQuery();
			return res.getString(1);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z pobraniem lastLogout od gracza " + player, 1);
			ex.printStackTrace();
			return "Blad";
		}
	}
	
	public String getLastServer(String player) {
		try {
			prep = connect1.prepareStatement("SELECT LASTSERVER FROM DyrtCraftXP1 WHERE NICK='" + player + "'");
			res = prep.executeQuery();
			return res.getString(1);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z pobraniem lastServer od gracza " + player, 1);
			ex.printStackTrace();
			return "Blad";
		}
	}
	
	public int getXP(String player) {
		try {
			prep = connect1.prepareStatement("SELECT XP FROM DyrtCraftXP1 WHERE NICK='" + player + "'");
			res = prep.executeQuery();
			return res.getInt(1);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z pobraniem XP od gracza " + player, 1);
			ex.printStackTrace();
			return -1;
		}
	}
	
	public void setDeads(String player, int deads) {
		try {
			statement = connect1.createStatement();
			String a = "UPDATE DyrtCraftXP1 SET DEADS=" + deads + " WHERE NICK='" + player + "'";
			statement.executeUpdate(a);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z ustawieniem deads dla gracza " + player, 1);
			ex.printStackTrace();
		}
	}
	
	public void setKills(String player, int kills) {
		try {
			statement = connect1.createStatement();
			String a = "UPDATE DyrtCraftXP1 SET KILLS=" + kills + " WHERE NICK='" + player + "'";
			statement.executeUpdate(a);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z ustawieniem kills dla gracza " + player, 1);
			ex.printStackTrace();
		}
	}
	
	public void setLastLogout(String player, String logoutTime) {
		try {
			statement = connect1.createStatement();
			String a = "UPDATE DyrtCraftXP1 SET LASTLOGOUT=" + logoutTime + " WHERE NICK='" + player + "'";
			statement.executeUpdate(a);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z ustawieniem lastLogoutTime dla gracza " + player, 1);
			ex.printStackTrace();
		}
	}
	
	public void setLastServer(String player, String server) {
		try {
			statement = connect1.createStatement();
			String a = "UPDATE DyrtCraftXP1 SET LASTSERVER=" + server + " WHERE NICK='" + player + "'";
			statement.executeUpdate(a);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z ustawieniem lastServer dla gracza " + player, 1);
			ex.printStackTrace();
		}
	}
	
	public void setXP(String player, int xp) {
		try {
			statement = connect1.createStatement();
			String a = "UPDATE DyrtCraftXP1 SET XP=" + xp + " WHERE NICK='" + player + "'";
			statement.executeUpdate(a);
		} catch(SQLException ex) {
			DyrtCraftPlugin.sendMsgToOp("[SQL] Napotkano problem z ustawieniem XP dla gracza " + player, 1);
			ex.printStackTrace();
		}
	}
	
}
