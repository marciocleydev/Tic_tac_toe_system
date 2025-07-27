package domain;

import domain.domainExceptions.BoardException;

public class Board {
    private int row;
    private int column;
    private Piece[][] positions;

    public Board(int row, int column) {
        this.row = row;
        this.column = column;
        positions = new Piece[row][column];
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Piece[][] getPicies() {
        return positions;
    }
    public void placePiece(Piece piece, Position position){
        positions[position.getRow()][position.getColumn()] = piece;
    }

    public boolean possibleMove(Position position){
         if(position.getRow() >= 0 && position.getRow() < row && position.getColumn() >= 0 && position.getColumn() < column ){
            return   notPieceYet(position);
         }
         else {
             throw new BoardException(" This is not a valid position, please choose between 1 and 9 !");
         }
    }
    public boolean notPieceYet(Position position){
         if(positions[position.getRow()][position.getColumn()] == null){
            return true;
        }
         else {
             throw new BoardException("There is a piece on this position. \n choose another one!");
         }
    }
}
