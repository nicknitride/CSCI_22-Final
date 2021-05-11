import java.io.*;
import java.net.*;
public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;

    public GameServer(){
        System.out.println("---Game Server---");
        numPlayers = 0;
        try{
            ss = new ServerSocket(51734);//sets the port
        } catch (IOException ex){
            System.out.println("IOException from GameServer Constructor");
        }
    }
    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");
            while(numPlayers<2){
                Socket s = ss.accept();//tells the server to begin accepting connections
                numPlayers++;
                System.out.println("Player #"+numPlayers+" has connected.");
                ServerSideConnection ssc = new ServerSideConnection(s,numPlayers);
                if(numPlayers==1){
                    player1 = ssc;
                } else {
                    player2 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();
            }
            System.out.println("Server full");
        } catch (IOException e) {
            System.out.println("IO exception from acceptConnections()");
            e.printStackTrace();
        }
    }

    private class ServerSideConnection implements Runnable{
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;

        public ServerSideConnection(Socket s, int id){
            socket = s;
            playerID = id;
            try{
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            } catch (Exception e) {
                System.out.println("IO Exception fom server side connection constructor");
                e.printStackTrace();
            }
        }
        public void run(){
            try{
                dataOut.writeInt(playerID);
                dataOut.flush();

                while(true){

                }
            } catch (IOException e) {
                System.out.println("IO Exception from run() SSC");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
