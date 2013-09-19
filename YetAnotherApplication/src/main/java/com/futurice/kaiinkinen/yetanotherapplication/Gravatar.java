package com.futurice.kaiinkinen.yetanotherapplication;

import android.os.Message;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kink on 2013.09.19.
 */
public class Gravatar {

    private final MessageDigest digest;
    private final Charset UTF8;

    public Gravatar() {
        try {
            digest = MessageDigest.getInstance("md5");
            UTF8 = Charset.forName("utf-8");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHash(final String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot hash null email address");
        }

        final String cleanedEmail = email.trim().toLowerCase();
        byte[] bytes = digest.digest(cleanedEmail.getBytes(UTF8));

        BigInteger bigInt = new BigInteger(1,bytes);
        return bigInt.toString(16);
    }

    public String getUrl(final String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot calculate url for null email");
        }

        return "http://www.gravatar.com/avatar/" + getHash(email);
    }

}
