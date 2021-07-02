package ru.jpol.vocabot.service;

import ru.jpol.vocabot.entity.Word;

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

    Word findByIdAndChatId(Long id, Long chatId);
}
