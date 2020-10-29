package com.niton.fileorganizer.gui.popups;

import com.niton.fileorganizer.gui.logic.models.ClassificationChooserModel;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditTreeNodeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameInput;
	private JComboBox<Classification<?>> typeChooser;
	private final Object lock = new Object();

	public static EditTreeNodeDialog showDialog(ClassificationManager manager) {
		EditTreeNodeDialog dialog = new EditTreeNodeDialog(manager);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//dialog.setModalityType(ModalityType.TOOLKIT_MODAL);
		dialog.setVisible(true);
		return dialog;
	}

	/**
	 * Create the dialog.
	 */
	public EditTreeNodeDialog(ClassificationManager manager) {
		setBounds(100, 100, 293, 103);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblType = new JLabel("Classification");
			GridBagConstraints gbc_lblType = new GridBagConstraints();
			gbc_lblType.anchor = GridBagConstraints.EAST;
			gbc_lblType.insets = new Insets(0, 0, 0, 5);
			gbc_lblType.gridx = 0;
			gbc_lblType.gridy = 0;
			contentPanel.add(lblType, gbc_lblType);
		}
		{
			typeChooser = new JComboBox<>();
			typeChooser.setModel(new ClassificationChooserModel(manager));
			GridBagConstraints gbc_typeChooser = new GridBagConstraints();
			gbc_typeChooser.fill = GridBagConstraints.HORIZONTAL;
			gbc_typeChooser.gridx = 1;
			gbc_typeChooser.gridy = 0;
			contentPanel.add(typeChooser, gbc_typeChooser);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Choose");
				okButton.addActionListener(e -> {
					synchronized (lock) {
						lock.notifyAll();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}


	public void awaitUser() {
		synchronized (lock){
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Classification getTypeInput() {
		return (Classification) typeChooser.getSelectedItem();
	}
}
