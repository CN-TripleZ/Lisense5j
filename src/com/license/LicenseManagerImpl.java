package com.license;

import java.security.GeneralSecurityException;
import java.util.Date;

import com.license.util.DateUtil;

/**
 * @author jeffreyzhang
 * 
 */
public class LicenseManagerImpl extends LicenseManager {

	private License license;

	protected LicenseManagerImpl() {
		license = License.loadLicense();
	}

	@Override
	public boolean isValid() throws GeneralSecurityException {
		return daysLeft() > 0;
	}

	@Override
	public int daysLeft() {
		String str = license.getExpiration();
		Date date = DateUtil.toDate(str);
		return DateUtil.dayLeft(date);
	}

	@Override
	public String getFeature(String featureKey) {
		return license.getFeature(featureKey);
	}

}
