import javax.swing.*;
import java.awt.*;

//This class contains the code that sets up the main JFrame for the player.
public class GameFrame {
    JFrame frame;
    GameCanvas gameCanvas;
    Container container;
    public void setUpGUI(){
        frame = new JFrame();
        gameCanvas = new GameCanvas();
        container.add(gameCanvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
