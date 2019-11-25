package project.chinesechess.chess.specificchess;

import project.chinesechess.chess.ChessColor;
import project.chinesechess.chess.ChessComponent;
import project.chinesechess.chess.EmptySlotComponent;
import project.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class MaChessComponent extends ChessComponent {
    public MaChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int[] xmove=new int[]{-2,-1,1,2,2,1,-1,-2};
        int[] ymove=new int[]{1,2,2,1,-1 ,-2,-2,-1};
        int[] xblock=new int[]{-1,0,0,1,1,0,0,-1};
        int[] yblock=new int[]{0,1,1,0,0,-1,-1,0};
        for (int i=0;i<8;i++){
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawString("马", 15, 25); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the chess if selected.
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
