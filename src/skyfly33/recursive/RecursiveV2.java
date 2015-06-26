package skyfly33.recursive;

/**
 * 
 * @author skyfly33
 *
 */
public class RecursiveV2 {

	public static void main(String[] args) {
		RecursiveV2 re = new RecursiveV2();
		String value = re.printChar(3);
		System.out.println("value : " + value);

	}

	public static String printChar(int a) {

		String chirp = null;
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < a; i++) {
			if (i == 0)
				chirp = "chirp";
			else if (i > 0)
				chirp = "-chirp";
			builder.append(chirp);
		}

		return builder.toString();
	}
}
