/*
 * Line class
 * Calculates the line between two given points.
 * Provides slope, y-intercept, and x-intercept of the line
 * for use in the MixedNashSolver class
 * 
 */
public class Line {
	
	//double variable representing the slope of the Line
	private double slope;
	
	//Point variables representings x- and y-intercepts of Line
	private Point y_intercept;
	private Point x_intercept;
	
	/*
	 * Constructor
	 */
	public Line(Point p1, Point p2){
		this.slope = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
		this.y_intercept = new Point(0.0, (p1.getY()-(this.slope*p1.getX())));
		this.x_intercept = new Point(-this.y_intercept.getY()/this.slope, 0.0);
	}

	/*
	 * toString method for Line class
	 * Displays slope, y-intercept, x-intercept
	 */
	public String toString() {
		return "Line [slope=" + slope + ", y_intercept=" + y_intercept
				+ ", x_intercept=" + x_intercept + "]";
	}
	
	/*
	 * Determines the x-value of the intercept of two lines
	 * Must know that an intercept exists
	 */
	public Point robustIntercept(Line line2){
		double X_val = -(this.getY_intercept()-line2.getY_intercept()) / (this.getSlope()-line2.getSlope());
		return new Point(X_val, this.get_y(X_val));
	}
	
	/*
	 * Determines if an intercept occurs between two lines
	 * Outputs array of size 0 if no intercept
	 * Outputs array of size 1 containing point of intercept if 1 intercept
	 * Outputs array of size 2 if same line
	 */
	public Point[] intercept(Line line2){
		//if line have same slope
		if(this.getSlope() == line2.getSlope()){
			//if lines have same x-intercept
			if(this.isSameLine(line2)){
				//must be the same line
				Point[] arr = {new Point(0.0, this.get_y(0.0)), new Point(1.0, this.get_y(1.0))};
				return arr;
			}
			else{ //if they don't intercept
				Point[] arr = {};
				return arr;
			}
		}
		//else they intercept one another at one point
		double X_val = -(this.getY_intercept()-line2.getY_intercept()) / (this.getSlope()-line2.getSlope());	
		Point[] arr = {new Point(X_val,this.get_y(X_val))};	
		return arr;
	}
	
	/*
	 * Check if same as another line by comparing x- and y-intercepts
	 */
	public boolean isSameLine(Line line2){
		return ((this.get_y(0.0) == line2.get_y(0.0)) && (this.get_y(1.0) == line2.get_y(1.0)));
	}
	
	/*
	 * Getter methods
	 */
	public double get_x(double y){
		return (y-y_intercept.getY())/slope;
	}
	
	public double get_y(double x){
		return slope*x + y_intercept.getY();
	}
	
	public double getSlope() {
		return slope;
	}

	public double getY_intercept() {
		return y_intercept.getY();
	}
	
	public double getX_intercept() {
		return x_intercept.getX();
	}	
}

