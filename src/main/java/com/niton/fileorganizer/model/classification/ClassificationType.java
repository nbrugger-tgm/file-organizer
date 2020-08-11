package com.niton.fileorganizer.model.classification;

public interface ClassificationType {
    public String getDisplayName();
    public Classification<D> createNewClassification(String name);
}
