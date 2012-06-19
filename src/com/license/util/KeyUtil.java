package com.license.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * @author jeffreyzhang
 * 
 */
public class KeyUtil {

	private byte[] pubKey;
	private byte[] priKey;

	public byte[] getPubKey() {
		return pubKey;
	}

	public byte[] getPriKey() {
		return priKey;
	}

	public void generator() {
		generator("jeffreyzhang");
	}

	public void generator(String plainText) {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			random.setSeed(plainText.getBytes());
			keyGen.initialize(1024, random);

			KeyPair keys = keyGen.generateKeyPair();
			PublicKey publicKey = keys.getPublic();
			PrivateKey privateKey = keys.getPrivate();
			
			this.pubKey = publicKey.getEncoded();
			this.priKey = privateKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

	}
}
