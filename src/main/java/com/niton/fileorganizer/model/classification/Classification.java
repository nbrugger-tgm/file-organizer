package com.niton.fileorganizer.model.classification;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.media.filesystem.NFile;

import javax.swing.*;
import java.io.IOException;


public abstract class Classification {
    private final ClassificationType type;
    private String name;

    public Classification(ClassificationType type) {
        this.type = type;
    }
    public abstract String classify(NFile file) throws IOException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassificationType getType() {
        return type;
    }

    public ClassificationEditorController createController(ClassificationController superController){
        return getType().createController(this,superController);
    }
}
