package ru.jpol.vocabot.service.restImpl;

import org.springframework.stereotype.Service;
import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.service.WordService;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    @Override
    public List<Word> findAllWord(Long chatID) {
        return null;
    }

    @Override
    public void createWord(Long chatID, String word, String translation) {

    }

    @Override
    public void updateWord(Long chatID, String word, String translation) {

    }

    @Override
    public void deleteWord(Long chatID, String word, String translation) {

    }
}
