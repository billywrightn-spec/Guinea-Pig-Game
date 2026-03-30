package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import java.awt.DisplayMode;

public class Main {
	public static void main(String[] args) {

	    JFrame window = new JFrame("Guinea pig");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setResizable(true);

	    GamePanel gp = new GamePanel();
	    window.add(gp);

	    window.pack();                 // makes window match GamePanel preferred size
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);

	    gp.startGameThread();
	}
}
