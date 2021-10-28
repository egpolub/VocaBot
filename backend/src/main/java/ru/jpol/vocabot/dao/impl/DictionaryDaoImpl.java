package ru.jpol.vocabot.dao.impl;

import org.springframework.stereotype.Repository;
import ru.jpol.vocabot.dao.DictionaryDao;
import ru.jpol.vocabot.entity.Dictionary;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;

import java.util.List;

@Repository
public class DictionaryDaoImpl implements DictionaryDao {
    @Override
    public List<Dictionary> getListDictionaries(Long userId) {
        return null;
    }

    @Override
    public Dictionary getDictionary(Long userId) {
        return null;
    }

    @Override
    public boolean createDictionary(Dictionary dictionary) throws CustomDuplicateKeyDaoException {
        return false;
    }

    @Override
    public boolean updateDictionary(Dictionary dictionary) throws CustomDuplicateKeyDaoException {
        return false;
    }

    @Override
    public boolean deleteDictionary(Integer dictionaryId, Long userId) {
        return false;
    }
}
