package Final;
/**
 @author Nicholo Patrick C. Pardines (193821)
 @version May 23,2020
This class extends JComponent in orde to override the paintComponent method.
The graphics object this method generates is cast to Graphics2D and is then sent to The player instance in order to render the
player rectangles.
 */

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

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameCanvas extends JComponent {
    Player playerInstance;
    JPanel interimJPanel;
    InputMap inputMap;
    ActionMap actionMap;
    public GameCanvas(int w, int h, JPanel panel, ObjectOutputStream oOut, ObjectInputStream oIn, String playerType){//The constructor then sets the enemy rectangle to a class variable
        setPreferredSize(new Dimension(w,h));
        playerInstance = new Player(w, h, oOut, oIn,playerType);
        interimJPanel = panel;
        inputMap = interimJPanel.getInputMap();//Passes the InputMap taken from GameFrame to the Player Instance
        actionMap = interimJPanel.getActionMap();
        playerInstance.initializeInputMap(inputMap);
        playerInstance.initializeActionMap(actionMap);
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);
        try {
            playerInstance.initPlayerRectangle(g2d);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        try {
            playerInstance.initEnemyRectangle(g2d);
        } catch (IOException | ClassNotFoundException exception) {
            try {
                System.out.println("Other play may have been disconnected or they may have left the game");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}
