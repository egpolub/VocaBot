package ru.jpol.vocabot.utils;

public class DaoUtils {
    /**
     * String concatenation between schema and table name with dot delimiter
     *
     * @param schemaName schema name
     * @param tableName table name
     * @return extended table name
     */
    public static String getExtendedTableName(String schemaName, String tableName) {
        return schemaName + "." + tableName;
    }
}
