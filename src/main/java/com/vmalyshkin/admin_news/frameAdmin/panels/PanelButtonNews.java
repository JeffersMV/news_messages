package com.vmalyshkin.admin_news.frameAdmin.panels;

import com.vmalyshkin.admin_news.frameAdmin.panels.configPanel.ConfigurationPanel;
import com.vmalyshkin.admin_news.frameAdmin.panels.listener.ButtonActionListener;

import javax.swing.*;
import java.awt.*;

public class PanelButtonNews extends JPanel {
	private static final String BUTTON_TITLE = "Опубликовать";
	public static JButton publish = new JButton(BUTTON_TITLE);

	public PanelButtonNews() {
		setLayout(new GridBagLayout());
		configureButton();
	}

	public void configureButton() {
		publish.setFont(ConfigurationPanel.LABEL_FONT);
		publish.setPreferredSize(new Dimension(240, 50));
		add(publish, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(15, 15, 15, 15), 0, 0));

		publish.addActionListener(new ButtonActionListener());
	}
}
