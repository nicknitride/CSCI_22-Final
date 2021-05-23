package FInalProj;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import javax.swing.*;

//This class contains the code that manages the player's appearance and functionality
public class Player{
    PlayerRectangles friendlyRectangle;
    int defaultRectangleWidth = 20, defaultRectangleHeight = 50;
    int playerX, playerY;
    int enemyX,enemyY;//Enemy Y will be used to detect collision between enemy fire and the squares
    int rightBorder, bottomBorder;
    ObjectOutputStream oOut;
    ObjectInputStream oIn;
    public Player(int width, int height, ObjectOutputStream oOut, ObjectInputStream oIn){
        playerX = 320;
        playerY = 390;
        rightBorder = width;
        bottomBorder = height;
        this.oOut = oOut;
        this.oIn = oIn;
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
            }//TODO implement an actionMap to create projectiles - assign the actionMap to a key using inputMap later on (High Priority)
        };
        actionMap.put("left",moveLeft);
        actionMap.put("right",moveRight);
    }
    public void initializeInputMap(InputMap inputMap){
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),"left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),"right");
    }
    public void initPlayerRectangle(Graphics2D g2d){
        friendlyRectangle = new PlayerRectangles(playerX,playerY,defaultRectangleWidth,defaultRectangleHeight);
        try{
            oOut.writeObject(friendlyRectangle);
        } catch (IOException exception) {
            exception.printStackTrace();
            if(friendlyRectangle==null){
                System.out.println("Friendly rectangle was null");
            }
        } finally {
            friendlyRectangle.draw(g2d);
        }

    }
    public void initEnemyRectangle(Graphics2D g2d){
        try {
            Object receivedObject = oIn.readObject();
            if (receivedObject instanceof PlayerRectangles) {
                PlayerRectangles instance = (PlayerRectangles) receivedObject;
                enemyX = instance.getX();
                instance = new PlayerRectangles(enemyX, 0, defaultRectangleWidth, defaultRectangleHeight);
                instance.draw(g2d);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }


    }
    public PlayerRectangles getFriendlyRectangle(){
        return friendlyRectangle;
    }

    //TODO - Implement chat (low priority)
}
