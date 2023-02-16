package kenigsberg.wordledictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

class WordleGameTest
{

    @Test
    public void getCorrectWord() throws IOException
    {
        // given
        WordleGame game = new WordleGame();

        // then
        String correctWord = game.getCorrectWord();

        // when
        Assertions.assertEquals(5, correctWord.length());
    }

    @Test
    public void guessCorrect() throws IOException
    {
        // given
        WordleGame game = new WordleGame();

        // then
        String correctWord = game.getCorrectWord();
        CharResult[] results = game.guess(correctWord.toLowerCase());//checking if works for lowercase words
        CharResult[] expected = {CharResult.Correct, CharResult.Correct, CharResult.Correct, CharResult.Correct, CharResult.Correct};


        // when
        Assertions.assertArrayEquals(expected, results);
    }

    @Test
    public void guessIncorrectAmountLetters() throws IOException
    {
        // given
        WordleGame game = new WordleGame();

        // then
        CharResult[] results = game.guess("AA");

        // when
        Assertions.assertArrayEquals(null, results);
    }

    @Test
    public void guessIncorrect() throws IOException
    {
        // given
        WordleGame game = new WordleGame();

        // then
        CharResult[] results = game.guess("POOLS");
        CharResult[] incorrect = {CharResult.Correct, CharResult.Correct, CharResult.Correct, CharResult.Correct, CharResult.Correct};
        // when
        Assertions.assertFalse(Arrays.equals(results, incorrect));
    }

}