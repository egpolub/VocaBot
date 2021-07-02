package ru.jpol.vocabot.utils;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class RestUtils {

    /**
     * HMAC-SHA256 hash generation
     * @param key
     * @param data
     * @return hash - encoded in HEX
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static String encode(String key, String data) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return String.valueOf(Hex.encode(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8))));
    }

    /**
     * Convert object fields with separator - '\n' to line
     * <p>Ignore field with name - "hash"
     * @param obj Java Object
     * @return line - String representation for object fields with separator
     * @throws IllegalAccessException
     */
    public static String fieldSeparator(Object obj) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getName().equals("hash")) {
                continue;
            }
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value != null) {
                result.append(value.toString()).append('\n');
            }
        }
        return result.toString();
    }

}
