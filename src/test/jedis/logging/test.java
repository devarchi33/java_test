package test.jedis.logging;


public class test {
	static String osName = System.getProperty("os.name");
	static String homePath = System.getProperty("user.home");

	public static void main(String[] args) {
		test t = new test();
		t.getDesktopPath(); 
		String temp = osName.startsWith("M") ? homePath.concat("/Desktop") : homePath.concat("\\Desktop");
		System.out.println("temp : " +  temp);
	}

	public void getDesktopPath() {

		if (osName.startsWith("m") || osName.startsWith("M")
				|| osName.startsWith("l") || osName.startsWith("L")) {
			System.out.println(homePath + "/Desktop");
		} else if (osName.startsWith("W") || osName.startsWith("w")) {
			System.out.println(homePath + "\\Desktop");
		}
	}
}
