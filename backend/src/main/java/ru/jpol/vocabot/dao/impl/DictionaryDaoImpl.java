package ru.jpol.vocabot.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.jpol.vocabot.dao.DictionaryDao;
import ru.jpol.vocabot.dao.mapper.EnumPropertyRowMapper;
import ru.jpol.vocabot.entity.Dictionary;
import ru.jpol.vocabot.exception.CustomDuplicateKeyDaoException;
import ru.jpol.vocabot.utils.DaoUtils;

import java.util.Collections;
import java.util.List;

@Repository
public class DictionaryDaoImpl implements DictionaryDao {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryDaoImpl.class);

    private static final String TABLE_DICTIONARY_NAME = "dictionary";

    @Value("${spring.flyway.schemas}")
    private String DEFAULT_SCHEMA_NAME;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DictionaryDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Dictionary> getListDictionaries(Long userId) {
        List<Dictionary> dictionaries = Collections.emptyList();
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("userId", userId);

            String sql = "SELECT * FROM " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_DICTIONARY_NAME) +
                    " WHERE " +
                    "user_id = :userId";

            dictionaries = namedParameterJdbcTemplate.query(
                    sql,
                    namedParameters,
                    new BeanPropertyRowMapper<>(Dictionary.class));

            logger.info("Found count={} dictionaries", dictionaries.size());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return dictionaries;
    }

    @Override
    public Dictionary getDictionary(Integer dictionaryId, Long userId) {
        Dictionary dictionary = null;
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("dictionaryId", dictionaryId)
                    .addValue("userId", userId);

            String sql = "SELECT * FROM " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_DICTIONARY_NAME) +
                    " WHERE " +
                    "dictionary_id = :dictionaryId" +
                    " AND " +
                    "user_id = :userId";

            dictionary = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    namedParameters,
                    new EnumPropertyRowMapper());

            assert dictionary != null;
            logger.info("Found dictionary by dictionaryId={} and userId={}", dictionary.getDictionaryId(), dictionary.getUserId());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Dictionary by dictionaryId={} and userId={} does not exist in the system", dictionaryId, userId);
        }
        return dictionary;
    }

    @Override
    public boolean createDictionary(Dictionary dictionary) throws CustomDuplicateKeyDaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("name", dictionary.getName())
                    .addValue("type", dictionary.getType().toString())
                    .addValue("userId", dictionary.getUserId())
                    .addValue("total", dictionary.getTotal())
                    .addValue("totalLimit", dictionary.getTotalLimit());

            String sql = "INSERT INTO " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_DICTIONARY_NAME) +
                    " (name, type, user_id, total, total_limit)" +
                    " VALUES (:name, :type, :userId, :total, :totalLimit )";

            int rows = namedParameterJdbcTemplate.update(
                    sql,
                    namedParameters,
                    keyHolder,
                    new String[] {"dictionary_id"});
            if (rows == 1) {
                dictionary.setDictionaryId((Integer) keyHolder.getKey());
                logger.info("Added dictionary with name={} and userId={}", dictionary.getName(), dictionary.getUserId());
                return true;
            }
        }
        catch (DuplicateKeyException e) {
            String errorMessage = String.format("Could not create dictionary with duplicated name=%s and userId=%d",
                    dictionary.getName(), dictionary.getUserId());
            logger.warn(errorMessage);
            throw new CustomDuplicateKeyDaoException(errorMessage);
        }
        catch (DataAccessException e2) {
            logger.error(e2.getMessage(), e2);
        }
        logger.warn("Could not add dictionary with name={} and userId={}", dictionary.getName(), dictionary.getUserId());

        return false;

    }

    @Override
    public boolean updateDictionary(Dictionary dictionary) throws CustomDuplicateKeyDaoException {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("dictionaryId", dictionary.getDictionaryId())
                    .addValue("name", dictionary.getName())
                    .addValue("type", dictionary.getType().toString())
                    .addValue("totalLimit", dictionary.getTotalLimit());

            String sql = "UPDATE " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_DICTIONARY_NAME) +
                    " SET name = :name," +
                    "type = :type," +
                    "totalLimit" +
                    " WHERE dictionary_id = :dictionaryId";
            int rows = namedParameterJdbcTemplate.update(
                    sql,
                    namedParameters);
            if (rows == 1) {
                logger.info("Updated dictionary with dictionaryId={} and userId={}",
                        dictionary.getDictionaryId(), dictionary.getUserId());
                return true;
            }
        } catch(DuplicateKeyException e) {
            String errorMessage = String.format("Could not update dictionary with duplicated name=%s",
                    dictionary.getName());
            logger.warn(errorMessage);
            throw new CustomDuplicateKeyDaoException(errorMessage);
        } catch (DataAccessException e2) {
            logger.error(e2.getMessage(), e2);
        }
        logger.warn("Could not update dictionary with name={} and userId={}", dictionary.getName(), dictionary.getUserId());

        return true;
    }

    @Override
    public boolean deleteDictionary(Integer dictionaryId, Long userId) {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("dictionaryId", dictionaryId)
                    .addValue("userId", userId);

            String sql = "DELETE FROM " +
                    DaoUtils.getExtendedTableName(DEFAULT_SCHEMA_NAME, TABLE_DICTIONARY_NAME) +
                    " WHERE dictionary_id = :dictionaryId" +
                    " AND " +
                    "user_id = :userId";

            int rows = namedParameterJdbcTemplate.update(
                    sql,
                    namedParameters);
            if (rows == 1) {
                logger.info("Deleted dictionary by dictionaryId={} and userId={}", dictionaryId, userId);
                return true;
            }
        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
        }
        logger.warn("Could not delete dictionary by dictionaryId={} and userId={}", dictionaryId, userId);

        return false;
    }
}
