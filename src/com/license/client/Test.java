package com.license.client;

import java.security.GeneralSecurityException;

import com.license.LicenseManager;
import com.license.SignatureUtil;
import com.license.util.ByteHex;
import com.license.util.KeyUtil;

public class Test {

	/**
	 * @param args
	 * @throws GeneralSecurityException 
	 */
	public static void main(String[] args) throws GeneralSecurityException {
		boolean valid = LicenseManager.getInstance("conf/license.lic").isValid();
		System.out.println(valid);
		
		KeyUtil ku = new KeyUtil();
		ku.generator();
		byte[] signText = SignatureUtil.signature(ku.getPriKey(), "test");
		String sign = ByteHex.byte2hex(signText);
		System.out.println(sign);
		
		boolean isValid = SignatureUtil.verify(ku.getPubKey(), ByteHex.hex2byte(sign), "test");
		System.out.println(isValid);

	}

}
