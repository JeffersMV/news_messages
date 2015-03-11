package com.vmalyshkin.admin_news.frameAdmin.panels;

import com.vmalyshkin.admin_news.frameAdmin.panels.configPanel.ConfigurationPanel;

import javax.swing.*;
import java.awt.*;

public class PanelNewNews extends JPanel {
	public static final String LABEL_TEXT = "  Архив новостей";
	public static JTextPane newNewsTextField = new JTextPane();

	public PanelNewNews() {
		setLayout(new GridBagLayout());
		configureLabel();
		configureNewNewsTextField();
	}

	private void configureLabel() {
		JLabel newNewsLabel = new JLabel(LABEL_TEXT);
		newNewsLabel.setPreferredSize(new Dimension(ConfigurationPanel.WIDTH, ConfigurationPanel.LABEL_HEIGHT));
		newNewsLabel.setBorder(ConfigurationPanel.BORDER);
		add(newNewsLabel, new GridBagConstraints(
						0, 0, 1, 1, 1, 0,
						GridBagConstraints.NORTH,
						GridBagConstraints.BOTH,
						new Insets(15, 15, -1, 15), 0, 0
				)
		);
	}

	private void configureNewNewsTextField() {
		JScrollPane scrollPaneNewNewsTextField = new JScrollPane(newNewsTextField);
		scrollPaneNewNewsTextField.setPreferredSize(new Dimension(ConfigurationPanel.WIDTH, ConfigurationPanel.TEXT_HEIGHT));
		scrollPaneNewNewsTextField.setBorder(ConfigurationPanel.BORDER);
		add(scrollPaneNewNewsTextField, new GridBagConstraints(
						0, 1, 1, 1, 1, 0.1,
						GridBagConstraints.NORTH,
						GridBagConstraints.BOTH,
						new Insets(-1, 15, 0, 15), 0, 0
				)
		);
	}
}
