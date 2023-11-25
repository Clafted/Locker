package Data.Security;

public interface EncryptorDecryptor<T, K> {

	public T encrypt(T data, K key);
	public T decrypt(T data, K key);
}
