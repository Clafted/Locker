package data.security.breaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import data.security.cipher.MonoAlphabeticCipher;

import java.util.Set;

/**
 * An implementation of the Breaker interface.
 * <br><br>
 * The MonoAlphabeticBreaker targets a MonoAlphabetic Cipher,
 * and does not work for other encryption algorithms.
 * 
 * @see CaesarBreaker
 * @author Noah Perez
 *
 */
public class MonoAlphabeticBreaker implements Breaker{

	public static final double[] charFrequency = {
			0.0812, 0.0149, 0.0271, 0.0432, 0.1202, 0.023, 
			0.0203, 0.0592, 0.0731, 0.001, 0.0069, 0.0398, 
			0.00261, 0.0695, 0.0768, 0.0182, 0.0011, 0.0692, 
			0.0628, 0.091, 0.0288, 0.0209, 0.0017, 0.0211, 0.0007
	}; 

	@Override
	public String breakKey(String cipherText, String plainText) {

		var cipher = new MonoAlphabeticCipher();
		char randomLetter;
		var usedKeys = new HashMap<String, Byte>();
		var key = "";
		var decryptedText = "";

		double startTime = System.nanoTime();
		
		int[] charCount = calculateCharCount(cipherText);
		
		// Iterates until the key is found
		while((System.nanoTime() - startTime) / 1000000000 < 600) {
			key = "";

			/* Adds a randomly generated number to represent
			 * an ASCII value to the HashMap until 26 keys
			 * have been added.
			 */
			while(key.length() < 26) {
				randomLetter = (char) ((int)(Math.random() * 26) + 65);
				if (key.indexOf(randomLetter) == -1) {
					key += randomLetter;
				}
			}

			key = key.toUpperCase();
			if (usedKeys.containsKey(key)) {
				continue;
			} else {
				System.out.println("Testing key: " + key);
				decryptedText = cipher.decrypt(cipherText, key);

				if (decryptedText.equals(plainText)) {
					System.out.println("\nElapsed Time: " + ((System.nanoTime() - startTime) / 1000000000) + "\nFinal Key: " + key);
					return key;
				} else {
					usedKeys.put(key, (byte)0);
				}
			}
		}

		// Print the amount of time taken to correctly determine the key
		System.out.println("\nElapsed Time: " + ((System.nanoTime() - startTime) / 1000000000));
		return "Not worth breaking...";
	}

	/**
	 * Counts how often each English letter appears in the
	 * given String of text.
	 * <br><br>
	 * The counts are  and is ordered
	 * from A to Z.
	 * 
	 * @param text the text from which the letters are counted
	 * @return an array of doubles representing the frequency of
	 * each English letter in whole numbers.
	 */
	public int[] calculateCharCount(String text) {
		var encryptedFrequencies = new int[26];
		int currentCharASCII;

		// Counts the frequency of each letter in the cipherText
		for (int i = 0; i < 26; i++) {
			currentCharASCII = (int) text.charAt(i);

			// Convert lower-case characters to upper-case
			if (currentCharASCII > 91) {
				currentCharASCII -= 32; 
			}
			encryptedFrequencies[currentCharASCII - 65]++;
		}

		return encryptedFrequencies;
	}

	public Object getRandomWeightedValue(HashMap<Object, Integer> weightedValues) {

		var inversedHashMap = new HashMap<Integer, ArrayList<Object>>();
		for (Entry<Object, Integer> entry : weightedValues.entrySet()) {
			if (!inversedHashMap.containsKey(entry.getValue())) {
				inversedHashMap.put(entry.getValue(), new ArrayList<Object>());
			}
			inversedHashMap.get(entry.getValue()).add(entry.getKey());
		}
		
		int sumOfWeights = 0;
		for(Integer weight : inversedHashMap.keySet()) {
			sumOfWeights += weight;
		}
		
		int randomIndex = (int) (Math.random() * weightedValues.size());
		ArrayList<Object> target;
		
		for (Integer weight : inversedHashMap.keySet()) {
			if (randomIndex <= weight) {
				target = inversedHashMap.get(weight);
				return target.get((int) (Math.random() * target.size()));
			} else {
				randomIndex -= weight;
			}
		}
		return null;
	}
}
