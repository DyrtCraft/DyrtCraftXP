package pl.DyrtCraft.DyrtCraftXP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
	Connection connect1 = null;
	String login;
	String password;
	
	public MySQL(DyrtCraftXP dyrtCraftXP, String address, String login, String password) {
		plugin=dyrtCraftXP;
		try {
			connect(address, login, password);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			DyrtCraftPlugin.sendMsgToOp("Nie zaladowano MySQL, Zobacz konsole", 1);
		}
	}
	
	private void connect(String address, String login, String password) throws IllegalAccessException {
		this.login = login;
		this.password = password;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			DyrtCraftPlugin.sendMsgToOp("Zaladowano sterownik MySQL (com.mysql.jdbc.Driver)", 1);
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			DyrtCraftPlugin.sendMsgToOp("Nie zaladowano sterownika MySQL, Zobacz konsole", 1);
		} catch(InstantiationException ex) {
			ex.printStackTrace();
			DyrtCraftPlugin.sendMsgToOp("Nie zaladowano sterownika MySQL, Zobacz konsole", 1);
		}
		
		try {
			connect1 = DriverManager.getConnection("jdbc:mysql://localhost:3306", login, password);
			DyrtCraftPlugin.sendMsgToOp("Polaczono z baza danych MySQL", 1);
		} catch(SQLException ex) {
			ex.printStackTrace();
			DyrtCraftPlugin.sendMsgToOp("Nie polaczono z MySQL, zobacz konsole", 1);
		}
	}
	
}
