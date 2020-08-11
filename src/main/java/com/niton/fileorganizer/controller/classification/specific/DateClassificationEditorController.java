package com.niton.fileorganizer.controller.classification.specific;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.gui.classification.editors.specific.DateClassificationEditor;
import com.niton.fileorganizer.model.classification.implementation.DateClassification;

import java.awt.event.ActionEvent;

public class DateClassificationEditorController extends ClassificationEditorController<DateClassification> {
    private DateClassificationEditor UI;

    public DateClassificationEditorController(DateClassification classification, ClassificationController superController) {
        super(classification,superController);
        getRootUI().setEditor(UI = new DateClassificationEditor(this));
        UI.showSourceOptions(DateClassification.SourceAttribute.values());
        UI.displayPattern(classification.getPattern());
        UI.showSource(classification.getSource());
    }


    public void pollSourceFromGui(ActionEvent actionEvent) {
        System.out.println(UI.getSelectedSource());
        classification.setSource(UI.getSelectedSource());
    }

    public void pollPatternFromGui(ActionEvent actionEvent) {
        System.out.println(UI.getPattern());
        classification.setPattern(UI.getPattern());
    }
}
