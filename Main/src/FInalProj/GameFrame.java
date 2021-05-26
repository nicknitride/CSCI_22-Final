package FInalProj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//This class contains the code that sets up the main JFrame for the player.
public class GameFrame {
    public JFrame frame;
    public GameCanvas gameCanvas;
    public Timer timer;
    public JPanel framePanel;
    public void setUpGUI(int width, int height, ObjectOutputStream oOut, ObjectInputStream oIn, String playerType){
        frame = new JFrame();
        framePanel = (JPanel) frame.getContentPane();

        frame.setSize(width,height);

        gameCanvas = new GameCanvas(width,height,exportJPanel(), oOut,oIn,playerType);
        framePanel.add(gameCanvas);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public JPanel exportJPanel(){
        return framePanel;
    }

    public void setUpTimer(){
        ActionListener timerActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                gameCanvas.repaint();
            }
        };
        timer = new javax.swing.Timer(20,timerActionListener);
        timer.start();
    }
}
