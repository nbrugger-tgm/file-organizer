package com.niton.fileorganizer.model.classification.types;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.controller.classification.specific.ExtensionClassificationEditorController;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.implementation.ExtensionClassification;

public class ExtensionType implements ClassificationType<ExtensionClassification> {
    @Override
    public String getDisplayName() {
        return "File ending";
    }

    @Override
    public ExtensionClassification createNewClassification() {
        return new ExtensionClassification();
    }

    @Override
    public ClassificationEditorController<ExtensionClassification> createController(ExtensionClassification classification, ClassificationController superController) {
        return new ExtensionClassificationEditorController(classification,superController);
    }
}
