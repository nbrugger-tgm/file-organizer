package com.niton.fileorganizer.model.classification.types;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.implementation.UserInputClassification;

public class UserInputType implements ClassificationType {
    @Override
    public String getDisplayName() {
        return "Manual Input";
    }

    @Override
    public Classification createNewClassification() {
        return new UserInputClassification();
    }

    @Override
    public ClassificationEditorController createController(Classification classification, ClassificationController controller) {
        return null;
    }
}
