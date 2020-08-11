package com.niton.fileorganizer.gui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import com.niton.media.filesystem.Directory;

public class DirectoryChooser extends JFileChooser{
	public DirectoryChooser() {
		setApproveButtonText("Choose");
		setFileSelectionMode(DIRECTORIES_ONLY);
		setMultiSelectionEnabled(true);
	}
	public List<Directory> getSelections(JComponent parent){
		setMultiSelectionEnabled(true);
		showOpenDialog(parent);
		return Arrays.stream(getSelectedFiles()).map(Directory::new).collect(Collectors.toList());
	}
	public Directory getSelection(JComponent parent){
		setMultiSelectionEnabled(false);
		showOpenDialog(parent);
		return new Directory(getSelectedFile());
	}
}
