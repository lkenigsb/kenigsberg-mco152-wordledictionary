package kenigsberg.wordledictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WordleDictionaryTest {
    @Test
    public void getDefinition() throws IOException {
        // given
        WordleDictionary dictionary = new WordleDictionary();

        // when
        String theDef = dictionary.getDefinition("AA");

        // then
        Assertions.assertEquals("rough, cindery lava [n -S]", theDef);
    }

    @Test
    public void getDefinitionFail() throws IOException {
        // given
        WordleDictionary dictionary = new WordleDictionary();

        // when
        String notFound = dictionary.getDefinition("LIBBY");

        // then
        assertNull(notFound);
    }

    @Test
    public void getList() throws IOException {
        // given
        WordleDictionary dictionary = new WordleDictionary();

        // when
        Set<String> wordList = dictionary.getList();

        // then
        assertEquals(167964, wordList.size());
    }

    @Test
    public void getDefinitionFormats() throws IOException {
        // given
        WordleDictionary dictionary = new WordleDictionary();

        // when
        String theDef = dictionary.getDefinition("formatting");

        // then
        Assertions.assertEquals("<format=v> [v]", theDef);
    }
}