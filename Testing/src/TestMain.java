import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TestMain implements Runnable {
    public static void main(String[] args) {

        Client client = new Client();


    }

    @Override
    public void run() {
        Server serve = new Server();
    }
}
