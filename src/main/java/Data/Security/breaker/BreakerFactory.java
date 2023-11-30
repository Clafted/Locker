package data.security.breaker;

public class BreakerFactory {
	public Breaker getBreaker(String subclass) {
		if (subclass.equals("caesar")) {
			return null;
		} else if (subclass.equals("monoalphabetic")) {
			return new MonoAlphabeticBreaker();
		} else {
			return null;
		}
	}
}
