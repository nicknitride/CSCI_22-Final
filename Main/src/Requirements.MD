#Project Requirements
- The project must contain:
1. A GUI
2. Inheritance
    - Define a class that will be extended by at least two other classes
    - Both the superclass and subclasses must be defined by you
3. Inner classes
4. Animation using Threads or Timers

##Networking Requirements
The game must accommodate at least two (2) human players who will play against, or in cooperation with,each other. The game must run on two (2) separate machines: one server-player 
and one client-player.

Chat system - Optional but not considered a networking requirement

##Required Classes
1. GameServer 
   * Manages the game server's functions
   * <b>Main method</b> of the server
        * Instantiates/starts the server
2. GameFrame 
   * JFrame of the player
3. GameCanvas 
   * <b>Extends</b> JComponent and overrides the paintComponent method
4. Player 
   * Manages the player's appearance and functionality
5. GameStarter 
    * <b>Main method</b> from the player's side