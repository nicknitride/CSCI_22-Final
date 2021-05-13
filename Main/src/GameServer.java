import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//This class contains the code that manages the game server's functionality. It also
//contains the main method that instantiates and starts the server.
public class GameServer {
    int connectedClientCount;
    ServerSocket serverSocket;
    Socket clientSocket;
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;
    public GameServer() throws IOException{
        serverSocket = new ServerSocket(52300);

    }
    public class connectionAttempt implements Runnable{
        public void run() {
            while(connectedClientCount<1) {
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("server full");
            try {
                serverSocket.close();
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
        GameServer server = new GameServer();
        server.initThreads();
        server.objectOut.writeChars("Test");
    }
}
