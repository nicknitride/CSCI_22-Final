package FInalProj2.FInalProj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    int connectedClientCount;
    PlayerRectangles currentRectangle,enemyRectangle;
    ServerSocket serverSocket;
    Socket clientSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;
    GameFrame serverFrame;
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
                System.out.println("Server failed the connection attempt");
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


    public void initGUIThread(){
        guiStarter gui = new guiStarter();
        Thread guiThread = new Thread(gui);
        guiThread.start();

    }



    public class guiStarter implements Runnable{

        guiStarter(){
            serverFrame = new GameFrame();
        }
        @Override
        public void run() {
            serverFrame.setUpGUI(640,480);//sets up the GUI
            serverFrame.setUpTimer();//runs a timer
            serverFrame.setObjOut(objectOut);
            serverFrame.setObjIn(objectIn);
        }
    }



    public static void main(String[] args){
        GameServer server = new GameServer();
        server.connectionAttempt();
        server.initGUIThread();
    }
}
