package com.niton.fileorganizer.gui.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PathDisplay extends JPanel {
	public String[] path;
	public final DisplaySettings settings = new DisplaySettings();
	public PathDisplay(File f){
		setPath(f);
	}

	public PathDisplay(String[] path) {
		this.path = path;
	}

	public PathDisplay() {
		path = new String[0];
	}
	@Override
	protected void paintComponent(Graphics g) {
		Color background = getBackground();
		Color lines = getBackground().brighter();

		graphicalSetup(g);
		int h = getHeight();
		int w = getWidth();
		g.setColor(background);
		g.fillRect(0,0,w,h);
		g.setColor(lines);
		if(path.length == 0){
			g.drawRect(0,0,w-1,h-1);
			g.drawString("-", (w/2)-stringWidth(g,"-"), h-(h-stringHeight(g))/2);
		}else if(path.length == 1){
			g.drawRect(0,0,w-1,h-1);
			g.drawString(path[0], (w/2)-stringWidth(g,path[0]), h-(h-stringHeight(g))/2);
		}else{
			drawFullPath(g, h, w);
		}
	}

	private void graphicalSetup(Graphics g) {
		Map<?, ?> desktopHints =
				(Map<?, ?>) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");

		Graphics2D g2d = (Graphics2D) g;
		if (desktopHints != null) {
			g2d.setRenderingHints(desktopHints);
		}
		((Graphics2D)g).setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	}

	private void drawFullPath(Graphics g, int h, int w) {
		g.drawLine(0,0,0, h);
		int last = -settings.arrowSize;
		int relSize = w/path.length;
		int relTextSize = stringWidth(g, String.join("", path)) / path.length;
		for (int i = 0; i < path.length-1; i++) {
			last = drawCell(g,last, h,i,relSize,relTextSize);
		}
		int lastCellMinSize = settings.cellPadding*2+stringWidth(g,path[path.length-1])+settings.arrowSize;
		if(lastCellMinSize>w-last){
			Color bev = g.getColor();
			g.setColor(getBackground());
			g.fillRect(w-lastCellMinSize,0,lastCellMinSize,h);
			g.setColor(bev);
			drawArrow(g,w-lastCellMinSize,h);
		}
		last = Math.min(last,w-lastCellMinSize);
		g.drawLine(last,0, w-1,0);
		g.drawLine(last, h-1, w-1, h-1);
		g.drawLine(w-1,0, w-1, h-1);
		writeCentral(g,path[path.length-1],last+settings.arrowSize, w, h-2,-1,-1);
	}

	private void writeCentral(Graphics g, String s, int from, int to, int h,int stringHeight,int stringWidth) {
		if(stringHeight == -1)
			stringHeight = stringHeight(g);
		if(stringWidth == -1)
			stringWidth = stringWidth(g,s);
		from += settings.cellPadding;
		to -= settings.cellPadding;

		int w = to-from;
		Color bev = g.getColor();
		g.setColor(getForeground());
		g.drawString(s, from+(w/2)-(stringWidth/2), h/2+stringHeight/2);
		g.setColor(bev);
	}

	private int drawCell(Graphics g, int start, int height, int index,int avgCellSize,int avgWordSize) {
		String text = path[index];
		int stringWidth = stringWidth(g,text);
		int stringHeight = stringHeight(g);
		double wordProportion = (double) stringWidth/avgWordSize;
		int cellStart = start+settings.arrowSize;
		int cellEnd = cellStart+(settings.cellPadding*2)+stringWidth;
		int prefSize = (int) (avgCellSize*wordProportion);
		int missing = prefSize-(cellEnd-start);
		if(missing > 0){
			cellEnd += missing;
		}

		//Draw bottom and top
		g.drawLine(start,0,cellEnd,0);
		g.drawLine(start,height-1,cellEnd,height-1);

		drawArrow(g,cellEnd,height);

		writeCentral(g,text,cellStart,cellEnd,height-2,stringHeight,stringWidth);
		return cellEnd;
	}

	@Override
	public Dimension getPreferredSize() {
		if(path == null)
			return super.getMinimumSize();
		return new Dimension(getFontMetrics(getFont()).stringWidth(String.join("",path))+(path.length*((2*settings.cellPadding)+settings.arrowSize))-settings.arrowSize,settings.cellPadding*2+getFontMetrics(getFont()).getAscent());
	}

	@Override
	public Dimension getMinimumSize() {

		if(path == null || path.length == 0)
			return super.getMinimumSize();
		if(path.length == 1)
			return new Dimension(
					getFontMetrics(getFont()).stringWidth(path[0])+2*settings.cellPadding,
					getFontMetrics(getFont()).getHeight());
		return new Dimension(getFontMetrics(getFont()).stringWidth(String.join("",path[0],path[path.length-1]))+4*settings.cellPadding+settings.arrowSize,getFontMetrics(getFont()).getHeight());
	}

	private void drawArrow(Graphics g, int cellEnd, int h) {
		g.drawLine(cellEnd,0,cellEnd+settings.arrowSize,h/2);
		g.drawLine(cellEnd,h,cellEnd+settings.arrowSize,h/2);
	}

	private int stringWidth(Graphics g, String text){
		return g.getFontMetrics().stringWidth(text);
	}
	private int stringHeight(Graphics g){
		return g.getFontMetrics().getAscent();
	}

	public void setPath(File f) {
		path = f.getAbsolutePath().split(String.format("\\%s", System.getProperty("file.separator")));
		repaint();
	}

	private static class DisplaySettings {
		public int cellPadding = 5;
		public int arrowSize = cellPadding*2;
	}
}
