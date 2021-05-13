import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/*
Generates player rectangles
 */
/*
To-do: Implement a JPEG overlay on top of the rectangles for player icons
 */
public class PlayerRectangles {
    int x,y,width,height, rightBorder, bottomBorder;
    PlayerRectangles(int x, int y, int width, int height, int rightBorder, int bottomBorder){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.rightBorder = rightBorder;
        this.bottomBorder = bottomBorder;
    }
    public void draw(Graphics2D g2d){
        Rectangle2D.Double rectangle2D = new Rectangle2D.Double(x,y,width,height);
        g2d.setColor(Color.BLACK);
        g2d.fill(rectangle2D);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void friendlyKeybindingMovement(int x){
        /* use this when implementing KeyMap and actionMap in the player class
         * Friendly movement right arrow key = positive +=x
         * Friendly movement left arrow key = negative +=-x
         * Implement collision/edge detection in the player class
         */

    }

    public void enemyMovement(int x){
        /*use this when implementing KeyMap and actionMap in the player class
         * enemy movement right arrow key = +=-x
         * Friendly movement left arrow key = +=x
         * Implement collision/edge detection in the player class
         */

    }

    //public displayEnemy() - implement later using DataOutputStream in Java Socket

}
