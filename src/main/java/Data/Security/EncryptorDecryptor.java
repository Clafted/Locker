package Data.Security;

public interface EncryptorDecryptor {

	public String encrypt(String data, String key);
	public String decrypt(String data, String key);
}
