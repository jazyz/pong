// This class runs the main game.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 600;

	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public GameBall ball;
	public PlayerPaddle leftPaddle;
	public Paddle rightPaddle;
	public boolean twoPlayer;
	public boolean randomSpeed;
	public Player p1, p2;

	public GamePanel(boolean twoPlayer, boolean randomSpeed) {
		this.twoPlayer = twoPlayer;
		this.randomSpeed = randomSpeed;
		p1 = new Player('a', 'q', 'e', true);
		if (twoPlayer) {
			p2 = new Player('[', 'k', 'p', false);
		} else {
			p2 = new Player('`', '`', '`', false);
		}
		reset();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void paint(Graphics g) {
		image = createImage(GAME_WIDTH, GAME_HEIGHT);
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);

	}

	// Update the screen as things move
	public void draw(Graphics g) {
		ball.draw(g);
		leftPaddle.draw(g);
		rightPaddle.draw(g);

		p1.draw(g);
		p2.draw(g);
	}

	// Move the objects around on the screen
	public void move() {
		ball.move();
		if (!twoPlayer) {
			((AIPaddle) rightPaddle).follow(ball);
		}
		leftPaddle.move();
		rightPaddle.move();
	}

	// Resets the screen when player loses life
	public void reset() {
		ball = new GameBall(GAME_WIDTH / 2, GAME_HEIGHT / 2);
		leftPaddle = new PlayerPaddle(0, GAME_HEIGHT / 2, 'w', 's');
		if (twoPlayer) {
			rightPaddle = new PlayerPaddle(GAME_WIDTH - Paddle.WIDTH, GAME_HEIGHT / 2, 'o', 'l');
		} else {
			rightPaddle = new AIPaddle(GAME_WIDTH - Paddle.WIDTH, GAME_HEIGHT / 2);
		}

	}

	// Updates velocity after collision
	public void updateVelocity() {
		ball.xVelocity *= -1;
		int dx = 1;
		int dy = 1;
		if (randomSpeed) { // Ball bounces off at random
			dx = (int) (Math.random() * 10.0) + 3;
			dy = (int) (Math.random() * 10.0) + 3;
			if (Math.random() > 0.5) {
				dy *= -1;
			}
			if (ball.xVelocity < 0) {
				dx *= -1;
			}
			ball.xVelocity = dx;
			ball.yVelocity = dy;
		} else { // Ball speed increases by random amount
			dx += (int) (Math.random() * 2.0);
			dy += (int) (Math.random() * 2.0);
			if (ball.xVelocity < 0) {
				dx *= -1;
			}
			if (ball.yVelocity < 0) {
				dy *= -1;
			}
			ball.xVelocity += dx;
			ball.yVelocity += dy;
			if (Math.random() > 0.5) {
				ball.yVelocity *= -1;
			}
		}
	}

	// Checks collisions
	public void checkCollision() {
		if (ball.intersects(leftPaddle)) {
			updateVelocity();
			p1.coins++;
		}
		if (ball.intersects(rightPaddle)) {
			updateVelocity();
			p2.coins++;
		}
		// Make sure paddle within bounds
		leftPaddle.y = Math.max(0, leftPaddle.y);
		leftPaddle.y = Math.min(leftPaddle.y, GAME_HEIGHT - leftPaddle.height);
		rightPaddle.y = Math.max(0, rightPaddle.y);
		rightPaddle.y = Math.min(rightPaddle.y, GAME_HEIGHT - rightPaddle.height);
		if (ball.y <= 0 || ball.y >= GAME_HEIGHT - GameBall.BALL_DIAMETER) {
			ball.yVelocity = (-ball.yVelocity); // Bounce off top and bottom
		}
		if (ball.x <= 0) { // P1 didn't hit the ball
			p1.lives--;
			reset();
		}
		if (ball.x >= GAME_WIDTH - ball.width) { // P2 didn't hit the ball
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
				checkCollision();
				repaint();
				if (checkGameEnd()) {
					break;
				}
				delta--;
			}
		}
	}

	// Checks if a player has lost
	public boolean checkGameEnd() {
		if (p1.lives <= 0 || p2.lives <= 0) {
			return true;
		} else {
			return false;
		}
	}

	// Calls methods of keyPressed for paddles and balls
	public void keyPressed(KeyEvent e) {
		leftPaddle.keyPressed(e);
		p1.keyPressed(e, ball);
		if (twoPlayer) {
			((PlayerPaddle) rightPaddle).keyPressed(e);
			p2.keyPressed(e, ball);
		}
	}

	// Calls methods of keyReleased for paddles
	public void keyReleased(KeyEvent e) {
		leftPaddle.keyReleased(e);
		if (twoPlayer) {
			((PlayerPaddle) rightPaddle).keyReleased(e);
		}
	}

	// Overridden
	public void keyTyped(KeyEvent e) {

	}
}
