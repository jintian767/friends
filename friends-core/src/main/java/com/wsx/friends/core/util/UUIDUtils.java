package com.wsx.friends.core.util;

import java.util.UUID;

/**
 * 
 * @author wangshuaixin
 *
 */
public class UUIDUtils {

	/**
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace(CommonFinalUtil.ENGLISH_SPIT, CommonFinalUtil.STRING_EMPTY);
	}
	
}
