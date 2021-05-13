import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

//This class extends JComponent and overrides the paintComponent method in
//order to create the custom drawing.
public class GameCanvas extends JComponent {
    Player playerInstance = new Player();
    JPanel interimJPanel;
    InputMap inputMap;
    ActionMap actionMap;

    public GameCanvas(int w, int h,JPanel panel){
        setPreferredSize(new Dimension(w,h));
        interimJPanel = panel;
        inputMap = interimJPanel.getInputMap();
        actionMap = interimJPanel.getActionMap();
        addMovementActionMap();
        implementActionMap();
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);

        playerInstance.initPlayerRectangle(g2d);
        //playerInstance.initEnemyRectangle(g2d,/*add the PlayerRectangle instance returned by the server*/);
    }
    public void addMovementActionMap(){
        AbstractAction moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                playerInstance.movePlayer(-5);
            }
        };
        AbstractAction moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                playerInstance.movePlayer(5);
            }
        };
        actionMap.put("left",moveLeft);
        actionMap.put("right",moveRight);
    }

    public void implementActionMap(){
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),"left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),"right");
    }

}
