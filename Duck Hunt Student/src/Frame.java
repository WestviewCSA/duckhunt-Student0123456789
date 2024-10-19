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
	Font smallFont = new Font ("Serif", Font.BOLD, 25);
//turtle needs to go across screen
//time will start once turtle hides behind coral
//When the mouse intersects with the jellyfish i want the turtle eating jellyfish to come up and go down
//The first round will have one jellyfish
//The second round will have two jellyfish
//The third round will have three jellyfish
//The game will end after three jellyfish
//actually just do as many jellyfish as you can
//if someone doesn't click any jellyfish by the time the round ends end the game
	 
	Jellyfish jellyfish1 = new Jellyfish();
	Jellyfish jellyfish2 = new Jellyfish();//Call this
	Jellyfish jellyfish3 = new Jellyfish(); //this
	Jellyfish jellyfish4 = new Jellyfish(); //and this Jellyfish
	
	Background ground = new Background("Background.png");
	Foreground foreground = new Foreground("Foreground.png");
	Stone stone = new Stone("Stone.png");
	Coral coral = new Coral();
	Turtle turtle = new Turtle();
	TurtleEatingJellyfish turtleEatingJellyfish = new TurtleEatingJellyfish();
//	JellyfishSound jellyfishSound = new JellyfishSound("Jellyfish-Sound.wav");
//	TurtleEatingJellyfishSound turtleEatingJellyfishSound = new TurtleEatingJellyfishSound("Turtle-Eating-Jellyfish-Sound.wav");
	//Score related variables and timer
	int roundTimer = 35;
	int score = 0;
	long time = 0;
	int currRound = 1;
	public void init() {
		
		turtle.setWidthHeight(200, 200); //not needed if its not colliding
		turtle.setScale(4, 4);
		turtle.setXY(0, 450); //initially off the screen at the bottom
		turtle.setVx(3);
		
		turtleEatingJellyfish.setScale(4, 4);
		turtleEatingJellyfish.setWidthHeight(30, 30);
		turtleEatingJellyfish.setXY(550,430);
		turtleEatingJellyfish.setVx(0);
		turtleEatingJellyfish.setVy(0);
		
		jellyfish1.setXY(50, 410);
		jellyfish1.setScale(3, 3);
		jellyfish1.setWidthHeight(95, 125);
		jellyfish1.setVx(3);
		jellyfish1.setVy(3);
		
		jellyfish2.setScale(3, 3);
		jellyfish2.setWidthHeight(95, 125);
		jellyfish2.setVx(3);
		jellyfish2.setXY(50, 500);

		jellyfish3.setXY(600, 500);
		jellyfish3.setScale(3, 3);
		jellyfish3.setWidthHeight(95, 125);
		jellyfish3.setVx(3);

		jellyfish4.setXY(400, 300);
		jellyfish4.setScale(3, 3);
		jellyfish4.setWidthHeight(95, 125);
		jellyfish4.setVx(3);
		
		ground.setScale(8.95,6); //scale the size of my background
		ground.setXY(0,0); //moves the background
		
		foreground.setScale(8.95, 6);
		foreground.setXY(0, 0);
		
		coral.setWidthHeight(100,300);
		coral.setScale(.43, .53);
		coral.setXY(500,410);
		
		stone.setScale(5,5);
		
	}
	
	public void reset () {
		
	}
	//*initialize objects and vars for the next round
	public void nextRound() {
		int randX1 = ((int)(Math.random()*(801) + 1));
		int randY1= ((int)(Math.random()*(501) + 1));
		int randX2 = ((int)(Math.random()*(801) + 1));
		int randY2= ((int)(Math.random()*(501) + 1));
		int randX3 = ((int)(Math.random()*(801) + 1));
		int randY3= ((int)(Math.random()*(501) + 1));
		int randX4 = ((int)(Math.random()*(801) + 1));
		int randY4= ((int)(Math.random()*(501) + 1));
//		jellyfish1.setVx(randVx+currRound);
//		jellyfish1.setVy(randVy+currRound);
		//jellyfish1.setVy(3);
		if (currRound >=1) {
			jellyfish1.setX(randX1);
			jellyfish1.setY(randY1);
		}
		if (currRound >=2) {
			jellyfish2.setX(randX2);
			jellyfish2.setY(randY2);
//			jellyfish2.setVx(randVx+currRound);
//			jellyfish2.setVy(randVy+currRound);
//			jellyfish2.setVx(2);
			jellyfish2.setVx(3);
			jellyfish2.setVy(3);
		}
		if (currRound>=3) {
			jellyfish3.setX(randX3);
			jellyfish3.setY(randY3);
//			jellyfish3.setVx(2);
			jellyfish3.setVy(4);
			jellyfish3.setVx(4);
//			jellyfish3.setVx(randVx+currRound);
//			jellyfish3.setVy(randVy+currRound);
		} 
		if (currRound == 4) {
			jellyfish2.setX(randX4);
			jellyfish2.setY(randY4);
//			jellyfish4.setVx(2);
			jellyfish4.setVx(5);
			jellyfish4.setVy(5);
//			jellyfish4.setVx(randVx+currRound);
//			jellyfish4.setVy(randVy+currRound);
		}
		t.start();
		//reset the roundCOunter
		//re-calibrate your objects	
		//maybe additional objects that start off off the screen in -1000 y
		//the characters could have 0 Vx and Vy and then turn it on after the round ends
		//speed of the objects get faster over time each round
		
	}
		
		
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//add 16 to time since paint is called every 20ms
		time += 20; //time elapse update
		if(time%1000 == 0) {
			if (roundTimer > 0){
				roundTimer--;//has it been 1 second
							}
		}
		//Text for moving to the next round
//		if ((currRound==1 && score ==0)||(currRound==2 && score==1) ||(currRound==3 && score ==2 )){
		//}
		if (roundTimer<=35) {
			turtleEatingJellyfish.setXY(550, 430);
		}
		g.setFont(bigFont);
		
		//layer your thing as you want them to layer
		stone.paint(g);
	
		ground.paint(g); //paint the background
		
		foreground.paint(g);
		
		turtle.paint(g);
		
		turtleEatingJellyfish.paint(g);
		
		
		jellyfish1.paint(g);
		if (currRound >=2) {
			jellyfish2.paint(g);
		}
		if (currRound >=3) {
			jellyfish3.paint(g);
		}
		if (currRound >=4) {
		jellyfish4.paint(g);
		}
			
		if((jellyfish1.getX()<0) || (jellyfish1.getX() > 800)) {
			jellyfish1.setVx(jellyfish1.getVx()*-1);
			
		}
		if((jellyfish1.getY()<0)||(jellyfish1.getY() > 500)) {
			//turtle.setVx(turtle.);
			jellyfish1.setVy(jellyfish1.getVy()*-1);
		}
		
		//if the jellyfish is free-falling! and hits the ground
		if(((jellyfish1.getVx() == 0) && (jellyfish1.getVy()>0))) {
			jellyfish1.setVy(0); //jellyfish needs to stop moving
		}
		if((jellyfish2.getX()<0) || (jellyfish2.getX() > 800)) {
			jellyfish2.setVx(jellyfish2.getVx()*-1);
			
		}
		if((jellyfish2.getY()<0)||(jellyfish2.getY() > 500)) {
			//turtle.setVx(turtle.);
			jellyfish2.setVy(jellyfish2.getVy()*-1);
		}
		
		//if the jellyfish is free-falling! and hits the ground
		if(((jellyfish2.getVx()==0) && (jellyfish2.getVy()>0))) {
			jellyfish2.setVy(0); }//jellyfish needs to stop moving
			
		if((jellyfish3.getX()<0)|| (jellyfish3.getX() > 800)) {
				jellyfish3.setVx(jellyfish3.getVx()*-1);
				
			}
		if((jellyfish3.getY()<0)||(jellyfish3.getY() > 500)) {
				//turtle.setVx(turtle.);
				jellyfish3.setVy(jellyfish3.getVy()*-1);
			}
			//if the jellyfish is free-falling! and hits the ground
		if(((jellyfish3.getVx() ==0) && (jellyfish3.getVy()>0))) {
				jellyfish3.setVy(0);}
		
		
		if((jellyfish4.getX()<0)|| (jellyfish4.getX() > 800)) {
			jellyfish4.setVx(jellyfish4.getVx()*-1);
			
		}
		if((jellyfish4.getY()<0)||(jellyfish4.getY() > 500)) {
			//turtle.setVx(turtle.);
			jellyfish4.setVy(jellyfish4.getVy()*-1);
		}
		
		//if the jellyfish is free-falling! and hits the ground
		if(((jellyfish4.getVx() ==0) && (jellyfish4.getVy()>0))) {
			jellyfish4.setVy(0); }//jellyfish needs to stop moving
		//jellyfish needs to stop moving
		
		if(turtle.getX()>=coral.getX()) {
			turtle.setXY(0, 900);
//			jellyfish1.setVx(3);
//			jellyfish1.setVy(2);
			t.start();
			}
		

		coral.paint(g);

		g.setColor(Color.magenta);
		if (roundTimer<=30) {
		g.drawString(""+this.roundTimer, 750, 100);} //draw this last
		g.setFont(mediumFont);
		g.drawString("Round "+this.currRound , 50, 650);
		g.drawString("Score: "+this.score, 600, 650);
		
		g.setFont(smallFont);
		g.drawString("Press the space bar for the next round", 10, 30);
		g.drawString("Game starts when timer appears", 10, 60);
		
		g.setFont(mediumFont);

		if(roundTimer==0 && (jellyfish1.getVx()>0 || jellyfish2.getVx()>0 || jellyfish3.getVx()>0 || jellyfish4.getVx()>0)) {
			jellyfish1.setVx(0);
			jellyfish2.setVx(0);
			jellyfish3.setVx(0);
			jellyfish4.setVx(0);
			jellyfish1.setVy(0);
			jellyfish2.setVy(0);
			jellyfish3.setVy(0);
			jellyfish4.setVy(0);
		}
		if (jellyfish1.getVx()==0 && jellyfish2.getVx()==0 && jellyfish3.getVx()==0 && jellyfish4.getVx()==0 && currRound<5) {
			g.drawString("YOU LOST! Be Better! :)", 300, 200);
		}
		else if (currRound>=5) {
			g.drawString("Congratulations you have beat the game!", 2, 200);
			t.stop();
			jellyfish1.setVx(0);
			jellyfish2.setVx(0);
			jellyfish3.setVx(0);
			jellyfish4.setVx(0);
			jellyfish1.setVy(0);
			jellyfish2.setVy(0);
			jellyfish3.setVy(0);
			jellyfish4.setVy(0);
		}
		//collision between the sides of the image
		

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
		//make a rectangle for each jellyfish and for round 1 make it so that jellyfish 1 has to be clicked and so on for the others
		Rectangle rJellyfish1 = new Rectangle(
										jellyfish1.getX(), jellyfish1.getY(),
										jellyfish1.getWidth(), jellyfish1.getHeight());
		Rectangle rJellyfish2 = new Rectangle(
				jellyfish2.getX(), jellyfish2.getY(),
				jellyfish2.getWidth(), jellyfish2.getHeight());
		Rectangle rJellyfish3 = new Rectangle(
				jellyfish3.getX(), jellyfish3.getY(),
				jellyfish3.getWidth(), jellyfish3.getHeight());
		Rectangle rJellyfish4 = new Rectangle(
				jellyfish4.getX(), jellyfish4.getY(),
				jellyfish4.getWidth(), jellyfish4.getHeight());
		
		//check if they're colliding
		//if(rMouse.intersects(rJellyfish1)) { //do the 2 rect intersect?
			//There was a successful click
			//dog needs to move in the same x position as the duck
			//make sure dog is currently off the screen
			//make the dog y velocity negative (so it goes up)
			//turtle.setX(jellyfish1.getX()
//			t.stop();
//			roundTimer=0;
//			turtleEatingJellyfish.setVy(3);
//			jellyfish1.setVx(0);
//			jellyfish1.setVy(0);
//			t.stop();
//			roundTimer = 0;}
	if (roundTimer<=30) {
		if(rMouse.intersects(rJellyfish1)) { //do the 2 rect intersect?
			System.out.println("I just clicked Jellyfish 1");
			turtleEatingJellyfish.setXY(550,430);
			turtleEatingJellyfish.setVy(-10);
			//There was a successful click
			//dog needs to move in the same x position as the duck
			//make sure dog is currently off the screen
			//make the dog y velocity negative (so it goes up)
			//turtle.setX(jellyfish1.getX()
			jellyfish1.setVx(0);
			jellyfish1.setVy(0);
			System.out.println("Playing jellyfish sound");
			StdAudio.playInBackground("soundEffect/Jellyfish-Sound.wav");
//			jellyfishSound.play();
	       // turtleEatingJellyfish.setVy(-3); // Set initial upward velocity

			if (currRound==1) {
				t.stop();
				System.out.println("I am inside the if statement if currRound==1");
				roundTimer=0;
//				turtle.setXY(0, 820);//finish this
//				turtle.setVx(3);
//				turtleEatingJellyfish.setVx(-3);
				score=1;
				currRound=2;

			}
				else if (currRound==2 && jellyfish2.getVx()==0 && jellyfish2.getVy()==0) {
				System.out.println("I just clicked Jellyfish 1 I am in the if-statement if jellyfish 2 and currRound = smth");

				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=2;
				currRound=3;

				
				
			}
				else if (currRound==3 && jellyfish2.getVx()==0 && jellyfish2.getVy()==0 && jellyfish3.getVx()==0 && jellyfish3.getVy()==0) {
				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=3;
				currRound=4;

			}
				else if (currRound==4 && jellyfish2.getVx()==0 && jellyfish2.getVy()==0 && jellyfish3.getVx()==0 && jellyfish3.getVy()==0 && jellyfish4.getVx()==0 && jellyfish4.getVy()==0) {
				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=4;
				currRound=5;

				}

			}
	
		if(rMouse.intersects(rJellyfish2)) { //do the 2 rect intersect?

			//There was a successful click
			//dog needs to move in the same x position as the duck
			//make sure dog is currently off the screen
			//make the dog y velocity negative (so it goes up)
			//turtle.setX(jellyfish1.getX()
			jellyfish2.setVx(0);
			jellyfish2.setVy(0);
			StdAudio.playInBackground("soundEffect/Jellyfish-Sound.wav");
//			jellyfishSound.play();
	     //   turtleEatingJellyfish.setVy(-3); // Set initial upward velocity

			if (currRound==2 && jellyfish1.getVx()==0 && jellyfish1.getVy()==0) {

				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=2;
				currRound=3;

			}
			else if (currRound==3 && jellyfish1.getVx()==0 && jellyfish1.getVy()==0 && jellyfish3.getVx()==0 && jellyfish3.getVy()==0) {
				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=3;
				currRound=4;

			}
			else if (currRound==4 && jellyfish1.getVx()==0 && jellyfish1.getVy()==0 && jellyfish3.getVx()==0 && jellyfish3.getVy()==0 && jellyfish4.getVx()==0 && jellyfish4.getVy()==0) {
				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=4;
				currRound=5;

				}
			
			
		}
		if(rMouse.intersects(rJellyfish3)) { //do the 2 rect intersect?

			//There was a successful click
			//dog needs to move in the same x position as the duck
			//make sure dog is currently off the screen
			//make the dog y velocity negative (so it goes up)
			//turtle.setX(jellyfish1.getX()
			jellyfish3.setVx(0);
			jellyfish3.setVy(0);
			StdAudio.playInBackground("soundEffect/Jellyfish-Sound.wav");
//			jellyfishSound.play();
	        //turtleEatingJellyfish.setVy(-3); // Set initial upward velocity

			if (currRound==3 && jellyfish1.getVx()==0 && jellyfish1.getVy()==0 && jellyfish2.getVx()==0 && jellyfish2.getVy()==0) {
				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=3;
				currRound=4;

			}
			else if (currRound==4 && jellyfish1.getVx()==0 && jellyfish1.getVy()==0 && jellyfish2.getVx()==0 && jellyfish2.getVy()==0 && jellyfish4.getVx()==0 && jellyfish4.getVy()==0) {
				t.stop();
				roundTimer=0;
//				turtleEatingJellyfish.setVy(-3);
				score=4;
				currRound=5;}

			
		}
		
		if(rMouse.intersects(rJellyfish4)) { //do the 2 rect intersect?
			//There was a successful click
			//dog needs to move in the same x position as the duck
			//make sure dog is currently off the screen
			//make the dog y velocity negative (so it goes up)
			//turtle.setX(jellyfish1.getX()
			jellyfish4.setVx(0);
			jellyfish4.setVy(0);
//			jellyfishSound.play();
			StdAudio.playInBackground("soundEffect/Jellyfish-Sound.wav");

	        //turtleEatingJellyfish.setVy(-3); // Set initial upward velocity
	        
			if (currRound==4 && jellyfish1.getVx()==0 && jellyfish1.getVy()==0 && jellyfish3.getVx()==0 && jellyfish3.getVy()==0 && jellyfish2.getVx()==0 && jellyfish2.getVy()==0) {
				t.stop();
				roundTimer=0;
				//turtleEatingJellyfish.setVy(-3);
				score=4;
				currRound=5;

			
			}
			

		}
	}
	

			//maybe take out the eating jellyfish part and just make the og turtle move 
	
			
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
				System.out.println("Sound Effect Turtle");
				StdAudio.playInBackground("soundEffect/Turtle-Eating-Jellyfish-Sound.wav");
				turtleEatingJellyfish.setVy(5);
//				turtleEatingJellyfishSound.play();
				jellyfish1.setVx(3);
				jellyfish1.setVy(2);
				nextRound();
				roundTimer=30;
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


