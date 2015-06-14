package skyfly33.net;

import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class WhoIsQuery {

	public static void main(String[] args) throws Exception {
		String domainNameToCheck = "abcnews.com	";
		try {
			performWhoisQuery("whois.internic.net", 43, domainNameToCheck);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void performWhoisQuery(String host, int port, String query)
			throws Exception {
		System.out.println("***** Performing whois query for '" + query
				+ "' at " + host + ":" + port);

//		Socket socket = new Socket(host, port);
		Socket socket = new Socket();
		SocketAddress socketAddress = new InetSocketAddress(host,port);
		int timeout = 2000;
		socket.connect(socketAddress, timeout);

		/*Writer out = new OutputStreamWriter(socket.getOutputStream(),
				"ISO-8859-1");
		out.write(query);
		out.write("\r\n");
		out.flush();*/
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(query);

		InputStreamReader isr = new InputStreamReader(socket.getInputStream());
		BufferedReader br = new BufferedReader(isr);

		String line = "";
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		
		socket.close();
	}
}
