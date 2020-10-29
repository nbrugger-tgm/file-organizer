package com.niton.fileorganizer.controller.classification.specific;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.gui.components.classification.editors.specific.ExtensionClassificationEditor;
import com.niton.fileorganizer.model.classification.implementation.ExtensionClassification;

import java.io.Serializable;

public class ExtensionClassificationEditorController extends ClassificationEditorController<ExtensionClassification> implements Serializable {
    public ExtensionClassificationEditorController(ExtensionClassification classification, ClassificationController c) {
        super(classification, c);
        ExtensionClassificationEditor UI = new ExtensionClassificationEditor();
        getRootUI().setEditor(UI);
        UI.linkModel(classification.getMap());
    }
}
