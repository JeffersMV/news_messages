package com.vmalyshkin.client_news.frameClient.panelsClient;

import com.vmalyshkin.client_news.frameClient.panelsClient.configPanel.ConfigurationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class NewsPanel extends JPanel {
	public static final String LABEL_TEXT = "  Новости";
	public static JTextArea newsTextArea = new JTextArea();

	public NewsPanel() {
		setLayout(new GridBagLayout());
		configureLabel();
		configureNewsTextArea();
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

	private void configureNewsTextArea() {
		newsTextArea.setEditable(false);
		newsTextArea.setLineWrap(true);
		JScrollPane scrollPaneNewNewsTextField = new JScrollPane(newsTextArea);
		scrollPaneNewNewsTextField.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
		scrollPaneNewNewsTextField.setPreferredSize(new Dimension(ConfigurationPanel.WIDTH, ConfigurationPanel.TEXT_HEIGHT));
		scrollPaneNewNewsTextField.setBorder(ConfigurationPanel.BORDER);
		add(scrollPaneNewNewsTextField, new GridBagConstraints(
						0, 1, 1, 1, 1, 0.1,
						GridBagConstraints.NORTH,
						GridBagConstraints.BOTH,
						new Insets(-1, 15, 15, 15), 0, 0
				)
		);
	}

	public static void appendNewsTextArea(String news) {
		newsTextArea.append(news);
	}
}
