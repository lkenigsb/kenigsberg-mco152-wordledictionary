package kenigsberg.wordledictionary;

import javax.swing.*;
import java.awt.*;

public class WordleController {
    private final WordleGame wordleGame;
    private final WordleDictionary wordleDictionary;

    private final JLabel[][] labels;
    private final JButton[] keyboard;
    private final JButton enter;
    private final JButton backspace;
    private int guessNum;
    private int guessLetter;
    private boolean complete;
    private StringBuilder theGuess;
    private String correct;


    public WordleController(WordleGame wordleGame, WordleDictionary wordleDictionary, JLabel[][] labels, JButton[] keyboard, JButton enter, JButton backspace) {
        this.wordleGame = wordleGame;
        this.wordleDictionary = wordleDictionary;
        this.labels = labels;
        this.keyboard = keyboard;
        this.enter = enter;
        this.backspace = backspace;
        this.theGuess = new StringBuilder();
        this.guessNum = 0;
        this.guessLetter = 0;
    }

    /**
     * Called when you type the letter in or press letter on keyboard AKA actionListener button
     *
     * @param letter
     */
    public void addLetter(String letter) {
        this.correct = wordleGame.getCorrectWord();
        System.out.println(correct);
        System.out.println("Reached addLetter method");
        this.theGuess.append(letter); //adds the letter to the guess

        //set the letter to the next guess label
        labels[guessNum][guessLetter].setText(letter);
        labels[guessNum][guessLetter].setHorizontalAlignment(SwingConstants.CENTER);
        labels[guessNum][guessLetter].setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        guessLetter++;
    }


    public void enterGuess() {
        complete = true;

        if (wordleDictionary.getDefinition(String.valueOf(theGuess)) == null) {
            JOptionPane.showMessageDialog(labels[guessNum][0], "Word Does Not Exist");
        } else {
            //word exists
            //Then guess
            CharResult[] guessResult = wordleGame.guess(String.valueOf(theGuess));

            //set theGuess variable to empty - so that it'll be empty for the next guess
            theGuess.delete(0, theGuess.length());

            //set the word labels based on the guessResult (color coded)
            for (int letterIndex = 0; letterIndex < labels[guessNum].length; letterIndex++) {
                if (guessResult[letterIndex] == CharResult.NotFound) {
                    labels[guessNum][letterIndex].setForeground(Color.gray);
                    complete = false;
                } else if (guessResult[letterIndex] == CharResult.Correct) {
                    labels[guessNum][letterIndex].setForeground(Color.green);
                } else if (guessResult[letterIndex] == CharResult.WrongPlace) {
                    labels[guessNum][letterIndex].setForeground(Color.orange);
                    complete = false;
                }
            }

            //Once guess was entered can update the guessNum and guessLetter
            guessNum++;
            guessLetter = 0;

            //if completed!
            if (complete) {
                JOptionPane.showMessageDialog(labels[guessNum][0], "CONGRATS!!! YOU GOT THE GUESS!!");
            }
            //if it was the last guess
            if (guessNum == 6) {
                JOptionPane.showMessageDialog(labels[--guessNum][4], "Game Over, try again next time");
            }
        }
    }

    public void backspace() {
        guessLetter--;
        labels[guessNum][guessLetter].setText("");
        theGuess.deleteCharAt(theGuess.length() - 1);
    }
}
