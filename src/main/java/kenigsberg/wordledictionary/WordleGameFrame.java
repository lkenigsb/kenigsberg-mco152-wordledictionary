package kenigsberg.wordledictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class WordleGameFrame extends JFrame {

    //How to we instantiate a 2D array
    private JTextField[][] fields = new JTextField[6][5];

    private CharResult[] guessResult;

    private String theGuess;


    public WordleGameFrame(WordleGame wordleGame) {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        /*
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 5));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                JTextField field = new JTextField("MY LABEL " + i + ", " + j);
                centerPanel.add(field);
            }
        }
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        */

        JTextField guess = new JTextField("Enter your guess");
        mainPanel.add(guess, BorderLayout.NORTH);


        JButton button = new JButton("Guess");
        mainPanel.add(button, BorderLayout.SOUTH);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Guess Clicked. Calling guess method...");
                button.setText("Processing Guess...");

                theGuess = guess.getText();
                guessResult = wordleGame.guess(theGuess);

                JLabel result = new JLabel(Arrays.toString(guessResult));
                mainPanel.add(result, BorderLayout.CENTER);
                button.setText("Guess Again");
            }
        });


        setContentPane(mainPanel);
        setSize(400, 400);
        setTitle("Wordle Game");
        //What happens when we hit the exit button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
