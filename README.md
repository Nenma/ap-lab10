# ap-lab10

## Overview
At the moment, the projects contains:
- **ServerApplication project - Lab10Server**:
  - The GameServer class that is an enum acting as a singleton for accessing an instance of the server socket
  - The GameThread class which creates a separate thread for each client in order to treat all of them concurrently. It contains an infinite loop in order to "handle" each client command, up until the *exit* or *stop* command
- **ClientApplication project - Lab10Client**:
  - The GameClient class which waits for client input and sends it through a socket to the server to be handlet in its own thread
  - Sending the *exit* or *stop* command will terminate the client app and close its socket

## Compulsory
The tasks are:
- **Create the project ServerApplication**. This will contain (at least) the classes: *GameServer* and *ClientThread*.
  - Create the class *GameServer*. An instance of this class will create a *ServerSocket* running at a specified port. The server will receive requests (commands) from clients and it will execute them.
  - Create the class *ClientThread*. An instance of this class will be responsible with communicating with a client *Socket*. If the server receives the command *stop* it will stop and will return to the client the respons "Server stopped", otherwise it return: "Server received the request ... ".
- **Create the project ClientApplication**. This will contain (at least) the class: *GameClient*.
  - Create the class *GameClient*. An instance of this class will read commands from the keyboard and it will send them to the server. The client stops when it reads from the keyboard the string "exit". 
