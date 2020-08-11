package com.niton.fileorganizer.gui;

import javax.swing.JPanel;

import com.niton.fileorganizer.controller.GuiController;
import com.niton.fileorganizer.controller.OrganizerController;
import com.niton.media.filesystem.Directory;

import java.awt.GridLayout;
import java.util.List;

public class OrganizePanel extends JPanel {
	private StartParameterScreen startScene;
	private OrganizerController controller;
	/**
	 * Create the panel.
	 * @param controller 
	 */
	public OrganizePanel(OrganizerController controller) {
		this.controller = controller;
		setLayout(new GridLayout(1, 0, 0, 0));
		createSubScenes();
		setStartScene();
	}
	private void setStartScene() {
		removeAll();
		add(startScene);
		validate();
	}
	private void createSubScenes() {
		startScene = new StartParameterScreen(controller);
	}
	public void showList(List<Directory> sources) {
		startScene.setSourceList(sources);
	}
	public Directory getSelectedSource() {
		return startScene.getSelectedSource();
	}
	public void setTargetLocation(String string) {
		startScene.setTargetText(string);
	}

}
