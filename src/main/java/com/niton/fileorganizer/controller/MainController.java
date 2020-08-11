package com.niton.fileorganizer.controller;

import com.formdev.flatlaf.FlatDarkLaf;

public class MainController {
	public GuiController guiController;
	public void startApp() {
		guiController = new GuiController();
		guiController.openGui();
	}

}
