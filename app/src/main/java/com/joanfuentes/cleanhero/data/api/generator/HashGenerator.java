package com.joanfuentes.cleanhero.data.api.generator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

public class HashGenerator {
    private static final String BASE_HASH_PATTERN = "%s%s%s";
    private static final String DIGEST_ALGORITHM_MD5 = "MD5";
    private static final String NO_HASH = "";
    private static final int POSITIVE_SIGNUM = 1;
    private static final int BASE_RADIX = 16;

    @Inject
    public HashGenerator() { }

    private String getTextToHash(String timestamp, String privateKey, String publicKey) {
        return String.format(BASE_HASH_PATTERN, timestamp, privateKey, publicKey);
    }

    public String getHash(String timestamp, String privateKey, String publicKey) {
        String textToHash = getTextToHash(timestamp, privateKey, publicKey);
        String result = NO_HASH;
        try {
            MessageDigest md = MessageDigest.getInstance(DIGEST_ALGORITHM_MD5);
            result = new BigInteger(POSITIVE_SIGNUM,md.digest(textToHash.getBytes())).toString(BASE_RADIX);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
