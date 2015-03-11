package com.vmalyshkin.admin_news.db;

import java.sql.SQLException;
import java.util.List;

public interface NewsDao {
	public void insert(String insert);

	public List<String> select() throws SQLException;

	public String selectLastInsert();

	public void close_DB();

	public java.sql.Timestamp getCurrentDate();
}
