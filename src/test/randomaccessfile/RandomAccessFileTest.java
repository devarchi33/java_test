package test.randomaccessfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

	public static void main(String[] args) {

		try {
			// 읽어들일 사이즈.
			int seekSize = 5;

			// 읽기 전용으로 파일을 읽음.
			RandomAccessFile rdma = new RandomAccessFile(
					"/Users/jeoos43/temp/randomaccessfile_test.txt", "r");

			String line = "";

			// 문자열 총 길이
			System.out.println("rdma length : " + rdma.length());

			byte[] data = null;

			// 루프 사이즈 = 총길이/seekSize + (총길이%seekSize의 나머지가 0이면 0을 반환 아니면 1을 반환.
			long size = rdma.length() / seekSize
					+ (rdma.length() % seekSize == 0 ? 0 : 1);

			for (int i = 0; i < size; i++) {
				data = new byte[seekSize];

				rdma.seek(i * seekSize); //포인터 위치값 설정.
				rdma.read(data); //현 포인터 위치에서 부터 인자로 받은 바이트 배열의 길이 만큼 읽어 들임.

				// byte data 를 문자열로 변환(trim()을 사용하여 공백제거.)
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
