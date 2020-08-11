package com.niton.fileorganizer.gui.classification.editors.specific;

import com.niton.fileorganizer.controller.classification.specific.DateClassificationEditorController;
import com.niton.fileorganizer.gui.classification.editors.SpecificClassifierEditor;
import com.niton.fileorganizer.model.classification.implementation.DateClassification;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DateClassificationEditor extends SpecificClassifierEditor {
	private JTextField patternInput;
	private DateClassificationEditorController controller;
	private JComboBox<DateClassification.SourceAttribute> sourceChooser;

	public DateClassificationEditor(DateClassificationEditorController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSourceOfThe = new JLabel("Source of the Date");
		GridBagConstraints gbc_lblSourceOfThe = new GridBagConstraints();
		gbc_lblSourceOfThe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceOfThe.anchor = GridBagConstraints.EAST;
		gbc_lblSourceOfThe.gridx = 0;
		gbc_lblSourceOfThe.gridy = 0;
		add(lblSourceOfThe, gbc_lblSourceOfThe);
		
		sourceChooser = new JComboBox();
		GridBagConstraints gbc_sourceChooser = new GridBagConstraints();
		gbc_sourceChooser.insets = new Insets(0, 0, 5, 0);
		gbc_sourceChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_sourceChooser.gridx = 1;
		gbc_sourceChooser.gridy = 0;
		add(sourceChooser, gbc_sourceChooser);
		
		JLabel lblOutput = new JLabel("Output");
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.anchor = GridBagConstraints.EAST;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 0;
		gbc_lblOutput.gridy = 1;
		add(lblOutput, gbc_lblOutput);
		
		patternInput = new JTextField();
		
				GridBagConstraints gbc_patterinInput = new GridBagConstraints();
				gbc_patterinInput.insets = new Insets(0, 0, 5, 0);
				gbc_patterinInput.fill = GridBagConstraints.HORIZONTAL;
				gbc_patterinInput.gridx = 1;
				gbc_patterinInput.gridy = 1;
				add(patternInput, gbc_patterinInput);
				patternInput.setColumns(10);
		
		JTextPane txtpnVariables = new JTextPane();
		txtpnVariables.setEditable(false);
		txtpnVariables.setBackground(UIManager.getColor("Panel.background"));
		txtpnVariables.setText("Variables:\r\nYYYY - Full year (eg 2021)\r\nYY - Century based (eg. 13 for 2013)\r\nMM - Month in number (04\r\nDD - Day of the month");
		GridBagConstraints gbc_txtpnVariables = new GridBagConstraints();
		gbc_txtpnVariables.anchor = GridBagConstraints.NORTH;
		gbc_txtpnVariables.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnVariables.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnVariables.gridx = 1;
		gbc_txtpnVariables.gridy = 2;
		add(txtpnVariables, gbc_txtpnVariables);
		registerListeners();
	}

	private void registerListeners() {
		sourceChooser.addActionListener(controller::pollSourceFromGui);
		patternInput.addActionListener(controller::pollPatternFromGui);
	}

	public DateClassification.SourceAttribute getSelectedSource() {
		return sourceChooser.getItemAt(sourceChooser.getSelectedIndex());
	}

	public void showSourceOptions(DateClassification.SourceAttribute[] values) {
		sourceChooser.setModel(new DefaultComboBoxModel<>(values));
	}

	public String getPattern() {
		return patternInput.getText();
	}

	public void displayPattern(String pattern) {
		patternInput.setText(pattern);
	}

	public void showSource(DateClassification.SourceAttribute source) {
		sourceChooser.setSelectedItem(source);
	}
}
