package data.security.breaker;

import java.util.HashMap;

import data.security.MonoAlphabeticCipher;

public class MonoAlphabeticBreaker implements Breaker{

	public String breakKey(String data, String expectedOutput) {
		
		MonoAlphabeticCipher cipher = new MonoAlphabeticCipher();
		HashMap<Integer, Integer> deconstructedKey = new HashMap<>();
		String key = "";
		
		double startTime = System.nanoTime();
		
		while(!data.equals(expectedOutput)) {
			deconstructedKey.clear();
			key = "";
			while(deconstructedKey.size() < 26) {
				deconstructedKey.put((int) (Math.random() * 26), 1);
			}
			
			for(Integer letter : deconstructedKey.keySet()) {
				key += (char) letter.intValue();
			}
			
			System.out.println("Testing key: " + key);
			expectedOutput = cipher.decrypt(data, key);
		}
		
		System.out.println("\nElapsed Time: " + ((System.nanoTime() - startTime) / 1000000000)
				+ "\nFinal Key: " + key);
		return key;
	}
	
}
