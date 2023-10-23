package br.com.pimenta.tarefasGraphql.domains.commons.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PassCodeConverter {

    static Logger logger = LoggerFactory.getLogger(PassCodeConverter.class);

    public static String toMd5(String password) throws RuntimeException {
        try {
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(password.getBytes(),0,password.length());
            return new BigInteger(1,m.digest()).toString(16);
        } catch (Exception ex) {
            logger.error("" , ex);
        }
        throw new RuntimeException("Fail to convert pass");
    }
}
