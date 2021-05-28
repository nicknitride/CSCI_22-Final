package FInalProj;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;

//This class contains the code that manages the player's appearance and functionality
public class Player {
    //Server Related
    ObjectOutputStream oOut;
    ObjectInputStream oIn;
    String playerType;
    double rawHitCount;
    int hostileHitCount;

    //Friendly Rectangle
    PlayerRectangles friendlyRectangle;
    int defaultRectangleWidth = 20, defaultRectangleHeight = 50;
    int playerX, playerY;
    int projectileY;

    //Enemy and Enemy Projectiles
    int enemyX;
    int rightBorder, bottomBorder;
    Circle projectile, enemyProjectile;
    boolean projectileIsActive;


    public Player(int width, int height, ObjectOutputStream oOut, ObjectInputStream oIn, String playerType) {
        playerX = 320;
        playerY = 400;
        rightBorder = width;
        bottomBorder = height;
        this.oOut = oOut;
        this.oIn = oIn;
        this.playerType = playerType;
    }

    public void movePlayer(int x) {
        playerX += x;
    }

    public void initializeActionMap(ActionMap actionMap) {
        AbstractAction moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                movePlayer(-5);
                if (playerX <= 0) {
                    movePlayer(5);
                }
            }
        };
        AbstractAction moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                movePlayer(5);
                if (playerX + defaultRectangleWidth >= rightBorder) {
                    movePlayer(-5);
                }
            }
        };
        AbstractAction fireProjectile = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectileIsActive = true;
                System.out.println("Projectile movement status:" + projectileIsActive);
            }
        };
        actionMap.put("left", moveLeft);
        actionMap.put("right", moveRight);
        actionMap.put("space", fireProjectile);
    }

    public void initializeInputMap(InputMap inputMap) {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space");
    }

    public void initPlayerRectangle(Graphics2D g2d) {
        friendlyRectangle = new PlayerRectangles(playerX, playerY, defaultRectangleWidth, defaultRectangleHeight, getPlayerType(), projectileY);
        try {
            oOut.writeObject(friendlyRectangle);
        } catch (IOException exception) {
            exception.printStackTrace();
            if (friendlyRectangle == null) {
                System.out.println("Friendly rectangle was null");
            }
        } finally {
            friendlyRectangle.draw(g2d);
            checkHealth();
            projectile = new Circle(playerX, projectileY, 30, new Color(16, 99, 113));//TODO white projectile if inert and when in motion enable color
            if (projectileIsActive) {
                projectile.draw(g2d);
                moveProjectile();
                projectileBorderCollision();
            } else if (!projectileIsActive) {
                projectileY = 370;
                projectile.draw(g2d);
            }
        }
    }
    public void initEnemyRectangle(Graphics2D g2d){
        try {
            Object receivedObject = oIn.readObject();
            if (receivedObject instanceof PlayerRectangles) {
                PlayerRectangles instance = (PlayerRectangles) receivedObject;
                enemyX = instance.getX();
                instance = new PlayerRectangles(enemyX, 0, defaultRectangleWidth, defaultRectangleHeight,"",instance.getProjectilePos());
                renderEnemyProjectile(g2d,instance);
                checkHostileCollisionWithFriendly(instance);
                instance.draw(g2d);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String getPlayerType(){
        return playerType;
    }

    public void moveProjectile(){
        projectileY-=5;
    }

    public void projectileBorderCollision(){
        if(projectileY<=0) {
            projectileIsActive = false;
            System.out.println("Projectile has collided with the border");
        }
    }

    public void renderEnemyProjectile(Graphics2D g2d, PlayerRectangles instance){
        enemyProjectile = new Circle(instance.getX(), 420-instance.getProjectilePos(), 30, new Color(16, 99, 113));
        enemyProjectile.draw(g2d);
    }
    public void checkHostileCollisionWithFriendly(PlayerRectangles instance){
        if ((enemyX==playerX+5 || enemyX==playerX-5 || enemyX==playerX) && !((enemyProjectile.getY())<=playerY-defaultRectangleHeight)) {//TODO figure out the boolean expression for collision
            rawHitCount +=0.067;//Because each hit counts as 15 when using int 0.06 = 1/15
        }
    }
    public void checkHealth() {
        int interpretedHitCount = (int) rawHitCount;
        if (interpretedHitCount >= 3) {
            System.out.println("Friendly has no hit points remaining");//This works TODO a condition that gets called when health reaches 0
        }
    }
    //TODO write a method that checks if Player projectile has damaged hostile rectangle
}
