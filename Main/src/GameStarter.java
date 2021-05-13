import java.io.*;
import java.net.Socket;

//This class contains the main method that will start the game from the player's
//side.
public class GameStarter {
    Socket clientSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;

    public GameStarter() throws IOException {
        initThreads();
    }
    public class connectionAttempt implements Runnable{
        public void run() {
            try {
                clientSocket = new Socket("localhost", 52300);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void initThreads() throws IOException {
        guiStarter gui = new guiStarter();
        Thread guiThread = new Thread(gui);
        guiThread.start();
        Runnable conRunnable = new connectionAttempt();
        Thread conAttempt = new Thread(conRunnable);
        conAttempt.start();
        objectIn = new ObjectInputStream(clientSocket.getInputStream());
        objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
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

    public static void main(String[] args) throws IOException {
        GameStarter game = new GameStarter();
        String message = game.objectIn.readUTF();
    }
}
