package com.license;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

import com.license.util.DateUtil;
import com.license.util.KeyUtil;

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
		String licensor = license.getLicensor();
		String expiration = license.getExpiration();
		byte[] pt = null;
		try {
			pt = KeyUtil.decrypt(license.getSignature());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String ptStr = new String(pt);
		if (!ptStr.equals(licensor + expiration)) {
			return false;
		}
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
