package data.security.cipher;

/**
 * An implementation of EncryptorDecryptor interface.
 * <br><br>
 * The Caesar Cipher is a symmetric encryption algorithm
 * which shifts every character by the number specified
 * by the given key.
 * 
 * @author Noah Perez
 *
 */
public class CaesarCipher implements EncryptorDecryptor {

	public String encrypt(String data, String key) throws NumberFormatException {

		int shiftValue = Integer.parseInt(key);
		return shift(data, shiftValue);
	}

	public String decrypt(String data, String key) throws NumberFormatException {

		int shiftValue = Integer.parseInt(key);
		return shift(data, -shiftValue);
	}
	
	/**
	 * Use the Caesar Cipher method to shift all characters with the given shift value
	 * 
	 * @param data the content to shift
	 * @param shift the value by which the content will be shifted
	 * @return
	 */
	private String shift(String data, int shift) {
		
		int initialLength = data.length();
		int shiftedAscii = 0;
		
		// Iterate through each character
		for(int i = 0; i < initialLength; i++) {
			
			/*
			 * 1. Convert character to int ASCII value
			 * 2. Shift ASCII value down by 32 (ASCII characters 0-32 are not visible)
			 * 3. Modulo by 96 (characters range from 32 to 127)
			 * 4. Add 96 if current value is negative to stay within range
			 * 5. Add 32 to return to actual ASCII value
			 * 6. Convert ASCII value to character and append
			 */
			shiftedAscii = ((int)data.charAt(i) - 32 + shift) % 96;
			shiftedAscii += (shiftedAscii < 0 ? 96 : 0) + 32;
			data += (char)shiftedAscii;
		}
		
		return data.substring(initialLength, initialLength * 2);
	}
}
