package project.chinesechess;

import project.chinesechess.chessboard.ChessboardComponent;

import javax.swing.*;

public class ChessGameFrame extends JFrame {
    final static int WIDTH=400;
    final static int HEIGHT=400;
    public JLabel statusLabel;
    public ChessGameFrame() {
        setTitle("2019 CS102A Project Demo");
        setSize(WIDTH+10, HEIGHT+55);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        ChessboardComponent chessboard = new ChessboardComponent(WIDTH, HEIGHT);
        add(chessboard);

        statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(0, 400);
        statusLabel.setSize(200, 15);
        add(statusLabel);

        JButton button = new JButton("...");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(370, 400);
        button.setSize(20, 10);
        add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame();
            mainFrame.setVisible(true);
        });
    }
}
