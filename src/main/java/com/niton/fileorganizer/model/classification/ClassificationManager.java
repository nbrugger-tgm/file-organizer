package com.niton.fileorganizer.model.classification;

import com.niton.fileorganizer.controller.classification.ClassificationEditorController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassificationManager  implements Serializable {
    private List<ClassificationType<?>> types = new ArrayList<>();
    private List<Classification<?>> classifications = new ArrayList<>();


    public void register(ClassificationType<?> type){
        types.add(type);
    }
    public ClassificationType<?> getClassificationType(String name){
        for (ClassificationType<?> type : types) {
            if(type.getDisplayName().equals(name))
                return type;
        }
        return null;
    }


    public void addClassificationType(ClassificationType<?> type){
        types.add(type);
    }
    public void removeClassificationType(ClassificationType<?> type){
        types.add(type);
    }

    public void createClassifier(String name,String type){
        ClassificationType<?> realType = getClassificationType(type);
        classifications.add(realType.createNewClassification(name));
    }
    public void removeClassification(Classification<?> name){
        classifications.remove(name);
    }

    public List<Classification<?>> getClassifications() {
        return classifications;
    }

    public List<ClassificationType<?>> getTypes() {
        return types;
    }

    public Classification<?> getClassification(String name){
        for (Classification<?> type : classifications) {
            if(type.getName().equals(name))
                return type;
        }
        return null;
    }

    public void addClassification(Classification<?> classification) {
        classifications.add(classification);
    }
}
