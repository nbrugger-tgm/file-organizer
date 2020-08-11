package com.niton.fileorganizer.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateClassificationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameInput;
	private JComboBox typeChooser;
	private final Object lock = new Object();
	private boolean done = false;
	/**
	 * Launch the application.
	 */
	public static CreateClassificationDialog showDialog() {
		try {
			CreateClassificationDialog dialog = new CreateClassificationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			return dialog;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Create the dialog.
	 */
	public CreateClassificationDialog() {
		setBounds(100, 100, 444, 133);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			nameInput = new JTextField();
			GridBagConstraints gbc_nameInput = new GridBagConstraints();
			gbc_nameInput.insets = new Insets(0, 0, 5, 0);
			gbc_nameInput.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameInput.gridx = 1;
			gbc_nameInput.gridy = 0;
			contentPanel.add(nameInput, gbc_nameInput);
			nameInput.setColumns(10);
		}
		{
			JLabel lblType = new JLabel("Type");
			GridBagConstraints gbc_lblType = new GridBagConstraints();
			gbc_lblType.anchor = GridBagConstraints.EAST;
			gbc_lblType.insets = new Insets(0, 0, 0, 5);
			gbc_lblType.gridx = 0;
			gbc_lblType.gridy = 1;
			contentPanel.add(lblType, gbc_lblType);
		}
		{
			typeChooser = new JComboBox();
			GridBagConstraints gbc_typeChooser = new GridBagConstraints();
			gbc_typeChooser.fill = GridBagConstraints.HORIZONTAL;
			gbc_typeChooser.gridx = 1;
			gbc_typeChooser.gridy = 1;
			contentPanel.add(typeChooser, gbc_typeChooser);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Create");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						synchronized (lock) {
							done = true;
							lock.notifyAll();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						synchronized (lock) {
							done = false;
							lock.notifyAll();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	public boolean awaitUser() {
		synchronized (lock){
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
}
