/*
 * Main method for testing Mixed Nash Solver.
 * Provides test cases (payoff tables) to be tested
 * with the Mixed Nash Solver class.
 * 
 */

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		//Examples of games that work.
		
		// Chicken game - WORKS
		double[] chickenGame = 	
		{
		    /*__________________*/	
			0,    0, /*|*/ -1,  1,
			/*_________|________*/
			1,   -1, /*|*/ -2,  -2	
		    /*_________|________*/
		};
		
		// Chicken Game Opposite - WORKS
		double[] chickenGameOpposite = 	
		{
		    /*__________________*/	
			 0,   0, /*|*/  1, -1,
			/*_________|________*/
			-1,   1, /*|*/  2,   2	
		    /*_________|________*/
		};
		
		//Will test these
		// U10 - WORKS
		double[] U10_Matrix = 	
		{
		    /*__________________*/	
			  3,  1, /*|*/  2,  2,
			/*_________|________*/
			  0,  2, /*|*/  2,  3	
		    /*_________|________*/
		};
		
		//Professor-Student game 
		double[] teacherGameMatrix = 	
		{
		    /*__________________*/	
			  3,  3, /*|*/ -1,  4,
			/*_________|________*/
			 -2,  1, /*|*/  0,  0	
		    /*_________|________*/
		};
		
		// Prisoner's Dilemma - WORKS
		double[] prisoners = 	
		{
		    /*__________________*/	
			  -10,  -10, /*|*/ -1,  -25,
			/*_________|________*/
			 -25,  -1, /*|*/  -3,  -3	
		    /*_________|________*/
		};
		

		double[] onePureHasMixed = 	
		{
		    /*__________________*/	
		    5,   5, /*|*/  0,   5,
			/*_________|________*/
			0,   6, /*|*/   5,  0	
		    /*_________|________*/
		};

		//Examples of Games that I've created/come across that
		//are not "solvable" so far.
		double[] zero = 	
		{
		    /*__________________*/	
		     0,   0, /*|*/   0,   0,
			/*_________|________*/
			 0,   0, /*|*/   0 ,  0	
		    /*_________|________*/
		};
		
		// New Examples
		
		//Heads & Tails - WORKS
		double[] headsTails =
		{
			 /*__________________*/	
			  -1,   1, /*|*/   1,   -1,
			 /*_________|________*/
			  1,   -1, /*|*/   -1,  1	
			 /*_________|________*/
		};
		
		//Battle of the Sexes - WORKS
		double[] battle =
		{
			 /*__________________*/	
			  2,   1, /*|*/   0,   0,
			 /*_________|________*/
			  0,   0, /*|*/   1,  2	
			 /*_________|________*/
		};
		
		//Heads & Tails 2 - WORKS
		double[] headsTails2 =
		{
			 /*__________________*/	
			  0,   1, /*|*/   1,   0,
			 /*_________|________*/
			  1,   0, /*|*/   0,  1	
			 /*_________|________*/
		};
		
		//U7 - James & Dean - WORKS
		double[] jamesDean =
		{
			/*__________________*/	
			 0,   0, /*|*/   -1,   1,
			/*_________|________*/
			 2,   -1, /*|*/   -2,  -2	
			/*_________|________*/
		};
		
		//Harry & Sally (from book) - WORKS
		double[] harrySally =
		{
			/*__________________*/	
			 1,   1, /*|*/   0,   0,
			/*_________|________*/
			 0,   0, /*|*/   2,  2	
			/*_________|________*/
		};
				
		//Test Code
		
		System.out.println("Begin Test\n");
		
		System.out.println("Chicken Game");
		MixedNashSolver t1 = new MixedNashSolver(chickenGame);
		System.out.println("p = " + t1.getP() + " || q = " + t1.getQ());
		System.out.println(t1);
		System.out.println("");
		
		System.out.println("Chicken Game Opposite");
		MixedNashSolver t2 = new MixedNashSolver(chickenGameOpposite);
		System.out.println("p = " + t2.getP() + " || q = " + t2.getQ());
		System.out.println(t2);
		System.out.println("");
		
		System.out.println("Heads & Tails");
		MixedNashSolver t3 = new MixedNashSolver(headsTails);
		System.out.println("p = " + t3.getP() + " || q = " + t3.getQ());
		System.out.println(t3);
		System.out.println("");
		
		System.out.println("Battle of the Sexes");
		MixedNashSolver t4 = new MixedNashSolver(battle);
		System.out.println("p = " + t4.getP() + " || q = " + t4.getQ());
		System.out.println(t4);
		System.out.println("");
		
		System.out.println("Heads & Tails 2");
		MixedNashSolver t5 = new MixedNashSolver(headsTails2);
		System.out.println("p = " + t5.getP() + " || q = " + t5.getQ());
		System.out.println(t5);
		System.out.println("");
		
		System.out.println("Professor-Student");
		MixedNashSolver t6 = new MixedNashSolver(teacherGameMatrix);
		System.out.println("p = " + t6.getP() + " || q = " + t6.getQ());
		System.out.println(t6);
		System.out.println("");

		System.out.println("U10 - Column/Row");
		MixedNashSolver t7 = new MixedNashSolver(U10_Matrix);
		System.out.println("p = " + t7.getP() + " || q = " + t7.getQ());
		System.out.println(t7);
		System.out.println("");
		
		System.out.println("U7 - James/Dean");
		MixedNashSolver t8 = new MixedNashSolver(jamesDean);
		System.out.println("p = " + t8.getP() + " || q = " + t8.getQ());
		System.out.println(t8);
		System.out.println("");
		
		System.out.println("Harry & Sally");
		MixedNashSolver t9 = new MixedNashSolver(harrySally);
		System.out.println("p = " + t9.getP() + " || q = " + t9.getQ());
		System.out.println(t9);
		System.out.println("");
		
		System.out.println("Prisoner's Dilemma");
		MixedNashSolver t10 = new MixedNashSolver(prisoners);
		System.out.println("p = " + t10.getP() + " || q = " + t10.getQ());
		System.out.println(t10);
		System.out.println("");
		
		System.out.println("\nTest Finished");
		
		
		/*
		MixedNashSolver teacherGame = new MixedNashSolver(teacherGameMatrix);

		
		
		//Test
		
		System.out.println(new Point(0.0, 1.0).compareY(new Point(0.0, 0.0)));
		
		
		Line l1 = new Line(new Point(0,1.1), new Point(1,4));
		Line l2 = new Line(new Point(0,1), new Point(1,5));
		//
		System.out.println(l1.isSameLine(l2));
		System.out.println(teacherGame.hasDominantStrat(l1, l2));
		//System.out.println(teacherGame.whichCurveIsDominantStart(l1, l2).equals(l2));
		
		*/
		
		
		
		
		/*
		//Player I	
		//HELP
		double[][] P1_pay_off_p = {{0.0, matrix[2]},{1.0, matrix[0]}};		// p
		//IGNORE
		double[][] P1_pay_off_MIN_p = {{0.0, matrix[6]}, {1.0, matrix[4]}};	// 1-p
		

		//Player II
		//WORK
		double[][] P2_pay_off_q = {{0.0, matrix[5]}, {1.0, matrix[1]}};		// q
		//SLACK
		double[][] P2_pay_off_MIN_q = {{0.0, matrix[7]}, {1.0, matrix[3]}; // 1-q
		
		
		//Player I Curves
		Line l1 = new Line(P1_pay_off_MIN_p[0], P1_pay_off_MIN_p[1]);
		Line l2 = new Line(P1_pay_off_p[0], P1_pay_off_p[1]);
		
		//Player II Curves
		Line l3 = new Line(P2_pay_off_MIN_q[0], P2_pay_off_MIN_q[1]);
		Line l4 = new Line(P2_pay_off_q[0], P2_pay_off_q[1]);
		
		
		System.out.println(l1.toString());
		System.out.println(l2.toString());
		System.out.println(l1.intercept(l1, l2));
		
		System.out.println(l3.toString());
		System.out.println(l4.toString());
		System.out.println(l3.intercept(l3, l4));
		
		//System.out.print(Arrays.toString(matrix));
		*/
	}

}
