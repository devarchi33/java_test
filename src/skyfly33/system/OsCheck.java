package skyfly33.system;

public class OsCheck {

	public static void main(String[] args){
		System.out.println("os.name : " + System.getProperty("os.name"));
		System.out.println("os.arch : " + System.getProperty("os.arch"));
		System.out.println("os.version"
				+ " : " + System.getProperty("os.version"));
	}
}
