package kenigsberg.wordledictionary;

import javax.swing.*;
import java.awt.*;

public class WordleController {
    private final WordleGame wordleGame;
    private final WordleDictionary wordleDictionary;

    private final JLabel[][] labels;
    int guessNum;
    int guessLetter;
    StringBuilder theGuess;


    public WordleController(WordleGame wordleGame, WordleDictionary wordleDictionary,
                            JLabel[][] labels) {
        this.wordleGame = wordleGame;
        this.wordleDictionary = wordleDictionary;
        this.labels = labels;
        this.theGuess = new StringBuilder();
        this.guessNum = 0;
        this.guessLetter = 0;
    }


    /**
     * Called when you type the letter in or press letter on keyboard AKA actionListener button
     *
     * @param letter - the letter to be added to the fields grid
     */
    public void addLetter(String letter) {
        //Below if statement ensures that only use 5 letters typed used as a guess
        // (more than 5 letters before hitting enter will not be included.
        if (theGuess.length() < 5) {
            this.theGuess.append(letter); //adds the letter to the guess

            //This if statement accounts for bug if backspace
            // more than the amount of letters that exists,
            // will give index out of bounds error for adding a letter
            if (labels[guessNum][0].getText() == null) {
                guessLetter = 0;
                labels[guessNum][0].setText(letter);
            } else {
                //set the letter to the next guess label
                labels[guessNum][guessLetter].setText(letter);

            }
            guessLetter++;
        }

    }


    public void enterGuess() {
        System.out.println(wordleGame.getCorrectWord());
        boolean complete = true;

        if (wordleDictionary.getDefinition(String.valueOf(theGuess)) == null) {
            JOptionPane.showMessageDialog(labels[guessNum][0], "Word Does Not Exist");
        } else {
            //word exists
            //Then guess
            CharResult[] guessResult = wordleGame.guess(theGuess.toString());

            //set theGuess variable to empty - so that it'll be empty for the next guess
            theGuess.delete(0, theGuess.length());

            //set the word labels based on the guessResult (color coded)
            for (int letterIndex = 0; letterIndex < labels[guessNum].length; letterIndex++) {
                if (guessResult[letterIndex] == CharResult.NotFound) {
                    labels[guessNum][letterIndex].setForeground(Color.white);
                    labels[guessNum][letterIndex].setBackground(Color.gray);
                    labels[guessNum][letterIndex].setOpaque(true);
                    complete = false;
                } else if (guessResult[letterIndex] == CharResult.Correct) {
                    labels[guessNum][letterIndex].setForeground(Color.white);
                    labels[guessNum][letterIndex].setBackground(Color.green);
                    labels[guessNum][letterIndex].setOpaque(true);
                } else if (guessResult[letterIndex] == CharResult.WrongPlace) {
                    labels[guessNum][letterIndex].setForeground(Color.white);
                    labels[guessNum][letterIndex].setBackground(Color.orange);
                    labels[guessNum][letterIndex].setOpaque(true);
                    complete = false;
                }
            }

            //if completed!
            if (complete) {
                JOptionPane.showMessageDialog(null,
                        "CONGRATS!!! YOU GOT THE GUESS!!");
            }

            //if it was the last guess
            if (guessNum == 5 && !complete) {
                JOptionPane.showMessageDialog(null,
                        "Game Over, try again next time");
            }


            //Once guess was entered can update the guessNum and guessLetter
            guessNum++;
            guessLetter = 0;


        }
    }

    public void backspace() {
        guessLetter--;
        labels[guessNum][guessLetter].setText("");
        theGuess.deleteCharAt(theGuess.length() - 1);
    }
}
