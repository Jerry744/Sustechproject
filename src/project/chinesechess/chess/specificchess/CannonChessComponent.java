package project.chinesechess.chess.specificchess;

import project.chinesechess.chess.ChessColor;
import project.chinesechess.chess.ChessComponent;
import project.chinesechess.chess.EmptySlotComponent;
import project.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class CannonChessComponent extends ChessComponent {
    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent) {
            if (source.getX() == destination.getX()) {
                int row = source.getX();
                for (int col = Math.min(source.getY(), destination.getY()) + 1;
                     col < Math.max(source.getY(), destination.getY()); col++) {
                    if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (source.getY() == destination.getY()) {
                int col = source.getY();
                for (int row = Math.min(source.getX(), destination.getX()) + 1;
                     row < Math.max(source.getX(), destination.getX()); row++) {
                    if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else { // Not on the same row or the same column.
                return false;
            }
        } else {
            boolean haveonechess=false;
                if (source.getX() == destination.getX()) {
                    int row = source.getX();
                    for (int col = Math.min(source.getY(), destination.getY()) + 1;
                         col < Math.max(source.getY(), destination.getY()); col++) {
                        if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                            if (!haveonechess){
                                haveonechess=true;
                            }
                            else{
                                return false;
                            }
                        }
                    }
                } else if (source.getY() == destination.getY()) {
                    int col = source.getY();
                    for (int row = Math.min(source.getX(), destination.getX()) + 1;
                         row < Math.max(source.getX(), destination.getX()); row++) {
                        if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                            if (!haveonechess){
                                haveonechess=true;
                            }
                            else{
                                return false;
                            }
                        }
                    }
                } else { // Not on the same row or the same column.
                    return false;
                }
                return haveonechess;
        }
        return true;
    }

    @Override
    public String toString() {
        return getChessColor().equals(ChessColor.BLACK)?"N":"n";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawString("炮", 15, 25); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the chess if selected.
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
