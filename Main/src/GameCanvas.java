import javax.swing.*;
import java.awt.*;

//This class extends JComponent and overrides the paintComponent method in
//order to create the custom drawing.
public class GameCanvas extends JComponent {
    Player playerInstance;
    JPanel interimJPanel;
    InputMap inputMap;
    ActionMap actionMap;
    PlayerRectangles rectangle, enemyRectangle;

    public GameCanvas(int w, int h,JPanel panel, PlayerRectangles enemyRectangle){//The constructor then sets the enemy rectangle to a class variable
        //which is used in the paintComponent method
        setPreferredSize(new Dimension(w,h));
        playerInstance = new Player(w, h);
        interimJPanel = panel;
        inputMap = interimJPanel.getInputMap();
        actionMap = interimJPanel.getActionMap();
        playerInstance.initializeInputMap(inputMap);
        playerInstance.initializeActionMap(actionMap);
        this.enemyRectangle = enemyRectangle;
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);

        playerInstance.initPlayerRectangle(g2d);
        rectangle = playerInstance.getFriendlyRectangle();
        playerInstance.initEnemyRectangle(g2d,enemyRectangle);//This gets passed to the player method
    }

    public PlayerRectangles getRectangle(){
        return rectangle;
    }

}
