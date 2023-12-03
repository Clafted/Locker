package data.security.cipher;

import static org.junit.Assert.assertTrue;

import org.junit.*;

public class XORCipherTest {

	private final String plainText = "26";
	private final String cipherText = ((char) 1) + "" + ((char) 5);
	
	private final String shortKey = "3";
	private final String longKey = "33333";
	
	@Test
	public void testXOREncryptwithShortKeyReturnsExpectedCiphertext() {
		var cipher = new XORCipher();
		assertTrue(cipherText.equals(cipher.encrypt(plainText, shortKey)));
	}
	

	@Test
	public void testXOREncryptwithLongKeyReturnsExpectedCiphertext() {
		var cipher = new XORCipher();
		assertTrue(cipherText.equals(cipher.encrypt(plainText, longKey)));
	}
	
	@Test
	public void testXORDecryptwithShortKeyReturnsExpectedPlaintext() {
		var cipher = new XORCipher();
		assertTrue(plainText.equals(cipher.decrypt(cipherText, shortKey)));
	}
	
	@Test
	public void testXORDecryptwithLongKeyReturnsExpectedPlaintext() {
		var cipher = new XORCipher();
		assertTrue(plainText.equals(cipher.decrypt(cipherText, longKey)));
	}
}
