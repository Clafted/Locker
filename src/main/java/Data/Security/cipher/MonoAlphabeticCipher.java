package data.security.cipher;

/**
 * An implementation of the Cipher interface.
 * <br><br>
 * The MonoAlphabeticCipher is a symmetric encryption algorithm which
 * uses a key of 26 letters and maps each letter a given plainText
 * accordingly.
 * 
 * @author Noah Perez
 *
 */
public class MonoAlphabeticCipher implements Cipher{

	public String encrypt(String data, String key) {
		return cipher(data, key, true);
	}

	public String decrypt(String data, String key) {
		return cipher(data, key, false);
	}

	private String cipher(String data, String key, boolean encrypt) {

		if (key.length() != 26) return data;

		int initialLength = data.length();
		key = key.toUpperCase();
		char[] capKeySet = key.toCharArray();
		char[] lowKeySet = key.toLowerCase().toCharArray();

		// Shift every letter in the data
		int asciiValue;
		for(int i = 0; i < initialLength; i++) {

			asciiValue = (int)data.charAt(i);
			
			if (asciiValue > 122 
					|| asciiValue < 65 
					|| Math.abs(asciiValue - 93.5) <= 2.5) {
				data += data.charAt(i);
			} else if (encrypt == true) {
				if (asciiValue < 91) {
					data += capKeySet[asciiValue - 65];
				} else {
					data += lowKeySet[asciiValue - 97];
				}
			} else {
				if (asciiValue < 91) {
					data += (char) (key.indexOf((char) asciiValue) + 65);
				} else {
					data += (char) (key.indexOf((char) (asciiValue - 32)) + 97);
				}
			}
		}

		return data.substring(initialLength, initialLength * 2);
	}

}
