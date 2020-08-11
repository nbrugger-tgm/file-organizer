package com.niton.fileorganizer.model.classification;

import java.util.ArrayList;
import java.util.List;

public class ClassificationManager {
    private List<ClassificationType> types = new ArrayList<>();
    public void register(ClassificationType type){
        types.add(type);
    }
    public ClassificationType getClassificationType(String name){
        for (ClassificationType type : types) {
            if(type.getDisplayName().equals(name))
                return type;
        }
        return null;
    }
    private List<Classification<D>> classifications = new ArrayList<>();
    public void addClassificationType(ClassificationType type){
        types.add(type);
    }
    public void removeClassificationType(ClassificationType type){
        types.add(type);
    }

    public void addClassification(Classification<D> type){
        classifications.add(type);
    }
    public void removeClassification(Classification<D> type){
        classifications.add(type);
    }

    public List<Classification<D>> getClassifications() {
        return classifications;
    }

    public List<ClassificationType> getTypes() {
        return types;
    }

    public Classification<D> getClassification(String name){
        for (Classification<D> type : classifications) {
            if(type.getName().equals(name))
                return type;
        }
        return null;
    }

}
