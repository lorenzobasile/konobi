# Konobi 

[![Build Status](https://travis-ci.com/lorenzobasile/konobi.svg?branch=main)](https://travis-ci.com/lorenzobasile/konobi)

Final project for Software Development Methods exam.



## Rules

Official rulebook: https://boardgamegeek.com/boardgame/123213/konobi

Konobi is a board game for two players. It is played on a chess board, which is initially empty.

Starting with black, the players alternate in placing stones in empty cells on the board, with the aim of forming connections between equally colored stones.

In order to make the game fair, White has the option, on his first turn only, to switch colors with Black instead of making a regular move (Pie rule).

Two equally colored stones are strongly connected if they are orthogonally adjacent to each other, and weakly connected if they are diagonally adjacent to each other without sharing any strongly connected neighbor. A chain is a set of (strongly or weakly) connected stones.

It is illegal to make a weak connection to a certain stone unless it is impossible to make a placement which is both strongly connected to that stone and not weakly connected to another. It is also illegal to form a crosscut, i.e. a 2x2 pattern of stones consisting of two weakly connected Black stones and two weakly connected White stones.

If a player cannot make a move on his turn, he must pass. Passing is otherwise not allowed. There will always be a move available to at least one of the players.

The game is won by the player who completes a chain of stones of his color touching the two opposite board edges: White chains should connect left and right edges, while Black chains should connect top and bottom edges. Draws are not possible.



## Tools

* Java 15

* Gradle

* TravisCI

* Git & Github

  

## How to compile and run

### Unix-like systems 

**Command Line Version** 
Go to the root folder of the project and execute this command on the terminal:

```bash
./gradlew runKonobi --console plain
```

**Client Server Version**
To run (as server) the Client-Server version, a port number has to be specified using the following command:

  ```bash
./gradlew runKonobi -PortNumber=<PORT_NUMBER> --console plain
  ```

Then, two clients can connect to the server and play by executing the command:

```bash
telnet <SERVER_IP_ADDRESS> <PORT_NUMBER>
```


### Windows

**Command Line Version** 
Go to the root folder of the project and execute this command on the terminal:

```bash
gradlew.bat runKonobi --console plain
```

**Client Server Version**
To run (as server) the Client-Server version, a port number has to be specified using the following command:

  ```bash
gradlew.bat runKonobi -PortNumber=<PORT_NUMBER> --console plain
  ```

Then, two clients can connect to the server and play by executing the command:

```bash
telnet <SERVER_IP_ADDRESS> <PORT_NUMBER>
```



## Authors

* Lorenzo Basile
* Irene Brugnara
* Roberto Corti
* Arianna Tasciotti