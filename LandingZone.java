package Main;

public class LandingZone  {
	
	private double x ;
	private double y ;
	private double sizeX;
	private double x1;
	private double y1;
	
	
	public LandingZone (double x, double y, double sizeX) {
		
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.x1 = x+sizeX;
		this.y1 = y;
		
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSizeX() {
		return sizeX;
	}

	public void setSizeX(double sizeX) {
		this.sizeX = sizeX;
	}

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}


}
