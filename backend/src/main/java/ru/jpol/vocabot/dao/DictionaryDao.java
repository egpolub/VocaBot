package ru.jpol.vocabot.dao;

import ru.jpol.vocabot.entity.Dictionary;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;

import java.util.List;

public interface DictionaryDao {
    /**
     * Get list of dictionaries by user id
     *
     * @param userId user id
     * @return list of dictionaries
     */
    List<Dictionary> getListDictionaries(Long userId);

    /**
     * Get dictionary by dictionary id and user id
     *
     * @param dictionaryId dictionary id
     * @param userId user id
     * @return dictionary
     */
    Dictionary getDictionary(Integer dictionaryId, Long userId);

    /**
     * Add dictionary to the system
     *
     * @param dictionary
     * @return true - if the dictionary was added to the system, false - otherwise
     * @throws CustomDuplicateKeyDaoException if user id or name not unique
     */
    boolean createDictionary(Dictionary dictionary) throws CustomDuplicateKeyDaoException;

    /**
     * Update an existing dictionary in the system,
     * user id will be ignored
     *
     * @param dictionary
     * @return true - if the dictionary was updated to the system, false - otherwise
     * @throws CustomDuplicateKeyDaoException if name not unique
     */
    boolean updateDictionary(Dictionary dictionary) throws CustomDuplicateKeyDaoException;

    /**
     * Delete an existing dictionary from the system
     *
     * @param dictionaryId dictionary id
     * @param userId user id
     * @return true - if the dictionary was deleted from the system, false - otherwise
     */
    boolean deleteDictionary(Integer dictionaryId, Long userId);

}
