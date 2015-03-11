package com.vmalyshkin.admin_news.db;

import com.vmalyshkin.admin_news.frameAdmin.panels.PanelArchiveNews;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsDao implements NewsDao {
	private Connection mysqlConnect;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public MySqlNewsDao(MySqlConnectionProvider provider) {
		this.mysqlConnect = provider.createNewConnection();
	}

	@Override
	public void insert(String insert2) {
		try {
			mysqlConnect.createStatement().execute("INSERT INTO newsdb (date_time, news) VALUES ('" + getCurrentDate() + "' , '" + insert2 + "')");
		} catch (SQLException e) {
			System.out.println("ERROR MySqlNewsDao Adding a new news database failed!!!");
		}
	}

	@Override
	public List<String> select() {
		PanelArchiveNews.archiveNewsTextArea.setText("");
		List<String> resultNews = new ArrayList<>();
		try {
			ResultSet resultSet = mysqlConnect.createStatement().executeQuery("SELECT * FROM newsdb ORDER BY newsdb.id ASC");
			while (resultSet.next()) {
				String feed = simpleDateFormat.format(resultSet.getTimestamp("date_time")) + "\n - " + resultSet.getString("news");
				resultNews.add(feed);
			}
		} catch (SQLException e) {
			System.out.println("ERROR MySqlNewsDao Get news from the database failed!!!");
		}
		return resultNews;
	}

	@Override
	public String selectLastInsert() {
		String result = null;

		try {
			ResultSet resultSetClient = mysqlConnect.createStatement().executeQuery("SELECT * FROM newsdb ORDER BY id DESC LIMIT 1");
//			while (resultSetClient.next()) {//
			if (resultSetClient.next()) {
				result = simpleDateFormat.format(resultSetClient.getTimestamp("date_time")) + "\n - " + resultSetClient.getString("news");
			}
		} catch (SQLException e) {
			System.out.println("ERROR MySqlNewsDao Get the latest news from the database failed!!!");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void close_DB() {
		try {
			mysqlConnect.close();
		} catch (SQLException e) {
			System.out.println("ERROR MySqlNewsDao Closing database failed!!!");
		}
	}

	@Override
	public java.sql.Timestamp getCurrentDate() {
		ZoneId zoneId = ZoneId.of("Europe/Minsk");
		LocalDateTime localTime = LocalDateTime.now(zoneId);
		return Timestamp.valueOf(localTime);
	}
}
