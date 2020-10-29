package com.niton.fileorganizer.controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.niton.fileorganizer.model.classification.ClassificationManager;

import java.io.Serializable;

public class MainController  implements Serializable {
	public GuiController guiController;
	public void startApp() {
		guiController = new GuiController(this);
		guiController.openGui();
	}

	public void reopenGui() {
		guiController.buildUI();
		guiController.openGui();
	}

}
