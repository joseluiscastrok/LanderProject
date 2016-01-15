package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private Random randomgen1 = new Random();
	public KeyInput (Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed (KeyEvent e) {
	
		int key = e.getKeyCode();
//		System.out.println("KEY : "+key);		
		
		if (key == KeyEvent.VK_ESCAPE) {	System.exit(0);}

		
		
		
		for (int i = 0 ;  i < handler.objects.size(); i++ ) {
		GameObject tempObject = handler.objects.get(i);		
		if (tempObject.id == 1)  { 
				if (key == KeyEvent.VK_RIGHT) {	tempObject.setRotationSpeed(4);}
				if (key == KeyEvent.VK_LEFT) {	tempObject.setRotationSpeed(-4);}
				if (key == KeyEvent.VK_UP) {	tempObject.setAccelerationSpeed(.0020);}
				
			
		}		
		}
		
	}
	public void keyReleased (KeyEvent e) {
		int key = e.getKeyCode();
//		System.out.println("KEY : "+key);		
		for (int i = 0 ;  i < handler.objects.size(); i++ ) {
		GameObject tempObject = handler.objects.get(i);		
		if (tempObject.id == 1)  { 
				if (key == KeyEvent.VK_RIGHT) {	tempObject.setRotationSpeed(0);}
				if (key == KeyEvent.VK_LEFT) {	tempObject.setRotationSpeed(0);}
				if (key == KeyEvent.VK_UP) {	tempObject.setAccelerationSpeed(0);}
				if (key == KeyEvent.VK_SPACE) {	
					Game.reinit = true;
				}
				
			
		}		
		}
	
		
	}
	public void keyTyped (KeyEvent e) {
//		System.out.println("ddddd");

		
		
	}
}
