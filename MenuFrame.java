// This class is the frame for the menu panel

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {
	
	public MenuFrame() {
		MenuPanel menu = new MenuPanel();
		this.add(menu);
		this.setTitle("Main Menu"); 
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
