package com.niton.fileorganizer.gui.logic.editors;

import com.niton.fileorganizer.gui.logic.models.ClassificationChooserModel;
import com.niton.fileorganizer.model.OrganisationNode;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationManager;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class OrganisationNodeTableEditor extends AbstractCellEditor implements TableCellEditor {
    private ClassificationManager manager;
    private OrganisationNode node = new OrganisationNode();
    private JComboBox<Classification<?>> jcb = new JComboBox<>();
    public OrganisationNodeTableEditor(ClassificationManager manager) {
        this.manager = manager;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        node = (OrganisationNode) value;
        if(node == null)
            node = new OrganisationNode();
        jcb.setModel(new ClassificationChooserModel(manager));
        if(node.getClassification() != null)
            jcb.setSelectedItem(node.getClassification());
        jcb.addActionListener(e -> {
            node.setClassification((Classification<?>) jcb.getSelectedItem());
            System.out.println("Classification chuusen");
            fireEditingStopped();
        });
        return jcb;
    }

    @Override
    public OrganisationNode getCellEditorValue() {
        return node;
    }
}
