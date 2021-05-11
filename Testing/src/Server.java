import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public Server() {
    }
    public void finalizedServer() throws IOException{
        InitializeServer serverInitialization = new InitializeServer();
    }

    private class InitializeServer {
        ServerSocket serverSocket;
        Socket randomSocket;
        DataOutputStream serverOut;
        DataInputStream serverIn;

        public InitializeServer() throws IOException {
            serverSocket = new ServerSocket(52199);
            randomSocket = serverSocket.accept();
            serverOut = new DataOutputStream(randomSocket.getOutputStream());
            serverIn = new DataInputStream(randomSocket.getInputStream());
            System.out.println("Server: Connection established.");
            serverOut.writeUTF("The server says hi!");

            Runnable readClient = new ReadChat();
            Runnable sendToClient = new SendChat();
            Thread read, write;
            read = new Thread(readClient);
            write = new Thread(sendToClient);
            read.start();
            write.start();
        }

        public class ReadChat implements Runnable {
            String readClientMessage;

            @Override
            public void run() {
                do {
                    try {
                        readClientMessage = serverIn.readUTF();
                        System.out.println("Client:" + readClientMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } while (!readClientMessage.contentEquals("quit"));
                System.out.println("Client has left");
            }

        }

        public class SendChat implements Runnable {
            String serverMessage;
            Scanner scan = new Scanner(System.in);

            @Override
            public void run() {
                do {
                    serverMessage = scan.nextLine();
                    try {
                        serverOut.writeUTF(serverMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }while(!serverMessage.contentEquals("quit"));
            }
        }
    }



    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.finalizedServer();
    }
}
