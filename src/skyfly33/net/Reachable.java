package skyfly33.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Reachable {

	public static void main(String[] args) {
		InetAddress thisComputer = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Enter name and IP address. Enter \"exit\" to quit.");
		try {
			while (true) {
				String host = in.readLine();
				if (host.equalsIgnoreCase("exit")
						|| host.equalsIgnoreCase("quit")) {
					break;
				}

				try {
					thisComputer = InetAddress.getByName(host);
					System.out.println(thisComputer.getHostAddress());

					if (thisComputer.isReachable(2000))
						System.out.printf("%s is reachable \n",
								thisComputer.getHostName());
					else
						System.out.printf("%s is unreachable \n",
								thisComputer.getHostName());
				} catch (UnknownHostException e) {
					// TODO: handle exception
					System.out.println("Cannot find host " + host);
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}

	}
}
