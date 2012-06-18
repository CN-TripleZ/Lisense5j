package com.license.util;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @author jeffreyzhang
 * 
 */
public class KeyUtil {

	public static KeyPair generateKey(String key) {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			random.setSeed(key.getBytes());

			keygen.initialize(1024, random);

			KeyPair keys = keygen.generateKeyPair();
			return keys;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
			InvalidKeySpecException, UnsupportedEncodingException {
		KeyPair key = KeyUtil.generateKey("jeffreyzhang");
		String pri = KeyUtil.byte2hex(key.getPrivate().getEncoded());
		String pub = KeyUtil.byte2hex(key.getPublic().getEncoded());
		System.out.println(pri);
		System.out.println(pub);
	}
}
