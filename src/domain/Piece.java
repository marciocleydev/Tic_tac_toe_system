package domain;

import domain.domainEnums.Type;

public class Piece {
    private Type pieceType;


    public Piece(Type pieceType) {
        this.pieceType = pieceType;
    }

    public Type getPieceType() {
        return pieceType;
    }


    @Override
    public String toString(){
        return "" + pieceType;
    }
}
