package ru.jpol.vocabot.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.jpol.vocabot.entity.Word;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findByDictionaryId(Integer dictionaryId);

    Word findByWordIdAndDictionaryId(Integer wordId, Integer dictionaryId);

    @Transactional
    void deleteWordByDictionaryId(Integer dictionaryId);

}
