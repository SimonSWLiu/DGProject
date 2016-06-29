package com.study.demo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class testURL {


    /**
     * 获取URL指定的资源。
     * 
     * @throws IOException
     */
    public static void getName() throws IOException {
        URL url =
                new URL(
                        "https://s3-us-west-2.amazonaws.com/s3-onemenu/resources/restaurant-res/A0DC-1422846413618/1422207801941_1620_911.jpeg");
        // 获得此 URL 的内容。
        Object obj = url.getContent();
        System.out.println(obj.getClass().getName());
    }

    /**
     * 获取URL指定的资源
     * 
     * @throws IOException
     */
    public static void getInputStream() throws IOException {
        URL url =
                new URL(
                        "https://s3-us-west-2.amazonaws.com/s3-onemenu/resources/restaurant-res/A0DC-1422846413618/1422207801941_1620_911.jpeg");
        // 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
        URLConnection uc = url.openConnection();
        // 打开的连接读取的输入流。
        InputStream in = uc.getInputStream();
        int c;
        while ((c = in.read()) != -1)
            System.out.print(c);
        in.close();
    }

    /**
     * 读取URL指定的网页内容
     * 
     * @throws IOException
     */
    public static void getInputSteam2() throws IOException {
        URL url =
                new URL(
                        "https://s3-us-west-2.amazonaws.com/s3-onemenu/resources/restaurant-res/A0DC-1422846413618/1422207801941_1620_911.jpeg");
        // 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
        Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
        reader.close();
    }

    /**
     * 获取URL的输入流，并输出
     * 
     * @throws IOException
     */
    public static void getInputStream3() throws IOException {
        URL url =
                new URL(
                        "https://s3-us-west-2.amazonaws.com/s3-onemenu/resources/restaurant-res/A0DC-1422846413618/1422207801941_1620_911.jpeg");
        // 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
        InputStream in = url.openStream();
        int c;
        while ((c = in.read()) != -1)
            System.out.print(c);
        in.close();
    }

    public static void testUrlSupport() {

        String host = "www.java2s.com";
        String file = "/index.html";

        String[] schemes =
                {"http", "https", "ftp", "mailto", "telnet", "file", "ldap", "gopher", "jdbc",
                        "rmi", "jndi", "jar", "doc", "netdoc", "nfs", "verbatim", "finger",
                        "daytime", "systemresource"};

        for (int i = 0; i < schemes.length; i++) {
            try {
                URL u = new URL(schemes[i], host, file);
                System.out.println(u);
                System.out.println(schemes[i] + " is supported\r\n");
            } catch (Exception ex) {
                System.out.println(schemes[i] + " is not supported\r\n");
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // testUrlSupport();
        try {
            // getName();
            // getInputStream();
            getInputSteam2();
            // getInputStream3();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
