package FInalProj;

import java.io.*;
import java.net.Socket;

public class GameStarter extends Network{
    public static void main(String[] args) {
        int port;
        setPlayerType("client");
        if(defaultPortPrompt()){
            port = 54000;
        }
        else {
            port = setPort();
        }
        String host = setHost();
        startClient(host,port);
        initGUIThread();
    }
}
