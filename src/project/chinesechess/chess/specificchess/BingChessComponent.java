package project.chinesechess.chess.specificchess;

import project.chinesechess.chess.ChessColor;
import project.chinesechess.chess.ChessComponent;
import project.chinesechess.chess.EmptySlotComponent;
import project.chinesechess.chessboard.ChessboardComponent;
import project.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class BingChessComponent extends ChessComponent {
    public BingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        int desX = destination.getX();
        int desY = destination.getY();
        int inX = getChessboardPoint().getX();
        int inY = getChessboardPoint().getY();

        if (getChessColor().equals(ChessColor.BLACK)) {
            if (inX<= 4) {
                if (desX == inX + 1 && desY == inY) return true;
                return false;
            } else {
                if (desX == inX + 1 && desY == inY || desX == inX && desY == inY + 1 || desX == inX && desY == inY - 1)
                    return true;
                return false;
            }
        } else {
            if (inX >= 5) {
                if (desX == inX - 1 && desY == inY) return true;
                return false;
            } else {
                if (desX == inX - 1 && desY == inY || desX == inX && desY == inY + 1 || desX == inX && desY == inY - 1)
                    return true;
                return false;
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawString("兵", 15, 25); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the chess if selected.
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
