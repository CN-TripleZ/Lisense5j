package com.license.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author jeffreyzhang
 * 
 */
public class KeyUtil {

	public final static String PUBLIC_KEY_NAME = "conf/publicKey.dat";
	public final static String PRIVATE_KEY_NAME = "conf/privateKey.dat";

	public static void generateKey(String key) throws Exception {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			random.setSeed(key.getBytes());

			keygen.initialize(1024, random);

			KeyPair keys = keygen.generateKeyPair();

			PublicKey pbkey = keys.getPublic();
			FileOutputStream f1 = new FileOutputStream(PUBLIC_KEY_NAME);
			ObjectOutputStream b1 = new ObjectOutputStream(f1);
			b1.writeObject(pbkey);

			PrivateKey prkey = keys.getPrivate();
			FileOutputStream f2 = new FileOutputStream(PRIVATE_KEY_NAME);
			ObjectOutputStream b2 = new ObjectOutputStream(f2);
			b2.writeObject(prkey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String plainText) throws IOException,
			ClassNotFoundException {
		FileInputStream f = new FileInputStream(PUBLIC_KEY_NAME);
		ObjectInputStream b = new ObjectInputStream(f);
		RSAPublicKey pbk = (RSAPublicKey) b.readObject();
		// RSA算法是使用整数进行加密的，在RSA公钥中包含有两个整数信息：e和n。对于明文数字m,计算密文的公式是m的e次方再与n求模。
		BigInteger e = pbk.getPublicExponent();
		BigInteger n = pbk.getModulus();
		// 获取明文的大整数
		byte ptext[] = plainText.getBytes("UTF8");
		BigInteger m = new BigInteger(ptext);
		// 加密明文
		BigInteger c = m.modPow(e, n);
		// 将密文以字符串形式保存在文件中
		return c.toString();
	}

	public static byte[] decrypt(String signText) throws IOException,
			ClassNotFoundException {
		BigInteger c = new BigInteger(signText);
		// 获取私钥
		FileInputStream f = new FileInputStream(PRIVATE_KEY_NAME);
		ObjectInputStream b = new ObjectInputStream(f);
		RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
		// 获取私钥的参数d,n
		BigInteger d = prk.getPrivateExponent();
		BigInteger n = prk.getModulus();
		// 解密明文
		BigInteger m = c.modPow(d, n);
		// 计算明文对应的字符串并输出。
		byte[] mt = m.toByteArray();

		return mt;
	}

	public static void main(String[] args) throws Exception {
		KeyUtil.generateKey("jeffreyzhang");
		String signText = KeyUtil.encrypt("jeffreyzhang");
		byte[] bt = KeyUtil.decrypt(signText);
		System.out.println(signText);
		System.out.println(new String(bt));
	}
}
