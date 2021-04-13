package ru.jpol.vocabot.controller;

import io.tej.SwaggerCodgen.api.WordApi;
import io.tej.SwaggerCodgen.model.InlineResponse201;
import io.tej.SwaggerCodgen.model.UserInfo;
import io.tej.SwaggerCodgen.model.WordInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class WordController implements WordApi {
    @Override
    public ResponseEntity<InlineResponse201> createWord(WordInfo wordInfo) {
        return null;
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
        return null;
    }

    @Override
    public ResponseEntity<UserInfo> updateWordByChatId(Long chatId, UserInfo userInfo) {
        return null;
    }
}
