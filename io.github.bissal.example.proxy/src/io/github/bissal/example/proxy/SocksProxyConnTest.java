package io.github.bissal.example.proxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class SocksProxyConnTest {

	public static void testConnect(String destIp, int destPort, String proxyIp, int proxyPort) {
		BufferedReader reader = null;
		BufferedWriter writer = null;

		try {
			SocketAddress proxyAddress = new InetSocketAddress(proxyIp, proxyPort);
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);

			Socket socket = new Socket(proxy);
			SocketAddress remote = new InetSocketAddress(destIp, destPort);

			socket.connect(remote);

			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			writer.write("Hellllllooooooooo:");
			writer.flush();

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line + "\n");
			}

			System.out.println("connect success!! " + remote);
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Properties prop = loadProperties();
		String destIp	 = prop.getProperty("dest.ip");
		String destPort	 = prop.getProperty("dest.port");
		String proxyIp	 = prop.getProperty("proxy.ip");
		String proxyPort = prop.getProperty("proxy.port");

		testConnect(destIp, Integer.parseInt(destPort), proxyIp, Integer.parseInt(proxyPort));
	}

	private static Properties loadProperties() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			prop.load(input);

			Set<Entry<Object, Object>> entrySet = prop.entrySet();

			for (Entry<Object, Object> entry : entrySet) {
				System.out.println(entry);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return prop;
	}
}
