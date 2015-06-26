package skyfly33.recursive;

/**
 * 
 * @author John resig (jquery creator).
 *
 */
public class Recursive {

	public static void main(String[] args) {
		Recursive p = new Recursive();
		String value = p.chirp(3);
		System.out.println("value : " + value);
	}

	public static String chirp(int num) {
		return (num > 1) ? chirp(num - 1) + "-chirp" : "chrip";
	}
}
