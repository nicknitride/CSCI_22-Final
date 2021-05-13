import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//This class contains the code that manages the player's appearance and functionality
public class Player {
    Circle tempTestCircle = new Circle(320,480,40,Color.black);
    PlayerRectangles friendlyRectangle;
    int playerX, playerY;
    int enemyX, enemyY;



    public Player(){
        playerX = 320;
        playerY = 390;
    }

    public void movePlayer(int x){
        playerX+=x;
    }

    //Action Map and InputMap were moved into game canvas

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
