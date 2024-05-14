package com.solumesl.aims.saas.adapter.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class SaasSecurity {

	private static Logger logger = LoggerFactory.getLogger(SaasSecurity.class);

	private static byte[] key = { 0x24, 0x44, 0x60, 0x56, 0x11, 0x73, 0x30, 0x53, 0x69, 0x67, 0x08, 0x19, 0x74, 0x4b, 0x65, 0x37 };
	private final static String AES_ALGORITHM = "AES";


	public static String encrypt(String strToEncrypt){
		try{
			StringBuffer sb = new StringBuffer(strToEncrypt);
			strToEncrypt = sb.reverse().toString();
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
			return encryptedString;
		}
		catch (Exception e)
		{
			logger.error("Error while encrypting", e);
		}
		return "";

	}

	public static String decrypt(String strToDecrypt){
		try{

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
			StringBuffer sb = new StringBuffer(decryptedString);
			return sb.reverse().toString();

		} catch (Exception e){
			logger.error("Error while decrypting", e);
		}
		return "";
	}

	public static void main(String... args){
		String encryptVal = encrypt("Solum@2022");
		System.out.println(encryptVal);
		System.out.println(decrypt("PaOm7WH4UhV1oQmBbQFnuQ=="));
	}

}
