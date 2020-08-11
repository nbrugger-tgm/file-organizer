package com.niton.fileorganizer.controller.classification;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.gui.classification.editors.RootClassificationEditor;
import com.niton.fileorganizer.model.classification.Classification;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;

public class ClassificationEditorController<C extends Classification> {
    private RootClassificationEditor rootUI;
    protected C classification;
    private ClassificationController superController;

    public ClassificationEditorController(C classification,ClassificationController c) {
        this.superController = c;
        this.classification = classification;
        rootUI = new RootClassificationEditor(this);
        rootUI.showName(classification.getName());
        rootUI.showType(classification.getType().getDisplayName());
    }

    public RootClassificationEditor getRootUI() {
        return rootUI;
    }

    public void pollNameFromUI(){
        classification.setName(rootUI.getNameInput());
        superController.updateList();
    }
}
