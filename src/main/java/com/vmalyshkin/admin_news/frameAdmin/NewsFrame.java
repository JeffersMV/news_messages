package com.vmalyshkin.admin_news.frameAdmin;

import com.vmalyshkin.admin_news.frameAdmin.panels.PanelArchiveNews;
import com.vmalyshkin.admin_news.frameAdmin.panels.PanelButtonNews;
import com.vmalyshkin.admin_news.frameAdmin.panels.PanelNewNews;

import javax.swing.*;
import java.awt.*;

public class NewsFrame extends JFrame {
	private String title;
	private Dimension d;

	public NewsFrame(String title, Dimension d) throws HeadlessException {
		this.title = title;
		this.d = d;
		init();
	}

	public void init() {
		setTitle(title);
		setSize(d);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		PanelArchiveNews panelArchiveNews = new PanelArchiveNews();
		PanelNewNews panelNewNews = new PanelNewNews();
		PanelButtonNews panelButtonNews = new PanelButtonNews();

		add(panelArchiveNews, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		add(panelNewNews, new GridBagConstraints(0, 1, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		add(panelButtonNews, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.EAST,
				new Insets(0, 0, 0, 0), 0, 0));
		setVisible(true);
	}
}
