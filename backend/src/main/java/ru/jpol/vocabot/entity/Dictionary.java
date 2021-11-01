package ru.jpol.vocabot.entity;

import javax.persistence.*;

@Entity
@Table(name = "dictionary", schema = "voca")
public class Dictionary {
    @Id
    @Column(name = "dictionary_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer dictionaryId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type = Type.UNKNOWN_LANG;


    public enum Type {
        RUS_ENG("rus-eng"),
        UNKNOWN_LANG("unknown-languages");

        private final String value;

        Type (String value) {
            this.value = value;
        }

        public static Type valueOfName(String name) {
            for (Type e : values()) {
                if (e.value.equals(name)) {
                    return e;
                }
            }
            throw new UnsupportedOperationException("Unknown dictionary type");
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total")
    private final Integer total = 0;

    @Column(name = "total_limit")
    private Integer totalLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Integer totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
}
