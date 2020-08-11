package com.niton.fileorganizer.controller;

import java.awt.event.ActionEvent;

import com.formdev.flatlaf.FlatDarkLaf;
import com.niton.fileorganizer.gui.MainGui;
import com.niton.fileorganizer.model.classification.Classification;


public class GuiController {
	private MainGui UI;
	private OrganizerController organizerController;
	private ClassificationController classificationController;

	public GuiController() {
		FlatDarkLaf.install();
		UI = new MainGui(this);
		organizerController = new OrganizerController();
		classificationController = new ClassificationController();
	}

	public void openGui() {
		UI.open();
	}
	public void openOrganizeView(ActionEvent e) {
		UI.showView(organizerController.getUI());
	}

    public void openClassificationView(ActionEvent actionEvent) {
		UI.showView(classificationController.getUI());
    }
}
