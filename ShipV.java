package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class ShipV extends GameObject {

	private int[] myShipX;
	private int[] myShipY;
	private double[] myShipD; 
	private double[] myShipA;
	private int[] myShipDrawX;
	private int[] myShipDrawY;
	
	Random rand = new Random(8);
	
	public ShipV(double x, double y, double speedX, double speedY, double travelDirection, double shipDirection, double rotationSpeed, double acceleration ,double accelerationSpeed ,int id) 
	{
		super(x, y, speedX, speedY, travelDirection, shipDirection, rotationSpeed, acceleration,accelerationSpeed, id);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		// gravity
		speedY = speedY + Game.GRAVITY;
				
		// rotation
		shipDirection = shipDirection + rotationSpeed;
		
		if (shipDirection <0) {shipDirection = shipDirection + 360 ;}
		if (shipDirection >360) {shipDirection = shipDirection - 360;}
		
		// speed
		   /// maximun acceleration is set here 0.05  or will accelerate infinitely
		if (acceleration < .05) { acceleration=acceleration+accelerationSpeed;}
		speedX = speedX + acceleration*Math.cos(Math.toRadians(shipDirection));
		speedY = speedY + acceleration*Math.sin(Math.toRadians(shipDirection));
		
		// friction ...deccelerate to simulate air or something to decrease speed
		if (accelerationSpeed <= 0 && acceleration >0) {acceleration = acceleration * .8;}
		
		x=x+speedX;
		y=y+speedY;
		
		//System.out.println(acceleration);
		for (int i =0 ; i < myShipX.length; i++) {
			if (myShipDrawX[i] <= Game.LIMIT_X1 && speedX <0) { 
				speedX = speedX * -.5;
				if (myShipDrawY[i]<y) {rotationSpeed = 5;}
				if (myShipDrawY[i]>y) {rotationSpeed = -5;}
				};

			if (myShipDrawX[i] >= Game.LIMIT_X2 && speedX >0) {
				speedX = speedX * -.5;
				if (myShipDrawY[i]<y) {rotationSpeed = -5;}
				if (myShipDrawY[i]>y) {rotationSpeed = 5;}
				
			};
			
			if (myShipDrawY[i] <= Game.LIMIT_Y1 && speedY <0) { 
				speedY = speedY * -.5;
				if (myShipDrawX[i]<x) {rotationSpeed = -5;}
				if (myShipDrawX[i]>x) {rotationSpeed = 5;}			
				
			};
			// repalce 300 wiht landing zone Y
			if ((myShipDrawY[i] >= Game.LIMIT_Y2 || myShipDrawY[i] >= 300 )&& speedY >0) { 
				speedY = speedY * -.5;
				if (myShipDrawX[i]<x) {rotationSpeed = 5;}
				if (myShipDrawX[i]>x) {rotationSpeed = -5;}
			
				
			};
		}
		
		
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		double myCos = Math.cos(Math.toRadians(shipDirection));
		double mySin = Math.sin(Math.toRadians(shipDirection));
		
		//coordinates of the center of the ship x and y
		int myX = (int)x;
		int myY = (int)y;
		
		//	relative coordinates x and y ....ship shape
		myShipX = new int[] {12,-8,-12,-8,-8,-12,-8};
		myShipY = new int[] {0, 8, 6, 4,-4,-6,-8};
		// vector distance from center of the ship D
		myShipD = new double[myShipX.length];
		// angle relative to the center of the ship
		myShipA = new double[myShipX.length];
		// final x and y coordinates to be drawn on the screen
		myShipDrawX = new int[myShipX.length];
		myShipDrawY = new int[myShipX.length];
		

		double flashy;
		if (acceleration > 0.005 ) {flashy = rand.nextInt(8);}
		else {flashy = 0;}
		
		int[] myTrustX = new int[] {-8,(int)(acceleration*-1000-8-flashy)  ,-8};
		
		int[] myTrustY = new int[] {-4,0,4};
		double[] myTrustD = new double[myTrustX.length];
		double[] myTrustA = new double[myTrustX.length];
		int[] myTrustDrawX = new int[myTrustX.length];
		int[] myTrustDrawY = new int[myTrustX.length];
				
		//  D is for Dimension (size) A is for Angle 
		// calculates each point angle and direction using formulas ... D = sqrt (x^2  + y^2),   A = atan2(x,y)
		// calculates each ship coordinate using x,y as center and direction as initial angel, calculates distance/angle of each point
		for (int i =0 ; i < myShipX.length; i++) {
		myShipD[i] = Math.sqrt(myShipX[i]*myShipX[i]+myShipY[i]*myShipY[i]);
		myShipA[i] = Math.atan2(myShipY[i],myShipX[i]);
		myShipDrawX[i]=myX+(int)(myShipD[i]*Math.cos(myShipA[i]+Math.toRadians(shipDirection)));
		myShipDrawY[i]=myY+(int)(myShipD[i]*Math.sin(myShipA[i]+Math.toRadians(shipDirection)));
		}

		for (int i =0 ; i < myTrustX.length; i++) {
		myTrustD[i] = Math.sqrt(myTrustX[i]*myTrustX[i]+myTrustY[i]*myTrustY[i]);
		myTrustA[i] = Math.atan2(myTrustY[i],myTrustX[i]);
		myTrustDrawX[i]=myX+(int)(myTrustD[i]*Math.cos(myTrustA[i]+Math.toRadians(shipDirection)));
		myTrustDrawY[i]=myY+(int)(myTrustD[i]*Math.sin(myTrustA[i]+Math.toRadians(shipDirection)));
		}
		
		
		
		
		
		// draw Truster
		g.setColor(Color.RED);
		g.drawPolygon(myTrustDrawX, myTrustDrawY, myTrustX.length);
	
		
		//g.setColor(Color.WHITE);
		//g.drawLine(myX, myY,(int)(x+10*myCos),(int)(y+10*mySin) );
		//g.drawOval((int)x, (int)y, 3, 3);
	
		// Draw the Ship		
		if (id ==1) {g.setColor(Color.GRAY);}
		if (id >=2) {g.setColor(Color.YELLOW);}
	
		
		
		//g.drawPolygon(myShipX, myShipY, myShipX.length);
		g.drawPolygon(myShipDrawX, myShipDrawY, myShipX.length);
		// draw some ship point in red to use for collision later
		g.setColor(Color.WHITE);
		//for (int i =0 ; i < myShipDrawX.length; i++) {
		//	g.drawLine(myShipDrawX[i],myShipDrawY[i], myShipDrawX[i],myShipDrawY[i]);
		//}
		int i = 0;
		g.drawLine(myShipDrawX[i],myShipDrawY[i], myShipDrawX[i],myShipDrawY[i]);
		
		
		//
		// Show speed x , y  for Ship 1
		g.setColor(Color.WHITE);
		g.drawString("Speed x", Game.WIDTH - 200, 40);
		g.drawString(    Double.toString(speedX)   , Game.WIDTH - 100, 40);

		g.setColor(Color.WHITE);
		g.drawString("Speed y", Game.WIDTH - 200, 60);
		g.drawString(    Double.toString(speedY)   , Game.WIDTH - 100, 60);

		g.setColor(Color.WHITE);
		g.drawString("Direction", Game.WIDTH - 200, 80);
		g.drawString(    Double.toString(shipDirection)   , Game.WIDTH - 100, 80);
		

		
		
		//, objects.get(1)).getX());
		

		
	}

	
	
}
