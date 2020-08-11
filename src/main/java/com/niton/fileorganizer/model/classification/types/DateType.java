package com.niton.fileorganizer.model.classification.types;

import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.implementation.DateClassification;

public class DateType implements ClassificationType {
    @Override
    public String getDisplayName() {
        return "Date";
    }

    @Override
    public Classification<D> createNewClassification(String name) {
        return new DateClassification();
    }
}
