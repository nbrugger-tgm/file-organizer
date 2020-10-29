package com.niton.fileorganizer.gui.scenes;

import javax.swing.JPanel;

import com.niton.fileorganizer.controller.OrganizerController;
import com.niton.media.filesystem.Directory;
import com.niton.media.filesystem.NFile;

import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

public class OrganizePanel extends JPanel {
	private StartParameterScreen startScene;
	private OrganizerController controller;
	private FileClassificationScene classificationScene;

	/**
	 * Create the panel.
	 * @param controller 
	 */
	public OrganizePanel(OrganizerController controller) {
		this.controller = controller;
		setLayout(new GridLayout(1, 0, 0, 0));
		createSubScenes();
		showStartScene();
	}
	public void showStartScene() {
		removeAll();
		add(startScene);
		validate();
	}
	private void createSubScenes() {
		startScene = new StartParameterScreen(controller);
		classificationScene = new FileClassificationScene(controller);
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

	public void showClassificationScene() {
		removeAll();
		add(classificationScene);
		revalidate();
	}

	public void displayFile(NFile p) {
		classificationScene.showFile(p);
	}

	public void displayFileClassification(Map<String, String> stateMap, Map<String, Map<String, Double>> likelinessMap) {
		classificationScene.showFileClassifications(stateMap,likelinessMap);
	}

	public void awaitClassification() {
		classificationScene.awaitSubmit();
	}

	public String getValue(String classifier) {
		return classificationScene.getValue(classifier);
	}
}
