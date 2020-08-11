package com.niton.fileorganizer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.niton.fileorganizer.controller.OrganizerController;
import com.niton.media.filesystem.Directory;

public class StartParameterScreen extends JPanel {
	private JTextField targetInputField;
	private OrganizerController controller;
	private JButton srcRemoveButton;
	private JButton addSourceBtn;
	private JButton browseTargetButton;
	private JButton startBtn;
	private JList<Directory> sourceList;

	/**
	 * Create the panel.
	 * @param controller 
	 */
	public StartParameterScreen(OrganizerController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel sourceFilePanel = new JPanel();
		GridBagConstraints gbc_sourceFilePanel = new GridBagConstraints();
		gbc_sourceFilePanel.gridheight = 2;
		gbc_sourceFilePanel.insets = new Insets(10, 5, 10, 5);
		gbc_sourceFilePanel.fill = GridBagConstraints.BOTH;
		gbc_sourceFilePanel.gridx = 0;
		gbc_sourceFilePanel.gridy = 0;
		add(sourceFilePanel, gbc_sourceFilePanel);
		GridBagLayout gbl_sourceFilePanel = new GridBagLayout();
		gbl_sourceFilePanel.columnWidths = new int[]{0, 0};
		gbl_sourceFilePanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_sourceFilePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_sourceFilePanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		sourceFilePanel.setLayout(gbl_sourceFilePanel);
		
		JLabel srcFolderLabel = new JLabel("Files to Organize");
		GridBagConstraints gbc_srcFolderLabel = new GridBagConstraints();
		gbc_srcFolderLabel.insets = new Insets(0, 0, 5, 0);
		gbc_srcFolderLabel.gridx = 0;
		gbc_srcFolderLabel.gridy = 0;
		sourceFilePanel.add(srcFolderLabel, gbc_srcFolderLabel);
		
		JPanel srcFolderButtonPane = new JPanel();
		GridBagConstraints gbc_srcFolderButtonPane = new GridBagConstraints();
		gbc_srcFolderButtonPane.fill = GridBagConstraints.BOTH;
		gbc_srcFolderButtonPane.insets = new Insets(0, 0, 5, 0);
		gbc_srcFolderButtonPane.gridx = 0;
		gbc_srcFolderButtonPane.gridy = 1;
		sourceFilePanel.add(srcFolderButtonPane, gbc_srcFolderButtonPane);
		GridBagLayout gbl_srcFolderButtonPane = new GridBagLayout();
		gbl_srcFolderButtonPane.columnWidths = new int[]{0, 0, 0};
		gbl_srcFolderButtonPane.rowHeights = new int[]{23, 0};
		gbl_srcFolderButtonPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_srcFolderButtonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		srcFolderButtonPane.setLayout(gbl_srcFolderButtonPane);
		
		srcRemoveButton = new JButton("Remove");
		GridBagConstraints gbc_srcRemoveButton = new GridBagConstraints();
		gbc_srcRemoveButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_srcRemoveButton.insets = new Insets(0, 0, 0, 5);
		gbc_srcRemoveButton.gridx = 0;
		gbc_srcRemoveButton.gridy = 0;
		srcFolderButtonPane.add(srcRemoveButton, gbc_srcRemoveButton);
		
		addSourceBtn = new JButton("Add");
		GridBagConstraints gbc_addSourceBtn = new GridBagConstraints();
		gbc_addSourceBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addSourceBtn.gridx = 1;
		gbc_addSourceBtn.gridy = 0;
		srcFolderButtonPane.add(addSourceBtn, gbc_addSourceBtn);
		
		JScrollPane sourceListScroler = new JScrollPane();
		GridBagConstraints gbc_sourceListScroler = new GridBagConstraints();
		gbc_sourceListScroler.fill = GridBagConstraints.BOTH;
		gbc_sourceListScroler.gridx = 0;
		gbc_sourceListScroler.gridy = 2;
		sourceFilePanel.add(sourceListScroler, gbc_sourceListScroler);
		
		sourceList = new JList();
		sourceListScroler.setViewportView(sourceList);
		
		JPanel targetChooser = new JPanel();
		GridBagConstraints gbc_targetChooser = new GridBagConstraints();
		gbc_targetChooser.insets = new Insets(10, 5, 10, 10);
		gbc_targetChooser.fill = GridBagConstraints.BOTH;
		gbc_targetChooser.gridx = 1;
		gbc_targetChooser.gridy = 0;
		add(targetChooser, gbc_targetChooser);
		GridBagLayout gbl_targetChooser = new GridBagLayout();
		gbl_targetChooser.columnWidths = new int[]{0, 0};
		gbl_targetChooser.rowHeights = new int[]{0, 0, 0};
		gbl_targetChooser.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_targetChooser.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		targetChooser.setLayout(gbl_targetChooser);
		
		JLabel targetLabel = new JLabel("New FileSystem location");
		GridBagConstraints gbc_targetLabel = new GridBagConstraints();
		gbc_targetLabel.anchor = GridBagConstraints.WEST;
		gbc_targetLabel.insets = new Insets(0, 0, 5, 0);
		gbc_targetLabel.gridx = 0;
		gbc_targetLabel.gridy = 0;
		targetChooser.add(targetLabel, gbc_targetLabel);
		
		JPanel targetChoosePanel = new JPanel();
		GridBagConstraints gbc_targetChoosePanel = new GridBagConstraints();
		gbc_targetChoosePanel.anchor = GridBagConstraints.NORTH;
		gbc_targetChoosePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_targetChoosePanel.gridx = 0;
		gbc_targetChoosePanel.gridy = 1;
		targetChooser.add(targetChoosePanel, gbc_targetChoosePanel);
		GridBagLayout gbl_targetChoosePanel = new GridBagLayout();
		gbl_targetChoosePanel.columnWidths = new int[]{0, 0, 0};
		gbl_targetChoosePanel.rowHeights = new int[]{0, 0};
		gbl_targetChoosePanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_targetChoosePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		targetChoosePanel.setLayout(gbl_targetChoosePanel);
		
		targetInputField = new JTextField();
		targetInputField.setEditable(false);
		GridBagConstraints gbc_targetInputField = new GridBagConstraints();
		gbc_targetInputField.insets = new Insets(0, 0, 0, 5);
		gbc_targetInputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_targetInputField.gridx = 0;
		gbc_targetInputField.gridy = 0;
		targetChoosePanel.add(targetInputField, gbc_targetInputField);
		targetInputField.setColumns(10);
		
		browseTargetButton = new JButton("Set Location");
		GridBagConstraints gbc_browseTargetButton = new GridBagConstraints();
		gbc_browseTargetButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_browseTargetButton.gridx = 1;
		gbc_browseTargetButton.gridy = 0;
		targetChoosePanel.add(browseTargetButton, gbc_browseTargetButton);
		
		startBtn = new JButton("Start");
		GridBagConstraints gbc_startBtn = new GridBagConstraints();
		gbc_startBtn.anchor = GridBagConstraints.EAST;
		gbc_startBtn.insets = new Insets(0, 5, 10, 10);
		gbc_startBtn.gridx = 1;
		gbc_startBtn.gridy = 1;
		add(startBtn, gbc_startBtn);
		registerListeners();
	}

	private void registerListeners() {
		addSourceBtn.addActionListener(controller::addSourceFolder);
		srcRemoveButton.addActionListener(controller::removeSourceFolder);
		browseTargetButton.addActionListener(controller::browseTargetLocation);
		startBtn.addActionListener(controller::submitSources);
	}

	public void setSourceList(List<Directory> sources) {
		sourceList.setListData(sources.toArray(new Directory[sources.size()]));
		sourceList.repaint();
	}

	public Directory getSelectedSource() {
		return sourceList.getSelectedValue();
	}

	public void setTargetText(String string) {
		targetInputField.setText(string);
	}
	

}
