package project.chinesechess.chessboard;

import project.chinesechess.ChessGameFrame;
import project.chinesechess.chess.specificchess.*;
import project.chinesechess.listener.ChessboardChessListener;
import project.chinesechess.chess.*;
import project.chinesechess.listener.ChessListener;

import javax.swing.*;
import java.awt.*;

public class ChessboardComponent extends JComponent {
    private ChessListener chessListener = new ChessboardChessListener(this);
    private ChessComponent[][] chessboard = new ChessComponent[10][9];
    private ChessColor currentColor = ChessColor.BLACK;

    public ChessboardComponent(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);

        ChessComponent.registerListener(chessListener);
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
            }
        }

        // FIXME: Initialize chessboard for testing only.
        initChessBoard();
    }

    public ChessComponent[][] getChessboard() {
        return chessboard;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    private void initChessBoard(){
        putChessOnBoard(new ChariotChessComponent(new ChessboardPoint(0,0),calculatePoint(0,0),ChessColor.BLACK));
        putChessOnBoard(new ChariotChessComponent(new ChessboardPoint(0,8),calculatePoint(0,8),ChessColor.BLACK));
        putChessOnBoard(new ChariotChessComponent(new ChessboardPoint(9,0),calculatePoint(9,0),ChessColor.RED));
        putChessOnBoard(new ChariotChessComponent(new ChessboardPoint(9,8),calculatePoint(9,8),ChessColor.RED));

        putChessOnBoard(new MaChessComponent(new ChessboardPoint(0,1),calculatePoint(0,1),ChessColor.BLACK));
        putChessOnBoard(new MaChessComponent(new ChessboardPoint(0,7),calculatePoint(0,7),ChessColor.BLACK));
        putChessOnBoard(new MaChessComponent(new ChessboardPoint(9,1),calculatePoint(9,1),ChessColor.RED));
        putChessOnBoard(new MaChessComponent(new ChessboardPoint(9,7),calculatePoint(9,7),ChessColor.RED));

        putChessOnBoard(new XiangChessComponent(new ChessboardPoint(0,2),calculatePoint(0,2),ChessColor.BLACK));
        putChessOnBoard(new XiangChessComponent(new ChessboardPoint(0,6),calculatePoint(0,6),ChessColor.BLACK));
        putChessOnBoard(new XiangChessComponent(new ChessboardPoint(9,2),calculatePoint(9,2),ChessColor.RED));
        putChessOnBoard(new XiangChessComponent(new ChessboardPoint(9,6),calculatePoint(9,6),ChessColor.RED));

        putChessOnBoard(new ShiChessComponent(new ChessboardPoint(0,3),calculatePoint(0,3),ChessColor.BLACK));
        putChessOnBoard(new ShiChessComponent(new ChessboardPoint(0,5),calculatePoint(0,5),ChessColor.BLACK));
        putChessOnBoard(new ShiChessComponent(new ChessboardPoint(9,3),calculatePoint(9,3),ChessColor.RED));
        putChessOnBoard(new ShiChessComponent(new ChessboardPoint(9,5),calculatePoint(9,5),ChessColor.RED));

        putChessOnBoard(new BingChessComponent(new ChessboardPoint(3,0),calculatePoint(3,0),ChessColor.BLACK));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(3,2),calculatePoint(3,2),ChessColor.BLACK));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(3,4),calculatePoint(3,4),ChessColor.BLACK));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(3,6),calculatePoint(3,6),ChessColor.BLACK));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(3,8),calculatePoint(3,8),ChessColor.BLACK));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(6,0),calculatePoint(6,0),ChessColor.RED));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(6,2),calculatePoint(6,2),ChessColor.RED));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(6,4),calculatePoint(6,4),ChessColor.RED));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(6,6),calculatePoint(6,6),ChessColor.RED));
        putChessOnBoard(new BingChessComponent(new ChessboardPoint(6,8),calculatePoint(6,8),ChessColor.RED));

        putChessOnBoard(new PaoChessComponent(new ChessboardPoint(2,1),calculatePoint(2,1),ChessColor.BLACK));
        putChessOnBoard(new PaoChessComponent(new ChessboardPoint(2,7),calculatePoint(2,7),ChessColor.BLACK));
        putChessOnBoard(new PaoChessComponent(new ChessboardPoint(7,1),calculatePoint(7,1),ChessColor.RED));
        putChessOnBoard(new PaoChessComponent(new ChessboardPoint(7,7),calculatePoint(7,7),ChessColor.RED));

        putChessOnBoard(new JiangChessComponent(new ChessboardPoint(0,4),calculatePoint(0,4),ChessColor.BLACK));
        putChessOnBoard(new ShuaiChessComponent(new ChessboardPoint(9,4),calculatePoint(9,4),ChessColor.RED));
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();
        if (chessboard[row][col] != null) {
            remove(chessboard[row][col]);
        }
        add(chessboard[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation()));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessboard[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessboard[row2][col2] = chess2;
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBoardLine(g, 0, 0, 8, 0);
        paintBoardLine(g, 0, 8, 9, 8);
        paintHalfBoard(g, 0);
        paintHalfBoard(g, 5);
        paintKingSquare(g, 1, 4);
        paintKingSquare(g, 8, 4);
    }

    private void paintHalfBoard(Graphics g, int startRow) {
        for (int row = startRow; row < startRow + 5; row++) {
            paintBoardLine(g, row, 0, row, 8);
        }
        for (int col = 0; col < 9; col++) {
            paintBoardLine(g, startRow, col, startRow + 4, col);
        }
    }

    private void paintKingSquare(Graphics g, int centerRow, int centerCol) {
        paintBoardLine(g, centerRow - 1, centerCol - 1, centerRow + 1, centerCol + 1);
        paintBoardLine(g, centerRow - 1, centerCol + 1, centerRow + 1, centerCol - 1);
    }

    private void paintBoardLine(Graphics g, int rowFrom, int colFrom, int rowTo, int colTo) {
        int offsetX = ChessComponent.CHESS_SIZE.width / 2, offsetY = ChessComponent.CHESS_SIZE.height / 2;
        Point p1 = calculatePoint(rowFrom, colFrom), p2 = calculatePoint(rowTo, colTo);
        g.drawLine(p1.x + offsetX, p1.y + offsetY, p2.x + offsetX, p2.y + offsetY);
    }

    public Point calculatePoint(int row, int col) {
        return new Point(col * getWidth() / 9, row * getHeight() / 10);
    }
}
