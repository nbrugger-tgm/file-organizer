package com.niton.fileorganizer.model.classification;

import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.controller.classification.DateClassificationEditorController;
import com.niton.media.filesystem.NFile;

import javax.swing.*;
import java.io.IOException;

public abstract class Classification<C extends ClassificationEditorController> {
    private final ClassificationType type;
    private String name;

    public Classification(ClassificationType type) {
        this.type = type;
    }
    public abstract String classify(NFile file) throws IOException;
    protected abstract JPanel createEditorPanel(C controller);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
