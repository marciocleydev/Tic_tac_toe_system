# Tic_tac_toe_system
Console-based Tic Tac Toe game in Java using OOP and layered architecture. Includes turn handling, win/draw detection, input validation, custom exceptions, formatted board display, and support for multiple matches. Cleanly organized into domain, application, and UI layers.

# Tic Tac Toe – Java Console Version

A simple Tic Tac Toe game built in Java using object-oriented principles and a layered architecture.

## Features
- Turn-based game between two players (X and O)
- Win and draw detection
- Input validation and error handling
- Console display with formatted board
- Clean code organized by domain, application, and UI layers

## Structure
- `domain` → Core entities like Board, Player, Piece, Position, Type
- `application` → GameMatch class controlling the game logic
- `ui` → Console interface and screen rendering (GameUI)
- `Program.java` → Main entry point

## How to Run
1. Compile: `javac application/Program.java`
2. Run: `java application.Program`
