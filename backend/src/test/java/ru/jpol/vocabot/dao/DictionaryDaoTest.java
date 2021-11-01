package ru.jpol.vocabot.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.jpol.vocabot.VocaBotApplicationTest;
import ru.jpol.vocabot.entity.Dictionary;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;

public class DictionaryDaoTest extends VocaBotApplicationTest {
    @Autowired
    private DictionaryDao dictionaryDao;

    @Test
    public void testCreateDictionary() throws Exception{
        config_01();

        // normal case. Full properties
        Dictionary dictionary = new Dictionary();
        Integer ignoredDictionaryId = 123;
        dictionary.setDictionaryId(ignoredDictionaryId); // should be ignored
        dictionary.setName("Animals");
        dictionary.setType(Dictionary.Type.RUS_ENG);
        dictionary.setUserId(userIds.get(0));
        dictionary.setTotalLimit(150);

        Assertions.assertTrue(dictionaryDao.createDictionary(dictionary));
        Assertions.assertNotEquals(ignoredDictionaryId, dictionary.getDictionaryId());
        Assertions.assertNotNull(dictionary.getDictionaryId());


        Dictionary createdDictionary = dictionaryDao.getDictionary(dictionary.getDictionaryId(), dictionary.getUserId());
        Assertions.assertNotNull(createdDictionary);
        Assertions.assertNotNull(createdDictionary.getDictionaryId());
        Assertions.assertEquals(dictionary.getDictionaryId(), createdDictionary.getDictionaryId());
        Assertions.assertEquals(dictionary.getName(), createdDictionary.getName());
        Assertions.assertEquals(dictionary.getUserId(), createdDictionary.getUserId());
        Assertions.assertEquals(dictionary.getType(), createdDictionary.getType());
        Assertions.assertEquals(dictionary.getTotalLimit(), createdDictionary.getTotalLimit());
        Assertions.assertEquals(0, createdDictionary.getTotal()); // current dictionary contains no words

        // normal case. Required properties
        dictionary = new Dictionary();
        dictionary.setName("Country");
        dictionary.setUserId(userIds.get(0));

        Assertions.assertTrue(dictionaryDao.createDictionary(dictionary));

        createdDictionary = dictionaryDao.getDictionary(dictionary.getDictionaryId(), dictionary.getUserId());
        Assertions.assertNull(createdDictionary.getTotalLimit());
        Assertions.assertNotNull(createdDictionary.getDictionaryId());
        Assertions.assertEquals(Dictionary.Type.UNKNOWN_LANG, createdDictionary.getType()); // default value
        Assertions.assertEquals(dictionary.getName(), createdDictionary.getName());
        Assertions.assertEquals(dictionary.getUserId(), createdDictionary.getUserId());
        Assertions.assertEquals(0, createdDictionary.getTotal()); // default value

        // normal case. The same dictionary name for different user id
        Dictionary dictionary2 = new Dictionary();
        dictionary2.setUserId(userIds.get(1)); // another user id
        dictionary2.setName(dictionary.getName());

        Assertions.assertTrue(dictionaryDao.createDictionary(dictionary2));

        Dictionary createdDictionary2 = dictionaryDao.getDictionary(dictionary2.getDictionaryId(), dictionary2.getUserId());
        Assertions.assertEquals(dictionary2.getName(), createdDictionary2.getName());

        // error case. Not uniq dictionary name for one user id
        Dictionary noUniqDictionary = new Dictionary();
        noUniqDictionary.setUserId(dictionary.getUserId());
        noUniqDictionary.setName(dictionary.getName());

        Assertions.assertThrows(CustomDuplicateKeyDaoException.class, () -> dictionaryDao.createDictionary(noUniqDictionary));

        // error case. Not nullable property is null
        Dictionary dictionary3 = new Dictionary();
        dictionary3.setName(null);
        dictionary3.setUserId(userIds.get(2));

        Assertions.assertFalse(dictionaryDao.createDictionary(dictionary3));
    }
}
