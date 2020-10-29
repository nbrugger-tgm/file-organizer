package com.niton.fileorganizer.gui.components.classification.editors;

import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class RootClassificationEditor<T extends ClassificationType<C>,C extends Classification<T>> extends JPanel {
	private final JTextField typeDisplay;
	private final JTextField nameEditer;
	private final JPanel editorPane;
	private final ClassificationEditorController<C> controller;

	/**
	 * Create the panel.
	 */
	public RootClassificationEditor(ClassificationEditorController<C> controller) {
		this.controller = controller;
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
		
		typeDisplay = new JTextField();
		typeDisplay.setEditable(false);
		GridBagConstraints gbc_typeDisplay = new GridBagConstraints();
		gbc_typeDisplay.insets = new Insets(0, 0, 5, 0);
		gbc_typeDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeDisplay.gridx = 1;
		gbc_typeDisplay.gridy = 0;
		add(typeDisplay, gbc_typeDisplay);
		typeDisplay.setColumns(10);
		
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

		registerListeners();

	}

	private void registerListeners() {
		nameEditer.addInputMethodListener(new InputMethodListener() {
			@Override
			public void inputMethodTextChanged(InputMethodEvent event) {
				controller.pollNameFromUI();
			}
			@Override
			public void caretPositionChanged(InputMethodEvent event) {}
		});
		nameEditer.addActionListener(e -> controller.pollNameFromUI());
	}
	public void setEditor(SpecificClassifierEditor dateClassificationEditor) {

		editorPane.removeAll();
		editorPane.add(dateClassificationEditor);
		editorPane.validate();
    }

	public String getNameInput() {
		return nameEditer.getText();
	}

	public void showName(String name) {
		nameEditer.setText(name);
	}

	public void showType(String displayName) {
		typeDisplay.setText(displayName);
	}
}
