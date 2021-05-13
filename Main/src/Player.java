import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//This class contains the code that manages the player's appearance and functionality
public class Player {
    Circle tempTestCircle = new Circle(320,480,40,Color.black);
    PlayerRectangles friendlyRectangle;
    int playerX, playerY;
    int enemyX, enemyY;
    InputMap inputMap;
    ActionMap actionMap;
    public Player(){
        playerX = 320;
        playerY = 390;

        inputMap = new InputMap();
        actionMap = new ActionMap();

        addMovementActionMap();
        implementActionMap();
    }

    public void addMovementActionMap(){
        AbstractAction moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                playerX-=5;
            }
        };
        AbstractAction moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                playerX+=5;
            }
        };
        actionMap.put("left",moveLeft);
        actionMap.put("right",moveRight);
    }

    public void implementActionMap(){
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),"left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),"right");
    }
    //create a class that returns a JPanel with the input map and action map attached
    public void initPlayerRectangle(Graphics2D g2d){
        friendlyRectangle = new PlayerRectangles(playerX,playerY,20,50,640,480);
        friendlyRectangle.draw(g2d);
    }
    public void initEnemyRectangle(Graphics2D g2d, PlayerRectangles instance){
        enemyY = instance.getY();
        enemyX = instance.getX();
        instance = new PlayerRectangles(enemyX,enemyY,20,50,640,480);
        instance.draw(g2d);
    }



}
