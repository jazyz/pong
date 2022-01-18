import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

	public int yVelocity;
	public static final int HEIGHT = 50; // height of paddle
	public static final int WIDTH = 10; // width of paddle

	public Paddle(int x, int y) {
		super(x, y, WIDTH, HEIGHT);
	}

	// Moves paddle
	public void move() {
		y = y + yVelocity;
	}

	// Draws paddle
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

}