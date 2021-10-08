package ru.jpol.vocabot.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jpol.vocabot.dao.WordService;
import ru.jpol.vocabot.dao.repository.WordRepository;
import ru.jpol.vocabot.entity.Word;

import java.util.List;

@Service
public class WordDaoImpl implements WordService {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final WordRepository wordRepository;

    @Autowired
    public WordDaoImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> findAllWord(Integer dictionaryId) {
        List<Word> words = wordRepository.findByDictionaryId(dictionaryId);
        logger.info("Found count={} words by dictionaryId={}", words.size(), dictionaryId);

        return words;
    }

    @Override
    public void createWord(Word word) {
        wordRepository.save(word);
        logger.info("Word with wordId={}, dictionaryId={}, word={}, translation={} added",
                word.getWordId(), word.getDictionaryId(), word.getWord(), word.getTranslation());
    }

    @Override
    public void updateWord(Word word) {
        // XXX duplicate code. Forced decision for current bot realisation
        // TODO use JDBC
        wordRepository.save(word);
        logger.info("Word with wordId={}, dictionaryId={}, word={}, translation={} updated",
                word.getWordId(), word.getDictionaryId(), word.getWord(), word.getTranslation());
    }

    @Override
    public void deleteWord(Integer wordId) {
        wordRepository.deleteById(wordId);
        logger.info("Word by wordId={} deleted", wordId);
    }

    @Override
    public void deleteAllWord(Integer dictionaryId) {
        wordRepository.deleteWordByDictionaryId(dictionaryId);
        logger.info("All words by  wordId={} deleted", dictionaryId);
    }

    @Override
    public Word findByWordIdAndDictionaryId(Integer wordId, Integer dictionaryId) {
        Word word = wordRepository.findByWordIdAndDictionaryId(wordId, dictionaryId);
        if (word == null) {
            logger.info("Could not find word by wordId={} and dictionaryId={}", wordId, dictionaryId);
        }
        logger.info("Found word by wordId={} and dictionaryId={}", word.getWordId(), word.getDictionaryId());
        return word;
    }
}
