package project.chinesechess.chess.specificchess;

import project.chinesechess.chess.ChessColor;
import project.chinesechess.chess.ChessComponent;
import project.chinesechess.chess.EmptySlotComponent;
import project.chinesechess.chessboard.ChessboardComponent;
import project.chinesechess.chessboard.ChessboardPoint;

import java.awt.*;

public class AdvisorChessComponent extends ChessComponent {
    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    final private static int[] xmove=new int[]{-1,1,1,-1};
    final private static int[] ymove=new int[]{1,1,-1,-1};

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        int desx=destination.getX();
        int desy=destination.getY();
        if (getChessColor().equals(ChessColor.BLACK)&&(desx>=3||desy<=2||desy>=6)) return false;
        if (getChessColor().equals(ChessColor.RED)&&(desx<=6||desy<=2||desy>=6)) return false;
        ChessboardPoint source = getChessboardPoint();
        for (int i=0;i<4;i++){
            int xlo=source.getX()+xmove[i];
            int ylo=source.getY()+ymove[i];
            if (!( xlo>=0&& xlo<=9&&ylo>=0&&ylo<=8)) continue;
            if (xlo==destination.getX() && ylo==destination.getY()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getChessColor().equals(ChessColor.BLACK)?"A":"a";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawString("士", 15, 25); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the chess if selected.
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
