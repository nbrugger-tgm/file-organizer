package com.niton.fileorganizer.gui.components.pathing;

import com.niton.fileorganizer.controller.PathBuilderController;
import com.niton.fileorganizer.gui.components.PathDisplay;
import com.niton.fileorganizer.gui.logic.models.ClassificationChooserModel;
import com.niton.fileorganizer.model.OrganisationNode;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationManager;
import com.niton.fileorganizer.model.classification.implementation.UserChoiceClassification;
import com.niton.fileorganizer.model.classification.types.UserChoiceType;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class OrganisationNodeEditor extends JPanel {
	private final JPanel mapPane;
	private final ClassificationManager manager;
	private JComponent optionInput;
	private JComboBox<Classification<?>> optionPathChooser;
	private JButton addBtn;
	private JButton removeBtn;
	private JScrollPane scrollPane;
	private JTextField txtDefault;
	private JComboBox<Classification<?>> defaultChooser;
	boolean supressEvents = false;
	public OrganisationNodeEditor(PathBuilderController controller,ClassificationManager manager) {
		this.manager = manager;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		txtDefault = new JTextField();
		txtDefault.setEditable(false);
		txtDefault.setText("Default");
		GridBagConstraints gbc_txtDefault = new GridBagConstraints();
		gbc_txtDefault.insets = new Insets(5, 5, 5, 5);
		gbc_txtDefault.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDefault.gridx = 0;
		gbc_txtDefault.gridy = 0;
		add(txtDefault, gbc_txtDefault);
		txtDefault.setColumns(10);
		defaultChooser = new JComboBox<>();
		defaultChooser.setModel(new ClassificationChooserModel(manager,true));
		GridBagConstraints gbc_defaultChooser = new GridBagConstraints();
		gbc_defaultChooser.insets = new Insets(5, 0, 5, 5);
		gbc_defaultChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_defaultChooser.gridx = 1;
		gbc_defaultChooser.gridy = 0;
		add(defaultChooser, gbc_defaultChooser);
		
		optionInput = new JTextField();
		GridBagConstraints gbc_optionInput = new GridBagConstraints();
		gbc_optionInput.insets = new Insets(5, 5, 5, 5);
		gbc_optionInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_optionInput.gridx = 0;
		gbc_optionInput.gridy = 1;
		add(optionInput, gbc_optionInput);
		
		optionPathChooser = new JComboBox<Classification<?>>();
		optionPathChooser.setModel(new ClassificationChooserModel(manager));
		GridBagConstraints gbc_optionPathChooser = new GridBagConstraints();
		gbc_optionPathChooser.insets = new Insets(5, 0, 5, 5);
		gbc_optionPathChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_optionPathChooser.gridx = 1;
		gbc_optionPathChooser.gridy = 1;
		add(optionPathChooser, gbc_optionPathChooser);
		
		addBtn = new JButton("Add");
		GridBagConstraints gbc_addBtn = new GridBagConstraints();
		gbc_addBtn.insets = new Insets(5, 0, 5, 5);
		gbc_addBtn.gridx = 2;
		gbc_addBtn.gridy = 1;
		add(addBtn, gbc_addBtn);


		mapPane = new JPanel();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(mapPane);
		GridBagConstraints gbc_mapPane = new GridBagConstraints();
		gbc_mapPane.insets = new Insets(5, 5, 5, 5);
		gbc_mapPane.gridwidth = 3;
		gbc_mapPane.fill = GridBagConstraints.BOTH;
		gbc_mapPane.gridx = 0;
		gbc_mapPane.gridy = 2;
		add(scrollPane, gbc_mapPane);
		
		removeBtn = new JButton("Remove selected");
		GridBagConstraints gbc_removeBtn = new GridBagConstraints();
		gbc_removeBtn.insets = new Insets(0, 0, 5, 0);
		gbc_removeBtn.gridwidth = 3;
		gbc_removeBtn.gridx = 0;
		gbc_removeBtn.gridy = 3;
		add(removeBtn, gbc_removeBtn);
		registerListeners(controller);
	}

	private void registerListeners(PathBuilderController controller) {
		addBtn.addActionListener(controller::addPathOption);
		removeBtn.addActionListener(controller::removeSelectedPathOption);
		defaultChooser.addActionListener(controller::changeSelectedDefaultOption);
	}

	public void displayNode(OrganisationNode node){
		if(node == null)
			return;
		if(node.getClassification() == null)
			return;
		if(node.getNoDecisionNode() != null) {
			supressEvents = true;
			defaultChooser.setSelectedItem(node.getNoDecisionNode().getClassification());
			supressEvents = false;
			defaultChooser.repaint();
			defaultChooser.revalidate();
		}
		remove(optionInput);
		if (node.getClassification() instanceof UserChoiceClassification) {
			optionInput = new JComboBox<>(((UserChoiceClassification)node.getClassification()).getOptions().toArray(new String[0]));
		} else {
			optionInput = new JTextField();
		}
		GridBagConstraints gbc_optionInput = new GridBagConstraints();
		gbc_optionInput.insets = new Insets(5, 5, 5, 5);
		gbc_optionInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_optionInput.gridx = 0;
		gbc_optionInput.gridy = 1;
		add(optionInput, gbc_optionInput);
		optionInput.repaint();
		optionInput.revalidate();

		mapPane.removeAll();
		mapPane.setFont(mapPane.getParent().getFont());
		for (Map.Entry<String, OrganisationNode> option : node.getDecisions().entrySet()) {
			PathDisplay dsply = new PathDisplay(new String[]{option.getKey(),option.getValue().toString()});
			//JLabel dsply = new JLabel(option.getKey()+"->"+option.getValue().toString());
			mapPane.add(dsply);
			dsply.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selected = option.getKey();
					System.out.println(selected);
					Arrays.stream(mapPane.getComponents()).forEach(el -> {
						el.setBackground(UIManager.getColor("Panel.background"));
						el.setForeground(UIManager.getColor("Panel.foreground"));
					});
					dsply.setBackground(UIManager.getColor("List.selectionBackground"));
					dsply.setForeground(UIManager.getColor("List.selectionForeground"));
					dsply.repaint();
					dsply.validate();
				}
			});
			dsply.setFont(dsply.getParent().getFont());
		}
		scrollPane.repaint();
		scrollPane.revalidate();
		repaint();
		revalidate();
	}
	private String selected;

	public String getOptionToAdd() {
		if(optionInput instanceof JTextComponent)
			return ((JTextComponent) optionInput).getText();
		else if (optionInput instanceof JComboBox)
			return (String) ((JComboBox<?>) optionInput).getSelectedItem();
		return null;
	}

	public boolean isSuppressingEvents() {
		return supressEvents;
	}

	public Classification<?> getClassificationToAdd() {
		return (Classification<?>) optionPathChooser.getSelectedItem();
	}

	public void clearInputs() {
		if(optionInput instanceof JTextComponent)
			((JTextComponent) optionInput).setText("");
		else if(optionInput instanceof JComboBox)
			((JComboBox<?>) optionInput).setSelectedIndex(-1);
		optionPathChooser.setSelectedItem(null);
		optionPathChooser.repaint();
	}

	public String getSelectedOption() {
		return selected;
	}

	public Classification<?> getSelectedDefaultOption() {
		return (Classification<?>) defaultChooser.getSelectedItem();
	}
}
