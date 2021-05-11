import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(52199);
        Socket randomSocket = serverSocket.accept();
        DataOutputStream serverOut = new DataOutputStream(randomSocket.getOutputStream());
        DataInputStream serverIn = new DataInputStream(randomSocket.getInputStream());
        System.out.println("Server: Connection established.");
        serverOut.writeUTF("The server says hi!");
    }
}
