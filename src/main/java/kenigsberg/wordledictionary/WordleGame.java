package kenigsberg.wordledictionary;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;


public class WordleGame {
    private final String correctWord;
    private final Random random = new Random();

    private final ArrayList<String> fiveLetterWords;

    public WordleGame(WordleDictionary wordleDictionary) {
        fiveLetterWords = new ArrayList<>();

        Set<String> allWords = wordleDictionary.getList();


        for (String allWord : allWords) {
            if (allWord.length() == 5) {
                fiveLetterWords.add(allWord);
            }
        }

        int indexOfCorrectWord = random.nextInt(fiveLetterWords.size());
        this.correctWord = fiveLetterWords.get(indexOfCorrectWord);
    }

    public String getCorrectWord() {
        return correctWord;
    }


    public CharResult[] guess(String guessString) {

        guessString = guessString.toUpperCase();

        CharResult[] results = new CharResult[guessString.length()];

        char letter;

        for (int i = 0; i < guessString.length(); i++) {
            letter = guessString.charAt(i);


            if (!this.correctWord.contains(Character.toString(letter))) {
                results[i] = CharResult.NotFound;
            } else if (this.correctWord.charAt(i) == letter) {
                results[i] = CharResult.Correct;
            } else {
                results[i] = CharResult.WrongPlace;
            }
        }
        return results;
    }

    public ArrayList<String> getFiveLetterWords() {
        return this.fiveLetterWords;
    }

}
