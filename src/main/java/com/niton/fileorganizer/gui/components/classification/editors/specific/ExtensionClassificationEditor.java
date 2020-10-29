package com.niton.fileorganizer.gui.components.classification.editors.specific;

import com.niton.fileorganizer.gui.components.classification.editors.SpecificClassifierEditor;
import com.niton.fileorganizer.gui.logic.models.map.StringMapTableModel;

import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.awt.Font;
import javax.swing.JLabel;

public class ExtensionClassificationEditor extends SpecificClassifierEditor {
	private JTable table;
	private StringMapTableModel model;
	public ExtensionClassificationEditor() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Extendions");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.insets = new Insets(0, 10, 10, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		table = new JTable();
		table.setTableHeader(new JTableHeader());
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Roboto", Font.PLAIN, 13));
		table.setCellSelectionEnabled(true);
		table.setModel(model = new StringMapTableModel());
		model.setCol1("Extendion");
		model.setCol2("Folder name");
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.insets = new Insets(0, 0, 10, 0);
		gbc_table.gridx = 1;
		gbc_table.gridy = 0;
		add(table, gbc_table);
	}
	public void linkModel(LinkedHashMap<String,String> map) {
		model.setData(map);
	}

}
