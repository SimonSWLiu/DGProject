package com.onemenu.server.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.onemenu.server.javabean.ienum.CommonReturnType;



public class WebUtil {
    private Logger mLogger = Logger.getLogger(WebUtil.class);

    private static WebUtil sWebUtil;

    private WebUtil() {
        super();
    }

    public static WebUtil getInstance() {
        if (sWebUtil == null) {
            sWebUtil = new WebUtil();
        }
        return sWebUtil;
    }

    public String getWebInputStream(int connectTimeOut, int readTimeOut, String url) {

        try {
            URL url1 = new URL(url); // 取得资源对象
            HttpURLConnection uc = (HttpURLConnection) url1.openConnection(); // 生成连接对象
            uc.setUseCaches(false);
            uc.setConnectTimeout(connectTimeOut);
            uc.setReadTimeout(readTimeOut);
            uc.connect(); // 发出连接

            String temp;
            final BufferedReader in =
                    new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
            final StringBuffer sb = new StringBuffer();
            try {
                while ((temp = in.readLine()) != null) {
                    // mLogger.error("开始读取数据");
                    // Thread.sleep(20000);
                    // sb.append("\n");
                    sb.append(temp);
                }
                return sb.toString();

            } catch (IOException e) {
                mLogger.error("WebUtil-->读取数据时出现异常");
                mLogger.error("异常信息", e);

            } finally {
                in.close();

            }
        } catch (SocketTimeoutException e) {
            mLogger.error("连接超时, 发送短信提醒");
            mLogger.error("异常信息", e);
            return CommonReturnType.TIMEOUT.toString();


        } catch (FileNotFoundException e) {
            mLogger.error("出现404错误, 发送短信提醒");
            mLogger.error("异常信息", e);
            return CommonReturnType.NOTFOUND.toString();

        } catch (Exception e) {
            mLogger.error("WebUtil-->error");
            mLogger.error("异常信息", e);


        }

        return CommonReturnType.FAILED.toString();

    }

    public static void main(String[] args) {
        WebUtil webUtil = new WebUtil();
        String result =
                webUtil.getWebInputStream(30000, 30000,
                        "http://gotheme.3g.cn/golauncherthemefactory/main/jspController/welcome.html?locale=zh_CN");

    }
}
