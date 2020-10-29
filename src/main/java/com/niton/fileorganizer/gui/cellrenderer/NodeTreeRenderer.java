package com.niton.fileorganizer.gui.cellrenderer;

import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;

import com.niton.fileorganizer.model.OrganisationNode;

import java.awt.*;

public class NodeTreeRenderer implements TreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        OrganisationNode node = ((OrganisationNode)value);
        return new JLabel(node.toString());
    }
}
