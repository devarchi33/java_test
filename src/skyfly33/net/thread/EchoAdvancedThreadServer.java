package skyfly33.net.thread;

public class EchoAdvancedThreadServer {
	
	public static void main(String[] args){
		try {
			ServerThread echoThread =  new ServerThread(6000);
			echoThread.start();
			
			//�Ϲ� �����尡 ����ɶ� ���� ����Ѵ�. (�ȵ���̵忡���� ���۵�)
			echoThread.join();
			System.out.println("Completed shutdown");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Interrupted before accept thread complted.");
			System.exit(1);
		}
	}
}