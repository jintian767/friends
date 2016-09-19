package com.wsx.friends.core.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;


public class DesUtil {

	private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
	private static final String KEYBYTES = "_t_1op0spt_wvm28wasvu_o6";
	
	public static final String ENCODING = "UTF-8";
    
    /**
     * 自定义key加密
     * @param keybyte
     * @param src
     * @return
     */
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

   /**
    * 自定义key解密
    * @param keybyte
    * @param src
    * @return
    */
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {      
    try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
    
    /**
     * 固定key加密
     * @param keybyte
     * @param src
     * @return
     */
    public static byte[] encryptMode(byte[] src) {
       try {
    	   byte[] keybyte = KEYBYTES.getBytes();
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

   /**
    * 固定key解密
    * @param keybyte
    * @param src
    * @return
    */
    public static byte[] decryptMode(byte[] src) {      
    try {
    		byte[] keybyte = KEYBYTES.getBytes();
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args)
    {

    	//        final byte[] keyBytes = {0x11, 0x22, 0x4F, 0x58, (byte)0x88, 0x10, 0x40, 0x38
//                               , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
//                               , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2};    //24字节的密钥
        String keyBytes = "_t_1op0ppt_avmm8whsvu_o6";
        String szSrc = "1";
        
        System.out.println("加密前的字符串:" + szSrc);
        
        String orderinfo = new String(Base64.encode(DesUtil.encryptMode(keyBytes.getBytes(), szSrc.getBytes())));
        
        System.out.println("加密后的字符串:" + orderinfo);
        
        byte[] srcBytes;
		try {
			srcBytes = decryptMode(keyBytes.getBytes(), Base64.decode(new String(orderinfo).getBytes(ENCODING)));
			
			System.out.println("解密后的字符串:" + (new String(srcBytes)));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
