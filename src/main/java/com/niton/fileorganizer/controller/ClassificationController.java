package com.niton.fileorganizer.controller;

import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.gui.components.classification.ClassificationPanel;
import com.niton.fileorganizer.gui.popups.CreateClassificationDialog;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationManager;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.types.DateType;
import com.niton.fileorganizer.model.classification.types.ExtensionType;
import com.niton.fileorganizer.model.classification.types.UserChoiceType;
import com.niton.fileorganizer.model.classification.types.UserInputType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class ClassificationController implements Serializable {
    private final ClassificationManager manager = new ClassificationManager();
    private transient ClassificationPanel UI;


    public ClassificationController() {
        registerClassificationTypes();
        buildUI();
    }

    private void registerClassificationTypes() {
        manager.addClassificationType(new DateType());
        manager.addClassificationType(new UserInputType());
        manager.addClassificationType(new UserChoiceType());
        manager.addClassificationType(new ExtensionType());
    }

    public void openAddingDialog(ActionEvent actionEvent) {
        new Thread(() -> {
            CreateClassificationDialog dialog = CreateClassificationDialog.showDialog(manager);
            boolean accept = dialog.awaitUser();
            dialog.dispose();
            if (accept) {
                ClassificationType<?> type = manager.getClassificationType(dialog.getTypeInput());
                String name = dialog.getNameInput();
                Classification<?> classification = type.createNewClassification(name);
                addClassification(classification);
            }
        }
        ).start();
    }

    public void addClassification(Classification classification) {
        if(manager.getClassification(classification.getName()) != null){
            JOptionPane.showMessageDialog(UI.getRootPane(), "The name is already in use","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        manager.addClassification(classification);
        updateList();
    }

    public void removeSelectedClassification(ActionEvent actionEvent) {
        Classification toRemove = manager.getClassification(UI.getSelectedClassification());
        manager.removeClassification(toRemove);
        updateList();
    }
    public void updateList(){
        UI.updateClassificationList(manager.getClassifications());
    }
    public void selectClassification(ListSelectionEvent listSelectionEvent) {
        Classification classification = manager.getClassification(UI.getSelectedClassification());
        if (classification == null)
            return;
        ClassificationEditorController<?> controller = classification.createController(this);
        if(controller == null)
            controller = new ClassificationEditorController(classification,this);
        UI.showEditor(controller.getRootUI());
        //This is kind of dirty, but i hope java cleans it up
    }

    public JPanel getUI() {
        return UI;
    }

    public void buildUI() {
        UI = new ClassificationPanel(this);
        updateList();
    }

    public ClassificationManager getManager() {
        return manager;
    }
}
