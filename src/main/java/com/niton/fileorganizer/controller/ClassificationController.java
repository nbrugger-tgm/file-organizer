package com.niton.fileorganizer.controller;

import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.gui.CreateClassificationDialog;
import com.niton.fileorganizer.model.classification.ClassificationManager;
import com.niton.fileorganizer.model.classification.types.DateType;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class ClassificationController {
    private Map<String, ClassificationEditorController> editorControllers = new HashMap<>();
    private ClassificationManager manager = new ClassificationManager();
    public ClassificationController() {
        registerClassificationTypes();
    }

    private void registerClassificationTypes() {
        manager.addClassificationType(new DateType());
    }

    public void openAddingDialog(ActionEvent actionEvent) {
        CreateClassificationDialog dialog = CreateClassificationDialog.showDialog();
        dialog.awaitUser();
    }

    public void removeSelectedClassification(ActionEvent actionEvent) {
    }

    public void selectClassification(ListSelectionEvent listSelectionEvent) {
    }
}
