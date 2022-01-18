// AI Paddle class allows paddle to follow the ball

import java.awt.*;
import java.awt.event.*;

public class AIPaddle extends Paddle {
	
	public static final int baseY = 3;

	// constructor creates ball at given location with given dimensions
	public AIPaddle(int x, int y) {
		super(x, y);
		yVelocity=baseY;
	}
	
	public void follow(GameBall ball) {
		if (y+HEIGHT/2>ball.y+GameBall.BALL_DIAMETER) {
			yVelocity = -Math.abs(ball.yVelocity);
			if(ball.xVelocity>-2*yVelocity) {
				yVelocity = -Math.abs(ball.xVelocity);
			}
		} else if (y+HEIGHT/2>ball.y+GameBall.BALL_DIAMETER) {
			yVelocity = 0;
		} else {
			yVelocity = Math.abs(ball.yVelocity);
			if(ball.xVelocity>2*yVelocity) {
				yVelocity = Math.abs(ball.xVelocity);
			}
		}
		
	}
	


}