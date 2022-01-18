// This class is the frame for the main game

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

	public GameFrame(boolean p, boolean d) {
		GamePanel gamePanel = new GamePanel(p, d);
		this.add(gamePanel);
		this.setTitle("Pong"); 
		this.setResizable(false); 
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}

}