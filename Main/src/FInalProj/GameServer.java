package FInalProj;
public class GameServer extends Network{
    public static void main(String[] args){
        int port;
        setPlayerType("server");
        if(defaultPortPrompt()){
        port = 54000;
        }
        else{
        port = setPort();
        }
        startServer(port);
        initGUIThread();
    }
}
