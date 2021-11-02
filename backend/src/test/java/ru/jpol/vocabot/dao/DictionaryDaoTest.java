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

        // error case. Try to add dictionary a second time
        Assertions.assertThrows(CustomDuplicateKeyDaoException.class, () -> dictionaryDao.createDictionary(dictionary2));

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

    @Test
    public void testUpdateDictionary() throws Exception{
        config_01();

        Dictionary dictionary = new Dictionary();
        dictionary.setName("Education");
        dictionary.setUserId(userIds.get(0));
        dictionary.setType(Dictionary.Type.UNKNOWN_LANG);
        dictionary.setTotalLimit(150);

        // try to update not existent dictionary
        Assertions.assertFalse(dictionaryDao.updateDictionary(dictionary));

        // normal case. Dictionary present in the system
        dictionaryDao.createDictionary(dictionary);

        Dictionary updatedDictionary = new Dictionary();
        updatedDictionary.setDictionaryId(dictionary.getDictionaryId());
        updatedDictionary.setType(Dictionary.Type.RUS_ENG);
        updatedDictionary.setName("Updated Education");
        updatedDictionary.setUserId(userIds.get(1)); // should be ignored
        updatedDictionary.setTotalLimit(200);

        Assertions.assertTrue(dictionaryDao.updateDictionary(updatedDictionary));

        Dictionary getUpdatedDictionary = dictionaryDao.getDictionary(dictionary.getDictionaryId(), dictionary.getUserId());
        Assertions.assertEquals(dictionary.getDictionaryId(), getUpdatedDictionary.getDictionaryId());
        Assertions.assertEquals(dictionary.getUserId(), getUpdatedDictionary.getUserId());
        Assertions.assertEquals(dictionary.getTotal(), getUpdatedDictionary.getTotal());
        Assertions.assertNotEquals(dictionary.getName(), getUpdatedDictionary.getName());
        Assertions.assertNotEquals(dictionary.getType(), getUpdatedDictionary.getType());
        Assertions.assertNotEquals(dictionary.getTotalLimit(), getUpdatedDictionary.getTotalLimit());

        Assertions.assertEquals(updatedDictionary.getName(), getUpdatedDictionary.getName());
        Assertions.assertEquals(updatedDictionary.getType(), getUpdatedDictionary.getType());
        Assertions.assertEquals(updatedDictionary.getTotalLimit(), getUpdatedDictionary.getTotalLimit());

        // TODO test dictionary.getTotal() functionality


        // error case. Try to update dictionary by no uniq name.
        Dictionary dictionary2 = new Dictionary();
        dictionary2.setName("Uniq Name");
        dictionary2.setUserId(getUpdatedDictionary.getUserId());

        dictionaryDao.createDictionary(dictionary2);
        Dictionary updatedDictionary2 = dictionaryDao.getDictionary(dictionary2.getDictionaryId(), dictionary2.getUserId());
        updatedDictionary2.setName(getUpdatedDictionary.getName()); // no uniq name

        Assertions.assertThrows(CustomDuplicateKeyDaoException.class, () -> dictionaryDao.updateDictionary(updatedDictionary2));
    }

    @Test
    public void testDeleteDictionary() throws Exception {
        config_01();

        // normal case
        Dictionary dictionary = new Dictionary();
        dictionary.setName("Cars");
        dictionary.setType(Dictionary.Type.UNKNOWN_LANG);
        dictionary.setTotalLimit(150);
        dictionary.setUserId(userIds.get(0));

        dictionaryDao.createDictionary(dictionary);

        Assertions.assertTrue(dictionaryDao.deleteDictionary(dictionary.getDictionaryId(), dictionary.getUserId()));

        // error case. Try to delete no existent dictionary
        Assertions.assertFalse(dictionaryDao.deleteDictionary(123, 123L));
    }
}
