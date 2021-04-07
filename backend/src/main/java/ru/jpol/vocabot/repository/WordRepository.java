package ru.jpol.vocabot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.jpol.vocabot.entity.Word;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByChatID(Long chatID);

    // XXX problem place. Current implementation should change!
    void deleteByChatIDAndWordAndTranslation(Long chatID, String word, String translation);

}
