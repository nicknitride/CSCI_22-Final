package Final;
/**
 @author Nicholo Patrick C. Pardines (193821)
 @version May 23,2020
The PlayerRectangles class is a slightly modified version of a class used in previous CSCI projects.
 A majority of its function is simply passing on values from its constructor in order to generate a Java2D object.
 Its secondary function is passing color values and projectile positions from the client to the server and vice-versa.
 It implements Serializable so that it can be deserialized after being sent and retrieved through Object streams. 
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
import java.awt.geom.Rectangle2D;
import java.io.Serializable;


public class PlayerRectangles implements Serializable {
    int x,y,width,height, projectilePos;
    Color projectileColor;
    String label;
    PlayerRectangles(int x, int y, int width, int height,String label,int projectilePos,Color color){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.label = label;
        this.projectilePos = projectilePos;
        projectileColor = color;
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

    public int getProjectilePos(){
        return projectilePos;
    }

    public Color getProjectileColor() {
        return projectileColor;
    }
}
