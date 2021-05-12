import javax.swing.*;
import java.awt.*;

//This class extends JComponent and overrides the paintComponent method in
//order to create the custom drawing.
public class GameCanvas extends JComponent {
    Circle circle1= new Circle(80,480,40,Color.black);
    public GameCanvas(int w, int h){
        setPreferredSize(new Dimension(w,h));
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);
        circle1.draw(g2d);
        circle1.move();
    }
}
