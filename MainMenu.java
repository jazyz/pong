import java.awt.*;    
import javax.swing.*;   
import java.awt.event.*;    

public class MainMenu extends JPanel implements ActionListener{
    
    int width, height;

    JButton play = new JButton("Play");
    JButton instructions = new JButton("Instructions");
    JButton exit = new JButton("Exit Game");
    
    AIGamePanel panel;

    JPanel panelContainer = new JPanel(); 
    JPanel menu = new JPanel(); 
    JPanel instruct = new JPanel();
    
    CardLayout layout = new CardLayout();
    
    public MainMenu() {
        this.width = 1000; this.height = 600;
        
        panelContainer.setLayout(layout);
    
        this.add(play);
        this.add(instructions);
        this.add(exit);

        this.setBackground(Color.cyan);
        instruct.setBackground(Color.red);
        
        panelContainer.add(menu, "Menu");
        panelContainer.add(instruct, "Instructions");

        play.addActionListener(this);
        instructions.addActionListener(this);
        exit.addActionListener(this);
        
        this.setPreferredSize(new Dimension(width, height));
        layout.show(panelContainer, "Menu");
        
    }


    public void actionPerformed(ActionEvent evt) {

        Object source = evt.getSource();
        
        if(source == play) {
        	this.add(panelContainer);
        	panel = new AIGamePanel(false, false); // run GamePanel constructor
        	panelContainer.add(panel, "Game");
            layout.show(panelContainer, "Game");
        }
        else if(source == instructions) {
            layout.show(panelContainer, "Instructions");
        }
        else if(source == exit) {
            System.exit(0);
        }
        

        
    }


}
