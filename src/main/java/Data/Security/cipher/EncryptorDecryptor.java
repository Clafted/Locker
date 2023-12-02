package data.security.cipher;

/**
 * The EncryptorDecryptor class provides functionality for 
 * the encryption and decryption of a String of text.
 * 
 * @author Noah Perez
 *
 */
public interface EncryptorDecryptor {

	/**
	 * Encrypt the data using a given key
	 * 
	 * @param data the content to encrypt
	 * @param key the key which will be used to encrypt the content
	 * @return cipherText the resultant cipherText from encryption
	 */
	public String encrypt(String data, String key);
	
	/**
	 * Decrypt the data using a given key
	 * 
	 * @param data  the content to decrypt
	 * @param key the key which will be used to decrypt the content
	 * @return plainTexxt the resultant plainText from decryption
	 */
	public String decrypt(String data, String key);
}
