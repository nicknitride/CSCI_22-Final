import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameStarter {
    PlayerRectangles currentRectangle, enemyRectangle;
    Socket clientSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;

    public GameStarter() throws IOException {
        connectionAttempt();
        initThreads();
    }
    public void connectionAttempt(){
    try {
        clientSocket = new Socket("localhost", 52300);
        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();
        objectOut = new ObjectOutputStream(outputStream);
        objectIn = new ObjectInputStream(inputStream);
    } catch (IOException e){
        e.printStackTrace();
        }
    }

    public void initThreads(){
        guiStarter gui = new guiStarter();
        Thread guiThread = new Thread(gui);
        guiThread.start();

        Runnable readClient = new ReadChat();
        Runnable sendToClient = new SendChat();
        Thread read, write;
        read = new Thread(readClient);
        write = new Thread(sendToClient);
        read.start();
        write.start();

        Runnable sendPosToServer = new sendPosToServer();
        Runnable getServerPos = new getServerPosition();
        Thread sendPos = new Thread(sendPosToServer);
        Thread receivePos = new Thread(getServerPos);
        sendPos.start();
        receivePos.start();
    }

    public class guiStarter implements Runnable{
        GameFrame clientFrame;
        guiStarter(){
            clientFrame = new GameFrame();
        }
        @Override
        public void run() {
            clientFrame.setUpGUI(640,480);
            clientFrame.setUpTimer();
            currentRectangle = clientFrame.getRectangle();
            clientFrame.updateRectanglePosition(enemyRectangle);
        }
    }

    public class sendPosToServer implements Runnable{
        @Override
        public void run() {
            try {
                objectOut.writeObject(currentRectangle);
            } catch (IOException e) {
                System.out.println("Failure: Client failed to send object");
                e.printStackTrace();
            }
        }
    }

    public class getServerPosition implements Runnable{
        @Override
        public void run() {
            try {
                enemyRectangle = (PlayerRectangles) objectIn.readObject();
            } catch (IOException e) {
                System.out.println("Client failed to receive server position");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public class ReadChat implements Runnable {
        String readClientMessage;

        @Override
        public void run() {
            do {
                try {
                    readClientMessage = objectIn.readUTF();
                    System.out.println("Server:" + readClientMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!readClientMessage.contentEquals("quit"));
            System.out.println("Server has left the chat");
        }

    }

    public class SendChat implements Runnable {
        String serverMessage;
        Scanner scan = new Scanner(System.in);

        @Override
        public void run() {
            do {
                serverMessage = scan.nextLine();
                try {
                    objectOut.writeUTF(serverMessage);
                    objectOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }while(!serverMessage.contentEquals("quit"));
        }
    }

    public static void main(String[] args) throws IOException {
        GameStarter game = new GameStarter();
    }///write network code to send the rectangle object we receive from GameFrame
    //and receive that rectangle on the opposite end where it gets forwarded back to the player class
}
