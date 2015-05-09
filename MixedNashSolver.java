/*
 * MixedNash Solver class
 * Creates an object that calculates the pure and mixed strategy
 * equilibrium given input of a payoff matrix.
 * Can represent any game that the user wants
 * Outputs the dominant strategies in pure strategy equilibrium and
 * the p and q values for each player in mixed strategy equilibrium
 * 
 */

import java.util.Arrays;

public class MixedNashSolver {

	// boolean variables for types of Nash equilibirum
	private boolean hasPure;
	private boolean hasMixed;
	private boolean hasInfiniteMixedP1;
	private boolean hasInfiniteMixedP2;
	
	// boolean variables for pure strategies
	private boolean TopLeftIsPure = false;
	private boolean TopRightIsPure = false;
	private boolean BottomLeftIsPure = false;
	private boolean BottomRightIsPure = false;
	
	// variables for average payoffs in mixed strategy equilibrium
	private double playerI_avgPayoff;
	private double playerII_avgPayoff;

	// arrays holding best response functions
	private double[] playerI_BR_p_given_q;
	private double[] playerII_BR_q_given_p;
	
	// variables for p and q in mixed strategy equilibrium
	private double p;
	private double q;
	
	private Line t1;
	private Line b1;
	private Line l2;
	private Line r2;
	
	/*
	 * toString method for MixedNashSolvel object
	 * Outputs important values from object
	 */
	public String toString() {
		return "MixedNashSolver [hasPure=" + hasPure + ", hasMixed=" + hasMixed 
				+ ", hasInfiniteMixedP1=" + hasInfiniteMixedP1 + ", hasInfiniteMixedP2=" + hasInfiniteMixedP2
				+ ", TopLeftIsPure=" + TopLeftIsPure + ", TopRightIsPure="
				+ TopRightIsPure + ", BottomLeftIsPure=" + BottomLeftIsPure
				+ ", BottomRightIsPure=" + BottomRightIsPure
				+ ", playerI_avgPayoff=" + playerI_avgPayoff
				+ ", playerII_avgPayoff=" + playerII_avgPayoff
				+ ", playerI_BR_p_given_q="
				+ Arrays.toString(playerI_BR_p_given_q)
				+ ", playerII_BR_q_given_p="
				+ Arrays.toString(playerII_BR_q_given_p) + ", p=" + p + ", q="
				+ q + "]";
	}

	/*
	 * Constructor
	 * Creates payoff curves and best response functions
	 * Calculates pure and mixed strategy equilibrium
	 * 
	 */
	public MixedNashSolver(double[] matrix){
		
		/* First Step: Compartmentalize Payoff Table */
		//TOP points Player I
		Point[] p1_payoff_p     = {new Point(0.0, matrix[2]), new Point(1.0, matrix[0])};
		//BOTTOM points Player I
		Point[] p1_payoff_MIN_p = {new Point(0.0, matrix[6]), new Point(1.0, matrix[4])};
		
		//LEFT points Player II
		Point[] p2_payoff_q     = {new Point(0.0, matrix[5]), new Point(1.0, matrix[1])};
		//RIGHT points Player II
		Point[] p2_payoff_MIN_q = {new Point(0.0, matrix[7]), new Point(1.0, matrix[3])};
		
		
		/* Second Step: Generate Payoff Curves */
		//TOP curve Player I
		t1 = new Line(p1_payoff_p[0],p1_payoff_p[1]);
		//BOTTOM curve Player I
		b1 = new Line(p1_payoff_MIN_p[0], p1_payoff_MIN_p[1]);
		
		//LEFT curve Player II
		l2 = new Line(p2_payoff_q[0], p2_payoff_q[1]);
		//RIGHT curve Player II
		r2 = new Line(p2_payoff_MIN_q[0], p2_payoff_MIN_q[1]);
		
		/* *** Setup Complete *** */
		
		/* 
		 * Third Step: Check if There is a unique Nash Equilibrium (Each player has a dominant strategy)
		 * Ergo, no mixed NashE, and only one pure strategy 
		 */
		
		if(hasDominantStrat(t1,b1) && hasDominantStrat(l2,r2)){
			
			System.out.println("DOmStrat");
			this.hasMixed = false;
			this.hasPure = true;
			
			//if Top is dominant strategy for player I
			if(whichCurveIsDominantStrat(t1,b1).equals(t1)){
				//if Left is dominant strategy for player II
				if(whichCurveIsDominantStrat(l2,r2).equals(l2)){
					//this.TopLeftIsPure = true;
					this.setPureNash(true, false, false, false);
					
				}
				else{ // if Right is dominant strategy for player II
					//this.TopRightIsPure = true;
					this.setPureNash(false, true, false, false);
				}
			}
			else{ // if Bottom is dominant strategy for player I
				//if Left is dominant strategy for player II
				if(whichCurveIsDominantStrat(l2,r2).equals(l2)){
					//this.BottomLeftIsPure = true;
					this.setPureNash(false, false, true, false);
				}
				else{ //if Right is dominant strategy for player II
					//this.BottomRightIsPure = true;
					this.setPureNash(false, false, false, true);
				}
			}
			// Finished, No Mixed Nash to compute
		}
		
		else if(t1.isSameLine(b1) && l2.isSameLine(r2)){
			this.hasPure = true;
			this.hasMixed = true;
			this.hasInfiniteMixedP1 = true;
			this.hasInfiniteMixedP2 = true;
			this.setPureNash(true, true, true, true);
			
		}
		
		/* 
		 * Fourth Step: Check if there is exactly one dominant strategy 
		 */
		
		else if(hasDominantStrat(t1,b1) || hasDominantStrat(l2,r2)){
			
			System.out.println("1 dominant strategy");
			this.hasPure = true;
			
			if(hasDominantStrat(t1, b1)){
				if(whichCurveIsDominantStrat(t1, b1) == t1){
					this.p = 1.0;
					
					if(getMaxPayoffLine(p, l2, r2) == null){
						this.hasMixed = true;
						this.hasInfiniteMixedP2 = true;
						this.setPureNash(true, true, false, false);
					}
					else if(getMaxPayoffLine(p, l2, r2) == l2){
						this.setPureNash(true, false, false, false);
					}
					else if(getMaxPayoffLine(p, l2, r2) == r2){
						this.setPureNash(false, true, false, false);
					}
					else{}
					
				}
				else{ // Bottom is dominant strategy
					this.p = 0.0;
					
					if(getMaxPayoffLine(p, l2, r2) == null){
						this.hasMixed = true;
						this.hasInfiniteMixedP2 = true;
						this.setPureNash(false, false, true, true);
					}
					else if(getMaxPayoffLine(p, l2, r2) == l2){
						this.setPureNash(false, false, true, false);
					}
					else if(getMaxPayoffLine(p, l2, r2) == r2){
						this.setPureNash(false, false, false, true);
					}
					else{}
				}
			}
			
			
			else {// Player II has dominant strategy
				
				// Left is dominant strategy for Player II
				if(whichCurveIsDominantStrat(l2, r2) == l2){
					this.q = 1.0;
					
					if(getMaxPayoffLine(q, t1, b1) == null){
						this.hasMixed = true;
						this.hasInfiniteMixedP1 = true;
						this.setPureNash(true, false, true, false);
					}
					else if(getMaxPayoffLine(q, t1, b1) == t1){
						this.setPureNash(true, false, false, false);
					}
					else if(getMaxPayoffLine(q, t1, b1) == b1){
						this.setPureNash(false, false, true, false);
					}
					else{}
					
				}
				
				else{ // Right is dominant strategy for Player II
					this.q = 0.0;
					
					if(getMaxPayoffLine(q, t1, b1) == null){
						this.hasMixed = true;
						this.hasInfiniteMixedP1 = true;
						this.setPureNash(false, true, false, true);
					}
					else if(getMaxPayoffLine(q, t1, b1) == t1){
						this.setPureNash(false, true, false, false);
					}
					else if(getMaxPayoffLine(q, t1, b1) == b1){
						this.setPureNash(false, false, false, true);
					}
					else{}
				}
				
			}
		}
		
		
		
		
		

		/* 
		 * Fifth Step: Then there are no dominant strategies, and we have a mixed Nash equilibrium
		 * with zero to two Pure Nash. There is one intercept, that is some X val s.t. 0 < X > 1 
		 */
		else{
			System.out.println("No dominant strategies");
			this.hasMixed = true;
			
			// Calculates average payoffs for each player
			this.playerI_avgPayoff  = t1.robustIntercept(b1).getY();
			this.playerII_avgPayoff = l2.robustIntercept(r2).getY();
			
			// Calculates p for player I and 1 for player II
			this.p = l2.robustIntercept(r2).getX();
			this.q = t1.robustIntercept(b1).getX();
			
			// check for 3 pure strategies
			if(p == 0 || q == 0 || p == 1 || q == 1){
				this.hasPure = true;
				this.hasMixed = false;
				
				if(p == 0 && q == 0){
					System.out.println(p + "||" + q);
					this.setPureNash(false, true, true, true);
				}
				if(p == 0 && q == 1){
					System.out.println(p + "||" + q);
					this.setPureNash(true, false, true, true);
				}
				if(p == 1 && q == 0){
					System.out.println(p + "||" + q);
					this.setPureNash(true, true, false, true);
				}
				if(p == 1 && q == 1){
					System.out.println("p = q = 1");
					this.setPureNash(true, true, true, false);
				}
				
			}
			else{
				// As there is an intersect between x=0 and x=1, we know that Best Response switches at p and q values
				if(t1.getX_intercept() > b1.getY_intercept()){
					this.playerI_BR_p_given_q  = new double[] {1.0, this.q, 0.0};
				}
				else{
					this.playerI_BR_p_given_q  = new double[] {0.0, this.q, 1.0};
				}
				if(l2.getY_intercept() > r2.getY_intercept()){
					this.playerII_BR_q_given_p = new double[] {1.0, this.p, 0.0};
				}
				else{
					this.playerII_BR_q_given_p = new double[] {0.0, this.p, 1.0};
				}
				
			/* 
			 * Sixth Step: Given BR functions, we can compute if there are two pure equilibria if BR
			 * values are equal, otherwise, there are no Pure Nashes 
			 */
				
				System.out.println(Arrays.toString(playerI_BR_p_given_q));
				System.out.println(Arrays.toString(playerII_BR_q_given_p));
				
				if(playerI_BR_p_given_q[0] == playerII_BR_q_given_p[0]){
					this.hasPure = true;
					if(playerI_BR_p_given_q[0] == 1.0){
						//this.TopRightIsPure = true;
						//this.BottomLeftIsPure = true;
						this.setPureNash(false, true, true, false);
					}
					else{
						//this.TopLeftIsPure = true;
						//this.BottomRightIsPure = true;
						this.setPureNash(true, false, false, true);
					}
				}
			}
		}
	}
	
	/*
	 * Determines if a player has a dominant strategy available
	 * by checking if there is an intercept
	 */
	public boolean hasDominantStrat(Line payOffCurve1, Line payOffCurve2){
		// || lines, that are NOT on top of each other.
		if(payOffCurve1.intercept(payOffCurve2).length == 0){
			return true;
		}
		// One intersection.
		else if(payOffCurve1.intercept(payOffCurve2).length == 1){
			//if intersection occurs outside of interval (0,1)
			if(payOffCurve1.intercept(payOffCurve2)[0].getX() < 0 || payOffCurve1.intercept(payOffCurve2)[0].getX() > 1){
				return true;
			}
			else{ //if intersection occurs within (0,1)
				return false;
			}
		}
		// || lines, that are on top of each other.
		return false;
	}
	
	//determines which curve, if any, provides a player with a dominant strategy
	public Line whichCurveIsDominantStrat(Line payOffCurve1, Line payOffCurve2){
		//if dominant strategy exists based on curves
		if(hasDominantStrat(payOffCurve1, payOffCurve2)){
			if(payOffCurve1.get_y(0.5) > payOffCurve2.get_y(0.5)){
				return payOffCurve1;
			}
			return payOffCurve2;
		}
		return null;
	}
	
	
	
	public Line getMaxPayoffLine(double x, Line l1, Line l2){
		if(l1.get_y(x) > l2.get_y(x)){
			return l1;
		}
		else if(l1.get_y(x) < l2.get_y(x)){
			return l2;
		}
		else{
			return null;
		}
	}
	
	
	
	/*
	 * Setter function for pure Nash boolean variables
	 */
	public void setPureNash(boolean tl, boolean tr, boolean bl, boolean br){
		this.TopLeftIsPure = tl;
		this.TopRightIsPure = tr;
		this.BottomLeftIsPure = bl;
		this.BottomRightIsPure = br;
	}
	/*
	 *  Getter methods
	 */
	public boolean isHasPure() {
		return hasPure;
	}

	public boolean isHasMixed() {
		return hasMixed;
	}
	
	public boolean isHasInfiniteMixedP1() {
		return hasInfiniteMixedP1;
	}

	public boolean isHasInfiniteMixedP2() {
		return hasInfiniteMixedP2;
	}

	public boolean isTopLeftIsPure() {
		return TopLeftIsPure;
	}

	public boolean isTopRightIsPure() {
		return TopRightIsPure;
	}

	public boolean isBottomLeftIsPure() {
		return BottomLeftIsPure;
	}

	public boolean isBottomRightIsPure() {
		return BottomRightIsPure;
	}

	public double getPlayerI_avgPayoff() {
		return playerI_avgPayoff;
	}

	public double getPlayerII_avgPayoff() {
		return playerII_avgPayoff;
	}

	public double[] getPlayerI_BR_p_given_q() {
		return playerI_BR_p_given_q;
	}

	public double[] getPlayerII_BR_q_given_p() {
		return playerII_BR_q_given_p;
	}

	public double getP() {
		return p;
	}

	public double getQ() {
		return q;
	}
	
	public Line getT1(){return t1;}
	
	public Line getB1(){return b1;}
	
	public Line getL2(){return l2;}
	
	public Line getR2(){return r2;}
}
