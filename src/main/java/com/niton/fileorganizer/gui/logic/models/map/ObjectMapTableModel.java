package com.niton.fileorganizer.gui.logic.models.map;


import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ObjectMapTableModel<K,V> implements TableModel , Serializable {
    private Class<K> keyClass;
    private Class<V> valueClass;
    private LinkedHashMap<K,V> data = new LinkedHashMap<>();
    private String col1 = "Key",col2="Value";
    private boolean keysEditable = true;

    @Override
    public int getRowCount() {
        return data == null ? 0 : data.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnIndex == 0 ? col1:col2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0 && keyClass != null)
            return keyClass;
        if(columnIndex == 1 && valueClass != null)
            return valueClass;
            return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(data == null)
            return false;
        return rowIndex+1>data.size() || columnIndex == 1 || keysEditable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex+1>data.size())
            return null;
        String key = (String)data.keySet().toArray()[rowIndex];
        return columnIndex == 0?key:data.get(key);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        K key;
        V value;
        if(rowIndex+1 > data.size()){
            if(columnIndex == 0){
                value = null;
                key = (K) aValue;
            }else{
                key = null;
                value = (V) aValue;
            }
        }else{
            if(columnIndex == 0){

                K oldKey = (K)data.keySet().toArray()[rowIndex];
                value = data.get(oldKey);
                data.remove(oldKey);
                key = (K) aValue;

            }else{
                key = (K)data.keySet().toArray()[rowIndex];
                value = (V) aValue;
            }
        }
        data.put(key, value);
        listeners.forEach(l -> l.tableChanged(new TableModelEvent(this, rowIndex)));
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }
    private final ArrayList<TableModelListener> listeners = new ArrayList<>();

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    public void setData(LinkedHashMap<K, V> data) {
        int lastRow = this.data == null ? 0 : this.data.size();
        int finalLastRow = lastRow;
        listeners.forEach(l -> l.tableChanged(new TableModelEvent(this, 0, finalLastRow +1,TableModelEvent.DELETE)));
        lastRow = this.data == null ? 0 : this.data.size();
        this.data = data;
        int finalLastRow1 = lastRow;
        listeners.forEach(l -> l.tableChanged(new TableModelEvent(this, 0, finalLastRow1 +1,TableModelEvent.INSERT)));
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public void setKeysEditable(boolean keysEditable) {
        this.keysEditable = keysEditable;
    }

    public Class<K> getKeyClass() {
        return keyClass;
    }

    public void setKeyClass(Class<K> keyClass) {
        this.keyClass = keyClass;
    }

    public Class<V> getValueClass() {
        return valueClass;
    }

    public void setValueClass(Class<V> valueClass) {
        this.valueClass = valueClass;
    }
}
