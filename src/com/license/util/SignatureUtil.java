package com.license.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;

/**
 * @author jeffreyzhang
 * 
 */
public class SignatureUtil {

	public static String sign(byte[] priKeyText, String plainText)
			throws GeneralSecurityException, IOException,
			ClassNotFoundException {
		FileInputStream f = new FileInputStream(KeyUtil.PUBLIC_KEY_NAME);
		ObjectInputStream b = new ObjectInputStream(f);
		RSAPublicKey pbk = (RSAPublicKey) b.readObject();
		byte ptext[] = plainText.getBytes("UTF8");
		BigInteger e = pbk.getPublicExponent();
		BigInteger n = pbk.getModulus();
		BigInteger m = new BigInteger(ptext);
		// 加密明文
		BigInteger c = m.modPow(e, n);
		// 打印密文c
		System.out.println("c= " + c);
		// 将密文以字符串形式保存在文件中
		String cs = c.toString();
		return null;
	}

	public static boolean verify(byte[] pubKeyText, String plainText,
			byte[] signText) throws GeneralSecurityException {
		return false;
	}
}
