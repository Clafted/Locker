package Data.Security;

public class CaesarCipher implements EncryptorDecryptor<String, Integer>{

	
	public String encrypt(String data, Integer key) {
		
		for(int i = 0; i < data.length(); i++)
			data += (char)((int)data.charAt(i) + key);
		
		return data.substring(data.length() / 2, data.length());
	}

	public String decrypt(String data, Integer key) {
		
		for(int i = 0; i < data.length(); i++)
			data += (char)((int)data.charAt(i) - key);
		
		return data.substring(data.length() / 2, data.length());
	}

}
