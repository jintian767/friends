package com.wsx.friends.core.secure;

import java.security.MessageDigest;

import com.wsx.friends.core.util.CommonFinalUtil;

public class MD5Util {
	
	// 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    
    public static String getMD5CodeSalt(String source, String salt) {
    	if (null == source) {
    		return CommonFinalUtil.STRING_EMPTY;
    	}
    	if (null == salt || CommonFinalUtil.STRING_EMPTY.equals(salt)) {
    		return getMD5Code(source);
    	}
    	return getMD5Code(getMD5Code(source).substring(10).concat(salt));
    }
    /**
     * 
     * @param source
     * @return
     */
	public static String getMD5Code(String source) {
		String result = null;
		try {
			result = new String(source);
			MessageDigest md5 = MessageDigest.getInstance(CommonFinalUtil.MD5);
			result = byteToString(md5.digest(source.getBytes(CommonFinalUtil.CHARSET_STR)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param digest
	 * @return
	 */
	private static String byteToString(byte[] digest) {
		StringBuffer buffer = new StringBuffer();
		int length = digest.length;
		for (int i = 0; i < length; i++) {
			buffer.append(byteToArrayString(digest[i]));
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @param bBype
	 * @return
	 */
	private static String byteToArrayString(byte bBype) {
		int nByte = bBype;
		if (nByte < 0) {
			nByte += 256;
		}
		
		int id1 = nByte / 16;
		int id2 = nByte % 16;
		return strDigits[id1] + strDigits[id2];
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5CodeSalt("wangeeeeeeeee","1"));
		System.out.println(getMD5CodeSalt("wangeeeeee","1"));
		System.out.println(getMD5CodeSalt("wangeee","1"));
		System.out.println(getMD5CodeSalt("wang","1"));
	}
}
