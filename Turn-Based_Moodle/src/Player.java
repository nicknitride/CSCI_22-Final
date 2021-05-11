import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Player extends JFrame{
    private int width,height;
    private Container container;
    private JTextArea message;
    private JButton b1,b2,b3,b4;
    private int playerID;
    private int otherPlayer;

    private ClientSideConnection clientConnection;
    public Player(int w, int h){
        width = w;
        height = h;
        container = this.getContentPane();
        message = new JTextArea();
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
    }

    public void setUpGUI(){
        this.setSize(width,height);
        this.setTitle("Player #:"+playerID);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new GridLayout(1,5));
        container.add(message);
        message.setText("Creating a simple turn-based game in Java");
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(false);
        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(b4);

        if (playerID == 1){
            message.setText("You are player #1. You go first.");
            otherPlayer = 2;
        } else {
            message.setText("You are player #2. Wait for player #1's move.");
        }

        this.setVisible(true);
    }

    public void connectToServer(){
        clientConnection = new ClientSideConnection();
    }

    //Client Connection
    private class ClientSideConnection {
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;

        public ClientSideConnection() {
            System.out.println("---Client---");
            try {
                socket = new Socket("localhost", 51734);//initiates connection to server
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                playerID = dataIn.readInt();
                System.out.println("Connected to server as Player #"+playerID+".");
            } catch (Exception e) {
                System.out.println("IO exception from client connection constructor");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Player p = new Player(500,100);
        p.connectToServer();
        p.setUpGUI();
    }
}
