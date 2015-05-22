package test.jedis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import redis.clients.jedis.Jedis;

public class MessageQueueProducerByRandomAccessFile {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.20");

		try {
			// ��쎌�대�ㅼ�� ��ъ�댁��.
			int seekSize = 1024;

			// ��쎄린 �����⑹�쇰�� �����쇱�� ��쎌��.
			RandomAccessFile rdma = new RandomAccessFile(
					"/Users/jeoos43/temp/audit_150401_1012041_1.log", "r");

			String line = "";

			// 臾몄����� 珥� 湲몄��
			System.out.println("rdma length : " + rdma.length());

			byte[] data = null;

			// 猷⑦�� ��ъ�댁�� = 珥�湲몄��/seekSize + (珥�湲몄��%seekSize��� ���癒몄��媛� 0��대㈃ 0��� 諛���� ������硫� 1��� 諛����.)
			long size = rdma.length() / seekSize
					+ (rdma.length() % seekSize == 0 ? 0 : 1);

			for (int i = 0; i < size; i++) {
				data = new byte[seekSize];

				rdma.seek(i * seekSize); // ��ъ�명�� ���移�媛� ��ㅼ��.
				rdma.read(data); // ��� ��ъ�명�� ���移������� 遺���� ��몄��濡� 諛���� 諛���댄�� 諛곗�댁�� 湲몄�� 留���� ��쎌�� ��ㅼ��.

				// byte data 瑜� 臾몄����대�� 蹂����(trim()��� ��ъ�⑺����� 怨듬갚���嫄�.)
//				jedis.rpush("log", new String(data).trim());
				
				//byte諛곗�대�� �����↔린��� 異�媛�.
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
