package kenigsberg.wordledictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordleDictionaryTest
{
    @Test
    public void getDefinition() throws IOException
    {
        // given
        WordleDictionary dictionary = new WordleDictionary();

        // when
        String theDef = dictionary.getDefinition("AA");

        // then
        Assertions.assertEquals("rough, cindery lava [n -S]", theDef);
    }

    @Test
    public void getList() throws IOException
    {
        // given
        WordleDictionary dictionary = new WordleDictionary();

        // when
        ArrayList<String> wordList = dictionary.getList();

        // then
        assertEquals(167964, wordList.size());
    }
}