package skyfly33.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LookupAll {

	public static void main(String[] args) {
		InetAddress addr[] = null;

		try {
			addr = InetAddress.getAllByName(args[0]);
			for (InetAddress each : addr) {
				System.out.println("Name : " + each.getHostName());
				System.out.println("Addr : " + each.getHostAddress());
				System.out
						.println("Canonical : " + each.getCanonicalHostName());
			}
		} catch (UnknownHostException e) {
			// TODO: handle exception
			System.out.println("Name : " + args[0]);
		}
	}
}
