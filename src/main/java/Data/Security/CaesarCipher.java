package Data.Security;

/**
 * An implementation of EncryptorDecryptor which
 * uses a Caesar Cipher Encryption method to
 * encrypt/decrypt messages.
 * 
 * @author noahm
 *
 */
public class CaesarCipher implements EncryptorDecryptor {


	public String encrypt(String data, String key) throws NumberFormatException {

		int initialLength = data.length();
		int shiftValue = Integer.parseInt(key);
		
		for(int i = 0; i < initialLength; i++)
			data += (char)((((int)data.charAt(i) - 32 + shiftValue) % 96) + 32) + " ";

		return data.substring(initialLength, data.length());
	}

	public String decrypt(String data, String key) throws NumberFormatException {

		String[] tokens = data.split(" " );
		int shiftValue = Integer.parseInt(key);
		
		String result = "";
		int shiftedInt = 0;
		
		for (String token : tokens) {
			
			if(token.length() == 0) continue;
			
			shiftedInt = (((int)token.charAt(0) - 32 - shiftValue) % 96) + 32;
			if (shiftedInt < 0) shiftedInt += 128;
			
			result += (char)shiftedInt;
		}
		
		return result;
	}
}
