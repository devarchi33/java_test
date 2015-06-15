package skyfly33.net.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ServerThread extends Thread {
	private Boolean loop;
	private final List<AdvancedEchoThread> threadList;
	private final ServerSocket server;
	
	public ServerThread(int port) throws IOException {
		super();
		server = new ServerSocket(port);
		server.setSoTimeout(3000);
		
		threadList = new ArrayList<AdvancedEchoThread>();
		loop = true;
	}
	
	@Override
	public void run() {
		System.out.println("접속을 기다립니다.");
		
		while(loop){
			try {
				Socket sock = server.accept();
				AdvancedEchoThread thread = new AdvancedEchoThread(sock);
				thread.start();
				threadList.add(thread);
			}  catch(IOException e){
				//System.out.println("Thread를 시작하지 못했습니다." + e.toString());
			}
		}
	}
	
	public void destroy() {
		loop = false;
		for (int i = 0; i < threadList.size(); i++) {
			AdvancedEchoThread t = threadList.remove(i);
			t.quit();
			t.interrupt();
			if(t.isAlive()){
				try {
					sleep(1000);	
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
