package kenigsberg.wordledictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class WordleGame
{
    private final String correctWord;
    private final Random random = new Random();

    public WordleGame() throws IOException
    {
        WordleDictionary wordleDictionary = new WordleDictionary();
        ArrayList<String> fiveLetterWords = new ArrayList<>();

        ArrayList<String> allWords = wordleDictionary.getList();


        for (int i = 0; i < allWords.size(); i++)
        {
            if (allWords.get(i).length() == 5)
            {
                fiveLetterWords.add(allWords.get(i));
            }
        }

        int indexOfCorrectWord = random.nextInt(fiveLetterWords.size());
        this.correctWord = fiveLetterWords.get(indexOfCorrectWord);
    }

    public String getCorrectWord()
    {
        return correctWord;
    }


    public CharResult[] guess(String guessString)
    {
        /*
        Ask Professor in person if should keep this in
        if (guessString.length() != 5)
        {
            return "Incorrect amount of Letter";
        }
        */

        guessString = guessString.toUpperCase();

        CharResult[] results = new CharResult[guessString.length()];

        char letter;

        for (int i = 0; i < guessString.length(); i++)
        {
            letter = guessString.charAt(i);


            if (!this.correctWord.contains(Character.toString(letter)))
            {
                results[i] = CharResult.NotFound;
            }

            else if (this.correctWord.charAt(i) == letter)
            {
                results[i] = CharResult.Correct;
            }
            else
            {
                results[i] = CharResult.WrongPlace;
            }
        }
        return results;
    }

}
