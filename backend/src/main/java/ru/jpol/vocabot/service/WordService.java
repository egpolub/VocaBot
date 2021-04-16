package ru.jpol.vocabot.service;

import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.repository.WordRepository;

import java.util.List;

public interface WordService {

    /**
     * Find all word by chat id
     * @param chatId - user chat id
     * @return empty list if not found words
     */
    List<Word> findAllWord(Long chatId);

    void createWord(Word word);

    void updateWord(Word word);

    void deleteWord(Long id);

    void deleteAllWord(Long chatId);
}
