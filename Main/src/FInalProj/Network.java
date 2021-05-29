package FInalProj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Network {
    static int connectedClientCount;
    static String playerType;
    static ServerSocket serverSocket;
    static Socket openServerSocket;
    static ObjectInputStream objectIn;
    static ObjectOutputStream objectOut;
    static GameFrame instanceFrame;
    static Socket clientSocket;
    static Scanner scan = new Scanner(System.in);
    public static int setPort(){
        System.out.println("Enter a port");
        return scan.nextInt();
    }
    public static String  setHost(){
        System.out.println("Enter a host IP/Name");
        return scan.next();
    }

    public static void setPlayerType(String string){
        playerType = string;
    }
    public static void startServer(int port){
        while(connectedClientCount<1) {
            try {//TODO set fallback host IP and port number
                serverSocket = new ServerSocket(port);
                System.out.println("Server open and awaiting connection");
                openServerSocket = serverSocket.accept();
                OutputStream outputStream = openServerSocket.getOutputStream();
                InputStream inputStream = openServerSocket.getInputStream();
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

    public static void startClient(String host, int port){
        try {
            clientSocket = new Socket(host, port);
            OutputStream outputStream = clientSocket.getOutputStream();
            InputStream inputStream = clientSocket.getInputStream();
            objectOut = new ObjectOutputStream(outputStream);
            objectIn = new ObjectInputStream(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void initGUIThread(){
        guiStarter gui = new guiStarter();
        Thread guiThread = new Thread(gui);
        guiThread.start();
    }

    public static class guiStarter implements Runnable{
        guiStarter(){
            instanceFrame = new GameFrame();
        }
        @Override
        public void run() {
            instanceFrame.setUpGUI(640,480,objectOut,objectIn, playerType);
            instanceFrame.setUpTimer();
        }
    }
}
