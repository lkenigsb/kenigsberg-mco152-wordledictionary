package kenigsberg.wordledictionary;

import java.io.*;
import java.util.ArrayList;

public class WordleDictionary
{
    private final File dictionary;
    private final ArrayList<String> words;
    private final ArrayList<String> definitions;
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    public WordleDictionary() throws IOException
    {
        this.dictionary = new File("src/main/java/kenigsberg/wordledictionary/dictionary.txt");
        words = new ArrayList<>();
        definitions = new ArrayList<>();
        fileReader = new FileReader(this.dictionary);
        bufferedReader = new BufferedReader(fileReader);


        String line = bufferedReader.readLine();

        while (line != null)
        {
            String[] lineSplit = line.split(" ", 2);

            if (lineSplit.length == 2)
            {
                words.add(lineSplit[0]);
                definitions.add(lineSplit[1]);
            }
            else
            {
                words.add(lineSplit[0]);
                definitions.add("No definition listed");
            }

            line = bufferedReader.readLine();
        }
    }

    public File getDictionary()
    {
        return dictionary;
    }


    public String getDefinition(String word) throws IOException
    {

        for (int i = 0; i < words.size(); i++)
        {
            if (word.equals(words.get(i)))
            {
                return definitions.get(i);

            }
        }
        return "Word not found";
    }


    public ArrayList<String> getList() throws IOException
    {

        return words;
    }

}
