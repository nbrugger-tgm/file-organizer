package com.niton.fileorganizer.controller.classification.specific;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.gui.components.classification.editors.specific.UserChoiceClassificationEditor;
import com.niton.fileorganizer.model.classification.implementation.UserChoiceClassification;

import java.awt.event.ActionEvent;
import java.io.Serializable;

public class UserChoiceClassificationEditorController extends ClassificationEditorController<UserChoiceClassification> implements Serializable {
    private UserChoiceClassificationEditor UI;
    public UserChoiceClassificationEditorController(UserChoiceClassification classification, ClassificationController c) {
        super(classification, c);
        UI = new UserChoiceClassificationEditor(this);
        UI.displayOptions(classification.getOptions());
        getRootUI().setEditor(UI);
    }

    public void removeSelectedOption(ActionEvent actionEvent) {
        classification.getOptions().remove(UI.getSelectedOption());
        UI.displayOptions(classification.getOptions());
    }

    public void addOptionFromUI(ActionEvent actionEvent) {
        classification.getOptions().add(UI.getOptionName());
        UI.displayOptions(classification.getOptions());
        UI.clearInputField();
    }
}
