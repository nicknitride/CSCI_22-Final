package Final; /**
 @author Nicholo Patrick C. Pardines (193821)
 @version March 29, 2020
 This class draws an ellipse using the Ellipse2D class and contains a method defining its movement.
 This class is used to render the projectiles in-game.
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
import java.awt.geom.Ellipse2D;

public class Circle{
    private int xPosition, yPosition, size;
    private Color color;

    public Circle(int xPosition, int yPosition, int size, Color color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics2D g2d) {
           Ellipse2D.Double circle1 = new Ellipse2D.Double(xPosition, yPosition, size, size);
            g2d.setColor(color);
            g2d.fill(circle1);
            //String coordinates = "coordinates: ("+getX()+", "+getY()+")";
            //g2d.drawString(coordinates,getX(),getY()+5);
    }

    public int getY(){
        return yPosition;
    }
    public int getX(){
        return xPosition;
    }
}
