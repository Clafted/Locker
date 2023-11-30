package data.security.breaker;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import data.security.MonoAlphabeticCipher;

public class MonoAlphabeticBreakerTest {
	
	String plainText = "The lazy BROWN FOX jumped OVER the fence.";
	String key = "pwodienqkasutrymnfgjxzcvbh";
	String cipherText = "Jqi uphb WFYCR EYV axtmid YZIF jqi eiroi.";
	
	@Test
	public void testMonoAlphabeticBreakerBreaksKey() {
		MonoAlphabeticBreaker breaker = new MonoAlphabeticBreaker();
		MonoAlphabeticCipher cipher = new MonoAlphabeticCipher();
		String brokenKey = breaker.breakKey(plainText, cipherText);
		
		assertTrue(cipher.decrypt(cipherText, brokenKey).equals(plainText));
	}
}
