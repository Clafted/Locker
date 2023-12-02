package data.security.breaker;

import data.security.cipher.CaesarCipher;

/**
 * An implementation of the Breaker interface.
 * <br><br>
 * The MonoCaesarBreaker targets a Caesar Cipher,
 * and does not work for other encryption algorithms.
 * 
 * @see MonoAlphabeticBreaker
 * @author Noah Perez
 *
 */
public class CaesarBreaker implements Breaker{

	@Override
	public String breakKey(String cipherText, String plainText) {
		long startTime = System.nanoTime();
		var cipher = new CaesarCipher();
		String decryptedText;
		
		for(int i = 0; i < 26; i++) {
			System.out.println("Testing key: " + i);
			decryptedText = cipher.decrypt(plainText, i + "");
					
			if (decryptedText.equals(plainText)) {
				System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " ms");
				return i + "";
			}
		}
		return "Unbreakable...";
	}
}
