package ru.jpol.vocabot.entity;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class Word extends BaseEntity {
    @Id
    @Column(name = "word_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer wordId;

    @Column(name ="dictionary_id")
    private Integer dictionaryId;
    @Column(name = "word")
    private String word;
    @Column(name = "translation")
    private String translation;

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}