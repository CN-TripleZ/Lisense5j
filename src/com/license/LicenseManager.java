package com.license;

import java.security.GeneralSecurityException;

/**
 * @author jeffreyzhang
 * 
 */
public abstract class LicenseManager {

	//private static LicenseManager LICENSE_MANAGER = new LicenseManagerImpl();

	public static LicenseManager getInstance(String licenseFile) {
		return new LicenseManagerImpl(licenseFile);
	}

	public abstract boolean isValid() throws GeneralSecurityException;

	public abstract int daysLeft();

	public abstract String getFeature(String featureKey);
}
