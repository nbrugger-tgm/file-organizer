package com.niton.fileorganizer.controller.classification.specific;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.fileorganizer.gui.components.classification.editors.specific.DateClassificationEditor;
import com.niton.fileorganizer.model.classification.implementation.DateClassification;

import java.awt.event.ActionEvent;
import java.io.Serializable;

public class DateClassificationEditorController extends ClassificationEditorController<DateClassification>  implements Serializable {
    private DateClassificationEditor UI;

    public DateClassificationEditorController(DateClassification classification, ClassificationController superController) {
        super(classification,superController);
        getRootUI().setEditor(UI = new DateClassificationEditor(this));
        UI.showSourceOptions(DateClassification.SourceAttribute.values());
        UI.displayPattern(classification.getPattern());
        UI.showSource(classification.getSource());
        UI.showIgnoreDay(classification.isIgnoreDay());
        UI.showIgnoreMonth(classification.isIgnoreMonth());
        UI.showIgnoreYear(classification.isIgnoreYear());
    }


    public void pollSourceFromGui(ActionEvent actionEvent) {
        System.out.println(UI.getSelectedSource());
        classification.setSource(UI.getSelectedSource());
    }

    public void pollPatternFromGui(ActionEvent actionEvent) {
        System.out.println(UI.getPattern());
        classification.setPattern(UI.getPattern());
    }

    public void pollIgnoredFromUI(ActionEvent actionEvent) {
        classification.setIgnoreMonth(UI.getIgnoreMonth());
        classification.setIgnoreDay(UI.getIgnoreDay());
        classification.setIgnoreYear(UI.getIgnoreYear());
    }
}
