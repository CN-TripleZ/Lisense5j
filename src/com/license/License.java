package com.license;

import java.io.IOException;
import java.io.InputStream;
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

	private Properties props = new Properties();

	public static License newLicense() {
		return new License();
	}

	public static License loadLicense() {
		License license = newLicense();
		Properties props = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream("license.lic");
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		license.setProps(props);
		return license;
	}

	public String getFeature(String paramString) {
		return props.getProperty(paramString);
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

	public void setProps(Properties props) {
		this.props = props;
	}
	
	public static void main(String[] args) {
		License license = License.loadLicense();
		System.out.println(license.getExpiration());
	}
}
