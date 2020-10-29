package com.niton.fileorganizer.gui.logic.models.map;


import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class StringMapTableModel implements TableModel , Serializable {
    private LinkedHashMap<String,String> data = new LinkedHashMap<>();
    private String col1 = "Key",col2="Value";
    private boolean keysEditable = true;
    @Override
    public int getRowCount() {
        return data.size()+1;
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
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex+1>data.size() || columnIndex == 1 || keysEditable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex+1>data.size())
            return new String();
        String key = (String)data.keySet().toArray()[rowIndex];
        return columnIndex == 0?key:data.get(key);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String key;
        String value;
        boolean rowAdded = false;
        if(rowIndex == data.size()){
            if(columnIndex == 0){
                value = new String();
                key = aValue.toString();
            }else{
                key = new String();
                value = aValue.toString();
            }
            rowAdded = true;
        }else{
            if(columnIndex == 0){

                String oldKey = (String)data.keySet().toArray()[rowIndex];
                value = data.get(oldKey);
                data.remove(oldKey);
                key = aValue.toString();

            }else{
                key = (String)data.keySet().toArray()[rowIndex];
                value = aValue.toString();
            }
        }
        data.put(key, value);
        boolean finalRowAdded = rowAdded;
        listeners.forEach(l -> l.tableChanged(new TableModelEvent(this, rowIndex, finalRowAdded ?rowIndex+1:rowIndex)));
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

    public void setData(LinkedHashMap<String, String> data) {
        int lastRow = data.size();
        this.data = data;
        listeners.forEach(l -> l.tableChanged(new TableModelEvent(this, 0,lastRow+1)));
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
}
