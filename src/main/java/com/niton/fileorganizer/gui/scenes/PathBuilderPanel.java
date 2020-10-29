package com.niton.fileorganizer.gui.scenes;

import com.niton.fileorganizer.controller.PathBuilderController;
import com.niton.fileorganizer.gui.components.pathing.OrganisationNodeEditor;
import com.niton.fileorganizer.gui.logic.editors.OrganisationNodeTableEditor;
import com.niton.fileorganizer.gui.logic.models.map.ObjectMapTableModel;
import com.niton.fileorganizer.gui.logic.models.tree.PathNodeTreeModel;
import com.niton.fileorganizer.gui.cellrenderer.NodeTreeRenderer;
import com.niton.fileorganizer.model.OrganisationNode;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.implementation.UserChoiceClassification;
import com.niton.fileorganizer.model.classification.types.UserChoiceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class PathBuilderPanel extends JPanel {
	private final JTree tree;
	private final OrganisationNodeEditor editor;
	private PathBuilderController controller;
	private JPanel editorPanel;
	//private JTable table;
	private	ObjectMapTableModel<String, OrganisationNode> model;

	/**
	 * Create the panel.
	 */
	public PathBuilderPanel(PathBuilderController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{150, 300, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				add(scrollPane, gbc_scrollPane);
				
						tree = new JTree();
						tree.setShowsRootHandles(true);
						tree.setRootVisible(true);
						tree.setSelectionModel(new DefaultTreeSelectionModel());
						scrollPane.setViewportView(tree);
						
						editorPanel = new JPanel();
						GridBagConstraints gbc_editorPanel = new GridBagConstraints();
						gbc_editorPanel.fill = GridBagConstraints.BOTH;
						gbc_editorPanel.gridx = 0;
						gbc_editorPanel.gridy = 1;
						add(editorPanel, gbc_editorPanel);
						GridBagLayout gbl_editorPanel = new GridBagLayout();
						gbl_editorPanel.columnWidths = new int[]{0, 0};
						gbl_editorPanel.rowHeights = new int[]{0, 0};
						gbl_editorPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
						gbl_editorPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
						editorPanel.setLayout(gbl_editorPanel);
						
//						table = new JTable();
//						table.setShowGrid(true);
//						table.setTableHeader(new JTableHeader());
//
//						model = new ObjectMapTableModel<>();
//						model.setKeyClass(String.class);
//						model.setValueClass(OrganisationNode.class);
//
//						table.setModel(model);
//						table.setDefaultEditor(OrganisationNode.class, new OrganisationNodeTableEditor(controller.getClassificationManager()));
//						GridBagConstraints gbc_table = new GridBagConstraints();
//						gbc_table.fill = GridBagConstraints.BOTH;
//						gbc_table.gridx = 0;
//						gbc_table.gridy = 0;
//						editorPanel.add(table, gbc_table);
		                editor = new OrganisationNodeEditor(controller,controller.getClassificationManager());
						GridBagConstraints gbc_table = new GridBagConstraints();
						gbc_table.fill = GridBagConstraints.BOTH;
						gbc_table.gridx = 0;
						gbc_table.gridy = 0;
						editorPanel.add(editor, gbc_table);
		registerListeners();
	}

	private void registerListeners() {
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e))
					controller.editSelectedEntry();
			}
		});
		tree.addTreeSelectionListener(controller::onEntrySelect);
	}

	public void initModel(PathNodeTreeModel node) {
		tree.setModel(node);
	}

	public OrganisationNode getSelectedNode() {
		return (OrganisationNode) tree.getSelectionPath().getLastPathComponent();
	}

	public void useCustomRender(){
		tree.setCellRenderer(new NodeTreeRenderer());
	}

	public void displayEditorFor(OrganisationNode lastPathComponent) {
//		if(lastPathComponent == null || lastPathComponent.getClassification() == null){
//			model.setData(null);
//			return;
//		}
//		model.setData(lastPathComponent.getDecisions());
//		if(lastPathComponent.getClassification().getType() instanceof UserChoiceType){
//			table.setDefaultEditor(String.class, new DefaultCellEditor(new JComboBox<>(((UserChoiceClassification) lastPathComponent.getClassification()).getOptions().toArray(new String[0]))));
//		}else{
//			table.setDefaultEditor(String.class, new DefaultCellEditor(new JTextField()));
//		}
//		System.out.println(lastPathComponent);
//		System.out.println(lastPathComponent.getDecisions());
		editor.displayNode(lastPathComponent);
	}

	public void updateTree(OrganisationNode node) {
		if(editor.isSuppressingEvents())
			return;
		TreePath p = new TreePath(tree.getModel().getRoot());
		p = findNode(tree.getModel(),p,node);
		tree.fireTreeExpanded(p);
		((PathNodeTreeModel)tree.getModel()).fireChange(p);
	}

	private TreePath findNode(TreeModel tree, TreePath p, OrganisationNode node) {
		for (int i = 0; i < tree.getChildCount(p.getLastPathComponent()); i++) {
			OrganisationNode subNode = (OrganisationNode) tree.getChild(p.getLastPathComponent(),i);
			if(subNode == null)
				continue;
			TreePath subPath = p.pathByAddingChild(subNode);
			if(node.equals(subNode)){
				return subPath;
			}else{
				subPath = findNode(tree,subPath,node);
				if(subPath != null)
					return subPath;
			}
		}
		return null;
	}

	public String getOptionToAdd() {
		return editor.getOptionToAdd();
	}

	public Classification<?> getClassificationToAdd() {
		return editor.getClassificationToAdd();
	}

	public void clearAddingInput() {
		editor.clearInputs();
	}

	public String getSelectedOption() {
		return editor.getSelectedOption();
	}

	public boolean isSupressingEvents() {
		return editor.isSuppressingEvents();
	}

	public void selectTreeNode(OrganisationNode node) {
		tree.setSelectionPath(findNode(tree.getModel(),new TreePath(tree.getModel().getRoot()), node));
	}

	public Classification<?> getSelectedDefaultOption() {
		return editor.getSelectedDefaultOption();
	}
}
