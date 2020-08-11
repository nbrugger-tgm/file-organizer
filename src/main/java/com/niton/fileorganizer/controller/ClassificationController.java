package com.niton.fileorganizer.controller;

import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.gui.classification.ClassificationPanel;
import com.niton.fileorganizer.gui.CreateClassificationDialog;
import com.niton.fileorganizer.gui.classification.editors.RootClassificationEditor;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationManager;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.types.DateType;
import com.niton.fileorganizer.model.classification.types.UserInputType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class ClassificationController {
    private ClassificationManager manager = new ClassificationManager();
    private ClassificationPanel UI;


    public ClassificationController() {
        registerClassificationTypes();
        UI = new ClassificationPanel(this);
    }

    private void registerClassificationTypes() {
        manager.addClassificationType(new DateType());
        manager.addClassificationType(new UserInputType());
    }

    public void openAddingDialog(ActionEvent actionEvent) {
        new Thread(() -> {
            CreateClassificationDialog dialog = CreateClassificationDialog.showDialog(manager);
            boolean accept = dialog.awaitUser();
            dialog.dispose();
            if (accept) {
                ClassificationType type = manager.getClassificationType(dialog.getTypeInput());
                String name = dialog.getNameInput();
                Classification classification = type.createNewClassification(name);
                addClassification(classification);
            }
        }
        ).start();
    }

    public void addClassification(Classification classification) {
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
        ClassificationEditorController<?> controller = classification.createController(this);
        if(controller == null)
            controller = new ClassificationEditorController(classification,this);
        UI.showEditor(controller.getRootUI());
        //This is kind of dirty, but i hope java cleans it up
    }

    public JPanel getUI() {
        return UI;
    }
}
