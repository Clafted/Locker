package data.security.breaker;

/**
 * A class to create different implementations of the Breaker interface.
 * 
 * @author Noah Perez
 */
public class BreakerFactory {
	
	/**
	 * Returns an object of the specified Breaker implementation.
	 * If the given subclass name does not match any of the supported
	 * implementations of the Breaker interface, null will be returned.
	 * 
	 * @param type the type of the object to be returned
	 * @return an object of the specified type if supported, null otherwise 
	 */
	public static Breaker getBreaker(String type) {
		if (type.equals("caesar")) {
			return new CaesarBreaker();
		} else if (type.equals("monoalphabetic")) {
			return new MonoAlphabeticBreaker();
		} else {
			return null;
		}
	}
}
