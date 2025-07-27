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

    private boolean isWinningLine(Piece a, Piece b, Piece c, Type type) {
        return !isNull(a, b, c) &&
                a.getPieceType() == type &&
                b.getPieceType() == type &&
                c.getPieceType() == type;
    }

    public boolean checkWinner(Type type) {
        Piece[][] p = board.getPicies();

        for (int i = 0; i < 3; i++) {
            if (isWinningLine(p[i][0], p[i][1], p[i][2], type) || // row
                    isWinningLine(p[0][i], p[1][i], p[2][i], type)) { // column
                return gameOver = true;
            }
        }

        if (isWinningLine(p[0][0], p[1][1], p[2][2], type) || // main diagonal
                isWinningLine(p[0][2], p[1][1], p[2][0], type)) { // secondary diagonal
            return gameOver = true;
        }

        return false;
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
