package ru.jpol.vocabot.service.restImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.repository.WordRepository;
import ru.jpol.vocabot.service.WordService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WordServiceImpl implements WordService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final WordRepository wordRepository;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }


    @Override
    public List<Word> findAllWord(Long chatId) {
        List<Word> words = wordRepository.findByChatId(chatId);
        logger.info("Found {} words by chat id = {}", words.size(), chatId);

        return words;
    }

    @Override
    public void createWord(Word word) {
        wordRepository.save(word);
        logger.info("Word with id = {}, chat id = {}, word = {}, translation = {} added",
                word.getId(), word.getChatId(), word.getWord(), word.getTranslation());
    }

    @Override
    public void updateWord(Word word) {
        // XXX duplicate code. Forced decision for current bot realisation
        wordRepository.save(word);
        logger.info("Word with id = {}, chat id = {}, word = {}, translation = {} updated",
                word.getId(), word.getChatId(), word.getWord(), word.getTranslation());
    }

    @Override
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
        logger.info("Word by id = {} deleted", id);
    }

    @Override
    public void deleteAllWord(Long chatId) {
        wordRepository.deleteWordByChatId(chatId);
        logger.info("All words by chat id = {} deleted", chatId);
    }

    @Override
    public Word findById(Long id) {
        Word word = null;
        try {
            word = wordRepository.findById(id).orElseThrow(NoSuchElementException::new);
            logger.info("Found word by id: {}", word.getId());
        } catch (NoSuchElementException e) {
            logger.warn("Could not find word by id: {}", id);
        }
        return word;
    }
}
