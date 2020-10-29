package com.niton.fileorganizer.model.classification.types;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.implementation.UserInputClassification;

public class UserInputType implements ClassificationType<UserInputClassification> {
    @Override
    public String getDisplayName() {
        return "Manual Input";
    }

    @Override
    public UserInputClassification createNewClassification() {
        return new UserInputClassification();
    }

    @Override
    public ClassificationEditorController<UserInputClassification> createController(UserInputClassification classification, ClassificationController controller) {
        return null;
    }
}
