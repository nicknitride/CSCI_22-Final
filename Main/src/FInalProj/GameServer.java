package FInalProj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Network{
    public static void main(String[] args){
        setPlayerType("server");//Player Type is specified to control if-else statements relating to the player class initiated within the GUI Thread
        startServer(52300);
        initGUIThread();
    }
}
