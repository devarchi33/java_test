package test.json;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenseByJSON {

	static Logger logger = LoggerFactory.getLogger(LicenseByJSON.class);

	public static void main(String[] args) {
		LicenseByJSON lj = new LicenseByJSON();
		String licenseInfo = lj.getMacHostNameFilePathInfo();
		System.out.println("라이센스 정보 : " + licenseInfo);
		String licenseInfo2 = lj.getLicenseInfoByJSON();
		System.out.println("라이센스 정보2 : " + licenseInfo2);
	}

	// 라이센스 체크를 위한 mac address, host name, file path 조합.
	public String getMacHostNameFilePathInfo() {
		String hostAddr = "";
		String hostName = "";
		String hostMac = "";

		// String fullPath = Config.getInstance().getProperties("jagent.home");
		String fullPath = "/home/wsql";
		String arrays[] = fullPath.split("/");
		String path = "";

		try {
			InetAddress[] local = InetAddress.getAllByName(InetAddress
					.getLocalHost().getHostName());
			hostAddr = local[0].getHostAddress();
			hostName = local[0].getHostName();
			path = "/" + arrays[1] + "/" + arrays[2];

			/* NetworkInterface를 이용하여 현재 로컬 서버에 대한 하드웨어 어드레스를 가져오기 */
			NetworkInterface ni = NetworkInterface.getByInetAddress(local[0]);
			byte[] mac = ni.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				hostMac += String.format("%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : "");
			}

			logger.info("@IP 정보 : " + local[0].getHostAddress());
			logger.info("@호스트 이름 : " + local[0].getHostName());
			logger.info("@MAC 정보 : " + hostMac);
			logger.info("@설치 경로 : " + path);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return hostName + hostMac + path;
	}

	@SuppressWarnings("unchecked")
	public String getLicenseInfoByJSON() {
		String hostName = "";
		String hostMac = "";
		String fullPath = "";

		try {
			// host name 구하기.
			InetAddress[] local = InetAddress.getAllByName(InetAddress
					.getLocalHost().getHostName());
			hostName = local[0].getHostName();

			// MacAddress 구하기.
			NetworkInterface ni = NetworkInterface.getByInetAddress(local[0]);
			byte[] mac = ni.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				hostMac += String.format("%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : "");
			}

			// 설치 경로 구하기.
			fullPath = "/home/wsql";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}

		JSONObject jobj = new JSONObject();

		jobj.put("host name", hostName);
		jobj.put("mac address", hostMac);
		jobj.put("install path", fullPath);

		String licenseInfo = jobj.toJSONString();

		return licenseInfo;
	}
}
