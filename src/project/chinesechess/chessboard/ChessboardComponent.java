package project.chinesechess.chessboard;

import project.chinesechess.chess.specificchess.*;
import project.chinesechess.listener.ChessboardChessListener;
import project.chinesechess.chess.*;
import project.chinesechess.listener.ChessListener;

import javax.swing.*;
import java.awt.*;

public class ChessboardComponent extends JComponent {
    private ChessListener chessListener = new ChessboardChessListener(this);
    private ChessComponent[][] chessboard = new ChessComponent[10][9];
    private ChessColor currentColor = ChessColor.RED;

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
        loadChessBoard("rCHEAGAEHC..........N.....N.S.S.S.S.S..................s.s.s.s.s.n.....n..........cheagaehc");
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

    @Override
    public String toString(){
        StringBuilder out=new StringBuilder();
        if (currentColor.equals(ChessColor.BLACK)) out.append("b");
        else out.append("r");
        for (int i=0;i<10;i++)
            for (int j=0;j<9;j++)
                out.append(chessboard[i][j]);
        return String.valueOf(out);
    }

    public void loadChessBoard(String chessBoard){
        if (chessBoard.charAt(0)=='b') currentColor=ChessColor.BLACK;
        else currentColor=ChessColor.RED;
        for (int i=1;i<=90;i++){
            putCharChess(chessBoard.charAt(i),(i-1)/9,(i-1)%9);
        }
    }

    private void putCharChess(char c,int row,int column){
        switch (c){
            case 'a':
                putChessOnBoard(new AdvisorChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.RED));
                return;
            case 'A':
                putChessOnBoard(new AdvisorChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.BLACK));
                return;
            case 'c':
                putChessOnBoard(new ChariotChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.RED));
                return;
            case 'C':
                putChessOnBoard(new ChariotChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.BLACK));
                return;
            case 'e':
                putChessOnBoard(new ElephantChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.RED));
                return;
            case 'E':
                putChessOnBoard(new ElephantChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.BLACK));
                return;
            case 'g':
                putChessOnBoard(new GeneralChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.RED));
                return;
            case 'G':
                putChessOnBoard(new GeneralChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.BLACK));
                return;
            case 'h':
                putChessOnBoard(new HorseChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.RED));
                return;
            case 'H':
                putChessOnBoard(new HorseChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.BLACK));
                return;
            case 's':
                putChessOnBoard(new SoldierChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.RED));
                return;
            case 'S':
                putChessOnBoard(new SoldierChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.BLACK));
                return;
            case 'n':
                putChessOnBoard(new CannonChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.RED));
                return;
            case 'N':
                putChessOnBoard(new CannonChessComponent(new ChessboardPoint(row,column),calculatePoint(row,column),ChessColor.BLACK));
                return;
            case '.':
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(row,column),calculatePoint(row,column)));
                return;
        }
    }
}
