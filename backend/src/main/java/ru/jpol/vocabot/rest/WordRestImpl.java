package ru.jpol.vocabot.rest;

import io.tej.SwaggerCodgen.api.WordApi;
import io.tej.SwaggerCodgen.api.WordsApi;
import io.tej.SwaggerCodgen.model.InlineResponse201;
import io.tej.SwaggerCodgen.model.WordInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;
import ru.jpol.vocabot.dao.restImpl.WordDao;
import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.security.jwt.JwtUserDetails;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class WordRestImpl implements WordApi, WordsApi {
    private static final Logger logger = LoggerFactory.getLogger(WordRestImpl.class);

    private final WordDao wordService;

    @Autowired
    public WordRestImpl(WordDao wordService) {
        this.wordService = wordService;
    }

    @Override
    public ResponseEntity<Void> deleteWordByWordId(Integer dictionaryId, Integer wordId) {
        logger.info("Request deleteWordByWordId() with dictionaryId={}, wordId={}", dictionaryId, wordId);

        // TODO add check for dictionary
        if (wordId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "WordId is required query parameter, can not be null");
        }
        if (wordService.findByWordIdAndDictionaryId(wordId, dictionaryId) == null) {
            String message = String.format("Word with wordId=%d and dictionaryId=%d not found", wordId, dictionaryId);
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
        wordService.deleteWord(wordId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateWordByWordId(Integer dictionaryId, Integer wordId, WordInfo wordInfo) {
        logger.info("Request updateWordByWordId() with dictionaryId={},  wordId={}", dictionaryId, wordId);

        // TODO add check for dictionary
        if (wordInfo.getWordId() != null && !wordInfo.getWordId().equals(wordId)) {
            String message = "WordId in the path and in the request body should be the same";
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        Word word = wordService.findByWordIdAndDictionaryId(wordId, dictionaryId);
        if (word == null) {
            String message = String.format("Word with wordId=%d not found", wordId);
            logger.warn(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        BeanUtils.copyProperties(wordInfo, word, "wordId");

        wordService.updateWord(word);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteAllWordsByDictionaryId(Integer dictionaryId) {
        logger.info("Request deleteAllWordsByDictionaryId() with dictionaryId={}", dictionaryId);

        // TODO add check for dictionary

        wordService.deleteAllWord(dictionaryId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<WordInfo>> getListWordsByDictionaryId(Integer dictionaryId) {
        logger.info("Request getListWordByDictionaryId() with dictionaryId={}", dictionaryId);
        // TODO add check for dictionary

        List<Word> words = wordService.findAllWord(dictionaryId);
        List<WordInfo> result = new ArrayList<>();
        words.forEach(word -> {
            WordInfo wordInfo = new WordInfo();
            BeanUtils.copyProperties(word, wordInfo, "created", "updated");
            wordInfo.setCreated(word.getCreated().toInstant().atOffset(ZoneOffset.UTC));
            wordInfo.setUpdated(word.getUpdated().toInstant().atOffset(ZoneOffset.UTC));
            result.add(wordInfo);
        });

        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<InlineResponse201> createWord(WordInfo wordInfo) {
        logger.info("Request createWord() with dictionaryId={}", wordInfo.getDictionaryId());

        Integer dictionaryId = wordInfo.getDictionaryId();
        if (dictionaryId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DictionaryId is required json property, can not be nullable");
        }
        String word = wordInfo.getWord();
        if (word == null || word.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word is required json property, can not be empty");
        }
        String translation = wordInfo.getTranslation();
        if (translation == null || translation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Translation is required json property can not be empty");
        }

        // TODO add check for dictionary

        Word daoWord = new Word();

        BeanUtils.copyProperties(wordInfo, daoWord, "wordId");

        wordService.createWord(daoWord);

        return daoWord.getWordId() != null
                ? ResponseEntity.ok(new InlineResponse201().wordId(daoWord.getWordId()))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    private void checkCredentials(Long chatId) {
        // TODO use filtering instead
        // TODO review me!
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized user");
        }
        JwtUserDetails jwtUser = (JwtUserDetails) auth.getPrincipal();
        if (!chatId.equals(jwtUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    private Long getChatIdFromPrincipal() {
        // TODO review me!
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized user");
        }
        JwtUserDetails jwtUser = (JwtUserDetails) auth.getPrincipal();
        return jwtUser.getId();
    }
}
