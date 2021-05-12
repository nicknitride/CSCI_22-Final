import java.awt.*;

//This class contains the code that manages the player's appearance and functionality
public class Player {
    Circle circle1= new Circle(320,480,40,Color.black);
    Circle circle2 = new Circle(320,480,40,Color.black);
    int hitCount= 0;
    MyRectangle friendlyRectangle;
    MyRectangle enemyRectangle;
    public void initPlayerRectangle(Graphics2D g2d){
        friendlyRectangle = new MyRectangle(320,390,20,50,640,480);
        friendlyRectangle.draw(g2d);
        circle1.draw(g2d);
        circle1.move(8);
        hitCount+=friendlyRectangle.hitCounter(circle1);
    }
    public void initEnemyRectangle(Graphics2D g2d){
        enemyRectangle = new MyRectangle(320,0,20,50,640,480);
        enemyRectangle.draw(g2d);
        printHitCount();
    }

    public void printHitCount(){
        System.out.println("Hit count: "+hitCount);
    }
}
