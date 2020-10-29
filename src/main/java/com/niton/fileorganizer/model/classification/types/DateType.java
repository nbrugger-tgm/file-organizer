package com.niton.fileorganizer.model.classification.types;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.controller.classification.specific.DateClassificationEditorController;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.implementation.DateClassification;

public class DateType implements ClassificationType<DateClassification> {
    @Override
    public String getDisplayName() {
        return "Date";
    }

    @Override
    public DateClassification createNewClassification() {
        return new DateClassification();
    }

	@Override
	public ClassificationEditorController<DateClassification> createController(DateClassification classification, ClassificationController superController) {
		return new DateClassificationEditorController(classification, superController);
	}
}
