// Player paddle class allows player to move the paddle

import java.awt.event.KeyEvent;

public class PlayerPaddle extends Paddle {
	
	public char upArrow;
	public char downArrow;
	public final int SPEED = 7; // movement speed of paddle
	public PlayerPaddle(int x, int y, char upArrow, char downArrow) {
		super(x, y);
		this.upArrow = upArrow;
		this.downArrow = downArrow;
	}
	
	// Sets velocity and moves based on key press
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == upArrow) {
			yVelocity = (SPEED * -1);
			move();
		}
	
		if (e.getKeyChar() == downArrow) {
			yVelocity = (SPEED);
			move();
		}
	}
	
	// Stops movement when key released
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == upArrow || e.getKeyChar() == downArrow) {
			yVelocity = (0);
			move();
		}
	}
}
