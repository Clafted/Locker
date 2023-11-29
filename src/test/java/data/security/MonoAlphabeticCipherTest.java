package data.security;

import static org.junit.Assert.assertTrue;
import org.junit.*;

public class MonoAlphabeticCipherTest {
	
	String plainText = "The lazy BROWN FOX jumped OVER the fence.";
	String key = "pwodienqkasutrymnfgjxzcvbh";
	String cipherText = "Jqi uphb WFYCR EYV axtmid YZIF jqi eiroi.";
	
	@Test
	public void testEncryptMatchesExpectedOutput() {
		MonoAlphabeticCipher cipher = new MonoAlphabeticCipher();
		assertTrue(cipher.encrypt(plainText, key).equals(cipherText));
	}
	
	@Test
	public void testDecryptMatchesExpectedOutput() {
		MonoAlphabeticCipher cipher = new MonoAlphabeticCipher();
		assertTrue(cipher.decrypt(cipherText, key).equals(plainText));
	}
}
