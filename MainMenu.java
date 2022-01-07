//NOT YET FINISHED
//NOT YET FINISHED
//NOT YET FINISHED

import java.awt.*;     
import javax.swing.*;   
import java.awt.event.*;    

public class MainMenu extends JFrame implements ActionListener{
    
    int width, height;

    JButton play = new JButton("Play");
    JButton instructions = new JButton("Instructions");
    JButton exit = new JButton("Exit Game");


    CardLayout layout = new CardLayout();

    JPanel menu = new JPanel();
    JPanel panelContainer = new JPanel();

    public MainMenu(int w, int h) {
        this.width = w;
        this.height = h;

        panelContainer.setLayout(layout);

        menu.add(play);
        menu.add(instructions);
        menu.add(exit);

        menu.setBackground(Color.cyan);

        panelContainer.add(menu, "Menu");

        //layout.show(panelContainer, "Menu");

        play.addActionListener(this);
        instructions.addActionListener(this);
        exit.addActionListener(this);


        setSize(width,height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Pong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();

    }


    public void actionPerformed(ActionEvent evt) {

        Object source = evt.getSource();

        if(source == play) {
            layout.show(panelContainer, "Game");
        }
        if(source == exit) {
            System.exit(0);
        }
        else if(source == instructions) {
            layout.show(panelContainer, "Instructions");
        }
    }


}

