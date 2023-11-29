package data.security;

public interface EncryptorDecryptor {

	/**
	 * Encrypt the data using a given key
	 * 
	 * @param data the content to encrypt
	 * @param key the key which will be used to encrypt the content
	 * @return
	 */
	public String encrypt(String data, String key);
	
	/**
	 * Decrypt the data using a given key
	 * 
	 * @param data  the content to decrypt
	 * @param key the key which will be used to decrypt the content
	 * @return
	 */
	public String decrypt(String data, String key);
}
