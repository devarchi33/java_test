package skyfly33.net.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

class AdvancedEchoThread extends Thread {
	
	private Socket sock;
	private int mCount;
	
	public AdvancedEchoThread(Socket sock){
		this.sock = sock;
		mCount = 0;
	}
	
	public void quit() {
		if(sock != null){
			try {
				sock.close();
				sock = null;
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		try {
			InetAddress inetAddr = sock.getInetAddress();
			System.out.println(inetAddr.getHostAddress() + "�� �����Ͽ����ϴ�.");
			
			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "MS949"));
			String line = null;
			try {
				while((line = br.readLine())!=null){
					System.out.println("Ŭ���̾�Ʈ�� ���� ���۹��� ���ڿ� : " + line);
					pw.println(line);
					pw.flush();
					
					if(line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")){
						this.quit();
					}
				}
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("Timeout Exception : " + mCount);
			} finally{
				System.out.println(inetAddr.getHostAddress() + "�� ������ �����Ͽ����ϴ�");
				pw.close();
				br.close();
				sock.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
}