package skyfly33.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressLookUp {

	public static void main(String[] args) {
		InetAddress[] addr = null;
		try {
			for (int i = 0; i < args.length; i++) {
				addr = InetAddress.getAllByName(args[i]);
				System.out.println("Name : " + addr[i].getHostName());
				System.out.println("Addr : " + addr[i].getHostAddress());
				System.out.println();
			}
		} catch (UnknownHostException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
