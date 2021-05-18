package TestServerIO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{


    public static void main(String[] args) {
        PlayerRectangles rectangle = new PlayerRectangles(69,69,69,69);
        ServerSocket serverSocket;
        Socket socketProper;
        ObjectInputStream objIn;
        ObjectOutputStream objOut;

        try {
            serverSocket = new ServerSocket(51222);
            socketProper = serverSocket.accept();
            objOut = new ObjectOutputStream(socketProper.getOutputStream());
            objIn = new ObjectInputStream(socketProper.getInputStream());

            while(true){
                objOut.reset();
                objOut.writeObject(rectangle);
                rectangle.x+=1;
                objOut.flush();
            }
        } catch(Exception exception){
            System.out.println("failed to start server");
        }


    }
}
