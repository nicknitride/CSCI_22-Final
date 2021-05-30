package Final;

/**
 @author Nicholo Patrick C. Pardines (193821)
 @version May 23,2020
This class is a subclass of the Network class.
It contains the main method which calls on threads in the superclass which initialize the game.
It implements methods in the Network superclass specific to the GameServer.
 */

/*
	I have not discussed the Java language code in my program
	with anyone other than my instructor or the teaching assistants
	assigned to this course.

	I have not used Java language code obtained from another student,
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program
	was obtained from another source, such as a textbook or website,
	that has been clearly noted with a proper citation in the comments
	of my program.
*/
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
