package com.vmalyshkin.admin_news.frameAdmin.panels.listener;

import com.vmalyshkin.admin_news.db.ConnectionProvider;
import com.vmalyshkin.admin_news.db.MySqlConnectionProvider;
import com.vmalyshkin.admin_news.db.MySqlNewsDao;
import com.vmalyshkin.admin_news.db.NewsDao;
import com.vmalyshkin.admin_news.frameAdmin.panels.PanelArchiveNews;
import com.vmalyshkin.admin_news.frameAdmin.panels.PanelButtonNews;
import com.vmalyshkin.admin_news.frameAdmin.panels.PanelNewNews;
import com.vmalyshkin.admin_news.threadsSocket.ThreadsSocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ButtonActionListener implements ActionListener, ConnectionProvider {

	@Override
	public void actionPerformed(ActionEvent ae) {
		String newNews = PanelNewNews.newNewsTextField.getText().trim();
		if (!newNews.equals("")) {
			NewsDao newsDao = new MySqlNewsDao(new MySqlConnectionProvider(HOST, USER, PASSWORD, NAME_DB));
			newsDao.addNews(newNews);
			NewsDao newsDao2 = new MySqlNewsDao(new MySqlConnectionProvider(HOST, USER, PASSWORD, NAME_DB));
			try {
				for (String news : newsDao2.listNews()) {
					PanelArchiveNews.appendArchiveNewsTextArea(news);
				}
			} catch (SQLException e) {
				System.out.println("ERROR in ButtonActionListener - \"for (String feed2 : newsDao.listNews()) {\"!!!");
			}
//			System.out.println(newsDao.getLastNote());/////////////////////////убрать потом
			NewsDao newsDao3 = new MySqlNewsDao(new MySqlConnectionProvider(HOST, USER, PASSWORD, NAME_DB));
			if (ae.getSource() == PanelButtonNews.publish) {
				String str = newsDao3.getLastNote();
				new ThreadsSocket(str);
			}
			PanelNewNews.newNewsTextField.setText(null);
		} else {
			System.out.println("Внимание newNewsTextField равен null! Введите сообщение!");
		}
	}
}
