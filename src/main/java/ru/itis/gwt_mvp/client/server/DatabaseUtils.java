package ru.itis.gwt_mvp.client.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

	private static final String CREATE_TABLE_IF_NOT_EXISTS_WELL = "CREATE TABLE IF NOT EXISTS WELL"
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " name VARCHAR(32) UNIQUE NOT NULL)";

	private static final String CREATE_TABLE_IF_NOT_EXISTS_EQUIPMENT = "CREATE TABLE IF NOT EXISTS EQUIPMENT"
			+ "(id INTEGER PRIMARY KEY," + " name VARCHAR(32) UNIQUE NOT NULL," + " wellId INTEGER      "
			+ "CONSTRAINT fk_well REFERENCES WELL (id))";
	private static final String DATABASE_FILE = "jdbc:sqlite:C:\\sdo_test.db";
	private static Connection c = null;

	public static void createTablesIfNotExists() {
		createWellTable();
		createEquipmentTable();
	}

	private static void createWellTable() {
		try {
			Connection connection = getConn(); 
			Statement statement = connection.createStatement();
			statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_WELL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createEquipmentTable() {
		try {
			Connection connection = getConn();
			Statement statement = connection.createStatement();
			statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_EQUIPMENT);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConn() {
		if (c == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection(DATABASE_FILE);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
}
