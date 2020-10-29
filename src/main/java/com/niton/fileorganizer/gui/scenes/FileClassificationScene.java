package com.niton.fileorganizer.gui.scenes;

import com.niton.fileorganizer.controller.OrganizerController;
import com.niton.fileorganizer.gui.components.PathDisplay;
import com.niton.fileorganizer.gui.components.organisation.ClassificationCard;
import com.niton.media.filesystem.NFile;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class FileClassificationScene extends JPanel {
	private final OrganizerController controller;
	private PathDisplay pathDisplay;
	private JPanel classingPanel;
	private JButton submitBtn;
	private JButton btnAbort;
	public FileClassificationScene(OrganizerController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblFileClassification = new JLabel("File classification");
		lblFileClassification.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_lblFileClassification = new GridBagConstraints();
		gbc_lblFileClassification.gridwidth = 2;
		gbc_lblFileClassification.insets = new Insets(5, 5, 5, 0);
		gbc_lblFileClassification.gridx = 0;
		gbc_lblFileClassification.gridy = 0;
		add(lblFileClassification, gbc_lblFileClassification);
		
		pathDisplay = new PathDisplay();
		GridBagConstraints gbc_pathDisplay = new GridBagConstraints();
		gbc_pathDisplay.insets = new Insets(0, 5, 5, 5);
		gbc_pathDisplay.fill = GridBagConstraints.BOTH;
		gbc_pathDisplay.gridx = 0;
		gbc_pathDisplay.gridy = 1;
		add(pathDisplay, gbc_pathDisplay);
		
		submitBtn = new JButton("Submit");
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.insets = new Insets(0, 0, 5, 0);
		gbc_submitBtn.gridx = 1;
		gbc_submitBtn.gridy = 1;
		add(submitBtn, gbc_submitBtn);
		
		classingPanel = new JPanel();
		GridBagConstraints gbc_classingPanel = new GridBagConstraints();
		gbc_classingPanel.gridwidth = 2;
		gbc_classingPanel.insets = new Insets(0, 5, 5, 0);
		gbc_classingPanel.fill = GridBagConstraints.BOTH;
		gbc_classingPanel.gridx = 0;
		gbc_classingPanel.gridy = 2;
		add(classingPanel, gbc_classingPanel);
		
		btnAbort = new JButton("Abort");
		GridBagConstraints gbc_btnAbort = new GridBagConstraints();
		gbc_btnAbort.anchor = GridBagConstraints.WEST;
		gbc_btnAbort.insets = new Insets(0, 5, 5, 5);
		gbc_btnAbort.gridx = 0;
		gbc_btnAbort.gridy = 3;
		add(btnAbort, gbc_btnAbort);

		registerListeners();
	}

	private void registerListeners() {
		btnAbort.addActionListener(controller::quitClassification);
		submitBtn.addActionListener((e)->{
			synchronized (waiter){
				waiter.notifyAll();
			}
		});
	}

	public void showFile(NFile p) {
		pathDisplay.setPath(p.toFile());
	}

	public void showFileClassifications(Map<String, String> stateMap,Map<String,Map<String,Double>> likelinessMap) {
		classingPanel.removeAll();
		for (Map.Entry<String, String> entry : stateMap.entrySet()) {
			ClassificationCard card = new ClassificationCard(controller);
			card.setName(entry.getKey());
			card.setClassificationName(entry.getKey());
			card.setValue(entry.getValue());
			card.showQualities(likelinessMap.get(entry.getKey()));
			classingPanel.add(card);
		}
		classingPanel.repaint();
		classingPanel.revalidate();
	}
	private final Object waiter = new Object();

	public void awaitSubmit() {
		synchronized (waiter){
			try {
				waiter.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public String getValue(String classifier) {
		String val;
		val = Arrays.stream(classingPanel.getComponents()).map(e->
			e.getName().equals(classifier)? ((ClassificationCard)e).getValue():null
		).filter(Objects::nonNull).findFirst().get();
		return val;
	}
}
