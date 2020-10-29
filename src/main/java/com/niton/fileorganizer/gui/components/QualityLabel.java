package com.niton.fileorganizer.gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class QualityLabel extends JLabel {
	private final double quality;
	public final Settings settings = new Settings();
	public QualityLabel(String text, double quality) {
		super(text);
		this.quality = quality;
		setBorder(new LineBorder(UIManager.getColor("Panel.foreground")));
		setFont(getFont().deriveFont(13));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int barHeight = (int) (getHeight()*settings.percent);
		int barWidth = (int) ((quality+0.1)*getWidth());
		Color c = new Color((int)((1-quality)*255), (int)(255*quality), 35);
		Color pre = g.getColor();
		g.setColor(c);
		g.fillRect(0,getHeight()-barHeight,barWidth,barHeight);
		g.setColor(pre);
	}
	private static class Settings{
		public double percent = 0.20;
	}
}
