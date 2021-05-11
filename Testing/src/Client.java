import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client() throws IOException {
        InitializeClient thisClient = new InitializeClient();
    }

    private class InitializeClient {
        Socket clientSocket = new Socket("localhost", 52199);
        DataInputStream intoClient = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());
        InitializeClient() throws IOException {
            Runnable read, write;
            read = new ReadChat();
            write = new SendChat();
            Thread r,w;
            r = new Thread(read);
            w = new Thread(write);
            r.start();
            w.start();
        }
        public class ReadChat implements Runnable {
            String readServerMessage;

            @Override
            public void run() {
                do {
                    try {
                        readServerMessage = intoClient.readUTF();
                        System.out.println("Server:" + readServerMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } while (!readServerMessage.contentEquals("quit"));
                System.out.println("Server has shut down");
            }

        }

        public class SendChat implements Runnable {
            String clientToServer;
            Scanner scan = new Scanner(System.in);

            @Override
            public void run() {
                do {
                    clientToServer = scan.nextLine();
                    try {
                        clientOut.writeUTF(clientToServer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } while (!clientToServer.contentEquals("quit"));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println("---CLIENT---");
        Client client = new Client();
    }
}
