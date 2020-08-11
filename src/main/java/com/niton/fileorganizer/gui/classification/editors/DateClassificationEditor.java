package com.niton.fileorganizer.gui.classification.editors;

import com.niton.fileorganizer.controller.classification.DateClassificationEditorController;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class DateClassificationEditor extends JPanel {
	private JTextField patterinInput;
	public DateClassificationEditor(DateClassificationEditorController controller) {
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
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		JLabel lblOutput = new JLabel("Output");
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.gridwidth = 2;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 0);
		gbc_lblOutput.gridx = 0;
		gbc_lblOutput.gridy = 1;
		add(lblOutput, gbc_lblOutput);
		
		JTextPane txtpnVariables = new JTextPane();
		txtpnVariables.setBackground(UIManager.getColor("Panel.background"));
		txtpnVariables.setText("Variables:\r\nYYYY - Full year (eg 2021)\r\nYY - Century based (eg. 13 for 2013)\r\nMM - Month in number (04\r\nDD - Day of the month");
		GridBagConstraints gbc_txtpnVariables = new GridBagConstraints();
		gbc_txtpnVariables.anchor = GridBagConstraints.NORTH;
		gbc_txtpnVariables.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnVariables.gridwidth = 2;
		gbc_txtpnVariables.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnVariables.gridx = 0;
		gbc_txtpnVariables.gridy = 2;
		add(txtpnVariables, gbc_txtpnVariables);
		
		patterinInput = new JTextField();

		GridBagConstraints gbc_patterinInput = new GridBagConstraints();
		gbc_patterinInput.insets = new Insets(0, 0, 5, 0);
		gbc_patterinInput.gridwidth = 2;
		gbc_patterinInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_patterinInput.gridx = 0;
		gbc_patterinInput.gridy = 3;
		add(patterinInput, gbc_patterinInput);
		patterinInput.setColumns(10);
	}

}
