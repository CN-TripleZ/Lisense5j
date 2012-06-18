package com.license;

import java.util.Properties;

/**
 * @author jeffreyzhang
 * 
 */
public class License {

	private final static String KEY_LICENSOR = "Licensor";
	private final static String KEY_EXPIRATION = "Expiration";
	private final static String KEY_SIGNATURE = "Signature";
	private final static String KEY_IP = "IP Address";
	private final static String KEY_MAC = "MAC Address";

	private Properties prop = new Properties();

	public static License loadLicense() {
		return null;
	}

	public String getFeature(String paramString) {
		return this.prop.getProperty(paramString);
	}

	public String getExpiration() {
		return getFeature(KEY_EXPIRATION);
	}

	public String getSignature() {
		return getFeature(KEY_SIGNATURE);
	}

	public String getLicensor() {
		return getFeature(KEY_LICENSOR);
	}

	public String getIPAddress() {
		return getFeature(KEY_IP);
	}

	public String getMacAddress() {
		return getFeature(KEY_MAC);
	}

}
