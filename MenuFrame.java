import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {
	
	public MenuFrame() {
		MenuPanel menu = new MenuPanel();
		this.add(menu);
		this.setTitle("Main Menu"); // set title for frame
		this.setResizable(false); // frame can't change size
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X button will stop program execution
		this.pack();// makes components fit in window - don't need to set JFrame size, as it will
					// adjust accordingly
		this.setLocationRelativeTo(null);// set window in middle of screen
		this.setVisible(true); // makes window visible to user
	}

}
