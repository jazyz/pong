// Puts three buttons onto the screen for each mode

import java.awt.*;    
import javax.swing.*;   
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener {
	
	JButton mode1,mode2,mode3;
	
	public MenuPanel() {
		super();
		
		this.setPreferredSize(new Dimension(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT));
		setBounds(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
		setLayout(null);
		mode1 = new JButton("2P - Increasing Speed");
		mode1.setBounds(GamePanel.GAME_WIDTH / 2 -100, GamePanel.GAME_HEIGHT / 2 - 200, 200, 100);
		add(mode1);
		mode2 = new JButton("2P - Random Speed");
		mode2.setBounds(GamePanel.GAME_WIDTH / 2 - 100, GamePanel.GAME_HEIGHT / 2 - 100, 200, 100);
		add(mode2);
		mode3 = new JButton("AI Mode");
		mode3.setBounds(GamePanel.GAME_WIDTH / 2 - 100, GamePanel.GAME_HEIGHT / 2 - 0, 200, 100);
		add(mode3);
		
		mode1.addActionListener(this);
		mode2.addActionListener(this);
		mode3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
        
        if(source == mode1) {
        	new GameFrame(true, false);
        }
        
        if(source == mode2) {
        	new GameFrame(true, true);
        }
        
        if(source == mode3) {
        	new GameFrame(false,false);
        }
		
	}
}
