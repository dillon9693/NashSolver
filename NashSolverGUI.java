import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class NashSolverGUI {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuHelp;
	private JMenuItem menuAbout;
	private JLabel lblTop;
	private JLabel lblBottom;
	private JTextField p1tl;
	private JTextField p2tl;
	private JTextField p1tr;
	private JTextField p2tr;
	private JTextField p1bl;
	private JTextField p2bl;
	private JTextField p1br;
	private JTextField p2br;
	private JPanel player1MovesTop;
	private JPanel player1MovesBottom;
	private JPanel player2MoveChoices;
	private JLabel lblLeftLabel;
	private JLabel lblRightLabel;
	//private JLabel p1label;
	//private JLabel p2label;
	private JTextField p1label;
	private JTextField p2label;
	private JLabel lblOfPlayers;
	private JComboBox numPlayersComboBox;
	private JComboBox dimComboBox;
	private JLabel lblDimensions;
	private JPanel optionPanel;
	private JSeparator separator;
	
	//Player I and II's lines
	private JFrame playerLines;
	private JFrame p1Lines;
	private JFrame p2Lines;
	//private JPanel playerLinesComp;
	private LineComponent p1LineComp;
	private LineComponent p2LineComp;
	
	
	private double p1tlVal = 0, p1trVal = 0, p1blVal = 0, p1brVal = 0;
	private double p2tlVal = 0, p2trVal = 0, p2blVal = 0, p2brVal = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NashSolverGUI window = new NashSolverGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NashSolverGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Create GUI frame with dimensions
		frame = new JFrame("Nash Solver");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		// Creates menu bar with Help and About items
		menuBar = new JMenuBar();
		menu = new JMenu("About");
		menuBar.add(menu);
		
		//Menu Help item
		menuHelp = new JMenuItem("Help");
		menuHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String helpMsg = "To find Nash Equilibrium:\n\n";
				helpMsg += "1. Input payoffs for each player into table\n";
				helpMsg += "\t\t a. The left text field for each combination of moves represents the payoff for Player I\n";
				helpMsg += "\t\t b. The right text field for each combination of moves represents the payoff for Player II\n\n";
				helpMsg += "2. Click 'Find Nash Equilibrium' for solution\n\n";
				helpMsg += "****To Reset current values in payoff table, click the Reset button****";
				JOptionPane.showMessageDialog(null, helpMsg, "Help", JOptionPane.INFORMATION_MESSAGE);
			}	
		});
		menu.add(menuHelp);
		
		//Menu About item
		menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String aboutMsg = "Welcome to the 2x2 Nash Equilibrium Solver!\n\n";
				aboutMsg += "This tool provides users with the ability to find the pure\n";
				aboutMsg += "and mixed strategy equilibria of a game.\n\n";
				aboutMsg += "Input: payoffs of player I and player II as decimal numbers\n\n";
				aboutMsg += "Created by Dillon Kerr & Oscar Uribe";
				JOptionPane.showMessageDialog(null, aboutMsg, "Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu.add(menuAbout);
		
		// Add menubar to frame
		frame.setJMenuBar(menuBar);
		
		//Label for player II
		//p2label = new JLabel("II");
		p2label = new JTextField();
		p2label.setText("II");
		p2label.setHorizontalAlignment(SwingConstants.CENTER);
		p2label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		p2label.setBounds(298, 76, 84, 25);
		frame.getContentPane().add(p2label);
		
		//Panel containing player II's move choices, Left & Right
		player2MoveChoices = new JPanel();
		player2MoveChoices.setBounds(180, 100, 320, 40);
		frame.getContentPane().add(player2MoveChoices);
		player2MoveChoices.setLayout(new GridLayout(1, 0, 0, 0));
		
		//Label for Left move
		lblLeftLabel = new JLabel("Left");
		lblLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		player2MoveChoices.add(lblLeftLabel);
		
		//Label for Right move
		lblRightLabel = new JLabel("Right");
		lblRightLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		player2MoveChoices.add(lblRightLabel);
		
		//Player I label
		//p1label = new JLabel("I");
		p1label = new JTextField();
		p1label.setText("I");
		p1label.setHorizontalAlignment(SwingConstants.CENTER);
		p1label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		p1label.setBounds(18, 180, 72, 25);
		frame.getContentPane().add(p1label);
		
		//Panel containing player I and II's Top payoffs
		player1MovesTop = new JPanel();
		player1MovesTop.setBounds(100, 140, 400, 50);
		frame.getContentPane().add(player1MovesTop);
		player1MovesTop.setLayout(new GridLayout(1, 0, 0, 0));
		
		//Label for Top move
		lblTop = new JLabel("Top");
		lblTop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesTop.add(lblTop);
		
		//Field for player I's Top-Left move
		p1tl = new JTextField();
		p1tl.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesTop.add(p1tl);
		p1tl.setColumns(10);
		
		//Field for player II's Top-Left move
		p2tl = new JTextField();
		p2tl.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesTop.add(p2tl);
		p2tl.setColumns(10);
		
		//Field for player I's Top-Right move
		p1tr = new JTextField();
		p1tr.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesTop.add(p1tr);
		p1tr.setColumns(10);
		
		//Field for player II's Top-Right move
		p2tr = new JTextField();
		p2tr.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesTop.add(p2tr);
		p2tr.setColumns(10);
		
		//Panel containing player I and II's Bottom payoffs
		player1MovesBottom = new JPanel();
		player1MovesBottom.setBounds(100, 190, 400, 50);
		frame.getContentPane().add(player1MovesBottom);
		player1MovesBottom.setLayout(new GridLayout(1, 0, 0, 0));
		
		//Label for Bottom move
		lblBottom = new JLabel("Bottom");
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBottom.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesBottom.add(lblBottom);
		
		//Field for player I's Bottom-Left move
		p1bl = new JTextField();
		p1bl.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesBottom.add(p1bl);
		p1bl.setColumns(10);
		
		//Field for player II's Bottom-Left move
		p2bl = new JTextField();
		p2bl.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesBottom.add(p2bl);
		p2bl.setColumns(10);
		
		//Field for player I's Bottom-Right move
		p1br = new JTextField();
		p1br.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesBottom.add(p1br);
		p1br.setColumns(10);
		
		//Field for player II's Bottom-Right move
		p2br = new JTextField();
		p2br.setHorizontalAlignment(SwingConstants.CENTER);
		player1MovesBottom.add(p2br);
		p2br.setColumns(10);
		
		/* 
		 * Button which takes in inputted payoff table, creates an instance
		 * of MixedNashSolver, and calculates the pure and mixed strategy Nash
		 * equilibria.
		 */
		JButton findNashButton = new JButton("Find Nash Equilibrium");
		findNashButton.setBounds(100, 300, 300, 30);
		findNashButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				//check if input can be converted to double
				try{
					p1tlVal = Double.parseDouble(p1tl.getText());
					p1trVal = Double.parseDouble(p1tr.getText());
					p1blVal = Double.parseDouble(p1bl.getText());
					p1brVal = Double.parseDouble(p1br.getText());	
					
					p2tlVal = Double.parseDouble(p2tl.getText());
					p2trVal = Double.parseDouble(p2tr.getText());
					p2blVal = Double.parseDouble(p2bl.getText());
					p2brVal = Double.parseDouble(p2br.getText());	
				}
				//if not, throw error and notify user with popup box
				catch(NumberFormatException e){
					String errorMsg = "Invalid Input: \nAll inputs must be integers.";
					System.out.println(errorMsg);
					JOptionPane.showMessageDialog(null, errorMsg, "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				// 1D Matrix containing the payoffs of each player
				double[] gameMatrix = {p1tlVal, p2tlVal, p1trVal, p2trVal, p1blVal, p2blVal, p1brVal, p2brVal};
				
				/* Display each player's set of payoffs in console (for debugging)
				System.out.println("Player I's payoffs:");
				for(int i=0; i< gameMatrix.length; i+=2){
					System.out.print(gameMatrix[i] + "; ");
				}
				
				System.out.println("");
				
				System.out.println("Player II's payoffs:");
				for(int i=1; i< gameMatrix.length; i+=2){
					System.out.print(gameMatrix[i] + "; ");
				}
				*/
				
				//Create Nash Solver with created payoff matrix
				MixedNashSolver solver = new MixedNashSolver(gameMatrix);
				/* Output elements of MixedNashSolver (debugging)
				System.out.println("Inputted Game Matrix");
				System.out.println("p = " + solver.getP() + " || q = " + solver.getQ());
				System.out.println(solver);
				*/
				
				String nashMsg = createNashMsg(solver);
				JOptionPane.showMessageDialog(null, nashMsg, "Nash Equilibrium", JOptionPane.INFORMATION_MESSAGE);
				
				playerLines = new JFrame(p1label.getText() + "'s & " + p2label.getText() +"'s Lines");
				//p2Lines = new JFrame(p2label.getText() +"'s Lines");
				playerLines.setBounds(100, 100, 600, 400);
				
				p1LineComp = new LineComponent();
				p2LineComp = new LineComponent();
				
				playerLines.getContentPane().add(p1LineComp, BorderLayout.CENTER);
				playerLines.getContentPane().add(p2LineComp, BorderLayout.CENTER);
				
				/*
				p1Lines.getContentPane().add(p1LineComp, BorderLayout.CENTER);
				p2Lines.getContentPane().add(p2LineComp, BorderLayout.CENTER);
				*/
				Line t1 = solver.getT1();
				Line b1 = solver.getB1();
				Line l2 = solver.getL2();
				Line r2 = solver.getR2();
				
				p1LineComp.addLine(t1.get_y(0.0), 0.0, t1.get_y(1.0), 1.0);
				p1LineComp.addLine(b1.get_y(0.0), 0.0, b1.get_y(1.0), 1.0);
				p2LineComp.addLine(l2.get_y(0.0), 0.0, l2.get_y(1.0), 1.0);
				p2LineComp.addLine(r2.get_y(0.0), 0.0, r2.get_y(1.0), 1.0);
					
				playerLines.pack();
				playerLines.setVisible(true);
				/*
				p1Lines.setVisible(true);
				p2Lines.setVisible(true);
				*/
			}
			
		});
		frame.getContentPane().add(findNashButton);
		
		/*
		 *  Button to clear all entries in payoff table
		 */
		JButton reset = new JButton("Reset");
		reset.setBounds(420, 300, 80, 30);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				p1tl.setText("");
				p1tr.setText("");
				p1bl.setText("");
				p1br.setText("");

				p2tl.setText("");
				p2tr.setText("");
				p2bl.setText("");
				p2br.setText("");
			}
		});
		frame.getContentPane().add(reset);

	}
	
	// Creates output message of Nash equilibria
	private String createNashMsg(MixedNashSolver n){
		String msg = "****Nash Equilibrium****\n\n";
		if(n.isHasPure()){
			
			msg += "The Nash Equilibria in pure strategies are: \n";
			if(n.isTopLeftIsPure()){
				msg += "(Top, Left)\n";
			}
			if(n.isTopRightIsPure()){
				msg += "(Top, Right)\n";
			}
			if(n.isBottomLeftIsPure()){
				msg += "(Bottom, Left)\n";
			}
			if(n.isBottomRightIsPure()){
				msg += "(Bottom, Right)\n";
			}
			msg += "\n";
		}
		else {
			msg += "There is no Nash equilibrium in pure strategies.\n\n";
		}
		if(n.isHasMixed()){
			msg += "The Mixed Nash Equilibirum occurs when:\n";
			if(n.isHasInfiniteMixedP1() && n.isHasInfiniteMixedP2()){
				msg += "Player " + p1label.getText() + " has infinite mixed strategies.\n";
				msg += "Player " + p2label.getText() + " has infinite mixed strategies.\n";
			}
			else if(n.isHasInfiniteMixedP1()){
				if(n.isTopRightIsPure()){
					msg += "Player " + p1label.getText() + " has infinite mixed strategies.\n";
					msg += "Player " + p2label.getText() + " has a dominant strategy playing Right.";
				}
				else {
					msg += "Player " + p1label.getText() + " has infinite mixed strategies.\n";
					msg += "Player " + p2label.getText() + " has a dominant strategy playing Left.";
				}
				
			}
			else if(n.isHasInfiniteMixedP2()){
				if(n.isTopRightIsPure()){
					msg += "Player " + p1label.getText() + " has a dominant strategy.\n";
					msg += "Player " + p2label.getText() + " has infinite mixed playing Top.";
				}
				else {
					msg += "Player " + p1label.getText() + " has a dominant strategy.\n";
					msg += "Player " + p2label.getText() + " has infinite mixed playing Bottom.";
				}
			}
			else {
				msg += "Player " + p1label.getText() + " plays the mixed strategy of (Top, Bottom) = (" + round(n.getP()) +"," + round((1.0 - n.getP())) + ").\n";
				msg += "Player " + p1label.getText() + "'s average payoff is " + round(n.getPlayerI_avgPayoff()) + ".\n";

				msg += "Player " + p2label.getText() + " plays the mixed strategy of (Left, Right) = (" + round(n.getQ()) +"," + round((1.0 - n.getQ())) + ").\n";
				msg += "Player " + p2label.getText() + "'s average payoff is " + round(n.getPlayerII_avgPayoff()) + ".\n";
			}
		}
		else {
			msg += "There is no Nash equilibrium in mixed strategies.\n";
		}
		return msg;
	}
	
	// Rounds double to 2 decimal places
	private double round(double d){
		return Math.round(d * 100.0) / 100.0;
	}
}
