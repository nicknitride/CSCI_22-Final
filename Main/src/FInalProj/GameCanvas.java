package FInalProj;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//This class extends JComponent and overrides the paintComponent method in
//order to create the custom drawing.
public class GameCanvas extends JComponent {
    Player playerInstance;
    JPanel interimJPanel;
    InputMap inputMap;
    ActionMap actionMap;
    PlayerRectangles rectangle;

    public GameCanvas(int w, int h, JPanel panel, ObjectOutputStream oOut, ObjectInputStream oIn, String playerType){//The constructor then sets the enemy rectangle to a class variable
        setPreferredSize(new Dimension(w,h));
        playerInstance = new Player(w, h, oOut, oIn,playerType);
        interimJPanel = panel;
        inputMap = interimJPanel.getInputMap();//Passes the InputMap taken from GameFrame to the Playerinstance
        actionMap = interimJPanel.getActionMap();
        playerInstance.initializeInputMap(inputMap);
        playerInstance.initializeActionMap(actionMap);

    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);

        playerInstance.initPlayerRectangle(g2d);
        playerInstance.initEnemyRectangle(g2d);
    }


}
