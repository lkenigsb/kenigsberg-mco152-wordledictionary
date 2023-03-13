package kenigsberg.wordledictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class WordleDictionary {
    private final File dictionary;
    private final ArrayList<String> words;
    private final ArrayList<String> definitions;
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    private final HashMap<String, String> hashMap;

    public WordleDictionary() throws IOException {
        this.dictionary = new File("src/main/java/kenigsberg/wordledictionary/dictionary.txt");
        this.words = new ArrayList<>();
        this.definitions = new ArrayList<>();
        this.fileReader = new FileReader(this.dictionary);
        this.bufferedReader = new BufferedReader(fileReader);
        this.hashMap = new HashMap<>();


        String line = bufferedReader.readLine();

        while (line != null) {
            String[] lineSplit = line.split(" ", 2);

            if (lineSplit.length == 2) {
                hashMap.put(lineSplit[0], lineSplit[1]);
                //words.add(lineSplit[0]);
                //definitions.add(lineSplit[1]);
            } else {
                hashMap.put(lineSplit[0], "No definition listed");
                //words.add(lineSplit[0]);
                //definitions.add("No definition listed");
            }
            line = bufferedReader.readLine();
        }
    }

    public File getDictionary() {
        return dictionary;
    }


    public String getDefinition(String word) {
        word = word.toUpperCase();

        return hashMap.getOrDefault(word, null);
        /*
        int index = words.indexOf(word.toUpperCase());

        if (index == -1) {
            return null;
        } else {
            return definitions.get(index);
        }*/
    }


    public Set<String> getList() {
        return hashMap.keySet();
    }

}
