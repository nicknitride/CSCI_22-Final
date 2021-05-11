import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost",52199);
        DataInputStream intoClient = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());
        String message = intoClient.readUTF();
        System.out.println("The server says: "+ message);
    }
}
