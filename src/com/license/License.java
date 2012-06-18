package com.license;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author jeffreyzhang
 * 
 */
public class License {

	public final static String LICENSE_PATH = "conf/license.lic";
	public final static String KEY_LICENSOR = "Licensor";
	public final static String KEY_EXPIRATION = "Expiration";
	public final static String KEY_SIGNATURE = "Signature";
	public final static String KEY_IP = "IP";
	public final static String KEY_MAC = "MAC";

	private Properties props = new Properties();

	public static License newLicense() {
		return new License();
	}

	public static License loadLicense() {
		License license = newLicense();
		Properties props = new Properties();
		FileInputStream is = null;
		try {
			is = new FileInputStream(new File("conf/license.lic"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
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
