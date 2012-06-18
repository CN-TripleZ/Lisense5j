package com.license.util;

import java.security.GeneralSecurityException;

/**
 * @author jeffreyzhang
 * 
 */
public class SignatureUtil {
	
	public static byte[] sign(byte[] priKeyText, String plainText)
			throws GeneralSecurityException {
		return null;
	}

	public static boolean verify(byte[] pubKeyText, String plainText,
			byte[] signText) throws GeneralSecurityException {
		return false;
	}
}
