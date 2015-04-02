package com.vmalyshkin.admin_news.db;

import java.sql.SQLException;
import java.util.List;

public interface NewsDao {
	void addNews(String insert);

	List<String> listNews() throws SQLException;

	String getLastNote();

	java.sql.Timestamp getCurrentDate();
}
