import java.awt.event.KeyEvent;

public class Player {
	int lives, coins;
	private char u1,u2,u3;
	public Player(char u1, char u2, char u3) {
		lives=5;
		coins=0;
		this.u1=u1;
		this.u2=u2;
		this.u3=u3;
	}
	public void keyPressed(KeyEvent e, Paddle op, Paddle my) {
		if(e.getKeyChar()==u1) {
			if(coins>=10) {
				coins-=10;
				lives++;				
			}
		}
		if(e.getKeyChar()==u2) {
			if(coins>=3) {
				coins-=3;
				op.yVelocity--;	
				System.out.println(1);
			}
		}
		if(e.getKeyChar()==u3) {
			if(coins>=3) {
				coins-=3;
				my.yVelocity++;				
			}
		}
	}
	
	
	
}
