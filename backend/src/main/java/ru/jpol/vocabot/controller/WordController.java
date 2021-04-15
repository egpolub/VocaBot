package ru.jpol.vocabot.controller;

import io.tej.SwaggerCodgen.api.WordApi;
import io.tej.SwaggerCodgen.model.InlineResponse201;
import io.tej.SwaggerCodgen.model.UserInfo;
import io.tej.SwaggerCodgen.model.WordInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.service.restImpl.UserServiceImpl;
import ru.jpol.vocabot.service.restImpl.WordServiceImpl;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


@RestController
public class WordController implements WordApi {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final WordServiceImpl wordService;
    private final UserServiceImpl userService;

    @Autowired
    public WordController(WordServiceImpl wordService, UserServiceImpl userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<InlineResponse201> createWord(WordInfo wordInfo) {
        logger.info(String.format("Request createWord() with chatId = %d", wordInfo.getChatId()));

        Long chatId = wordInfo.getChatId();
        String word = wordInfo.getWord(), translation = wordInfo.getTranslation();
        if (chatId == null || word == null || translation == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(
                    "Chat ID = %d, word = %s, translation = %s must be not null", chatId, word, translation));
        }

        if (userService.findUser(chatId) == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format(
                    "User by chat ID = %d not found", chatId));
        }

        Word daoWord = new Word();

        BeanUtils.copyProperties(wordInfo, daoWord, "id");

        wordService.createWord(daoWord);

        return daoWord.getId() != null
                ? ResponseEntity.ok(new InlineResponse201().id(daoWord.getId()))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<Void> deleteAllWordsByChatId(Long chatId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteWordById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<WordInfo>> getListOfWordsByChatId(Long chatId) {
        logger.info(String.format("Request getListOfWordsByChatId() with chatId = %d", chatId));

        List<Word> words = wordService.findAllWord(chatId);
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
    public ResponseEntity<UserInfo> updateWordByChatId(Long chatId, UserInfo userInfo) {
        return null;
    }
}