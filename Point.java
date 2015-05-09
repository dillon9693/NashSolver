/*
 * Point class
 * Creates a point in the x-y plane given
 * two double coordinates, x and y
 * 
 */
public class Point {

	private double x;
	private double y;
	
	// Constructor
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/*
	 * toString method for Point class
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	/*
	 * Compares the y-coordinate with that of another Point
	 */
	public Point compareY(Point point2){
		if(this.y > point2.getY()){
			return this;
		}
		return point2;
	}
	
	/*
	 *  Getter methods
	 */
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
