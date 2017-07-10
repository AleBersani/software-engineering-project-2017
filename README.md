# LolloTheMagnificent
Software Engineering Application 2017 - Campagna, Cassetta, Dottino

To start the application, first is necessary to start the server in the
/src/main/java/it/polimi/ingsw/server/ServerMain.java
class, the to start playing you should start the client side of the application
at the src/main/java/it/polimi/ingsw/client/ClientStarterMain.java class.

Every phase of the game is logged, it's possible to manage more games at the same time,
the connection works both with RMI or Socket:
both server and client use middleware classes thanks to that the communication between client 
and server and with the controller is easier.

The main pattern we used are:
- Visitor,
- Observer,
- Mediator,
- Command.

The Model is completely tested but because of time lack we coudn't test the controller.

We worked on part of the added functionality, so it's possible for the player to register to a DataBase,
actually all the players as they log are saved in the DB.

The game is entirely configurable by json file and thanks to how we structured the model, is possible to modify any game element
without problems with the game logic.
