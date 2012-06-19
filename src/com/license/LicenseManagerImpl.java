package com.license;

import java.security.GeneralSecurityException;
import java.util.Date;

import com.license.util.ByteHex;
import com.license.util.DateUtil;
import com.license.util.KeyUtil;

/**
 * @author jeffreyzhang
 * 
 */
public class LicenseManagerImpl extends LicenseManager {

	private License license;

	protected LicenseManagerImpl(String licenseFile) {
		license = License.loadLicense(licenseFile);
	}

	@Override
	public boolean isValid() throws GeneralSecurityException {
		String licensor = license.getLicensor();
		String expiration = license.getExpiration();
		KeyUtil keyUtil = new KeyUtil();
		keyUtil.generator();
		boolean isValid = SignatureUtil.verify(keyUtil.getPubKey(), ByteHex.hex2byte(license.getSignature()), licensor + expiration);

		if (!isValid) {
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
