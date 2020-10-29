package com.niton.fileorganizer.model.classification;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;

import java.io.Serializable;

/**
 *
 * @param <C> the classification this type is for
 */
public interface ClassificationType<C extends Classification> extends Serializable {
    public String getDisplayName();
    default C createNewClassification(String name){
        C c = createNewClassification();
        c.setName(name);
        return c;
    }

    C createNewClassification();

    ClassificationEditorController<C> createController(C classification, ClassificationController superController);

    default ClassificationEditorController<C> createController(String name, ClassificationController superController){
        C cls = createNewClassification(name);
        return createController(cls,superController);
    }
}
