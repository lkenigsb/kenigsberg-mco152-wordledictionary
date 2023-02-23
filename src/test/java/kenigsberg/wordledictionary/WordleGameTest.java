package kenigsberg.wordledictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static kenigsberg.wordledictionary.CharResult.*;
import static org.mockito.Mockito.doReturn;

class WordleGameTest {


    @Test
    public void guessCorrect() {
        // given
        WordleDictionary wordleDictionary = Mockito.mock(WordleDictionary.class);
        ArrayList<String> words = new ArrayList<>(List.of("APPLE"));
        doReturn(words).when(wordleDictionary).getList();
        WordleGame game = new WordleGame(wordleDictionary);

        // then
        //checking if works for lowercase words
        CharResult[] results = game.guess("APPLE");
        CharResult[] expected = {Correct, Correct, Correct, Correct, Correct};


        // when
        Assertions.assertArrayEquals(expected, results);
    }

    @Test
    public void guessIncorrect() {
        // given
        WordleDictionary wordleDictionary = Mockito.mock(WordleDictionary.class);
        ArrayList<String> words = new ArrayList<>(List.of("APPLE"));
        doReturn(words).when(wordleDictionary).getList();
        WordleGame game = new WordleGame(wordleDictionary);

        // then
        CharResult[] results = game.guess("HOYAH");
        CharResult[] incorrect = {NotFound, NotFound, NotFound, WrongPlace, NotFound};

        // when
        Assertions.assertArrayEquals(incorrect, results);
    }

}