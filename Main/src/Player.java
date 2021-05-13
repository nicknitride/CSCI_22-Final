import java.awt.*;

//This class contains the code that manages the player's appearance and functionality
public class Player {
    Circle circle1= new Circle(320,480,40,Color.black);
    int playerX =320, playerY = 390;
    PlayerRectangles friendlyRectangle;
    PlayerRectangles enemyRectangle;
    public void initPlayerRectangle(Graphics2D g2d){
        friendlyRectangle = new PlayerRectangles(playerX,playerY,20,50,640,480);
        friendlyRectangle.draw(g2d);
    }
    public void initEnemyRectangle(Graphics2D g2d){
        enemyRectangle = new PlayerRectangles(320,0,20,50,640,480);
        enemyRectangle.draw(g2d);
    }



}
