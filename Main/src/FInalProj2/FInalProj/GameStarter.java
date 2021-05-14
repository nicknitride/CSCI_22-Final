package FInalProj2.FInalProj;

import java.io.*;
import java.net.Socket;

/*
    Attempt to integrate the chat code and position code into a singular thread
 */
public class GameStarter{
    PlayerRectangles currentRectangle, enemyRectangle;
    Socket clientSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;

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

    public void initGUIThread(){
        guiStarter gui = new guiStarter();
        Thread guiThread = new Thread(gui);
        guiThread.start();
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
            clientFrame.setObjOut(objectOut);
            clientFrame.setObjIn(objectIn);
        }
    }





    public static void main(String[] args) {
        GameStarter client = new GameStarter();
        client.connectionAttempt();
        client.initGUIThread();
    }/*TODO write code that passes the input and output streams all the way to the FInalProj.Player class
        And remove remnant code which passes along the current and enemy rectangles from both client
        and server
    */
}
