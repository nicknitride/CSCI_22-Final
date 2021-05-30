package Final; /**
 @author Nicholo Patrick C. Pardines (193821)
 @version May 23,2020
This is the superclass responsible for managing the Game's networking.
This class eliminates redundancy by consolidating similar functionality that would have been spread among the GameServer and GameStarter class.
This consolidation enables an overview of how the network code runs on both the client and the server which may aid in maintainability and readability.
 */

/*
	I have not discussed the Java language code in my program
	with anyone other than my instructor or the teaching assistants
	assigned to this course.

	I have not used Java language code obtained from another student,
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program
	was obtained from another source, such as a textbook or website,
	that has been clearly noted with a proper citation in the comments
	of my program.
*/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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

    public static boolean defaultPortPrompt(){
        System.out.println("Use the default port [Yes/No]");
        String decision = scan.next();
        if (decision.contains("y") || decision.contains("Y")){
            return true;
        }
        else {return false;}
    }

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
            try {
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
