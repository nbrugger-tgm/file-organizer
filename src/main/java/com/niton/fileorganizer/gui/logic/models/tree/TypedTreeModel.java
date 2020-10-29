package com.niton.fileorganizer.gui.logic.models.tree;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public abstract class TypedTreeModel<T,R> implements TreeModel {

    @Override
    public Object getChild(Object parent, int index) {
        if(parent == getRoot())
            return getRootChild(index);
        return getTChild((T) parent,index);
    }

    protected abstract T getRootChild(int index);

    protected abstract T getTChild(T parent, int index);

    @Override
    public int getChildCount(Object parent) {
        if(parent == getRoot())
            return getRootChildCount();
        return getTChildCount((T) parent);
    }

    protected abstract int getRootChildCount();

    protected abstract int getTChildCount(T parent);

    @Override
    public boolean isLeaf(Object node) {
        return isTLeaf((T) node);
    }

    protected abstract boolean isTLeaf(T node);

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        if(path.getPath().length == 1)
            rootValueChange((R) newValue);
        else {
            tValueForPathChanged(path, (T) newValue);
        }
        listeners.forEach(e -> e.treeNodesChanged(new TreeModelEvent(this, path)));
    }

    protected abstract void rootValueChange(R newValue);

    protected abstract void tValueForPathChanged(TreePath path, T newValue);

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(parent == getRoot()){
            return getIndexOfRootChild((T)child);
        }
        return getTIndexOfChild((T)parent,(T)child);
    }

    protected abstract int getIndexOfRootChild(T child);

    protected abstract int getTIndexOfChild(T parent, T child);

    ArrayList<TreeModelListener> listeners = new ArrayList<>();

    public ArrayList<TreeModelListener> getListeners() {
        return listeners;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }
}
