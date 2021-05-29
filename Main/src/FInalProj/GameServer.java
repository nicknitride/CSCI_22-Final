package FInalProj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Network{
    public static void main(String[] args){
        int port = setPort();
        setPlayerType("server");//Player Type is specified to control if-else statements relating to the player class initiated within the GUI Thread
        startServer(port);
        initGUIThread();
    }
}
