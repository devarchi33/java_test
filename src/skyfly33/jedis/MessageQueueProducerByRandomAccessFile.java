package skyfly33.jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import redis.clients.jedis.Jedis;

public class MessageQueueProducerByRandomAccessFile {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.20");

		try {
			// ï¿½ï¿½???ï¿½ë??ï¿½ã?¼ï¿½ï¿? ï¿½ï¿½??ï¿½ë??ï¿½ï¿½.
			int seekSize = 1024;

			// ï¿½ï¿½???ë¦? ï¿½ï¿½ï¿½ï¿½ï¿½â?¹ï¿½??°ï¿½ï¿? ï¿½ï¿½ï¿½ï¿½ï¿½ì?±ï¿½ï¿? ï¿½ï¿½???ï¿½ï¿½.
			RandomAccessFile rdma = new RandomAccessFile(
					"/Users/jeoos43/temp/audit_150401_1012041_1.log", "r");

			String line = "";

			// ??¾ë??ï¿½ï¿½ï¿½ï¿½ï¿? ??¥ï¿½ æ¹²ë??ï¿½ï¿½
			System.out.println("rdma length : " + rdma.length());

			byte[] data = null;

			// ??·â??ï¿½ï¿½ ï¿½ï¿½??ï¿½ë??ï¿½ï¿½ = ??¥ï¿½æ¹²ë??ï¿½ï¿½/seekSize + (??¥ï¿½æ¹²ë??ï¿½ï¿½%seekSizeï¿½ï¿½ï¿? ï¿½ï¿½ï¿½ç??ëª?ï¿½ï¿½åª?ï¿? 0ï¿½ï¿½?????? 0ï¿½ï¿½ï¿? è«?ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï§?ï¿? 1ï¿½ï¿½ï¿? è«?ï¿½ï¿½ï¿½ï¿½.)
			long size = rdma.length() / seekSize
					+ (rdma.length() % seekSize == 0 ? 0 : 1);

			for (int i = 0; i < size; i++) {
				data = new byte[seekSize];

				rdma.seek(i * seekSize); // ï¿½ï¿½??ï¿½ë??ï¿½ï¿½ ï¿½ï¿½ï¿½ç§»ï¿½å??ï¿? ï¿½ï¿½??¼ï¿½ï¿?.
				rdma.read(data); // ï¿½ï¿½ï¿? ï¿½ï¿½??ï¿½ë??ï¿½ï¿½ ï¿½ï¿½ï¿½ç§»ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿? ??ºï¿½ï¿½ï¿½ï¿? ï¿½ï¿½ëª?ï¿½ï¿½æ¿¡ï¿½ è«?ï¿½ï¿½ï¿½ï¿½ è«?ï¿½ï¿½ï¿½ë??ï¿½ï¿½ è«?ê³?ï¿½ë??ï¿½ï¿½ æ¹²ë??ï¿½ï¿½ ï§?ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½???ï¿½ï¿½ ï¿½ï¿½??¼ï¿½ï¿?.

				// byte data ???ï¿? ??¾ë??ï¿½ï¿½ï¿½ï¿½???ï¿½ï¿½ è¹?ï¿½ï¿½ï¿½ï¿½(trim()ï¿½ï¿½ï¿? ï¿½ï¿½??ï¿½â?ºï¿½ï¿½ï¿½ï¿½ï¿½ ??¨ë?¬ê??ï¿½ï¿½ï¿½å??ï¿?.)
//				jedis.rpush("log", new String(data).trim());
				
				//byteè«?ê³?ï¿½ë??ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½â??ë¦°ï¿½ï¿½ï¿½ ??°ï¿½åª?ï¿?.
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
