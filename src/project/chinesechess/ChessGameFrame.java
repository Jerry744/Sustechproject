package project.chinesechess;

import project.chinesechess.chessboard.ChessboardComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGameFrame extends JFrame {
    final static int WIDTH=400;
    final static int HEIGHT=400;
    private ChessboardComponent chessboard = new ChessboardComponent(WIDTH, HEIGHT);
    public JLabel statusLabel;
    public ChessGameFrame() {
        setTitle("2019 CS102A Project Demo");
        setSize(WIDTH+11, HEIGHT+80);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("file");
        JMenu gameMenu = new JMenu("game");


        menubar.add(fileMenu);
        menubar.add(gameMenu);

        JMenuItem importChessBoard = new JMenuItem("Import chessboard");
        fileMenu.add(importChessBoard);


        JMenuItem newGame = new JMenuItem("New game");
        JMenuItem outGame = new JMenuItem("Out game");

        newGame.addActionListener(new NewGameHandler());
        outGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(chessboard);
            }
        });

        gameMenu.add(outGame);
        gameMenu.add(newGame);

        setJMenuBar(menubar);


        add(chessboard);

        statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(0, 400);
        statusLabel.setSize(200, 15);
        add(statusLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame();
            mainFrame.setVisible(true);
        });
    }

    private class NewGameHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            System.out.println("game start");
//            chessboard
//            add(chessboard);
        }
    }


}
