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
    int x,y,width,height,xSpeed, ySpeed;
    MyRectangle(int x, int y, int width, int height, int speed){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        xSpeed=speed;
        ySpeed=speed*-1;
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

    public int getxSpeed() {
        return xSpeed;
    }
    public int getySpeed() {
        return ySpeed;
    }

    public void xReverse(){xSpeed=xSpeed*-1;}

    public void yReverse(){ySpeed=ySpeed*-1;}


    public void totalReverse(){
        xSpeed=xSpeed*-1;
        ySpeed=ySpeed*-1;
        move();
    }


    public void borderCollision(){
        int rightBorder=800, bottomBorder=600;
        if(this.x+this.width>rightBorder){
            xReverse();
            move();
        }
        else if(this.y<0){
            yReverse();
            move();
        }
        else if(this.x<0){
            xReverse();
            move();
        }
        else if(this.y+this.height>bottomBorder){
            yReverse();
            move();
        }
        else
            move();
    }

    public void move(){
        x+=xSpeed;
        y+=ySpeed;
    }


    public Boolean isColliding(MyRectangle instance){
        boolean objectCollision = !(this.y>= instance.getY()+ instance.getHeight()
                || this.y+this.height<= instance.getY()
                || instance.getX()+ instance.getWidth()<=this.x
                || this.x+this.width<= instance.getX());
        return (objectCollision);//Made the boolean easier to read for maintainability
    }

}
