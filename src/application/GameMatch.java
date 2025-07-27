package application;

import domain.Board;
import domain.Piece;
import domain.Player;
import domain.Position;
import domain.domainEnums.Type;
import domain.domainExceptions.BoardException;
import ui.GameUI;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameMatch {
    private Board board;
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private boolean gameOver;
    private boolean draw;


    public GameMatch(Player player1, Player player2) {
        board = new Board(3, 3);
        players.add(player1);
        players.add(player2);
        currentPlayer = players.getFirst();
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public boolean getDraw() {
        return draw;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Position inputConverterToBoard(int input) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count++;
                if (count == input) {
                    return new Position(i, j);
                }
            }
        }
        return new Position(5, 5);
    }

    public void startGame(Scanner sc) {

        while (!isGameOver()) {
            try {
                GameUI.clearScreen();
                GameUI.printScreen(this);
                int r = GameUI.selectPositonToMove(sc);

                this.makeMove(this.inputConverterToBoard(r), this.selectPiece(this.getCurrentPlayer().getPlayertype()));
                boolean winner = this.checkWinner(this.getCurrentPlayer().getPlayertype());

                if (winner || this.isDraw()) {
                    GameUI.clearScreen();
                    GameUI.printScreen(this);
                } else {
                    this.switchPlayer();
                }

            } catch (InputMismatchException e) {
                System.out.println("input error, please enter a correct value in the range 1 to 9");
                sc.nextLine();
                sc.nextLine();
            }
            catch (BoardException e){
                System.out.println(e.getMessage());
                sc.nextLine();
                sc.nextLine();
            }
            finally {
                sc.close();
            }
        }
    }

    public void makeMove(Position position, Piece piece) {
        if (board.possibleMove(position)) {
            board.placePiece(piece, position);
        }
    }

    public void switchPlayer() {
        if (currentPlayer == players.get(0)) {
            currentPlayer = players.get(1);
        } else {
            currentPlayer = players.getFirst();
        }
    }

    public Piece selectPiece(Type type) {
        return new Piece(type);
    }

    public boolean checkWinner(Type type) {
        Piece[][] pieces = board.getPicies();
        //winnerInLine
        if (!isNull(pieces[0][0], pieces[0][1], pieces[0][2]) && pieces[0][0].getPieceType() == type && pieces[0][1].getPieceType() == type && pieces[0][2].getPieceType() == type) {
            gameOver = true;
        }
        if (!isNull(pieces[1][0], pieces[1][1], pieces[1][2]) && pieces[1][0].getPieceType() == type && pieces[1][1].getPieceType() == type && pieces[1][2].getPieceType() == type) {
            gameOver = true;
        }
        if (!isNull(pieces[2][0], pieces[2][1], pieces[2][2]) && pieces[2][0].getPieceType() == type && pieces[2][1].getPieceType() == type && pieces[2][2].getPieceType() == type) {
            gameOver = true;
        }
        //winnerInColumn
        if (!isNull(pieces[0][0], pieces[1][0], pieces[2][0]) && pieces[0][0].getPieceType() == type && pieces[1][0].getPieceType() == type && pieces[2][0].getPieceType() == type) {
            gameOver = true;
        }
        if (!isNull(pieces[0][1], pieces[1][1], pieces[2][1]) && pieces[0][1].getPieceType() == type && pieces[1][1].getPieceType() == type && pieces[2][1].getPieceType() == type) {
            gameOver = true;
        }
        if (!isNull(pieces[0][2], pieces[1][2], pieces[2][2]) && pieces[0][2].getPieceType() == type && pieces[1][2].getPieceType() == type && pieces[2][2].getPieceType() == type) {
            gameOver = true;
            ;
        }
        //winnerInDiagonal1
        if (!isNull(pieces[0][0], pieces[1][1], pieces[2][2]) && pieces[0][0].getPieceType() == type && pieces[1][1].getPieceType() == type && pieces[2][2].getPieceType() == type) {
            gameOver = true;
        }
        //winnerInDiagonal2
        if (!isNull(pieces[2][0], pieces[1][1], pieces[0][2]) && pieces[2][0].getPieceType() == type && pieces[1][1].getPieceType() == type && pieces[0][2].getPieceType() == type) {
            gameOver = true;
        }

        return gameOver;

    }

    private boolean isNull(Piece piece1, Piece piece2, Piece piece3) {
        return piece1 == null || piece2 == null || piece3 == null;
    }

    public boolean isDraw() {
        Piece[][] pieces = board.getPicies();
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == null) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            this.draw = true;
            this.gameOver = true;
        }
        return isDraw;
    }
}
