package com.niton.fileorganizer.model.classification;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;

/**
 *
 * @param <C> the classification this type is for
 */
public interface ClassificationType<C extends Classification> {
    public String getDisplayName();
    default C createNewClassification(String name){
        C c = createNewClassification();
        c.setName(name);
        return c;
    }

    C createNewClassification();

    ClassificationEditorController createController(C classification, ClassificationController superController);

    default ClassificationEditorController createController(String name, ClassificationController superController){
        C cls = createNewClassification(name);
        return createController(cls,superController);
    }
}
