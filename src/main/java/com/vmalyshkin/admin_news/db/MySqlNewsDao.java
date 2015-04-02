package com.vmalyshkin.admin_news.db;

import com.vmalyshkin.admin_news.frameAdmin.panels.PanelArchiveNews;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class MySqlNewsDao implements NewsDao {
	private Connection connection;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public MySqlNewsDao(MySqlConnectionProvider provider) {
		this.connection = provider.createNewConnection();
	}

	@Override
	public void addNews(String news) {
		try {
			connection.prepareStatement("INSERT INTO newsdb (date_time, news) VALUES ('" + getCurrentDate() + "' , '" + news + "')").execute();
		} catch (SQLException e) {
			System.out.println("ERROR MySqlNewsDao Adding a new news database failed!!!");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("ERROR MySqlNewsDao Closing database failed!!!");
			}
		}

	}

	@Override
	public List<String> listNews() {
		PanelArchiveNews.archiveNewsTextArea.setText("");
		List<String> listNews = new ArrayList<>();
		try {
			ResultSet resultSet = connection.prepareStatement("SELECT * FROM newsdb ORDER BY newsdb.id ASC").executeQuery();
			while (resultSet.next()) {
				String feed = simpleDateFormat.format(resultSet.getTimestamp("date_time")) + "\n - " + resultSet.getString("news");
				listNews.add(feed);
			}
		} catch (SQLException e) {
			System.out.println("ERROR MySqlNewsDao Get news from the database failed!!!");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("ERROR MySqlNewsDao Closing database failed!!!");
			}
		}
		return listNews;
	}

	@Override
	public String getLastNote() {
		String result = null;

		try {
			ResultSet resultSet = connection.prepareStatement("SELECT * FROM newsdb ORDER BY id DESC LIMIT 1").executeQuery();
			if (resultSet.next()) {
				result = simpleDateFormat.format(resultSet.getTimestamp("date_time")) + "\n - " + resultSet.getString("news");
			}
		} catch (SQLException e) {
			System.out.println("ERROR MySqlNewsDao Get the latest news from the database failed!!!");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("ERROR MySqlNewsDao Closing database failed!!!");
			}
		}
		return result;
	}


	@Override
	public java.sql.Timestamp getCurrentDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Minsk"), Locale.ENGLISH);
		System.out.println(calendar.getTime());
		Timestamp timestamp = new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("Europe/Minsk")).getTimeInMillis());
		return timestamp;
	}
}
