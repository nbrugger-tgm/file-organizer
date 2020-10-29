package com.niton.fileorganizer.model.classification.types;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.controller.classification.specific.UserChoiceClassificationEditorController;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.implementation.UserChoiceClassification;

public class UserChoiceType implements ClassificationType<UserChoiceClassification> {
    @Override
    public String getDisplayName() {
        return "Multiple Choice";
    }

    @Override
    public UserChoiceClassification createNewClassification() {
        return new UserChoiceClassification();
    }

    @Override
    public ClassificationEditorController<UserChoiceClassification> createController(UserChoiceClassification classification, ClassificationController superController) {
        return new UserChoiceClassificationEditorController(classification, superController);
    }
}
