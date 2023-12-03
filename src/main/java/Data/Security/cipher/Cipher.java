package data.security.cipher;

/**
 * The Cipher class provides functionality for 
 * the encryption and decryption of a String of text.
 * 
 * @author Noah Perez
 *
 */
public interface Cipher {

	/**
	 * Encrypt the data using a given key
	 * 
	 * @param plainText the content to encrypt
	 * @param key the key which will be used to encrypt the content
	 * @return cipherText the resultant cipherText from encryption
	 */
	public String encrypt(String plainText, String key);
	
	/**
	 * Decrypt the data using a given key
	 * 
	 * @param cipherText  the content to decrypt
	 * @param key the key which will be used to decrypt the content
	 * @return plainTexxt the resultant plainText from decryption
	 */
	public String decrypt(String cipherText, String key);
}
