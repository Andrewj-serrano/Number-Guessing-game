package newfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Final extends JFrame {

    // Declares the boolean, attempt, random && secret number variable
    private Random random = new Random();
    private int secretNumber;
    private int attempts;
    private boolean isNumberCorrect;

    // Declares the Jframe & buttons 
    private JTextField guessField;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JTextArea resultArea;

    // Starts the Jframe for the game
    public Final() {
        super("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout());

        // makes the buttons for each difficulty
        guessField = new JTextField();
        guessField.setPreferredSize(new Dimension(100, 30));
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        resultArea = new JTextArea();

        // add buttons to the jframe
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter your guess: "));
        inputPanel.add(guessField);
        inputPanel.add(easyButton);
        inputPanel.add(mediumButton);
        inputPanel.add(hardButton);

        // Creates the Jpanel that appears on screen
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // when pressed, this code wil activate easy mode and have the range of 10
        easyButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(10);
            }
        });
        // when pressed, this code will activate medium mode and have range of 100
        mediumButton.addActionListener(new ActionListener(){
            @Override
             public void actionPerformed(ActionEvent e) {
               startGame(100);
             }
        });
        // when pressed this code will activate hard mode and have range of 1000
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(1000);
            }
        });
        /** 
         * This code does 2 things:
         * 1. checks whether user guessed the number correctly or if they have passed the attempts limit
         * 2. helps clean the program when replayed so lines that are printed don't get printed over and over again
        */
        guessField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNumberCorrect || attempts >= 15) {
                    endGame();
                } else {
                   checkGuess();
             }
            }
        });
    }

    // This is code holds most of the coding for the game and start of game
    private void startGame(int range) {
        // adds values to the vairbles delcared earlier
        secretNumber = random.nextInt(range) + 1;
        attempts = 0;
        isNumberCorrect = false;

        resultArea.setText(""); // clear previous result

        // enables guessing field and turns of difficulty buttons
        guessField.setEnabled(true);
        easyButton.setEnabled(false);
        mediumButton.setEnabled(false);
        hardButton.setEnabled(false);

        guessField.setText(""); // clear previous result
    
    }
    // this code check the users guesses
    private void checkGuess() {

        int userGuess;
        // puts the user guess through the code to check if the guess is a number or not
        try {
            userGuess = Integer.parseInt(guessField.getText());
        }catch (NumberFormatException ex) {
            resultArea.append("Invalid input. Please enter a number. \n");
            return;
        }
        // Adds 1 to number of attempts to help keep track of how many times they have guessed
        attempts++;

        /**
         * This code odes 2 things:
         * 1:Checks if the user guess is the secret number
         * 2:If guees is wrong checks whether if the guess is too high or too low and prints the correct line
         */
        if (userGuess == secretNumber) {
            isNumberCorrect = true;
        } else if (userGuess > secretNumber) {
            resultArea.append("Number too high! Try guessings lower!\n");
        } else {
            resultArea.append("Number too low! Try guessing higher!\n");
        }

        // ends game if guees is too high or guess is correct
        if (isNumberCorrect || attempts >= 15) {
            endGame();
        }
    }
    //Method is used to end and restart the game
    private void endGame() {
        guessField.setEnabled(false);
        easyButton.setEnabled(true);
        mediumButton.setEnabled(true);
        hardButton.setEnabled(true);
        // Prints out a line depending whether the guess is right or if ran out of guesses
        if (isNumberCorrect) {
            resultArea.append("Congragulations! You've guessed the number in " + attempts + " attempts!\n");
        } else {
            resultArea.append("Game Over! You ran out ofattempts. The correct number was " + secretNumber + ".\n");
        }
    }
    //this is what starts the everything
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Final().setVisible(true);
            }
        });
    }
}