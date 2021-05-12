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
        InitializeServer serverInit = new InitializeServer();
    }

    private class InitializeServer {
        ServerSocket serverSocket;
        Socket randomSocket;
        DataOutputStream serverOut;
        DataInputStream serverIn;

        public InitializeServer() throws IOException {
            int clientCount = 0;
            serverSocket = new ServerSocket(52199);
            while(clientCount<1) {
                randomSocket = serverSocket.accept();//opens the socket on the server to connections
                clientCount+=1;
            }
            serverSocket.close();//stops the server from accepting further connections
            serverOut = new DataOutputStream(randomSocket.getOutputStream());
            serverIn = new DataInputStream(randomSocket.getInputStream());
            System.out.println("Server: Connection established and refusing other connections");
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
