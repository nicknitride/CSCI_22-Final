package FInalProj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Network{
    public static void main(String[] args){
        int port = setPort();
        setPlayerType("server");
        startServer(port);
        initGUIThread();
    }
}
