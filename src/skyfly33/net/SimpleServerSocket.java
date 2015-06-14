package skyfly33.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleServerSocket {

	public static void main(String[] args) throws IOException {
		final int portNumber = 6000;
		System.out.println("Creating server socket on port " + portNumber);
		ServerSocket ss = new ServerSocket(portNumber);

		while (true) {
			Socket socket = ss.accept();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("What's your name ?");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String str = br.readLine();

			pw.println("Hello, " + str);
			socket.close();

			System.out.println("Just said hello to : " + str);
		}
	}
}
