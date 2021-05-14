import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {
    int connectedClientCount;
    PlayerRectangles currentRectangle,enemyRectangle;
    ServerSocket serverSocket;
    Socket clientSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;
    public void connectionAttempt(){
        while(connectedClientCount<1) {
            try {
                serverSocket = new ServerSocket(52300);
                System.out.println("Server open and awaiting connection");
                clientSocket = serverSocket.accept();
                OutputStream outputStream = clientSocket.getOutputStream();
                InputStream inputStream = clientSocket.getInputStream();
                objectOut = new ObjectOutputStream(outputStream);
                objectIn = new ObjectInputStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            connectedClientCount+=1;
        }
        System.out.println("Server at capacity");
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initThreads(){
        Thread read, write,guiThread;
        guiStarter gui = new guiStarter();
        guiThread = new Thread(gui);


        Runnable readClient = new ReadChat();
        Runnable sendToClient = new SendChat();
        Runnable sendServerPos = new sendPosToClient();
        Runnable getClientPos = new getClientPosition();

        Thread sendPos = new Thread(sendServerPos);
        Thread receivePos = new Thread(getClientPos);
        read = new Thread(readClient);
        write = new Thread(sendToClient);

        sendPos.start();
        receivePos.start();
        guiThread.start();
        read.start();
        write.start();
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

    public class sendPosToClient implements Runnable{
        @Override
        public void run() {
            try {
                objectOut.writeObject(currentRectangle);
            } catch (IOException e) {
                System.out.println("Failure: Server failed to send Object");
                e.printStackTrace();
            }
        }
    }

    public class getClientPosition implements Runnable{
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
                    System.out.println("Client:" + readClientMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!readClientMessage.contentEquals("quit"));
            System.out.println("Client has left");
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
        GameServer server = new GameServer();
        server.connectionAttempt();
        server.initThreads();
    }
}
