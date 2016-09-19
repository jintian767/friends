package com.wsx.friends.core.token;

import com.google.common.hash.Hashing;
import com.wsx.friends.core.util.CommonFinalUtil;

public final class TokenUtil {

	private final static String PRIVATE_KEY = "13579,COM/wsx/FRIENDS,02468.@#$%^&*";
	
	public static String createToken(String source) {
		String pre = Hashing.md5().newHasher()
				.putString(source, CommonFinalUtil.CHARSET)
				.putString(PRIVATE_KEY, CommonFinalUtil.CHARSET)
				.putLong(System.currentTimeMillis())
				.hash()
				.toString();
		String fex = Integer.toHexString(source.hashCode());
		return pre + CommonFinalUtil.ENGLISH_SPIT + fex;
	}
	
}
