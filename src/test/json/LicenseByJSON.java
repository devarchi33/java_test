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
		System.out.println("���̼��� ���� : " + licenseInfo);
		String licenseInfo2 = lj.getLicenseInfoByJSON();
		System.out.println("���̼��� ����2 : " + licenseInfo2);
	}

	// ���̼��� üũ�� ���� mac address, host name, file path ����.
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

			/* NetworkInterface�� �̿��Ͽ� ���� ���� ������ ���� �ϵ���� ��巹���� �������� */
			NetworkInterface ni = NetworkInterface.getByInetAddress(local[0]);
			byte[] mac = ni.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				hostMac += String.format("%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : "");
			}

			logger.info("@IP ���� : " + local[0].getHostAddress());
			logger.info("@ȣ��Ʈ �̸� : " + local[0].getHostName());
			logger.info("@MAC ���� : " + hostMac);
			logger.info("@��ġ ��� : " + path);
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
			// host name ���ϱ�.
			InetAddress[] local = InetAddress.getAllByName(InetAddress
					.getLocalHost().getHostName());
			hostName = local[0].getHostName();

			// MacAddress ���ϱ�.
			NetworkInterface ni = NetworkInterface.getByInetAddress(local[0]);
			byte[] mac = ni.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				hostMac += String.format("%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : "");
			}

			// ��ġ ��� ���ϱ�.
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
