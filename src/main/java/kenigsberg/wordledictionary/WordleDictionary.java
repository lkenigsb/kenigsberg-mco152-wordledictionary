package kenigsberg.wordledictionary;

import java.io.*;
import java.util.ArrayList;

public class WordleDictionary
{
    private final File dictionary;

    public WordleDictionary() throws FileNotFoundException
    {
        this.dictionary = new File("src/main/java/kenigsberg/wordledictionary/dictionary.txt");
    }

    public File getDictionary()
    {
        return dictionary;
    }



    public String getDefinition(String word) throws IOException
    {
        StringBuilder definition = new StringBuilder("");

        FileReader fileReader = new FileReader(this.dictionary);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        while (line != null)
        {
            //line = bufferedReader.readLine();
            String[] words = line.split(" ");
            //now words[0] = the word to define

            if (words[0].equals(word))
            {
                definition.append(line.substring(word.length()+1));
            }
            line = bufferedReader.readLine();
        }

        return definition.toString();
    }


    public ArrayList<String> getList() throws IOException
    {
        ArrayList<String> words = new ArrayList<>();

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

        return words;
    }

}
