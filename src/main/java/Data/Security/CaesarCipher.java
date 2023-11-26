package Data.Security;

/**
 * An implementation of EncryptorDecryptor which
 * uses a Caesar Cipher Encryption method to
 * encrypt/decrypt messages.
 * 
 * @author noahm
 *
 */
public class CaesarCipher implements EncryptorDecryptor {

	
	public String encrypt(String data, String key) throws NumberFormatException {
		
		return caesarCipher(data, Integer.parseInt(key));
	}

	public String decrypt(String data, String key) throws NumberFormatException {
		
		return caesarCipher(data, -(Integer.parseInt(key)));
	}

	public String caesarCipher(String data, int shiftValue) {
		
		for(int i = 0; i < data.length(); i++)
			data += (char)((int)data.charAt(i) + shiftValue);
		
		return data.substring(data.length() / 2, data.length());
	}
}
