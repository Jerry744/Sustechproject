package project.chinesechess.chess;

import project.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class EmptySlotComponent extends ChessComponent {
    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location) {
        super(chessboardPoint, location, ChessColor.NONE);
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }
}
