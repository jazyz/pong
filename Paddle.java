import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

	public int yVelocity;
	public final int SPEED = 5; // movement speed of paddle
	public static final int HEIGHT = 50; // height of paddle
	public static final int WIDTH = 10; // width of paddle

	// constructor creates ball at given location with given dimensions
	public Paddle(int x, int y) {
		super(x, y, WIDTH, HEIGHT);
	}

	// called whenever the movement of the ball changes in the y-direction (up/down)
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	// called frequently from both PlayerBall class and GamePanel class
	// updates the current location of the ball
	public void move() {
		y = y + yVelocity;
	}

	// called frequently from the GamePanel class
	// draws the current location of the ball to the screen
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

}