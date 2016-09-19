package com.wsx.friends.core.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class DesBase64Util {

	/**
	 * 加密
	 * @param deskey
	 * @return
	 */
	public static String encode(String deskey){
		if(deskey != null && !CommonFinalUtil.STRING_EMPTY.equals(deskey)){
			BASE64Encoder encoder = new BASE64Encoder();
			String desresult =  encoder.encode(DesUtil.encryptMode(deskey.getBytes()));
			return desresult;
		}
		return null;
	}
	/**
	 * 解密
	 * @param desresult
	 * @return
	 */
	public static String decoder(String desresult){
		BASE64Decoder decoder = new BASE64Decoder();
		String result = null;
		try {
			byte[] deco = decoder.decodeBuffer(desresult);
			result = new String(DesUtil.decryptMode(deco));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
