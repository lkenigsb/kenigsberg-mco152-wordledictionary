package kenigsberg.wordledictionary;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

public class WordleGameMain {
    public static void main(String[] args) {
        WordleDictionary wordleDictionary = Mockito.mock(WordleDictionary.class);
        ArrayList<String> words = new ArrayList<>(List.of("APPLE"));
        doReturn(words).when(wordleDictionary).getList();

        WordleGameFrame frame = new WordleGameFrame(new WordleGame(wordleDictionary));
        frame.setVisible(true);
    }
}