package com.niton.fileorganizer.controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.niton.fileorganizer.Launcher;
import com.niton.fileorganizer.gui.popups.LoadingPopup;
import com.niton.fileorganizer.gui.MainGui;

import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class GuiController implements Serializable {
    private transient MainGui UI;
    private OrganizerController organizerController;
    private ClassificationController classificationController;
    private transient LoadingPopup pop;
    private MainController superController;
    private PathBuilderController pathBuilderController;

    public GuiController(MainController superController) {
        this.superController = superController;
        FlatDarkLaf.install();
        organizerController = new OrganizerController(this);
        classificationController = new ClassificationController();
        pathBuilderController = new PathBuilderController(this);
        buildUI();
    }

    public void buildUI() {
        FlatDarkLaf.install();
        pop = new LoadingPopup();
        UI = new MainGui(this);
        classificationController.buildUI();
        organizerController.buildUI();
        pathBuilderController.buildUI();
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

    public void onWindowClose() {
        Thread t = new Thread(()->{
            pop.setVisible(true);
            pop.setTitle("Saving ...");
            new Thread(()-> {
            }).start();
            try (FileOutputStream fos = new FileOutputStream(Launcher.f)) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(superController);
                try {
                    Thread.sleep(700);
                    pop.setTitle("Done");
                    Thread.sleep(1500);
                    pop.setTitle("Bye");
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                try {
                    pop.setTitle("Saving Failed :(");
                    Thread.sleep(4000);
                    pop.setTitle("Bye");
                    Thread.sleep(2000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            } finally {
                pop.dispose();
            }
            System.exit(0);
        });
        t.start();

    }

    public void openPathBuilder(ActionEvent actionEvent) {
        UI.showView(pathBuilderController.getUI());
    }

    public ClassificationController getClassificationController() {
        return classificationController;
    }

    public PathBuilderController getPathBuilderController() {
        return pathBuilderController;
    }
}
