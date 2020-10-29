package com.niton.fileorganizer.gui.logic.models;

import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationManager;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class ClassificationChooserModel implements ComboBoxModel<Classification<?>> {
    private boolean allowDeselect = false;
    private Classification<?> selected;
    private ClassificationManager backend;

    public ClassificationChooserModel(ClassificationManager backend) {
        this.backend = backend;
    }
    public ClassificationChooserModel(ClassificationManager backend,boolean allowDeselect) {
        this.backend = backend;
        this.allowDeselect = allowDeselect;
    }
    @Override
    public void setSelectedItem(Object anItem) {
        selected = (Classification<?>) anItem;
    }

    @Override
    public Classification<?> getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return backend.getClassifications().size()+(allowDeselect?1:0);
    }

    @Override
    public Classification<?> getElementAt(int index) {
        if(index>=backend.getClassifications().size())
            return null;
        return backend.getClassifications().get(index);
    }

    private ArrayList<ListDataListener> listeners = new ArrayList<>();

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
    public void renew(){
        listeners.forEach(e -> e.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED,0,backend.getClassifications().size())));
    }
}
