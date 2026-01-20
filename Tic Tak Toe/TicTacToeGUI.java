import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    JLabel titleLabel, playerLabel;
    JPanel boardPanel, topPanel;

    boolean playerX = true;

    String player1 = "Shivam Kumar Singh";
    String player2 = "Priyanshu Kumar Singh";

    int[][] winPatterns = {
        {0,1,2},{3,4,5},{6,7,8},
        {0,3,6},{1,4,7},{2,5,8},
        {0,4,8},{2,4,6}
    };

    TicTacToeGUI() {
        setTitle("Tic Tac Toe - Java Software");
        setSize(500,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Top Panel (Title & Names)
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2,1));
        topPanel.setBackground(new Color(30, 136, 229));

        titleLabel = new JLabel(
                "Shivam Kumar Singh (24SCSE2030504)  |  Priyanshu Kumar Singh (24SCSE2030505)",
                JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        playerLabel = new JLabel("Player 1 (X): Shivam Kumar Singh", JLabel.CENTER);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        topPanel.add(titleLabel);
        topPanel.add(playerLabel);

        // 🔹 Board Panel
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.BLACK);

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 48));
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(this);
            boardPanel.add(buttons[i]);
        }

        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        if(btn.getText().equals("")) {
            btn.setText(playerX ? "X" : "O");
            btn.setForeground(playerX ? Color.BLUE : Color.RED);

            if(checkWinner()) return;

            playerX = !playerX;
            playerLabel.setText(playerX ?
                    "Player 1 (X): Shivam Kumar Singh" :
                    "Player 2 (O): Priyanshu Kumar Singh");
        }
    }

    boolean checkWinner() {
        for(int[] p : winPatterns) {
            String b1 = buttons[p[0]].getText();
            String b2 = buttons[p[1]].getText();
            String b3 = buttons[p[2]].getText();

            if(!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                String winner = b1.equals("X") ? player1 : player2;
                JOptionPane.showMessageDialog(this,
                        "😁 Winner: " + winner,
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
                resetGame();
                return true;
            }
        }

        boolean draw = true;
        for(JButton b : buttons) {
            if(b.getText().equals("")) {
                draw = false;
                break;
            }
        }

        if(draw) {
            JOptionPane.showMessageDialog(this,
                    "😐 Game Draw!",
                    "Game Over",
                    JOptionPane.WARNING_MESSAGE);
            resetGame();
            return true;
        }
        return false;
    }

    void resetGame() {
        for(JButton b : buttons) {
            b.setText("");
        }
        playerX = true;
        playerLabel.setText("Player 1 (X): Shivam Kumar Singh");
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
