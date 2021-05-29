package FInalProj;

import java.io.*;
import java.net.Socket;

/*
    Attempt to integrate the chat code and position code into a singular thread
 */
public class GameStarter extends Network{
    public static void main(String[] args) {
        setPlayerType("client");
        int port = setPort();
        String host = setHost();
        startClient(host,port);
        initGUIThread();
    }
}
