package FInalProj2.FInalProj;

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
    public PlayerRectangles rectangle;
    public ObjectOutputStream dataOut;
    public ObjectInputStream dataIn;

    public void setUpGUI(int width, int height){
        frame = new JFrame();
        framePanel = (JPanel) frame.getContentPane();

        frame.setSize(width,height);
        gameCanvas = new GameCanvas(width,height,exportJPanel(),dataOut,dataIn);
        framePanel.add(gameCanvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }



    public JPanel exportJPanel(){
        return framePanel;
    }
    public void setObjOut(ObjectOutputStream dataOut){
        this.dataOut = dataOut;
    }
    public void setObjIn(ObjectInputStream dataIn){
        this.dataIn = dataIn;
    }

    public void setUpTimer(){
        ActionListener timerActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                gameCanvas.repaint();
            }
        };
        timer = new Timer(20,timerActionListener);
        timer.start();
    }
}
