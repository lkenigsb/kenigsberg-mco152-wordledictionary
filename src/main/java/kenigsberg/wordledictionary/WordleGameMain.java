package kenigsberg.wordledictionary;

import java.io.IOException;

public class WordleGameMain {
    public static void main(String[] args) throws IOException {

        WordleDictionary dictionary = new WordleDictionary();
        WordleGameFrame frame = new WordleGameFrame(new WordleGame(dictionary), dictionary);
        frame.setVisible(true);
    }
}