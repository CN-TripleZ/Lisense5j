package com.license.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import com.sun.xml.internal.messaging.saaj.util.Base64;

/**
 * @author jeffreyzhang
 * 
 */
public class KeyUtil {
	private byte[] priKey;
	private byte[] pubKey;

	public void generateKey() {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed("jeffreyzhang".getBytes()); // 初始化随机产生器
			keygen.initialize(1024, secrand);
			KeyPair keys = keygen.genKeyPair();
			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();

			priKey = Base64.encode(prikey.getEncoded());
			pubKey = Base64.encode(pubkey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
