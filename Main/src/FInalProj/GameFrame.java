package FInalProj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class contains the code that sets up the main JFrame for the player.
public class GameFrame {
    public JFrame frame;
    public GameCanvas gameCanvas;
    public Timer timer;
    public JPanel framePanel;
    public PlayerRectangles rectangle, enemyRectangle;
    public void setUpGUI(int width, int height){
        frame = new JFrame();
        framePanel = (JPanel) frame.getContentPane();

        frame.setSize(width,height);
        gameCanvas = new GameCanvas(width,height,exportJPanel(),enemyRectangle);//FInalProj.GameFrame's enemy rectangle
        //gets sent to FInalProj.GameCanvas
        framePanel.add(gameCanvas);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void updateRectanglePosition(PlayerRectangles instance){//Enemy rectangle is set from the main method
        enemyRectangle = instance;
    }

    public JPanel exportJPanel(){
        return framePanel;
    }

    public PlayerRectangles getRectangle(){
        return rectangle;
    }



    public void setUpTimer(){
        ActionListener timerActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                gameCanvas.repaint();
                rectangle = gameCanvas.getRectangle();
            }
        };
        timer = new javax.swing.Timer(20,timerActionListener);
        timer.start();
    }
}
