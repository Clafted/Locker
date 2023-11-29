package data.security;

public class EncryptorDecryptorFactory {
	
	public static EncryptorDecryptor makeEncryptorDecryptor(String subclass) {
		
		if(subclass.equals("caesar"))
			return new CaesarCipher();
		else if (subclass.equals("monoalphabetic"))
			return new MonoAlphabeticCipher();
		else
			return null;
	}
}
