import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.imageio.*; // for jar file
/**
 * AnimationExampleLameAndBuggy.java
 *
 *
 * @author Hamza Khan 
 * Date; Oct 2023
 * Description; Make program accroing to the requirments on the lab
 * 
 * 
 * METHOD LIST;
 * 
 * public class AnimationExampleLameAndBuggy extends JFrame implements ActionListener{ // class implements actionlister and jframe

 * 
 * public AnimationExampleLameAndBuggy(){ // runs all details under method to create the GUI
 * 
 * 
 * 
 * public void actionPerformed (ActionEvent e){ // method which dictates what happens when buttons are clicked 
 * 
 * 
 * public static void main(String[] args) { // main method which runs the program 
 * 
 */

public class AnimationExampleLameAndBuggy extends JFrame implements ActionListener{

	//instance variable for components
	private JPanel drawingPanel, controlPanel;
	private JButton btnStart, btnExit, btnStop; 

	//instance variables for picture arrays, speed and positions
	private int[] xPos, yPos, xVel, yVel; 
	private Timer timer;   // animation timer
	private int width, height; // height and width of frame
	private Die d1, d2, d3, d4;   // dice to randomize location and speeds as well as steps 
	private TextPicture titleText;  // the text that will be displayed
	private int reset = 0;  //value to reset the timer and repeat the program
	private SmartMinion [] pic;  //array


	/**
	 * Creates a new instance of AnimationExample.
	 */
	
	
	
	
	
	public AnimationExampleLameAndBuggy(){
		super("My Simple Animation Example");  // title for the frame

		String input = JOptionPane.showInputDialog("Enter the Amount of Minions You Would Like?:"); //number of minions
        int arraySize = Integer.parseInt(input); //set array size based of minions 

        //intialize array for all variables below 
        pic = new SmartMinion[arraySize];
        xPos = new int[arraySize];
        yPos = new int[arraySize];
        xVel = new int[arraySize];
        yVel = new int[arraySize];
		
		
		// initialize the width of the frame
		width = 500;
		height = 600;

		setLayout(null);  // layout for my frame

		controlPanel = new JPanel();  // panel for buttons
		drawingPanel = new JPanel(); // used only to set the boundaries to draw
		controlPanel.setBackground(Color.BLACK); //change the colour of the control panel to black

		
		btnStart = new JButton("Let's move");  //
		btnExit = new JButton("Exit"); //exit the program 
		btnStop = new JButton("Stop"); //exit the program 


		// set the size and position of the panels
		drawingPanel.setBounds(0, 50, width, height - 150);
		drawingPanel.setLayout(null);  // set layout of drawing panel
		controlPanel.setBounds(0,height - 100, width, 100);

		// add components to panels
		controlPanel.add(btnStart);    //button to control panel
		controlPanel.add(btnExit);
		controlPanel.add(btnStop);

		
		// add the title in the program
		titleText = new TextPicture("Moving the Minion", new Font("Serif", Font.BOLD + Font.ITALIC,24));
		titleText.setxPos(150);
		titleText.setyPos(30);

		//add my title direclty to the frame
		titleText.setBounds(0,0,500,40);
		add(titleText);

		// For each picture object, add the image
		// randomize its initial position using the Die class
		// and place the object on the drawingPanel

		
		//for loop to run through whole array 
		for (int i = 0; i < pic.length; i ++) { 
			pic[i] = new SmartMinion(new ImageIcon("minion.png"),0,0);
			pic[i].setBounds(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());   // set its boundaries
			drawingPanel.add(pic[i]);     // add the image to the panel      

			// create the dice for the positions and speed
			d1 = new Die(drawingPanel.getWidth() - pic[i].getMyWidth());  
			d2 = new Die(drawingPanel.getHeight() - pic[i].getMyHeight());
			d3 = new Die(); //velocity 
			d4 = new Die(900); //get steps randomized 

			// roll the dice for the image
			d1.rollDie();
			d2.rollDie();
			d3.rollDie();

			// place the image based on the first two dice
			xPos[i] = d1.getValue();
			yPos[i] = d2.getValue();

			pic[i].setxPos(xPos[i]);
			pic[i].setyPos(yPos[i]);

			// set the speed based on the value to the third die
			xVel[i] = d3.getValue();
			yVel[i] = d3.getValue();

		}  


		add(controlPanel);  // add the button and drawing panels to the frame
		add(drawingPanel);

		timer = new Timer (20, this);  //creates a timer and this class as the listener. set to fire every 20ms
		timer.setInitialDelay (5); //set the initial delay to 5 ms before it starts


		// add button as a listener in this frame
		btnStart.addActionListener(this);
		timer.addActionListener(this);
		btnExit.addActionListener(this); //new button
		btnStop.addActionListener(this); //new button

		
		// set size and location of frame
		setSize(width,height);  
		setLocation(100,100);

		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// method for actions events
	public void actionPerformed (ActionEvent e){

		if (e.getSource()==btnStart){  // if btn is clicked start timer
			timer.start();  

			//variables to get output and # of the pic 
			String output = "";
			int counter = 1;
			
			for (int z = 0; z < pic.length; z++) { //goes through the array of minion pics 
				d4.rollDie();          //and roll a number steps it should move 
				pic[z].setMySteps(d4.getValue() + 100);  //adds that number of steps to that picture plus 100 to make sure greater than 100 steps
				
				//output and counter 
				output = output + "The amount of steps the picture " + counter + " moved is " + pic[z].getMySteps() + "\n";
				counter++;
			}

			//display the output and steps moved 
			JOptionPane.showMessageDialog(null, output);

			//if timer is equal to 0 and the button is clicked the timer will start 
			//and plus one will be added, when the value is one the program will continue
			//to the else statement and allow the button to be clicked again and change the 
			//variables values such as the position and velocity. After the else statement is completed 
			//the program goes back to timer start and allows this block of code to be repeated
			
			
			if (reset == 0) { 
				timer.start();  
				reset++; 
			}
			
			//explanation of this else statement above 
			else { 
				for (int i = 0; i < pic.length; i++) {
				
					// roll the dice for the first image
					d1.rollDie();
					d2.rollDie();
					d3.rollDie();
					// place the image based on the first two dice
					xPos[i] = d1.getValue();
					yPos[i] = d2.getValue();
					pic[i].setxPos(xPos[i]);
					pic[i].setyPos(yPos[i]);
					// set the speed based on the value to the third die
					xVel[i] = d3.getValue();
					yVel[i] = d3.getValue();
				}
				timer.start();
			}
		}
		
		

		if (e.getSource()==timer){
			
			
			//when the timer start to run, the following code will 
			//go through all the pic in the array and set there new values 
			// based on the steps, while keeping in mind that the images can 
			// not escape the frame. However if the get mySteps == 0 then stop the image
			// from moving 

			for (int y = 0; y < pic.length; y++) {   

				if (pic[y].getMySteps() == 0) { //stops the minions
					continue;
				}
				
				else { 
					pic[y].setMySteps(pic[y].getMySteps()-1);   // minus one from step to keep track of steps taken
					pic[y].setStepsMoved(pic[y].getStepsMoved()+1); //add one to stepsMoved to keep track of steps moved 
					
					xPos[y] += xVel[y];
					yPos[y] += yVel[y];
					// change the position of the first image
					pic[y].setxPos(xPos[y]);
					pic[y].setyPos(yPos[y]);
					pic[y].repaint();      // call the paint for the  minion

					//check for the boundaries to reverse direction
					if (xPos[y] > (drawingPanel.getWidth() - pic[y].getMyWidth())) {
						xVel[y] =-1;   // reverse speed
					}
					else if (xPos[y] < 0){
						xVel[y] *=-1;   // reverse speed 
					}// if x pos

					if (yPos[y] > (drawingPanel.getHeight() - pic[y].getMyHeight())){
						yVel[y] *=-1;   // reverse speed
					}
					else if (yPos[y] < 0){
						yVel[y] *=-1;   // reverse speed
					}// if ypos
				}		
				

				
				}
			

			
			

		}// if timer

		if (e.getSource() == btnExit) { 
			System.exit(0);
		}

		if (e.getSource() == btnStop) { 
			timer.stop();
		}
		
		}
		
		
		
		

		
		
	// actionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		AnimationExampleLameAndBuggy animation = new AnimationExampleLameAndBuggy(); //Run Gui
	}
}