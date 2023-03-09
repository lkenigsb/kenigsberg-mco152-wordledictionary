package kenigsberg.wordledictionary;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.mockito.Mockito.*;

class WordleControllerTest {

    //Create global variables of the mocks
    WordleGame game = mock();
    WordleDictionary dictionary = mock();
    JLabel[][] labels = new JLabel[][]{
            {mock(), mock(), mock(), mock(), mock()},
            {mock(), mock(), mock(), mock(), mock()},
            {mock(), mock(), mock(), mock(), mock()},
            {mock(), mock(), mock(), mock(), mock()},
            {mock(), mock(), mock(), mock(), mock()},
            {mock(), mock(), mock(), mock(), mock()}
    };


    // given
    WordleController controller = new WordleController(
            game,
            dictionary,
            labels);

    @Test
    public void addLetter() {
        // when
        controller.addLetter("L");

        // then
        verify(labels[controller.guessNum][0]).setText("L");
    }

    @Test
    public void enterGuess() {
        // given
        controller.guessNum = 0;
        controller.guessLetter = 4;


        controller.theGuess = new StringBuilder("APPLE");


        doReturn("APPLE").when(game).getCorrectWord();
        doReturn("").when(dictionary).getDefinition("APPLE");
        doReturn(new CharResult[]{CharResult.Correct, CharResult.Correct, CharResult.Correct,
                CharResult.Correct, CharResult.Correct}).when(game).guess("APPLE");


        // when
        controller.enterGuess();

        // then
        verify(labels[0][0]).setBackground(Color.green);
        verify(labels[0][1]).setBackground(Color.green);
        verify(labels[0][2]).setBackground(Color.green);
        verify(labels[0][3]).setBackground(Color.green);
        verify(labels[0][4]).setBackground(Color.green);

    }

    @Test
    public void backspace() {
        // given
        controller.guessNum = 0;
        controller.guessLetter = 2;
        controller.theGuess = new StringBuilder("LO");

        // when
        controller.backspace();

        // then
        verify(labels[0][1]).setText("");
    }
}