package data.security.cipher;

public class CipherFactory {
	
	public static Cipher makeCipher(String subclass) {
		
		if(subclass.equals("caesar"))
			return new CaesarCipher();
		else if (subclass.equals("monoalphabetic"))
			return new MonoAlphabeticCipher();
		else
			return null;
	}
}
