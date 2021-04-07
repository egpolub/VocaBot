package ru.jpol.vocabot.service.restImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.repository.WordRepository;
import ru.jpol.vocabot.service.WordService;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final WordRepository wordRepository;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> findAllWord(Long chatID) {
        List<Word> words = wordRepository.findByChatID(chatID);
        logger.info("Found {} words by chat id = {}", words.size(), chatID);

        return words;
    }

    @Override
    public void createWord(Word word) {
        wordRepository.save(word);
        logger.info("Word with chat id = {}, word = {}, translation = {} added",
                word.getChatID(), word.getWord(), word.getTranslation());
    }

    @Override
    public void updateWord(Word word) {
        // TODO think about additional properties for entity word. Updated and Created
        // XXX duplicate code. Forced decision for current bot realisation
        wordRepository.save(word);
        logger.info("Word with chat id = {}, word = {}, translation = {} updated",
                word.getChatID(), word.getWord(), word.getTranslation());
    }

    @Override
    public void deleteWord(Long chatID, String word, String translation) {
        wordRepository.deleteByChatIDAndWordAndTranslation(chatID, word, translation);
        logger.info("Word by chat id = {}, word = {}, translation = {} deleted",
                chatID, word, translation);
    }
}
