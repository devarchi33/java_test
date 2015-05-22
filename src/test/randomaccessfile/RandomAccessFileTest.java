package test.randomaccessfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

	public static void main(String[] args) {

		try {
			// �о���� ������.
			int seekSize = 5;

			// �б� �������� ������ ����.
			RandomAccessFile rdma = new RandomAccessFile(
					"/Users/jeoos43/temp/randomaccessfile_test.txt", "r");

			String line = "";

			// ���ڿ� �� ����
			System.out.println("rdma length : " + rdma.length());

			byte[] data = null;

			// ���� ������ = �ѱ���/seekSize + (�ѱ���%seekSize�� �������� 0�̸� 0�� ��ȯ �ƴϸ� 1�� ��ȯ.
			long size = rdma.length() / seekSize
					+ (rdma.length() % seekSize == 0 ? 0 : 1);

			for (int i = 0; i < size; i++) {
				data = new byte[seekSize];

				rdma.seek(i * seekSize); //������ ��ġ�� ����.
				rdma.read(data); //�� ������ ��ġ���� ���� ���ڷ� ���� ����Ʈ �迭�� ���� ��ŭ �о� ����.

				// byte data �� ���ڿ��� ��ȯ(trim()�� ����Ͽ� ��������.)
				System.out.printf("pointer : %02d  str : %s \n",
						rdma.getFilePointer(), new String(data).trim());
			}

			rdma.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
