package ru.jpol.vocabot.utils;

public class DaoUtils {
    public static String getExtendedTableName(String schemaName, String tableName) {
        return schemaName + "." + tableName;
    }
}
