package data.security.breaker;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import data.security.cipher.MonoAlphabeticCipher;

public class MonoAlphabeticBreakerTest {
	
	String plainText = "The lazy BROWN FOX jumped OVER the fence.";
	String key = "PWODIENQKASUTRYMNFGJXZCVBH";
	String cipherText = "Jqi uphb WFYCR EYV axtmid YZIF jqi eiroi.";
	

	public void testMonoAlphabeticBreakerBreaksKey() {
		MonoAlphabeticBreaker breaker = new MonoAlphabeticBreaker();
		MonoAlphabeticCipher cipher = new MonoAlphabeticCipher();
		
		String brokenKey = breaker.breakKey(cipherText, plainText);
		assertTrue(cipher.decrypt(cipherText, brokenKey).equals(plainText));
	}
}
