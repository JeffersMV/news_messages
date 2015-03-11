package com.vmalyshkin.admin_news.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnectionProvider {

	private Connection mysqlConnect;
	private String host;
	private String root;
	private String password;
	private String nameDb;
	private String url;

	private Properties properties = new Properties();

	public MySqlConnectionProvider(String host, String root, String password, String nameDb) {
		this.host = host;
		this.root = root;
		this.password = password;
		this.nameDb = nameDb;
		initProperties();
	}

	private void initProperties() {
		url = "jdbc:mysql://" + host + "/" + nameDb;
		properties.setProperty("user", root);
		properties.setProperty("password", password);
		properties.setProperty("useUnicode", "true");
		properties.setProperty("characterEncoding", "UTF-8");
	}

	public Connection createNewConnection() {
		if (mysqlConnect == null) {
			try {
				mysqlConnect = DriverManager.getConnection(url, properties);
			} catch (SQLException e) {
				System.out.println("ERROR in MySqlConnectionProvider - \n\"mysqlConnect = DriverManager.getConnection(url, properties);\"\n!!! Подключение к базе данных не удалась!!!\n");
				JOptionPane.showMessageDialog(null, "Подключение к базе данных не удалась!!!\n" +
						"По умолчанию конфигурация: \n" +
						"HOST = \"localhost:3306\"\n" +
						"USER = \"\"\n" +
						"PASSWORD = \"\"\n" +
						"NAME_DB = \"newsdb\"");
			}
		}
		return mysqlConnect;
	}

}
