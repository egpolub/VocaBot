package ru.jpol.vocabot.controller;

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
import ru.jpol.vocabot.entity.Word;
import ru.jpol.vocabot.security.jwt.JwtUserDetails;
import ru.jpol.vocabot.service.restImpl.UserServiceImpl;
import ru.jpol.vocabot.service.restImpl.WordServiceImpl;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class WordController implements WordApi, WordsApi {
    private static final Logger logger = LoggerFactory.getLogger(WordController.class);

    private final WordServiceImpl wordService;
    private final UserServiceImpl userService;

    @Autowired
    public WordController(WordServiceImpl wordService, UserServiceImpl userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> deleteAllWordsByChatId(Long chatId) {
        logger.info(String.format("Request deleteAllWordsByChatId() with chatId = %d", chatId));
        checkCredentials(chatId);

        if (userService.findUser(chatId) == null)
        {
            String message = String.format("User with id = %d not found", chatId);
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        wordService.deleteAllWord(chatId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<InlineResponse201> createWord(WordInfo wordInfo) {
        logger.info(String.format("Request createWord() with chatId = %d", wordInfo.getChatId()));
        checkCredentials(wordInfo.getChatId());

        Long chatId = wordInfo.getChatId();


        String word = wordInfo.getWord(), translation = wordInfo.getTranslation();
        if (chatId == null || word == null || translation == null) {
            String message = String.format(
                    "Chat ID = %d, word = %s, translation = %s must be not null", chatId, word, translation);
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        if (userService.findUser(chatId) == null) {
            String message = String.format("User by chat ID = %d not found", chatId);
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }

        Word daoWord = new Word();

        BeanUtils.copyProperties(wordInfo, daoWord, "id");

        wordService.createWord(daoWord);

        return daoWord.getId() != null
                ? ResponseEntity.ok(new InlineResponse201().id(daoWord.getId()))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<Void> deleteWordById(Long id) {
        logger.info(String.format("Request deleteWordById() with id = %d", id));
        Long chatId = getChatIdFromPrincipal();
        if (wordService.findByIdAndChatId(id, chatId) == null) {
            String message = String.format("Word with id = %d and chatID = %d not found", id, chatId);
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        wordService.deleteWord(id);

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<List<WordInfo>> getListOfWordsByChatId(Long chatId) {
        logger.info(String.format("Request getListOfWordsByChatId() with chatId = %d", chatId));
        checkCredentials(chatId);

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
    public ResponseEntity<Void> updateWordById(Long id, WordInfo wordInfo) {
        logger.info(String.format("Request updateWordById() with id = %d, word_id = %d",
                id, wordInfo.getId()));
        checkCredentials(wordInfo.getChatId());

        if (!id.equals(wordInfo.getId())) {
            String message = "Word id in the path and in the request body should be the same";
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        if (userService.findUser(wordInfo.getChatId()) == null) {
            String message = String.format("User by chat ID = %d not found", wordInfo.getChatId());
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }
        Word word = wordService.findByIdAndChatId(id, wordInfo.getChatId());
        if (word == null) {
            String message = String.format("Word with id = %d not found", id);
            logger.error(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        BeanUtils.copyProperties(wordInfo, word);

        wordService.updateWord(word);

        return ResponseEntity.ok().build();
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
