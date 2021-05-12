import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class contains the code that sets up the main JFrame for the player.
public class GameFrame {
    public JFrame frame;
    public GameCanvas gameCanvas;
    public Timer timer;
    private int counter;
    public void setUpGUI(int width, int height){
        frame = new JFrame();
        Container frameContainer = frame.getContentPane();

        frame.setSize(width,height);
        gameCanvas = new GameCanvas(width,height);
        frameContainer.add(gameCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void setUpTimer(){
        ActionListener timerActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                gameCanvas.repaint();
                counter+=1;
                System.out.println(counter);
            }
        };
        timer = new javax.swing.Timer(20,timerActionListener);
        timer.start();
    }
}
