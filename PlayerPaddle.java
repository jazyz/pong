import java.awt.event.KeyEvent;

public class PlayerPaddle extends Paddle {
	
	public char upArrow;
	public char downArrow;
	
	public PlayerPaddle(int x, int y, char upArrow, char downArrow) {
		super(x, y);
		this.upArrow = upArrow;
		this.downArrow = downArrow;
	}
	
	// called from GamePanel when any keyboard input is detected
	// updates the direction of the ball based on user input
	// if the keyboard input isn't any of the options (d, a, w, s), then nothing
	// happens
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == upArrow) {
			setYDirection(SPEED * -1);
			move();
		}
	
		if (e.getKeyChar() == downArrow) {
			setYDirection(SPEED);
			move();
		}
	}
	
	// called from GamePanel when any key is released (no longer being pressed down)
	// Makes the ball stop moving in that direction
	public void keyReleased(KeyEvent e) {
	
		if (e.getKeyChar() == upArrow) {
			setYDirection(0);
			move();
		}
	
		if (e.getKeyChar() == downArrow) {
			setYDirection(0);
			move();
		}
	}
}
