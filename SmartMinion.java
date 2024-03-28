/**
 * 
 */
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * Authour; Hamza Khan
 * Date; Oct 2023
 * Description;Move minion certain amount of steps 
 * 
 */
public class SmartMinion extends ImagePicture{

	private int mySteps;     //variables 
	private int stepsMoved;

	public SmartMinion(ImageIcon img) {
		super(img);
		mySteps = 0;
		stepsMoved = 0;
	
	}

	public SmartMinion (ImageIcon img, int x, int y) {
		super (img,x,y);
		mySteps = 0;
		stepsMoved = 0;
	}
	
	
	
	
	
	
	/**
	 * @return the mySteps
	 */
	public int getMySteps() {
		return mySteps;
	}

	/**
	 * @param mySteps the mySteps to set
	 */
	public void setMySteps(int mySteps) {
		this.mySteps = mySteps;
	}

	/**
	 * @return the stepsMoved
	 */
	public int getStepsMoved() {
		return stepsMoved;
	}

	/**
	 * @param stepsMoved the stepsMoved to set
	 */
	public void setStepsMoved(int stepsMoved) {
		this.stepsMoved = stepsMoved;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        JFrame f = new JFrame("Testing Only"); //create a Jframe for testing 
		
		//set the size
		f.setSize(400, 350);
		
		SmartMinion p1 = new SmartMinion (new ImageIcon("minion.png"), 100, 100);
		
		f.add(p1);
		
		f.setVisible(true); //displays the frame 
		
		//pause
		JOptionPane.showMessageDialog(null, "Wait");
		
		p1.setMySteps(100);  //move image 100 steps 
		int steps = p1.getStepsMoved();  //get number of steps to move 
		
		for (int i = 0; i <= p1.getMySteps(); i++) { //if i < or equal to getMySteps then run loop 
			
			if (p1.getStepsMoved() < p1.getMySteps()) { //keep moving till the stepsMoved less than mySteps
				steps++; // add one to steps 
				p1.setStepsMoved(steps); //number of steps 
				p1.setxPos(p1.getxPos() + 1); //move right 1 
				p1.setyPos(p1.getyPos() + 1); //move left 1
				p1.repaint(); //repaint 
				
				try {
					Thread.sleep(10); //slow down and show movement 
				}
				
				catch (Exception error) { //catch error
					
				}
			}
		}
		
		//display message
		JOptionPane.showMessageDialog(null, "The amount of steps the picture moved is " + steps );

				

		
		

}

}
