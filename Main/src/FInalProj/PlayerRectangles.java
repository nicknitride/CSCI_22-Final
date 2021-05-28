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
    int x,y,width,height, projectilePos;
    String label;
    PlayerRectangles(int x, int y, int width, int height,String label,int projectilePos){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.label = label;
        this.projectilePos = projectilePos;
    }

    public void draw(Graphics2D g2d){
        Rectangle2D.Double rectangle2D = new Rectangle2D.Double(x,y,width,height);
        g2d.setColor(Color.BLACK);
        g2d.fill(rectangle2D);
        g2d.drawString(label,x+20,y+50);
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

    public int getProjectilePos(){
        return projectilePos;
    }
}
