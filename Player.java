// Player class stores lives, coins, and also controls power-ups

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
	int lives, coins;
	private char u1,u2,u3;
	boolean p1;
	public Player(char u1, char u2, char u3, boolean p1) {
		lives=5;
		coins=0;
		this.u1=u1;
		this.u2=u2;
		this.u3=u3;
		this.p1=p1;
	}
	public void keyPressed(KeyEvent e, GameBall ball) {
		if(e.getKeyChar()==u1) {
			if(coins>=10) { // power-up 1 costs 10 coins
				coins-=10;
				lives++;				
			}
		}
		if(e.getKeyChar()==u2) { 
			if(coins>=5) { // power-up 2 costs 5 coins
				coins-=5;
				ball.xVelocity*=2;	
			}
		}
		if(e.getKeyChar()==u3) {
			if(coins>=5) { // power-up 3 costs 5 coins
				coins-=5;
				if(Math.abs(ball.xVelocity)>=2){
					ball.xVelocity/=2;
				}
			}
		}
	}
	
	// Displays lives and coins or "You Lost"
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.PLAIN,20));
		String s;
		if(lives==0) {
			s="YOU LOST";
		}else {
			s = lives+" lives "+coins+" coins ";
		} 
		
		if(p1) {
			g.drawString(s, 200,20);	
		}else {
			g.drawString(s, (GamePanel.GAME_WIDTH/2)+200,20);
		}
	}
	
	
}
