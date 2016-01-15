package Main;

import java.awt.Graphics;


import java.util.ArrayList;
import java.util.Random;

public class Handler {
	
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	Random randomGenerator = new Random(8);
	public boolean Ship2Right = false;
	public boolean Ship2Left = false;
	public boolean Ship2Trust = false;
	public double Ship2RightTimer = 0;
	public double Ship2LeftTimer = 0;
	public double Ship2TrustTimer = 0;
	
	public void tick() {
		
		// Ship 2 AI
		
		if (!Ship2Right && !Ship2Left && !Ship2Trust) {
			if(randomGenerator.nextInt(20) == 1 ) {Ship2Right = true; Ship2RightTimer = randomGenerator.nextInt(20);}
			if(randomGenerator.nextInt(20) == 2 ) {Ship2Left = true; Ship2LeftTimer = randomGenerator.nextInt(20);}
			if(randomGenerator.nextInt(20) == 3 ) {Ship2Trust = true; Ship2TrustTimer = randomGenerator.nextInt(5);}
						
		} 
		
		
		for (int i = 0 ;  i < objects.size(); i++ ) {
		GameObject tempObject = objects.get(i);
		if (tempObject.id >= 2) {
			/// code here for Ship2 AI
			if (Ship2TrustTimer > 0 ) {if(tempObject.id==2){Ship2TrustTimer--;} ; tempObject.setAcceleration(.1); } else {Ship2Trust = false ;}
			if (Ship2LeftTimer > 0 ) {if(tempObject.id==2){Ship2LeftTimer--;} ; tempObject.setShipDirection(tempObject.getShipDirection()-5);; } else {Ship2Left = false ;}
			if (Ship2RightTimer > 0 ) {if(tempObject.id==2){Ship2RightTimer--;} ; tempObject.setShipDirection(tempObject.getShipDirection()+5);; } else {Ship2Right = false ;}

			
		}
		tempObject.tick();
		
		}	
		
		
	}
	public void render(Graphics g) {
	
		for (int i = 0 ;  i < objects.size() ; i++ ) {
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
	
		}
	
	
		
		
	}
	
	public void addObject (GameObject object) {
		objects.add(object);
	}
	
	public void removeObject (GameObject object) {
		objects.remove(object);
	}

		
}
