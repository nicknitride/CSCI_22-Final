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
            dataOut.reset();
            dataOut.writeObject(rectangle);
            dataOut.flush();
        } catch (IOException e) {
            System.out.println("Error writing the non-enemy rectangle");
        }
        try {
            enemyRectangle = (PlayerRectangles) dataIn.readObject();
            playerInstance.initEnemyRectangle(g2d,enemyRectangle);
        } catch (IOException e) {
            System.out.println("GameCanvas may have encountered a null object");
        } catch (ClassNotFoundException e) {
            System.out.println("GameCanvas may have encountered a null object");
        }
    }
}
