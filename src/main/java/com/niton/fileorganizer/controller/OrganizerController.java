package com.niton.fileorganizer.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import com.niton.fileorganizer.gui.components.DirectoryChooser;
import com.niton.fileorganizer.gui.scenes.OrganizePanel;
import com.niton.fileorganizer.model.OrganizeSession;
import com.niton.media.filesystem.Directory;
import com.niton.media.filesystem.NFile;

import javax.swing.*;

public class OrganizerController  implements Serializable {
	private final GuiController superController;
	private OrganizeSession session;
	private transient OrganizePanel UI;
	private static transient DirectoryChooser explorer = new DirectoryChooser();
	public OrganizerController(GuiController controller) {
		this.superController = controller;
		session = new OrganizeSession();
		buildUI();
	}

	public void buildUI() {
		UI = new OrganizePanel(this);
		updateSourceListDisplay();
		updateTargetLocation();
		if(session.inClassification){
			UI.showClassificationScene();
			processFiles();
		}
	}

	public OrganizePanel getUI() {
		return UI;
	}

	public void addSourceFolder(ActionEvent e) {
		List<Directory> dirs = explorer.getSelections(UI.getRootPane());
		if(dirs != null) {
			session.getLocations().addSources(dirs);
			updateSourceListDisplay();
		}
	}

	private void updateSourceListDisplay() {
		UI.showList(session.getLocations().getSources());
	}

	public void removeSourceFolder(ActionEvent e) {
		Directory dir = UI.getSelectedSource();
		if(dir != null) {
			session.getLocations().removeSource(dir);
			updateSourceListDisplay();
		}
	}

	public void setTargetLocation(Directory e) {
		session.getLocations().setTarget(e);
		updateTargetLocation();
	}

	private void updateTargetLocation() {
		UI.setTargetLocation(session.getLocations().getTarget().toString());
	}

	public void browseTargetLocation(ActionEvent e) {
		Directory d = explorer.getSelection(UI.getRootPane());
		if(d != null)
			setTargetLocation(d);
	}

	public void submitSources(ActionEvent e) {
			session.inClassification = true;
			session.tree = superController.getPathBuilderController().getModel().getRootNode();
			UI.showClassificationScene();
			session.clearFileStack();
			for (Directory source : session.getLocations().getSources()) {
				try {
					Files.walkFileTree(source.getPath(), new FileVisitor<Path>() {
						@Override
						public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
							session.addToStack(new NFile(file));
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
							return FileVisitResult.CONTINUE;
						}
					});
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
			processFiles();

	}

	private void processFiles() {
		new Thread(()->{
			while (session.hasFiles()){
				NFile p = session.currentFile();
				try {
					session.addFile(p);
				} catch (IOException ioException) {
					JOptionPane.showMessageDialog(UI,"Error : "+ioException,ioException.toString(),JOptionPane.ERROR_MESSAGE);
					continue;
				}
				UI.displayFile(p);
				UI.displayFileClassification(session.getNonNullValues(p), session.getCalculations(p));
				UI.awaitClassification();
				try {
					session.copyFile(p);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(UI,"Error : "+e,e.getClass().getSimpleName(),JOptionPane.ERROR_MESSAGE);
				}
				session.completeFile();
			}
			UI.showStartScene();
			session.clearClassifications();
		}).start();
	}

	public void quitClassification(ActionEvent actionEvent) {
		UI.showStartScene();
		session.clearClassifications();
	}


	public void pollValueFromUI(String classifier) {
		NFile file = session.currentFile();
		String value = UI.getValue(classifier);
		session.getProgress().get(file).put(classifier,value);
		try {
			session.calculatePath(file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(UI,"Error : "+e,e.getClass().getSimpleName(),JOptionPane.ERROR_MESSAGE);
		}
		UI.displayFile(file);
		UI.displayFileClassification(session.getNonNullValues(file), session.getCalculations(file));
	}
}
