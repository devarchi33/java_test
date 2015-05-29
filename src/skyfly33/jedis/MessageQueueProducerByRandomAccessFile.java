package skyfly33.jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import redis.clients.jedis.Jedis;

public class MessageQueueProducerByRandomAccessFile {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.20");

		try {
			// ��???��??��?���? ��??��??��.
			int seekSize = 1024;

			// ��???�? ������?��??���? ������?���? ��???��.
			RandomAccessFile rdma = new RandomAccessFile(
					"/Users/jeoos43/temp/audit_150401_1012041_1.log", "r");

			String line = "";

			// ??��??�����? ??�� 湲�??��
			System.out.println("rdma length : " + rdma.length());

			byte[] data = null;

			// ??��??�� ��??��??�� = ??��湲�??��/seekSize + (??��湲�??��%seekSize���? ����??�?���?�? 0��?????? 0���? �?���� �������?�? 1���? �?����.)
			long size = rdma.length() / seekSize
					+ (rdma.length() % seekSize == 0 ? 0 : 1);

			for (int i = 0; i < size; i++) {
				data = new byte[seekSize];

				rdma.seek(i * seekSize); // ��??��??�� ���移��??�? ��??���?.
				rdma.read(data); // ���? ��??��??�� ���移�������? ??�����? ���?��濡� �?���� �?����??�� �?�?��??�� 湲�??�� �?���� ��???�� ��??���?.

				// byte data ???�? ??��??����???�� �?����(trim()���? ��??��?������ ??��?��??����??�?.)
//				jedis.rpush("log", new String(data).trim());
				
				//byte�?�?��??�� ������??린��� ??���?�?.
				byte[] key = {'l','o','g'};
				jedis.rpush(key, data);

				jedis.close();
			}

			rdma.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
