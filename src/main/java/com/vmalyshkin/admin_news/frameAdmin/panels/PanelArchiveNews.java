package com.vmalyshkin.admin_news.frameAdmin.panels;

import com.vmalyshkin.admin_news.db.ConnectionProvider;
import com.vmalyshkin.admin_news.db.MySqlConnectionProvider;
import com.vmalyshkin.admin_news.db.MySqlNewsDao;
import com.vmalyshkin.admin_news.db.NewsDao;
import com.vmalyshkin.admin_news.frameAdmin.panels.configPanel.ConfigurationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.SQLException;

public class PanelArchiveNews extends JPanel implements ConnectionProvider {

	private static final String LABEL_TEXT = "  Архив новостей";
	public static JTextArea archiveNewsTextArea = new JTextArea();

	public PanelArchiveNews() {
		setLayout(new GridBagLayout());
		fillingArchiveNewsTextArea();
		configureLabel();
		configureArchiveNewsTextArea();
	}

	private void configureLabel() {
		JLabel archiveNewsLabel = new JLabel(LABEL_TEXT);
		archiveNewsLabel.setFont(ConfigurationPanel.LABEL_FONT);
		archiveNewsLabel.setPreferredSize(new Dimension(ConfigurationPanel.WIDTH, ConfigurationPanel.LABEL_HEIGHT));
		archiveNewsLabel.setBorder(ConfigurationPanel.BORDER);
		add(archiveNewsLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(15, 15, -1, 15), 0, 0
				)
		);
	}

	private void configureArchiveNewsTextArea() {
		archiveNewsTextArea.setEditable(false);
		archiveNewsTextArea.setLineWrap(true);
		JScrollPane scrollPaneArchiveNewsList = new JScrollPane(archiveNewsTextArea);
		scrollPaneArchiveNewsList.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
		scrollPaneArchiveNewsList.setPreferredSize(new Dimension(ConfigurationPanel.WIDTH, ConfigurationPanel.TEXT_HEIGHT));
		scrollPaneArchiveNewsList.setBorder(ConfigurationPanel.BORDER);
		add(scrollPaneArchiveNewsList,
				new GridBagConstraints(
						0, 1, 1, 1, 1, 1,
						GridBagConstraints.NORTH,
						GridBagConstraints.BOTH,
						new Insets(-1, 15, 0, 15), 0, 0
				)
		);
	}

	private void fillingArchiveNewsTextArea() {
		NewsDao newsDao = new MySqlNewsDao(new MySqlConnectionProvider(HOST, USER, PASSWORD, NAME_DB));
		try {
			for (String archiveNews : newsDao.select()) {
				appendArchiveNewsTextArea(archiveNews);
			}
		} catch (SQLException e) {
			System.out.println("ERROR PanelArchiveNews - \"for (String archiveNews : newsDao.select()) {\"");
		}
		newsDao.close_DB();
	}

	public static void appendArchiveNewsTextArea(String archiveNews) {
		archiveNewsTextArea.append(archiveNews + "\n \n");
	}
}
