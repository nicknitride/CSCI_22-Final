package TestServerIO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 51222);
        ObjectInputStream objIn;
        ObjectOutputStream objOut;
        objOut = new ObjectOutputStream(socket.getOutputStream());
        objIn = new ObjectInputStream(socket.getInputStream());
        while(true) {
            PlayerRectangles rectangle = (PlayerRectangles) objIn.readObject();
            System.out.println(rectangle.getX());
        }
    }
}
