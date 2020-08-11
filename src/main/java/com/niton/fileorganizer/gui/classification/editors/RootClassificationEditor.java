package com.niton.fileorganizer.gui.classification.editors;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.GridLayout;

public class RootClassificationEditor extends JPanel {
	private JTextField textField;
	private JTextField nameEditer;
	private JPanel editorPane;

	/**
	 * Create the panel.
	 */
	public RootClassificationEditor() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		add(lblType, gbc_lblType);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);
		
		nameEditer = new JTextField();
		GridBagConstraints gbc_nameEditer = new GridBagConstraints();
		gbc_nameEditer.insets = new Insets(0, 0, 5, 0);
		gbc_nameEditer.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameEditer.gridx = 1;
		gbc_nameEditer.gridy = 1;
		add(nameEditer, gbc_nameEditer);
		nameEditer.setColumns(10);
		
		editorPane = new JPanel();
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.gridwidth = 2;
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 2;
		add(editorPane, gbc_editorPane);
		editorPane.setLayout(new GridLayout(1, 0, 0, 0));

	}

}
