package ru.jpol.vocabot.dao;

import ru.jpol.vocabot.entity.Word;

import java.util.List;

public interface WordService {

    /**
     * Find all word by dictionary id
     *
     * @param dictionaryId - dictionary id
     * @return list of words, empty list - if not found words
     */
    List<Word> findAllWord(Integer dictionaryId);

    void createWord(Word word);

    void updateWord(Word word);

    void deleteWord(Integer wordId);

    /**
     * Delete all words by dictionary id
     *
     * @param dictionaryId - dictionary id
     */
    void deleteAllWord(Integer dictionaryId);

    Word findByWordIdAndDictionaryId(Integer wordId, Integer dictionaryId);
}
