# ap-lab10

## Overview
At the moment, the projects contains:
- **ServerApplication project - Lab10Server**:
  - The Game, Board and Player classes
  - A modified GameServer class that waits for 2 Clients before creating a separate ClientThread which takes a Game instance as a parameter; the Game instance is created using a Board instance and 2 Player instances which take, mainly, a socket and the other Player as a parameter
  - A modified ClientThread class that now creates 2 new threads for each Player of the Game instance, since Player implements Runnable
- **ClientApplication project - Lab10Client**:
  - Modified the while loop so now it only takes position commands to be sent to the server
  - Added a Board class similar to the server-side one in order to capture tha board status at each turn on the Client side (not used yet)

## Optional
The tasks are:
- Implement functionalities of the game, using the classes *Game*, *Board*, *Player*, etc.
- The clients will send to the server commands such as: *create game*, *join game*, *submit move*, etc.
- The server is responsible with the game management and mediating the players.
- Once a game is finished, an HTML or [SGF](https://en.wikipedia.org/wiki/Smart_Game_Format) representation of the game should be uploaded to a Web server. You may use [JCraft](http://www.jcraft.com/jsch/) for connecting to a server using SFTP and transferring a file (or a similar solution). 

## Compulsory
The tasks are:
- **Create the project ServerApplication**. This will contain (at least) the classes: *GameServer* and *ClientThread*.
  - Create the class *GameServer*. An instance of this class will create a *ServerSocket* running at a specified port. The server will receive requests (commands) from clients and it will execute them.
  - Create the class *ClientThread*. An instance of this class will be responsible with communicating with a client *Socket*. If the server receives the command *stop* it will stop and will return to the client the respons "Server stopped", otherwise it return: "Server received the request ... ".
- **Create the project ClientApplication**. This will contain (at least) the class: *GameClient*.
  - Create the class *GameClient*. An instance of this class will read commands from the keyboard and it will send them to the server. The client stops when it reads from the keyboard the string "exit". 
