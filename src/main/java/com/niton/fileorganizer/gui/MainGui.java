package com.niton.fileorganizer.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

import com.niton.fileorganizer.controller.GuiController;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class MainGui extends JFrame {

	private JButton organizeBtn;
	private JPanel contentView;
	private GuiController controller;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 0;
		mainPanel.add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] {150, 0};
		gbl_buttonPanel.rowHeights = new int[]{42, 0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		organizeBtn = new JButton("Organize");
		GridBagConstraints gbc_organizeBtn = new GridBagConstraints();
		gbc_organizeBtn.insets = new Insets(5, 5, 5, 5);
		gbc_organizeBtn.fill = GridBagConstraints.BOTH;
		gbc_organizeBtn.gridx = 0;
		gbc_organizeBtn.gridy = 0;
		buttonPanel.add(organizeBtn, gbc_organizeBtn);
		
		contentView = new JPanel();
		contentView.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_contentView = new GridBagConstraints();
		gbc_contentView.fill = GridBagConstraints.BOTH;
		gbc_contentView.gridx = 1;
		gbc_contentView.gridy = 0;
		mainPanel.add(contentView, gbc_contentView);
		contentView.setLayout(new GridLayout(0, 1, 0, 0));
		
		registerListeners();
	}

	private void registerListeners() {
		organizeBtn.addActionListener(controller::openOrganizeView);
	}

	public void showView(JPanel panel) {
		contentView.removeAll();
		contentView.add(panel);
		contentView.validate();
	}

}
