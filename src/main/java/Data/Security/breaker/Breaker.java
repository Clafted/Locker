package data.security.breaker;

public interface Breaker {
	public String breakKey(String data, String expectedOutput);
}
