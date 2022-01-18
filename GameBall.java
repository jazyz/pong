// Game Ball class implements movement of ball

import java.awt.*;
import java.awt.event.*;

public class GameBall extends Rectangle {

	public int yVelocity;
	public int xVelocity;
	public final int SPEED = 2; 
	public static final int BALL_DIAMETER = 20; 

	public GameBall(int x, int y) {
		super(x, y, BALL_DIAMETER, BALL_DIAMETER);
		double dx = Math.random();
		double dy = Math.random();
		int xSpeed = SPEED;
		int ySpeed = SPEED;
		if (dx > 0.5) {
			xSpeed *= -1;
		}
		if (dy > 0.5) {
			ySpeed *= -1;
		}
		xVelocity = (xSpeed);
		yVelocity = (ySpeed);
	}

	// Moves ball position 
	public void move() {
		y = y + yVelocity;
		x = x + xVelocity;
	}

	// Draws ball
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
	}

}