import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

//This class contains the code that manages the player's appearance and functionality
public class Player{
    PlayerRectangles friendlyRectangle;
    int defaultRectangleWidth = 20, defaultRectangleHeight = 50;
    int playerX, playerY;
    int enemyX, enemyY;
    int rightBorder, bottomBorder;
    ArrayList<Circle> circleArray = new ArrayList<>();

    public Player(int width, int height){
        playerX = 320;
        playerY = 390;
        rightBorder = width;
        bottomBorder = height;
    }

    public void movePlayer(int x){
        playerX+=x;
    }


    public void initializeActionMap(ActionMap actionMap){
        AbstractAction moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                movePlayer(-5);
                if(playerX<=0){
                    movePlayer(5);
                }
            }
        };
        AbstractAction moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                movePlayer(5);
                if(playerX+defaultRectangleWidth>=rightBorder){
                    movePlayer(-5);
                }
            }
        };
        /* Implement later
        AbstractAction fireWeapon = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        };
         */
        actionMap.put("left",moveLeft);
        actionMap.put("right",moveRight);
    }


    public void initializeInputMap(InputMap inputMap){
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),"left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),"right");
    }
    public void initPlayerRectangle(Graphics2D g2d){
        friendlyRectangle = new PlayerRectangles(playerX,playerY,defaultRectangleWidth,defaultRectangleHeight);
        friendlyRectangle.draw(g2d);
    }

    public void initEnemyRectangle(Graphics2D g2d, PlayerRectangles instance){
        try {
            enemyX = instance.getX();
            instance = new PlayerRectangles(enemyX, 0, defaultRectangleWidth, defaultRectangleHeight);
            instance.draw(g2d);
        }catch (Exception exception){
            System.out.println("Enemy rectangle failed to render");
            exception.printStackTrace();
        }
    }

    public PlayerRectangles getFriendlyRectangle(){
        return friendlyRectangle;
    }
}
