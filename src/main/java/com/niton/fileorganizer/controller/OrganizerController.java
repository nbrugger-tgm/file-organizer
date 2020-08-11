package com.niton.fileorganizer.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import com.niton.fileorganizer.gui.DirectoryChooser;
import com.niton.fileorganizer.gui.OrganizePanel;
import com.niton.fileorganizer.model.OrganizeSession;
import com.niton.media.filesystem.Directory;

public class OrganizerController {
	private OrganizeSession session = new OrganizeSession();
	private OrganizePanel UI;
	private static DirectoryChooser explorer = new DirectoryChooser();
	
	public OrganizerController() {
		UI = new OrganizePanel(this);
	}
	
	public OrganizePanel getUI() {
		return UI;
	}

	public void addSourceFolder(ActionEvent e) {
		List<Directory> dirs = explorer.getSelections(UI.getRootPane());
		if(dirs != null) {
			session.getLocations().addSources(dirs);
			updateSourceListDisplay();
		}
	}

	private void updateSourceListDisplay() {
		UI.showList(session.getLocations().getSources());
	}

	public void removeSourceFolder(ActionEvent e) {
		Directory dir = UI.getSelectedSource();
		if(dir != null) {
			session.getLocations().removeSource(dir);
			updateSourceListDisplay();
		}
	}

	public void setTargetLocation(Directory e) {
		session.getLocations().setTarget(e);
		UI.setTargetLocation(e.toString());
	}

	public void browseTargetLocation(ActionEvent e) {
		Directory d = explorer.getSelection(UI.getRootPane());
		if(d != null)
			setTargetLocation(d);
	}

	public void submitSources(ActionEvent e) {
	}
}
