package com.vmalyshkin.client_news.frameClient;

import com.vmalyshkin.client_news.frameClient.panelsClient.NewsPanel;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {
	private String title;
	private Dimension d;
	public static NewsPanel newsPanel;

	public ClientFrame(String title, Dimension d) throws HeadlessException {
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

		newsPanel = new NewsPanel();

		add(newsPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		setVisible(true);
	}
}

