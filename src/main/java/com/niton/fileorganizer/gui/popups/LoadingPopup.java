package com.niton.fileorganizer.gui.popups;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

public class LoadingPopup extends JDialog {
	private JLabel lblNewLabel;
	

	/**
	 * Create the dialog.
	 */
	public LoadingPopup() {
		//setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 346, 104);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 15, 0, 15);
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 1;
		getContentPane().add(progressBar, gbc_progressBar);

	}
	public void setTitle(String ttl) {
		lblNewLabel.setText(ttl);
		repaint();
		validate();
	}
}
