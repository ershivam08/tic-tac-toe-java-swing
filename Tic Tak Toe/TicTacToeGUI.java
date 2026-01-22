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













// Student Registration System

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Student {
    String name;
    String rollNo;
    int marks;

    Student(String rollNo, String name, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }
}

public class StudentRecordSearch extends JFrame implements ActionListener {

    JTextField searchField;
    JButton searchButton, showAllButton, clearButton;
    JTextArea resultArea;
    JComboBox<String> searchTypeCombo;
    JPanel statsPanel;
    JLabel totalLabel, foundLabel, complexityLabel, comparisonsLabel;
    
    // Student database
    Student[] students;
    HashMap<String, Student> studentMap;
    
    // For tracking statistics
    private int comparisons;
    private String timeComplexity;

    StudentRecordSearch() {
        setTitle("Student Record Search System");
        setSize(750, 600); // Increased height for new labels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        //  HEADER PANEL 
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 102, 204));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("Student Record Search System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        //SEARCH PANEL 
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Search Type
        gbc.gridx = 0; gbc.gridy = 0;
        searchPanel.add(new JLabel("Search By:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        String[] searchTypes = {"Roll Number", "Name", "Both"};
        searchTypeCombo = new JComboBox<>(searchTypes);
        searchPanel.add(searchTypeCombo, gbc);
        
        // Search Field
        gbc.gridx = 0; gbc.gridy = 1;
        searchPanel.add(new JLabel("Search Key:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        searchField = new JTextField(25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(searchField, gbc);
                                                                                                        
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Search Button (GREEN)
        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 153, 76)); // Green
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        searchButton.addActionListener(this);
        buttonPanel.add(searchButton);
        
        // Show All Button (BLUE)
        showAllButton = new JButton("Show All");
        showAllButton.setBackground(new Color(0, 102, 204)); // Blue
        showAllButton.setForeground(Color.WHITE);
        showAllButton.setFont(new Font("Arial", Font.BOLD, 14));
        showAllButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        showAllButton.addActionListener(this);
        buttonPanel.add(showAllButton);
        
        // Clear Button (RED)
        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(204, 0, 0)); // Red
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        searchPanel.add(buttonPanel, gbc);

        //Result
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBackground(Color.WHITE);
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Search Results"),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        resultsPanel.add(scroll, BorderLayout.CENTER);
        
        statsPanel = new JPanel(new GridLayout(2, 2, 20, 10));
        statsPanel.setBackground(new Color(240, 248, 255));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        totalLabel = new JLabel("Total Students: 0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 13));
        totalLabel.setForeground(Color.BLUE);
        
        foundLabel = new JLabel("Found: 0");
        foundLabel.setFont(new Font("Arial", Font.BOLD, 13));
        foundLabel.setForeground(new Color(0, 153, 76));
        
        complexityLabel = new JLabel("Time Complexity: O(1)");
        complexityLabel.setFont(new Font("Arial", Font.BOLD, 13));
        complexityLabel.setForeground(new Color(153, 0, 153));
        
        comparisonsLabel = new JLabel("Comparisons: 0");
        comparisonsLabel.setFont(new Font("Arial", Font.BOLD, 13));
        comparisonsLabel.setForeground(new Color(204, 102, 0));
        
        statsPanel.add(totalLabel);
        statsPanel.add(foundLabel);
        statsPanel.add(complexityLabel);
        statsPanel.add(comparisonsLabel);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(resultsPanel, BorderLayout.CENTER);
        centerPanel.add(statsPanel, BorderLayout.SOUTH);
        
        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // Database
        initializeStudentDatabase();
        
        // Update statistics
        updateStatistics(0, 0, "O(1)");
        
        // Center the window on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initializeStudentDatabase() {
        students = new Student[] {
            new Student("24SCSE2030501","Amit Kumar",85),
            new Student("24SCSE2030502","Rahul Singh",78),
            new Student("24SCSE2030503","Priya Sharma",92),
            new Student("24SCSE2030504","Shivam Kumar Singh",88),
            new Student("24SCSE2030505","Priyanshu Kumar Singh",81),
            new Student("24SCSE2030506","Neha Verma",76),
            new Student("24SCSE2030507","Rohan Patel",89),
            new Student("24SCSE2030508","Sneha Gupta",94),
            new Student("24SCSE2030509","Vikram Singh",72),
            new Student("24SCSE2030510","Anjali Mishra",87)
        };

        // Sort array by roll number
        Arrays.sort(students, Comparator.comparing(s -> s.rollNo));

        // HashMap for search - O(1) time complexity
        studentMap = new HashMap<>();
        for(Student s : students) {
            studentMap.put(s.rollNo.toLowerCase(), s);
            studentMap.put(s.name.toLowerCase(), s);
        }
    }
    
    private void updateStatistics(int foundCount, int comparisons, String complexity) {
        totalLabel.setText("Total Students: " + students.length);
        foundLabel.setText("Found: " + foundCount);
        comparisonsLabel.setText("Comparisons: " + comparisons);
        complexityLabel.setText("Time Complexity: " + complexity);
    }
    
    private void displayStudent(Student student) {
        resultArea.setText("");
        resultArea.append("========================================\n");
        resultArea.append("        STUDENT RECORD DETAILS\n");
        resultArea.append("========================================\n\n");
        resultArea.append("Roll No:    " + student.rollNo + "\n");
        resultArea.append("Name:       " + student.name + "\n");
        resultArea.append("Marks:      " + student.marks + "\n\n");
        
        // Add grade information
        String grade = getGrade(student.marks);
        resultArea.append("Grade:      " + grade + "\n");
        resultArea.append("Status:     " + getStatus(student.marks) + "\n");
        
        // Add search statistics
        resultArea.append("\nSearch Statistics:\n");
        resultArea.append("Comparisons: " + comparisons + "\n");
        resultArea.append("Time Complexity: " + timeComplexity + "\n");
        resultArea.append("========================================");
        
        // Change background color based on marks
        if (student.marks >= 85) {
            resultArea.setBackground(new Color(200, 255, 200)); // Light green
        } else if (student.marks >= 70) {
            resultArea.setBackground(new Color(255, 255, 200)); // Light yellow
        } else {
            resultArea.setBackground(new Color(255, 200, 200)); // Light red
        }
    }
    
    private String getGrade(int marks) {
        if (marks >= 90) return "A+ (Excellent)";
        if (marks >= 80) return "A (Very Good)";
        if (marks >= 70) return "B+ (Good)";
        if (marks >= 60) return "B (Average)";
        if (marks >= 50) return "C (Pass)";
        return "F (Fail)";
    }
    
    private String getStatus(int marks) {
        return marks >= 50 ? "PASS" : "FAIL";
    }
    
    private void showAllStudents() {
        resultArea.setText("");
        resultArea.setBackground(Color.WHITE);
        
        resultArea.append("========================================================\n");
        resultArea.append("              ALL STUDENT RECORDS\n");
        resultArea.append("========================================================\n\n");
        resultArea.append("No.  Roll No           Name                          Marks\n");
        resultArea.append("---  ---------------  --------------------------  ------\n");
        
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            resultArea.append(String.format("%2d   %-15s %-25s %6d\n", 
                i+1, s.rollNo, s.name, s.marks));
        }
        
        resultArea.append("\n========================================================");
        
        updateStatistics(students.length, students.length, "O(n)");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            performSearch();
        } else if (e.getSource() == showAllButton) {
            showAllStudents();
        } else if (e.getSource() == clearButton) {
            searchField.setText("");
            resultArea.setText("");
            resultArea.setBackground(Color.WHITE);
            updateStatistics(0, 0, "O(1)");
        }
    }
    
    private void performSearch() {
        String key = searchField.getText().trim().toLowerCase();
        String searchType = (String) searchTypeCombo.getSelectedItem();
        
        if(key.equals("")) {
            JOptionPane.showMessageDialog(this, 
                "Please enter search keyword!", 
                "Search Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Student found = null;
        comparisons = 0;
        timeComplexity = "O(1)"; // Default for hashmap search
        
        // Determine search method based on selection
        if (searchType.equals("Roll Number")) {
            // Search by roll number only - O(1)
            comparisons = 1;
            found = studentMap.get(key);
        } else if (searchType.equals("Name")) {
            // O(1)
            comparisons = 1;
            found = studentMap.get(key);
        } else {
            // Search both - try roll number first, then name
            comparisons = 1;
            found = studentMap.get(key);
            
            // If not found, try partial name match - O(n)
            if (found == null) {
                timeComplexity = "O(n)";
                for (Student s : students) {
                    comparisons++;
                    if (s.name.toLowerCase().contains(key)) {
                        found = s;
                        break;
                    }
                }
            }
        }

        if(found != null) {
            displayStudent(found);
            updateStatistics(1, comparisons, timeComplexity);
        } else {
            resultArea.setText("\n\n");
            resultArea.append("          ==================================\n");
            resultArea.append("                 SEARCH RESULT\n");
            resultArea.append("          ==================================\n\n");
            resultArea.append("     No student found with: \"" + searchField.getText() + "\"\n\n");
            resultArea.append("     Search Type: " + searchType + "\n");
            resultArea.append("     Comparisons: " + comparisons + "\n");
            resultArea.append("     Time Complexity: " + timeComplexity + "\n\n");
            resultArea.append("          ==================================");
            
            resultArea.setBackground(new Color(255, 200, 200));
            updateStatistics(0, comparisons, timeComplexity);
        }
    }

    public static void main(String[] args) {
        // Simple execution without complex look and feel
        new StudentRecordSearch();
    }
}

