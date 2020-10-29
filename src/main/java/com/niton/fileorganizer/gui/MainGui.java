package com.niton.fileorganizer.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

import com.niton.fileorganizer.controller.GuiController;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class MainGui extends JFrame {

	private JButton organizeBtn;
	private JPanel contentView;
	private GuiController controller;
	private JButton classificationButton;
	private JButton btnPathbuilder;

	/**
	 * Launch the application.
	 */
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui.this.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui(GuiController c) {
		this.controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 912, 659);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] {0, 500, 0};
		gbl_mainPanel.rowHeights = new int[] {0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(null);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 0, 5);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 0;
		mainPanel.add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] {150, 0};
		gbl_buttonPanel.rowHeights = new int[]{45, 45, 45, 0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		organizeBtn = new JButton("Organize");
		GridBagConstraints gbc_organizeBtn = new GridBagConstraints();
		gbc_organizeBtn.insets = new Insets(5, 0, 5, 0);
		gbc_organizeBtn.fill = GridBagConstraints.BOTH;
		gbc_organizeBtn.gridx = 0;
		gbc_organizeBtn.gridy = 0;
		buttonPanel.add(organizeBtn, gbc_organizeBtn);
		
		classificationButton = new JButton("Classifications");
		GridBagConstraints gbc_classificationButton = new GridBagConstraints();
		gbc_classificationButton.insets = new Insets(5, 0, 5, 0);
		gbc_classificationButton.fill = GridBagConstraints.BOTH;
		gbc_classificationButton.gridx = 0;
		gbc_classificationButton.gridy = 1;
		buttonPanel.add(classificationButton, gbc_classificationButton);
		
		btnPathbuilder = new JButton("PathBuilder");
		GridBagConstraints gbc_btnPathbuilder = new GridBagConstraints();
		gbc_btnPathbuilder.insets = new Insets(5, 0, 5, 0);
		gbc_btnPathbuilder.fill = GridBagConstraints.BOTH;
		gbc_btnPathbuilder.gridx = 0;
		gbc_btnPathbuilder.gridy = 2;
		buttonPanel.add(btnPathbuilder, gbc_btnPathbuilder);
		
		contentView = new JPanel();
		contentView.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_contentView = new GridBagConstraints();
		gbc_contentView.ipadx = 5;
		gbc_contentView.ipady = 5;
		gbc_contentView.fill = GridBagConstraints.BOTH;
		gbc_contentView.gridx = 1;
		gbc_contentView.gridy = 0;
		mainPanel.add(contentView, gbc_contentView);
		contentView.setLayout(new GridLayout(1, 1, 5, 5));
		
		registerListeners();
	}

	private void registerListeners() {
		organizeBtn.addActionListener(controller::openOrganizeView);
		classificationButton.addActionListener(controller::openClassificationView);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.onWindowClose();
			}
		});
		btnPathbuilder.addActionListener(controller::openPathBuilder);
	}

	public void showView(JPanel panel) {
		contentView.removeAll();
		contentView.add(panel);
		contentView.validate();
		contentView.repaint();
		validate();
		repaint();
	}

}
