package FInalProj;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/*
Generates player rectangles
 */
/*
To-do: Implement a JPEG overlay on top of the rectangles for player icons
 */
public class PlayerRectangles implements Serializable {
    int x,y,width,height, rightBorder, bottomBorder;
    PlayerRectangles(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
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


}
