//This class contains the main method that will start the game from the player's
//side.
public class GameStarter {
    public static void main(String[] args) {
        GameFrame clientFrame = new GameFrame();
        clientFrame.setUpGUI(640,480);
        clientFrame.setUpTimer();
    }
}
