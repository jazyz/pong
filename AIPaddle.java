import java.awt.*;
import java.awt.event.*;

public class AIPaddle extends Paddle {
	
	public static final int baseY = 3;

	// constructor creates ball at given location with given dimensions
	public AIPaddle(int x, int y) {
		super(x, y);
		yVelocity=baseY;
	}
	
	public void follow(PlayerBall ball) {
		if (y>ball.y) {
			yVelocity = (-Math.abs(ball.yVelocity));
		} else if (y == ball.y) {
			yVelocity = 0;
		} else {
			yVelocity = Math.abs(ball.yVelocity);
		}
	}
	
	public void predict(PlayerBall ball) {
		if(ball.xVelocity>0) {
			double d = 1.0*(GamePanel.GAME_WIDTH - ball.x)*(ball.yVelocity/ball.xVelocity)+ball.y;
			
			if(d>=0&&d<=GamePanel.GAME_HEIGHT) {
				if(y>d) {
					yVelocity=-Math.abs(yVelocity);
				}else {
					yVelocity=Math.abs(yVelocity);
				}	
			}
		}
	}


}