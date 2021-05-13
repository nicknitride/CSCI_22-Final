import javax.swing.*;
import java.awt.*;

//This class extends JComponent and overrides the paintComponent method in
//order to create the custom drawing.
public class GameCanvas extends JComponent {
    Player playerInstance;
    JPanel interimJPanel;
    InputMap inputMap;
    ActionMap actionMap;

    public GameCanvas(int w, int h,JPanel panel){
        setPreferredSize(new Dimension(w,h));
        playerInstance = new Player(w, h);
        interimJPanel = panel;
        inputMap = interimJPanel.getInputMap();
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
        //playerInstance.initEnemyRectangle(g2d,/*add the PlayerRectangle instance returned by the server*/);
    }

}
