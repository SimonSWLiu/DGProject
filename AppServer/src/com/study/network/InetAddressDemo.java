package com.study.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 演示InetAddress类的基本使用
 */
public class InetAddressDemo {
	public static void main(String[] args) {
		try {
			
			getHostNameAddress("www.163.com");
			getHostNameAddress("127.0.0.1");
			
			// 获得本机地址对象
			InetAddress inet3 = InetAddress.getLocalHost();
			System.out.println("LocalHost InetAddress -- > " + inet3);
			// 获得对象中存储的域名
			String host = inet3.getHostName();
			System.out.println("LocalHost name --> " + host);
			// 获得对象中存储的IP
			String ip = inet3.getHostAddress();
			System.out.println("LocalHost IP --> " + ip);
		} catch (Exception e) {
		}
	}

	static void getHostNameAddress(String url) throws UnknownHostException {

		// 获得本机地址对象
		InetAddress inet = InetAddress.getByName(url);
		System.out.println(url + " InetAddress -- > " + inet);
		// 获得对象中存储的域名
		String host = inet.getHostName();
		System.out.println(url + " Host name --> " + host);
		// 获得对象中存储的IP
		String ip = inet.getHostAddress();
		System.out.println(url + " IP --> " + ip);

	}
}
