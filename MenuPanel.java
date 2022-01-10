import java.awt.*;    
import javax.swing.*;   
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener {
	
	JButton btnStart;
	
	public MenuPanel() {
		super();
		
		this.setPreferredSize(new Dimension(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT));
		btnStart = new JButton("Start Game");
		
		setBounds(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
		setLayout(null);
		
		btnStart.setBounds(GamePanel.GAME_WIDTH / 2 - 100, GamePanel.GAME_HEIGHT / 2 - 100, 200, 100);
		add(btnStart);
		
		btnStart.addActionListener(this);
		 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
        
        if(source == btnStart) {
        	this.setVisible(false);
        	GameFrame gameFrame = new GameFrame();
        }
		
	}
}
