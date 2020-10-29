package com.niton.fileorganizer.controller;

import com.niton.fileorganizer.gui.scenes.PathBuilderPanel;
import com.niton.fileorganizer.gui.popups.EditTreeNodeDialog;
import com.niton.fileorganizer.gui.logic.models.tree.PathNodeTreeModel;
import com.niton.fileorganizer.model.OrganisationNode;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationManager;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class PathBuilderController implements Serializable {
	private transient PathBuilderPanel UI;
	private final GuiController superController;
	private PathNodeTreeModel model = new PathNodeTreeModel();

	public PathBuilderController(GuiController superController) {
		this.superController = superController;
		buildUI();
	}

	public PathNodeTreeModel getModel() {
		return model;
	}

	public JPanel getUI() {
		return UI;
	}

	public void buildUI() {
		UI = new PathBuilderPanel(this);
		UI.initModel(model);
		//UI.useCustomRender();
	}

	public void editSelectedEntry() {
		new Thread(()->{

			OrganisationNode node = UI.getSelectedNode();
			if(node == null)
				return;
			EditTreeNodeDialog dialog = EditTreeNodeDialog.showDialog(superController.getClassificationController().getManager());
			dialog.awaitUser();
			dialog.dispose();
			node.setClassification(dialog.getTypeInput());
		}).start();

	}

	public void onEntrySelect(TreeSelectionEvent treeSelectionEvent) {
		if(UI.isSupressingEvents())
			return;
		System.out.println("Select entry : "+treeSelectionEvent.getNewLeadSelectionPath());
		System.out.println(((JTree)treeSelectionEvent.getSource()).getSelectionPath());
		UI.displayEditorFor((OrganisationNode)treeSelectionEvent.getPath().getLastPathComponent());
	}

	public ClassificationManager getClassificationManager() {
		return superController.getClassificationController().getManager();
	}

	public void addPathOption(ActionEvent actionEvent) {
		if(UI.isSupressingEvents())
			return;
		String option = UI.getOptionToAdd();
		Classification<?> classification = UI.getClassificationToAdd();
		OrganisationNode node = UI.getSelectedNode();
		node.addDecision(option,new OrganisationNode(classification));
		UI.clearAddingInput();
		UI.updateTree(node);
		UI.displayEditorFor(node);
	}

	public void removeSelectedPathOption(ActionEvent actionEvent) {
		String option = UI.getSelectedOption();
		OrganisationNode node = UI.getSelectedNode();
		node.removeDecision(option);
		UI.updateTree(node);
		UI.displayEditorFor(node);
	}

	public void changeSelectedDefaultOption(ActionEvent actionEvent) {
		if(UI.isSupressingEvents())
			return;
		Classification<?> classification = UI.getSelectedDefaultOption();
		OrganisationNode node = UI.getSelectedNode();
		node.setNoDecisionNode(new OrganisationNode(classification));
		UI.updateTree(node);
		UI.selectTreeNode(node);
	}
}
