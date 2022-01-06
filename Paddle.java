import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

  public int yVelocity;
  public final int SPEED = 5; //movement speed of paddle
  public static final int HEIGHT = 50; // height of paddle
  public static final int WIDTH = 10; // width of paddle
  public char upArrow;
  public char downArrow;

  //constructor creates ball at given location with given dimensions
  public Paddle(int x, int y, char upArrow, char downArrow){
    super(x, y, WIDTH, HEIGHT);
    this.upArrow = upArrow;
    this.downArrow = downArrow;
  }

  //called from GamePanel when any keyboard input is detected
  //updates the direction of the ball based on user input
  //if the keyboard input isn't any of the options (d, a, w, s), then nothing happens
  public void keyPressed(KeyEvent e){
    if(e.getKeyChar() == upArrow){
      setYDirection(SPEED*-1);
      move();
    }

    if(e.getKeyChar() == downArrow){
      setYDirection(SPEED);
      move();
    }
  }

  //called from GamePanel when any key is released (no longer being pressed down)
  //Makes the ball stop moving in that direction
  public void keyReleased(KeyEvent e){

    if(e.getKeyChar() == upArrow){
      setYDirection(0);
      move();
    }

    if(e.getKeyChar() == downArrow){
      setYDirection(0);
      move();
    }
  }

  //called whenever the movement of the ball changes in the y-direction (up/down)
  public void setYDirection(int yDirection){
    yVelocity = yDirection;
  }


  //called frequently from both PlayerBall class and GamePanel class
  //updates the current location of the ball
  public void move(){
    y = y + yVelocity;
  }

  //called frequently from the GamePanel class
  //draws the current location of the ball to the screen
  public void draw(Graphics g){
    g.setColor(Color.black);
    g.fillRect(x, y, WIDTH, HEIGHT);
  }
  
}