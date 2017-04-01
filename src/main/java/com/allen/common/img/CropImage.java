package com.allen.common.img;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class CropImage extends JFrame implements MouseListener, MouseMotionListener {
	int drag_status = 0, c1, c2, c3, c4;

	public static void main(String args[]) {
		new CropImage().start();
	}

	public void start() {
		ImagePanel im = new ImagePanel("E:\\temp\\pic.jpg");
		add(im);
		setSize(400, 400);
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void draggedScreen() throws Exception {
		int w = c1 - c3;
		int h = c2 - c4;
		w = w * -1;
		h = h * -1;
		Robot robot = new Robot();
		BufferedImage img = robot.createScreenCapture(new Rectangle(c1, c2, w, h));
		File save_path = new File("screen1.jpg");
		ImageIO.write(img, "JPG", save_path);
		System.out.println("Cropped image saved successfully.");
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		repaint();
		c1 = arg0.getX();
		c2 = arg0.getY();
	}

	public void mouseReleased(MouseEvent arg0) {
		repaint();
		if (drag_status == 1) {
			c3 = arg0.getX();
			c4 = arg0.getY();
			try {
				draggedScreen();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		repaint();
		drag_status = 1;
		c3 = arg0.getX();
		c4 = arg0.getY();
	}

	public void mouseMoved(MouseEvent arg0) {

	}

	public void paint(Graphics g) {
		super.paint(g);
		int w = c1 - c3;
		int h = c2 - c4;
		w = w * -1;
		h = h * -1;
		if (w < 0)
			w = w * -1;
		g.drawRect(c1, c2, w, h);
	}
}
