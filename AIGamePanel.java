
/* GamePanel class acts as the main "game loop" - continuously runs the game and calls whatever needs to be called

Child of JPanel because JPanel contains methods for drawing to the screen

Implements KeyListener interface to listen for keyboard input

Implements Runnable interface to use "threading" - let the game do two things at once

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AIGamePanel extends JPanel implements Runnable, KeyListener {

	// dimensions of window
	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 600;

	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public PlayerBall ball;
	public Paddle leftPaddle;
	public Paddle rightPaddle;
	public boolean xDir; // right is true, left is false
	public boolean yDir; // up is true, down is false
	public int p1Lives;
	public int p2Lives;
	public boolean twoPlayer;
	public boolean randomDir;
	public Player p1,p2;
	public AIGamePanel(boolean twoPlayer, boolean randomDir) {
		this.twoPlayer = twoPlayer;
		this.randomDir = randomDir;
		p1=new Player('`','`','`');
		p2=new Player('`','`','`');
		
		reset();
		p1Lives = 5;
		p2Lives = 5;
		this.setFocusable(true); // make everything in this class appear on the screen
		this.addKeyListener(this); // start listening for keyboard input
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

		// make this class run at the same time as other classes (without this each
		// class would "pause" while another class runs). By using threading we can
		// remove lag, and also allows us to do features like display timers in real
		// time!
		gameThread = new Thread(this);
		gameThread.start();
	}

	// paint is a method in java.awt library that we are overriding. It is a special
	// method - it is called automatically in the background in order to update what
	// appears in the window. You NEVER call paint() yourself
	public void paint(Graphics g) {
		// we are using "double buffering here" - if we draw images directly onto the
		// screen, it takes time and the human eye can actually notice flashes of lag as
		// each pixel on the screen is drawn one at a time. Instead, we are going to
		// draw images OFF the screen, then simply move the image on screen as needed.
		image = createImage(GAME_WIDTH, GAME_HEIGHT); // draw off screen
		graphics = image.getGraphics();
		draw(graphics);// update the positions of everything on the screen
		g.drawImage(image, 0, 0, this); // move the image on the screen

	}

	// call the draw methods in each class to update positions as things move
	public void draw(Graphics g) {
		ball.draw(g);
		leftPaddle.draw(g);
		rightPaddle.draw(g);
	}

	// call the move methods in other classes to update positions
	// this method is constantly called from run(). By doing this, movements appear
	// fluid and natural. If we take this out the movements appear sluggish and
	// laggy
	public void move() {
		ball.move();
		((AIPaddle) rightPaddle).follow(ball);
		((AIPaddle) leftPaddle).follow(ball);
		leftPaddle.move();
		rightPaddle.move();
	}

	public void reset() {
		ball = new PlayerBall(GAME_WIDTH / 2, GAME_HEIGHT / 2); // create a player controlled ball, set start location
		
		// to middle of screen
		
		rightPaddle = new AIPaddle(GAME_WIDTH - Paddle.WIDTH, GAME_HEIGHT / 2);
		leftPaddle = new AIPaddle(0, GAME_HEIGHT / 2);
	}
//	public void ai() {
//		if(ball.xVelocity>0) {
//			double d = 1.0*(GAME_WIDTH - ball.x)*(ball.yVelocity/ball.xVelocity)+ball.y;
//			
//			if(d>=0&&d<=GAME_HEIGHT) {
//				if(rightPaddle.y>d) {
//					rightPaddle.yVelocity=-Math.abs(rightPaddle.yVelocity);
//				}else {
//					rightPaddle.yVelocity=Math.abs(rightPaddle.yVelocity);
//				}	
//			}
//		}
//		if(leftPaddle.y>ball.y) {
//			leftPaddle.yVelocity=-Math.abs(rightPaddle.yVelocity);
//		}else {
//			leftPaddle.yVelocity=Math.abs(rightPaddle.yVelocity);
//		}
//	}
	public void upd() {
		ball.xVelocity *= -1;
		int dx = 1;
		int dy = 1;
		if (randomDir) {
			ball.yVelocity=(int)Math.random()*4 +1;
			if(Math.random()>0.5) {
				ball.yVelocity*=-1;
			}
		}
		dx += (int) (Math.random() * 2);
		dy += (int) (Math.random() * 2);		
		
		if (ball.xVelocity < 0) {
			dx *= -1;
		} 
		if (ball.yVelocity < 0) {
			dy *= -1;
		}
		ball.xVelocity += dx;
		ball.yVelocity += dy;			
	}
	public void checkCollision() {
		if (ball.y <= 0 || ball.y >= GAME_HEIGHT - PlayerBall.BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.intersects(leftPaddle)) {
			upd();
			p1.coins++;
		}
		if(ball.intersects(rightPaddle)) {
			upd();
			p2.coins++;
		}
		
		leftPaddle.y = Math.max(0, leftPaddle.y);
		leftPaddle.y = Math.min(leftPaddle.y, GAME_HEIGHT - leftPaddle.height);
		rightPaddle.y = Math.max(0, rightPaddle.y);
		rightPaddle.y = Math.min(rightPaddle.y, GAME_HEIGHT - rightPaddle.height);
		if (ball.x <= 0) {
			p1.lives--;
			reset();
		}
		if (ball.x >= GAME_WIDTH - ball.width) {
			p2.lives--;
			reset();
		}
	}

	// run() method is what makes the game continue running without end. It calls
	// other methods to move objects, check for collision, and update the screen
	public void run() {
		// the CPU runs our game code too quickly - we need to slow it down! The
		// following lines of code "force" the computer to get stuck in a loop for short
		// intervals between calling other methods to update the screen.
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long now;

		while (true) { // this is the infinite game loop
			now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;

			// only move objects around and update screen if enough time has passed
			if (delta >= 1) {
				move();
//				ai();
				checkCollision();
				if (checkGameEnd()) {
					break;
				}
				repaint();
				delta--;
			}
		}
	}

	public boolean checkGameEnd() {
		if (p1.lives <= 0 || p2.lives <= 0) {
			return true;
		} else {
			return false;
		}
	}

	// if a key is pressed, we'll send it over to the PlayerBall class for
	// processing
	public void keyPressed(KeyEvent e) {
		
	}

	// if a key is released, we'll send it over to the PlayerBall class for
	// processing
	public void keyReleased(KeyEvent e) {
		
	}

	// left empty because we don't need it; must be here because it is required to
	// be overridden by the KeyListener interface
	public void keyTyped(KeyEvent e) {

	}
}
