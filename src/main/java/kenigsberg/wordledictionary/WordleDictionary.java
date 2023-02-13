package kenigsberg.wordledictionary;

import java.io.*;
import java.util.ArrayList;

public class WordleDictionary
{
    private final File dictionary;
    private final ArrayList<String> words;

    public WordleDictionary() throws IOException
    {
        this.dictionary = new File("src/main/java/kenigsberg/wordledictionary/dictionary.txt");

        words = new ArrayList<>();

        FileReader fileReader = new FileReader(this.dictionary);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        while (line != null)
        {

            String[] word = line.split(" ");
            //now words[0] = the word to define

            words.add(word[0]);
            line = bufferedReader.readLine();
        }
    }

    public File getDictionary()
    {
        return dictionary;
    }


    public String getDefinition(String word) throws IOException
    {
        StringBuilder definition = new StringBuilder();

        FileReader fileReader = new FileReader(this.dictionary);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        while (line != null)
        {

            String[] words = line.split(" ");

            if (words[0].equals(word))
            {
                definition.append(line.substring(word.length() + 1));
                break;
            }
            line = bufferedReader.readLine();
        }

        return definition.toString();
    }


    public ArrayList<String> getList() throws IOException
    {

        return words;
    }

}
