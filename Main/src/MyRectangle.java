import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 @author Nicholo Patrick C. Pardines (193821)
 @version May 3, 2021

 The "My Rectangle" Class contains methods which define movement through positive and negative x and/or y values.
 It also has separate methods for border collision and object collision.
 **/

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
public class MyRectangle {
    int x,y,width,height,xSpeed, ySpeed, rightBorder, bottomBorder;
    MyRectangle(int x, int y, int width, int height, int rightBorder, int bottomBorder){
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


    public int hitCounter(Circle circleInstance){
        int circleX, circleY, rectangleX, rectangleY;
        circleX = circleInstance.getX();
        circleY = circleInstance.getY();

        rectangleX = x+(x/2);
        rectangleY = y;
        double distance = Math.sqrt((circleX-rectangleX)*(circleX-rectangleX)
                + (circleY-rectangleY)*(circleY-rectangleY));


        if (circleInstance.getRadius()<distance ){
            return 1;
        }
        return 0;
    }

    //public displayEnemy() - implement later using DataOutputStream in Java Socket

}
