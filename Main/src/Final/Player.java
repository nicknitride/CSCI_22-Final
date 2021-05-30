package Final; /**
 @author Nicholo Patrick C. Pardines (193821)
 @version May 23,2020
The Player class takes the objects passed by the ObjectInput/ObjectOutputStream generated in the Main class, and generates rectangles for both the client and the server.
It contains all the functionality relevant to the game's players such as: movement, projectiles, and checking the Player's/Enemy's health in order to determine win/loss.
 */

/*
	I have not discussed the Java language code in my program
	with anyone other than my instructor or the teaching assistants
	assigned to this course.

	I have not used Java language code obtained from another student,
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program
	was obtained from another source, such as a textbook or website,
	that has been clearly noted with a proper citation in the comments
	of my program.
*/

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
    double hostileHitCount;
    Color projectileColor;
    Boolean win = false, loss = false;
    int enemyLife;
    int playerLife;


    //Friendly Rectangle
    PlayerRectangles friendlyRectangle;
    int defaultRectangleWidth = 20, defaultRectangleHeight = 50;
    int playerX, playerY;
    int projectileY;

    //Enemy and Enemy Projectiles
    PlayerRectangles instance;
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
                //System.out.println("Projectile movement status:" + projectileIsActive);
            }
        };
        AbstractAction quitGame = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(win || loss){
                    System.exit(0);
                }
            }
        };
        actionMap.put("left", moveLeft);
        actionMap.put("right", moveRight);
        actionMap.put("space", fireProjectile);
        actionMap.put("enter",quitGame);
    }

    public void initializeInputMap(InputMap inputMap) {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false),"enter");
    }

    public void initPlayerRectangle(Graphics2D g2d) throws IOException, ClassNotFoundException {
        friendlyRectangle = new PlayerRectangles(playerX, playerY, defaultRectangleWidth, defaultRectangleHeight, getPlayerType(), projectileY,projectileColor);
        if(!win && !loss) {
            oOut.writeObject(friendlyRectangle);
            friendlyRectangle.draw(g2d);
            projectile = new Circle(playerX, projectileY, 30, projectileColor);
            if (projectileIsActive) {
                projectileColor = new Color(255, 117, 117);
                projectile.draw(g2d);
                moveProjectile();
                projectileBorderCollision();
            } else {
                projectileColor = new Color(238, 238, 238);
                projectileY = 370;
                projectile.draw(g2d);
            }
        }
        else if (win){
            oOut.writeObject(friendlyRectangle);
            friendlyRectangle.draw(g2d);
            g2d.drawString("YOU'VE WON!",320,240);
            g2d.drawString("Press ENTER to Quit",320,255);
        }
        else if (loss){
            oOut.writeObject(friendlyRectangle);
            friendlyRectangle.draw(g2d);
            g2d.drawString("YOU'VE LOST :(",320,240);
            g2d.drawString("Press ENTER to Quit",320,255);
        }
    }
    public void initEnemyRectangle(Graphics2D g2d) throws IOException, ClassNotFoundException {
        Object receivedObject = oIn.readObject();
        if((!win && !loss) && receivedObject instanceof PlayerRectangles) {
            instance = (PlayerRectangles) receivedObject;
            enemyX = instance.getX();
            instance = new PlayerRectangles(enemyX, 0, defaultRectangleWidth, defaultRectangleHeight, ""+enemyLife, instance.getProjectilePos(), instance.getProjectileColor());
            renderEnemyProjectile(g2d, instance);
            checkFriendlyCollisionWithHostile(instance);
            checkHostileCollisionWithFriendly();
            checkEnemyHealth();
            checkHealth(g2d);
            instance.draw(g2d);
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
        }
    }

    public void renderEnemyProjectile(Graphics2D g2d, PlayerRectangles instance){
        enemyProjectile = new Circle(instance.getX(), 420-instance.getProjectilePos(), 30, instance.projectileColor);
        enemyProjectile.draw(g2d);
    }
    public void checkHostileCollisionWithFriendly(){
        if ((enemyX==playerX+5 || enemyX==playerX-5 || enemyX==playerX) && !((enemyProjectile.getY())<=playerY-defaultRectangleHeight)) {
            rawHitCount +=0.09;//Hostile hit count has to be delayed in order for the hit to register on the client side
            //When on the final strike changing to a higher value results in a JFrame crash
        }
    }
    public void checkFriendlyCollisionWithHostile(PlayerRectangles instance){
        if ((playerX==enemyX+5 || playerX==enemyX-5 || playerX==enemyX) && !((projectileY)>=instance.getY()+defaultRectangleHeight)) {
            hostileHitCount +=0.1;//Needs to be 0.01 faster than HostileCollisionWithFriendly
        }
    }
    public void checkHealth(Graphics2D g2d) {
        int friendlyHealth = 3;
        playerLife = friendlyHealth - ((int) rawHitCount);
        if (playerLife <= 0) {
            loss=true;
        }
        String display = ""+playerLife;
        g2d.drawString(display,playerX,playerY+10);
    }

    public void checkEnemyHealth(){
        int enemyHealth = 3;
        enemyLife = enemyHealth - ((int)hostileHitCount);
        if(enemyLife<=0){
            win=true;
        }
    }
}
