package com.niton.fileorganizer.gui.components.organisation;

import com.niton.fileorganizer.controller.OrganizerController;
import com.niton.fileorganizer.gui.components.QualityLabel;
import com.niton.media.filesystem.NFile;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class ClassificationCard extends JPanel {
	private final OrganizerController controller;
	private JPanel optionPanel;
	private JTextField textField;
	private JLabel classificationName;
	public ClassificationCard(OrganizerController controller) {
		this.controller = controller;
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{3.0, 5.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		classificationName = new JLabel("New label");
		classificationName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_classificationName = new GridBagConstraints();
		gbc_classificationName.gridwidth = 2;
		gbc_classificationName.insets = new Insets(5, 0, 1, 5);
		gbc_classificationName.gridx = 0;
		gbc_classificationName.gridy = 0;
		add(classificationName, gbc_classificationName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(5, 5, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(5, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		optionPanel = new JPanel();
		scrollPane.setViewportView(optionPanel);

		registerListeners();
	}

	private void registerListeners() {
		textField.addActionListener((e)->controller.pollValueFromUI(classificationName.getText()));
	}

	public void setClassificationName(String key) {
		classificationName.setText(key);
	}

	public void setValue(String value) {
		textField.setText(value);
		textField.repaint();
	}

	public void showQualities(Map<String, Double> likelinessMap) {
		optionPanel.removeAll();
		for (Map.Entry<String, Double> entry : likelinessMap.entrySet()) {
			QualityLabel label = new QualityLabel(entry.getKey(),entry.getValue());
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					textField.setText(entry.getKey());
					controller.pollValueFromUI(classificationName.getText());
				}
			});
			optionPanel.add(label);
		}
	}

	public String getValue() {
		return textField.getText();
	}
}
