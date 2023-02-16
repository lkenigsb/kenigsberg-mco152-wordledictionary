package kenigsberg.wordledictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    public void guessIncorrect() throws IOException
    {
        // given
        WordleGame game = new WordleGame();

        // then
        CharResult[] results = game.guess("HELLO");

        // when
        Assertions.assertEquals(5, results.length);
    }

}