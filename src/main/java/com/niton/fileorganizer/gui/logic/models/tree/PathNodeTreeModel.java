package com.niton.fileorganizer.gui.logic.models.tree;

import com.niton.fileorganizer.model.OrganisationNode;

import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreePath;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class PathNodeTreeModel extends TypedTreeModel<OrganisationNode,OrganisationNode> implements Serializable {
    private OrganisationNode rootNode = new OrganisationNode();

    @Override
    protected OrganisationNode getRootChild(int index) {
        return getTChild(rootNode,index);
    }

    @Override
    protected OrganisationNode getTChild(OrganisationNode parent, int index) {
        if(index == parent.getDecisions().size())
            return parent.getNoDecisionNode();
       return getEntryByIndex(parent,index).getValue();
    }

    public void fireChange(TreePath p){
        listeners.forEach((e) -> e.treeStructureChanged(new TreeModelEvent(this,p)));
    }

    private Map.Entry<String, OrganisationNode> getEntryByIndex(OrganisationNode n, int i) {
        Set<Map.Entry<String, OrganisationNode>> s = n.getDecisions().entrySet();
        int ci = 0;
        for (Map.Entry<String, OrganisationNode> e : s) {
            if(ci == i)
                return e;
            ci++;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    protected int getRootChildCount() {
        return getTChildCount(getRootNode());
    }

    @Override
    protected int getTChildCount(OrganisationNode parent) {
        return 1+parent.getDecisions().size();
    }

    @Override
    protected boolean isTLeaf(OrganisationNode node) {
        return node.getClassification() == null;
    }

    @Override
    protected void rootValueChange(OrganisationNode newValue) {
        tValueForPathChanged(new TreePath(new Object[]{getRoot()}), newValue);
    }

    @Override
    protected void tValueForPathChanged(TreePath path, OrganisationNode newValue) {
        OrganisationNode toModify = (OrganisationNode) path.getParentPath().getLastPathComponent();
        if(path.getLastPathComponent().equals(toModify.getNoDecisionNode()))
            toModify.setNoDecisionNode(newValue);
        else{
            String replaceKey = null;
            for (Map.Entry<String, OrganisationNode> nodeEntry : toModify.getDecisions().entrySet()) {
               if(nodeEntry.getValue().equals(path.getLastPathComponent())){
                   replaceKey = nodeEntry.getKey();
                   break;
               }
            }
            toModify.addDecision(replaceKey, newValue);
        }
        getListeners().forEach(l -> l.treeStructureChanged(new TreeModelEvent(PathNodeTreeModel.this, path.getParentPath())));
    }

    @Override
    protected int getIndexOfRootChild(OrganisationNode child) {
        return getTIndexOfChild(getRootNode(), child);
    }

    @Override
    protected int getTIndexOfChild(OrganisationNode parent, OrganisationNode child) {
        if(child == parent.getNoDecisionNode())
            return parent.getDecisions().size();
        else{
            int i = 0;
            for (Map.Entry<String, OrganisationNode> nodeEntry : parent.getDecisions().entrySet()) {
                if(nodeEntry.getValue().equals(child))
                    return i;
                i++;
            }
        }
        return -1;
    }

    public OrganisationNode getRootNode() {
        return rootNode;
    }

    @Override
    public Object getRoot() {
        return getRootNode();
    }

	public void setRootNode(OrganisationNode node) {
        OrganisationNode old = rootNode;
		rootNode = node;
		listeners.forEach(l -> l.treeStructureChanged(new TreeModelEvent(PathNodeTreeModel.this, new TreePath(old))));
	}
}
