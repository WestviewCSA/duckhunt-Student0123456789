import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	Font bigFont = new Font("Serif", Font.BOLD, 100);//finish this later
	Font mediumFont = new Font ("Serif", Font.BOLD, 50);
//turtle needs to go across screen
//once it intersects with the coral I want it to hide
//When the mouse intersects with the jellyfish i want the turtle eating jellyfish to come up and go down
//The first round will have one jellyfish
//The second round will have two jellyfish
//The third round will have three jellyfish
//The game will end after three jellyfish
	Jellyfish jellyfish = new Jellyfish();
	Background ground = new Background("Background.png");
	Foreground foreground = new Foreground("Foreground.png");
	Stone stone = new Stone("Stone.png");
	Coral coral = new Coral();
	Turtle turtle = new Turtle();
	TurtleEatingJellyfish turtleEatingJellyfish = new TurtleEatingJellyfish();
	
	//Score related variables and timer
	int roundTimer = 30;
	int score = 0;
	long time = 0;
	int currRound = 1;
	public void init() {
		turtle.setWidthHeight(200, 200); //not needed if its not colliding
		turtle.setScale(4, 4);
		turtle.setXY(0, 820); //initially off the screen at the bottom
		turtle.setVx(3);
		
		turtleEatingJellyfish.setScale(4, 4);
		turtleEatingJellyfish.setWidthHeight(30, 30);
		turtleEatingJellyfish.setXY(550,430);
		turtleEatingJellyfish.setVx(0);
		
		jellyfish.setXY(50, 30);
		jellyfish.setScale(3, 3);
		jellyfish.setWidthHeight(95, 125);
		
		jellyfish.setVy(3);
		jellyfish.setVx(2);
		
		ground.setScale(8.95,6); //scale the size of my background
		ground.setXY(0,0); //moves the background
		
		foreground.setScale(8.95, 6);
		foreground.setXY(0, 0);
		
		coral.setWidthHeight(100,300);
		coral.setScale(.43, .43);
		coral.setXY(500,410);
		
		stone.setScale(5,5);
		
	}
	
	public void reset () {
		
	}
	//*initialize objects and vars for the next round
	public void nextRound() {
		if(jellyfish.getVx()==0 && jellyfish.getVy()==0 && turtle.getY()>800) {
		score++;
		roundTimer = 30;
		currRound++;}
		
		//reset the roundCOunter
		//re-calibrate your objects	
		//maybe additional objects that start off off the screen in -1000 y
		//the characters could have 0 Vx and Vy and then turn it on after the round ends
		//speed of the objects get faster over time each round
		int randVx = ((int)(Math.random()*(5) + 1));
		int randVy= ((int)(Math.random()*(5) + 1));
		jellyfish.setVx(randVx+currRound);
		jellyfish.setVy(randVy+currRound);
		turtle.setXY(0, 900);
	}
		
		
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//add 16 to time since paint is called every 20ms
		time += 20; //time elapse update
		if(time%1000 == 0) { //has it been 1 second
			if(roundTimer==0) {
				//What do I want to do after one round
				nextRound();
				t.stop();
										}
		}
		//Text for moving to the next round
		if (roundTimer==30) {
			g.drawString("press the space bar for the next round", 0, 0);
			turtleEatingJellyfish.setXY(0, 900);
		}
		g.setFont(bigFont);
		
		//layer your thing as you want them to layer
		stone.paint(g);
	
		ground.paint(g); //paint the background
		
		foreground.paint(g);
		
		jellyfish.paint(g);
		turtle.paint(g);
		
		turtleEatingJellyfish.paint(g);
		
		
		coral.paint(g);
			
		if((turtleEatingJellyfish.getY()<400)||(turtleEatingJellyfish.getY() > 800)) {
			turtleEatingJellyfish.setVy(turtleEatingJellyfish.getVy() * -1);
		}
		if((jellyfish.getX()<0)|| (jellyfish.getX() > 800)) {
			jellyfish.setVx(jellyfish.getVx()*-1);
			
		}
		if((jellyfish.getY()<0)||(jellyfish.getY() > 500)) {
			//turtle.setVx(turtle.);
			jellyfish.setVy(jellyfish.getVy()*-1);
		}
		
		//if the jellyfish is free-falling! and hits the ground
		if(((jellyfish.getVx() ==0) && (jellyfish.getVy()>0) && (jellyfish.getY() > 500))) {
			turtle.setVy(-3);
			jellyfish.setVy(0); //jellyfish needs to stop moving
		}
		if(turtle.getX()>=coral.getX()) {
			turtle.setXY(0, 820);}
		
		g.setColor(Color.magenta);
		g.drawString(""+this.roundTimer, 750, 100); //draw this last
		g.setFont(mediumFont);
		g.drawString("Round "+this.currRound , 50, 650);
		g.drawString("Score: "+this.score, 600, 650);
		
		//collision between the sides of the image
		if (score==currRound) {
			nextRound();
		}
		
		//logic for reseting the doc
		
	}
		
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(900, 700));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		
		init(); //call your init method to give properties to the objects and variables
		
		
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t = new Timer(16, this);
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent mouse) {
		// TODO Auto-generated method stub
		//perform a rectangle collision with the mouse and the object
		Rectangle rMouse= new Rectangle(mouse.getX(), mouse.getY(), 25, 25);
		//guess and check size for now
		//2nd rectangle will be for your Object
		Rectangle rMain = new Rectangle(
										jellyfish.getX(), jellyfish.getY(),
										jellyfish.getWidth(), jellyfish.getHeight()
										);
		Rectangle rMTurtle = new Rectangle(turtle.getX(),turtle.getY(),turtle.getWidth(),turtle.getHeight());
		
		Rectangle rTurtleEatingJellyfish = new Rectangle (turtleEatingJellyfish.getX(), turtleEatingJellyfish.getY(), turtleEatingJellyfish.getWidth(), turtleEatingJellyfish.getHeight());
		
		Rectangle rCoral = new Rectangle (coral.getX(), coral.getY(), coral.getWidth(), coral.getHeight());
		//check if they're colliding
		if((rMouse.intersects(rMain))) { //do the 2 rect intersect?
			//There was a successful click
			//dog needs to move in the same x position as the duck
			//make sure dog is currently off the screen
			//make the dog y velocity negative (so it goes up)
			//turtle.setX(jellyfish1.getX()
			roundTimer=0;
			jellyfish.setVx(0);
			jellyfish.setVy(0);
			turtleEatingJellyfish.setVy(3);
			}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		//space bar continues the round
		//if the timer is stopped we can start it again
		if (arg0.getKeyCode()==32) {
			//start the timer again
			if(!t.isRunning()) {
				turtle.setXY(0, 400);
				turtle.setVx(2);
				t.start();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


