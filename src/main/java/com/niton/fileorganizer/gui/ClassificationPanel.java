package com.niton.fileorganizer.gui;

import com.niton.fileorganizer.controller.ClassificationController;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;

public class ClassificationPanel extends JPanel {
	private JPanel editorPanel;
	private JList<String> classificationList;
	private JButton addButton;
	private JButton btnRemove;
	private ClassificationController controller;

	/**
	 * Create the panel.
	 */
	public ClassificationPanel(ClassificationController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{250, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 3.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel headText = new JLabel("Classifications");
		headText.setFont(new Font("Roboto Medium", Font.PLAIN, 23));
		GridBagConstraints gbc_headText = new GridBagConstraints();
		gbc_headText.gridwidth = 2;
		gbc_headText.insets = new Insets(0, 0, 5, 5);
		gbc_headText.gridx = 0;
		gbc_headText.gridy = 0;
		add(headText, gbc_headText);
		
		JPanel listingPanel = new JPanel();
		GridBagConstraints gbc_listingPanel = new GridBagConstraints();
		gbc_listingPanel.insets = new Insets(0, 0, 0, 5);
		gbc_listingPanel.fill = GridBagConstraints.BOTH;
		gbc_listingPanel.gridx = 0;
		gbc_listingPanel.gridy = 1;
		add(listingPanel, gbc_listingPanel);
		GridBagLayout gbl_listingPanel = new GridBagLayout();
		gbl_listingPanel.columnWidths = new int[]{0, 0, 0};
		gbl_listingPanel.rowHeights = new int[]{0, 0, 0};
		gbl_listingPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_listingPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		listingPanel.setLayout(gbl_listingPanel);
		
		addButton = new JButton("Add");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 0;
		listingPanel.add(addButton, gbc_addButton);
		
		btnRemove = new JButton("Remove");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemove.gridx = 1;
		gbc_btnRemove.gridy = 0;
		listingPanel.add(btnRemove, gbc_btnRemove);
		
		JScrollPane listScroller = new JScrollPane();
		GridBagConstraints gbc_listScroller = new GridBagConstraints();
		gbc_listScroller.gridwidth = 2;
		gbc_listScroller.fill = GridBagConstraints.BOTH;
		gbc_listScroller.gridx = 0;
		gbc_listScroller.gridy = 1;
		listingPanel.add(listScroller, gbc_listScroller);
		
		classificationList = new JList();
		listScroller.setViewportView(classificationList);
		
		editorPanel = new JPanel();
		GridBagConstraints gbc_editorPanel = new GridBagConstraints();
		gbc_editorPanel.fill = GridBagConstraints.BOTH;
		gbc_editorPanel.gridx = 1;
		gbc_editorPanel.gridy = 1;
		add(editorPanel, gbc_editorPanel);
		GridBagLayout gbl_editorPanel = new GridBagLayout();
		gbl_editorPanel.columnWidths = new int[]{0};
		gbl_editorPanel.rowHeights = new int[]{0};
		gbl_editorPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_editorPanel.rowWeights = new double[]{Double.MIN_VALUE};
		editorPanel.setLayout(gbl_editorPanel);
		registerListeners();
	}

	private void registerListeners() {
		addButton.addActionListener(controller::openAddingDialog);
		btnRemove.addActionListener(controller::removeSelectedClassification);
		classificationList.addListSelectionListener(controller::selectClassification);
	}

}
