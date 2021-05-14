package FInalProj2.FInalProj;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//This class extends JComponent and overrides the paintComponent method in
//order to create the custom drawing.
public class GameCanvas extends JComponent {
    Player playerInstance;
    JPanel interimJPanel;
    InputMap inputMap;
    ActionMap actionMap;
    PlayerRectangles rectangle, enemyRectangle;
    ObjectOutputStream dataOut;
    ObjectInputStream dataIn;

    public GameCanvas(int w, int h,JPanel panel, ObjectOutputStream dataOut, ObjectInputStream dataIn){
        setPreferredSize(new Dimension(w,h));
        playerInstance = new Player(w, h);
        interimJPanel = panel;
        inputMap = interimJPanel.getInputMap();
        actionMap = interimJPanel.getActionMap();
        playerInstance.initializeInputMap(inputMap);
        playerInstance.initializeActionMap(actionMap);
        this.dataOut = dataOut;
        this.dataIn = dataIn;
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);

        playerInstance.initPlayerRectangle(g2d);
        rectangle = playerInstance.getFriendlyRectangle();
        try{
            if(rectangle != null) {
                dataOut.reset();
                dataOut.writeObject(rectangle);
                dataOut.flush();
            }
            else {
                System.out.println("Rectangle in canvas = null");
            }
        } catch (IOException e) {
            System.out.println("Error writing the non-enemy rectangle");
        }
        try {
            Object obj = dataIn.readObject();
            if(obj instanceof PlayerRectangles) {
                enemyRectangle = (PlayerRectangles) obj;
                playerInstance.initEnemyRectangle(g2d, enemyRectangle);
            }
            else {
                System.out.println("Player rectangle was null");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("GameCanvas may have encountered a null object");
        }
    }
}
