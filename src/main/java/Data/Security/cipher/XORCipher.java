package data.security.cipher;

/**
 * An implementation of the Cipher interface.
 * <br><br>
 * The XOR Cipher is a symmetric algorithm in which each 
 * byte a String of data is converted by applying the
 * XOR operator on it, with the given key.
 * 
 * @author Noah Perez
 *
 */
public class XORCipher implements Cipher{

	@Override
	public String encrypt(String plainText, String key) {
		byte[] encryptedBytes = XOR(plainText.getBytes(), key.getBytes());
		
		// Convert bytes into an array of chars
		var encryptedChars = new char[encryptedBytes.length];
		for(int i = 0; i < encryptedBytes.length; i++) {
			encryptedChars[i] = (char) encryptedBytes[i];
		}
		return String.valueOf(encryptedChars);
	}

	@Override
	public String decrypt(String cipherText, String key) {
		// The XOR operator works in both ways with the same key
		return encrypt(cipherText, key);
	}
	
	public byte[] XOR(byte[] byteStream, byte[] key) {
		// Apply XOR operator on each byte 
		for (int i = 0; i < byteStream.length; i += key.length) {
			// Use key byte(s) on the targeted bytes
			for (int j = 0; j < key.length && (j + i) < byteStream.length; j++) { 
				byteStream[i + j] ^= key[j];
			}
		}
		return byteStream; 
	}
	
}
