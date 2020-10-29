package com.niton.fileorganizer.gui.components.classification.editors.specific;

import com.niton.fileorganizer.controller.classification.specific.UserChoiceClassificationEditorController;
import com.niton.fileorganizer.gui.components.classification.editors.SpecificClassifierEditor;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.util.List;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserChoiceClassificationEditor extends SpecificClassifierEditor {
	private JTextField inputField;
	private JList<String> optionList;
	private JButton removeBtn;
	private UserChoiceClassificationEditorController controller;

	public UserChoiceClassificationEditor(UserChoiceClassificationEditorController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Options");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.insets = new Insets(0, 10, 10, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 10, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		optionList = new JList<>();
		scrollPane.setViewportView(optionList);
		
		inputField = new JTextField();
		GridBagConstraints gbc_inputField = new GridBagConstraints();
		gbc_inputField.insets = new Insets(0, 0, 0, 5);
		gbc_inputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputField.gridx = 0;
		gbc_inputField.gridy = 1;
		panel.add(inputField, gbc_inputField);
		inputField.setColumns(10);
		
		removeBtn = new JButton("Remove Selected entry");
		GridBagConstraints gbc_removeBtn = new GridBagConstraints();
		gbc_removeBtn.gridx = 1;
		gbc_removeBtn.gridy = 1;
		panel.add(removeBtn, gbc_removeBtn);
		registerListeners();
	}

	private void registerListeners() {
		removeBtn.addActionListener(controller::removeSelectedOption);
		inputField.addActionListener(controller::addOptionFromUI);
	}

	public void displayOptions(List<String> options) {
		optionList.setListData(options.stream().toArray(i->new String[i]));
	}

	public String getSelectedOption() {
		return optionList.getSelectedValue();
	}

	public String getOptionName() {
		return inputField.getText();
	}

	public void clearInputField() {
		inputField.setText("");
	}
}
