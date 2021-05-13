import java.io.*;
import java.net.Socket;

//This class contains the main method that will start the game from the player's
//side.
public class GameStarter {
    Socket clientSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;

    public GameStarter() throws IOException {
        /*
        clientSocket = new Socket("localhost", 51244);
        objectIn = new ObjectInputStream(clientSocket.getInputStream());
        objectOut = new ObjectOutputStream(clientSocket.getOutputStream());

         */
    }

    public void initGUIThread(){
        guiStarter gui = new guiStarter();
        Thread guiThread = new Thread(gui);
        guiThread.start();
    }

    public static class guiStarter implements Runnable{
        GameFrame clientFrame;
        guiStarter(){
            clientFrame = new GameFrame();
        }
        @Override
        public void run() {
            clientFrame.setUpGUI(640,480);
            clientFrame.setUpTimer();
        }
    }

    public static void main(String[] args){
        try {
            GameStarter game = new GameStarter();
            game.initGUIThread();
        } catch (IOException exception){
            System.out.println("Error initializing game thread");
            exception.printStackTrace();
        }


    }
}
