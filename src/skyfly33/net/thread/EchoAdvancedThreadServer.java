package skyfly33.net.thread;

public class EchoAdvancedThreadServer {
	
	public static void main(String[] args){
		try {
			ServerThread echoThread =  new ServerThread(6000);
			echoThread.start();
			
			//일반 스레드가 종료될때 까지 대기한다. (안드로이드에서는 오작동)
			echoThread.join();
			System.out.println("Completed shutdown");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Interrupted before accept thread complted.");
			System.exit(1);
		}
	}
}