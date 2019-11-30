package project.chinesechess.chess.specificchess;

import project.chinesechess.chess.ChessColor;
import project.chinesechess.chess.ChessComponent;
import project.chinesechess.chess.EmptySlotComponent;
import project.chinesechess.chessboard.ChessboardComponent;
import project.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class ElephantChessComponent extends ChessComponent {
    public ElephantChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        if (getChessColor().equals(ChessColor.BLACK)&&destination.getX()>=5) return false;
        if (getChessColor().equals(ChessColor.RED)&&destination.getX()<=4) return false;
        ChessboardPoint source = getChessboardPoint();
        int[] xmove=new int[]{-2,2,2,-2};
        int[] ymove=new int[]{2,2,-2,-2};
        int[] xblock=new int[]{-1,1,1,-1};
        int[] yblock=new int[]{1,1,-1,-1};
        for (int i=0;i<4;i++){
            int xlo=source.getX()+xmove[i];
            int ylo=source.getY()+ymove[i];
            if (!( xlo>=0&& xlo<=9&&ylo>=0&&ylo<=8)) continue;
            if (xlo==destination.getX() && ylo==destination.getY()
                    && chessboard[source.getX()+xblock[i]][source.getY()+yblock[i]] instanceof EmptySlotComponent){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getChessColor().equals(ChessColor.BLACK)?"E":"e";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawString("相", 15, 25); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the chess if selected.
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
