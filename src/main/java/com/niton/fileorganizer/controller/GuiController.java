package com.niton.fileorganizer.controller;

import java.awt.event.ActionEvent;

import com.formdev.flatlaf.FlatDarkLaf;
import com.niton.fileorganizer.gui.MainGui;


public class GuiController {
	private MainGui UI;
	private OrganizerController organizerController;
	
	public GuiController() {
		FlatDarkLaf.install();
		UI = new MainGui(this);
		organizerController = new OrganizerController();
	}

	public void openGui() {
		UI.open();
	}
	public void openOrganizeView(ActionEvent e) {
		UI.showView(organizerController.getUI());
	}

}
