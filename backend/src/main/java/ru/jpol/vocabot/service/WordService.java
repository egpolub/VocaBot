package ru.jpol.vocabot.service;

import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.repository.WordRepository;

import java.util.List;

public interface WordService {

    List<Word> findAllWord(Long chatID);

    void createWord(Long chatID, String word, String translation);

    void updateWord(Long chatID, String word, String translation);

    void deleteWord(Long chatID, String word, String translation);
}
