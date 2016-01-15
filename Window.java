package Main;

import java.awt.Canvas;
import java.awt.Dimension;


import javax.swing.JFrame;

public class Window extends Canvas {
	

	private static final long serialVersionUID = 1L;

	public Window (int width, int heigth, String title , Game game) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension (width,heigth));
		frame.setMinimumSize(new Dimension(width,heigth));
		frame.setMaximumSize(new Dimension(width,heigth));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.add(game);
		frame.setVisible(true);
		
	
		
		game.start();
		
		
		
		
	}



}
