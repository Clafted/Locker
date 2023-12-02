package data.security.breaker;

/**
 * The Breaker interface represents any class which determines
 * the key of an encryption algorithm using a specified set of
 * operations.
 * <br><br>
 * It is not used to find the key for ciphertext whose plaintext
 * is unknown, and is solely used to provide a general idea of
 * how strong an encryption algorithm is against an attacker.
 * 
 * @author Noah Perez
 *
 */
public interface Breaker {
	
	/**
	 * The only method of the Breaker interface.
	 * <br><br>
	 * breakKey(String data, String expectedOutput) is used to
	 * to test how easily an encryption algorithm can be broken
	 * into.
	 * <br><br>
	 * Using this method requires the plaintText of a given
	 * cipherText, and thus cannot be used to decrypt any piece
	 * of encrypted data.
	 * @param cipherText the cipherText to be decrypted
	 * @param plainText the plainText to determine success
	 * @return the key for the algorithm
	 */
	public String breakKey(String cipherText, String plainText);
}
