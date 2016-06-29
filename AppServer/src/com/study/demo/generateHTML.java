package com.study.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class generateHTML {

    public static String FILE_PATH = System.getProperty("user.dir") + File.separator + "OneMenu"
            + File.separator + "demo" + File.separator;
    public static String FILE_NAME = "test.html"; // A file in your filesystem

    public static void main(String[] args) {
        try {
            String title = "Order";
            String customerName = "Simon";
            String address = "39 Barlin Street Apt 2053 State College, PA, 16803";
            String phone = "13800138000";
            String preOrder = "Leave required pickup or delivery time and day here";
            String bill = "<tr> <td>Test</td> <td>+ test</td> <td>1.00</td> </tr>";

            // 模板路径
            String filePath = FILE_PATH + FILE_NAME;
            System.out.print(filePath);

            String templateContent = "";
            FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
            int lenght = fileinputstream.available();
            byte bytes[] = new byte[lenght];
            fileinputstream.read(bytes);
            fileinputstream.close();
            templateContent = new String(bytes);
            System.out.print("original html:\n" + templateContent);

            templateContent = templateContent.replaceAll("###title###", title);
            templateContent = templateContent.replaceAll("###customerName###", customerName);
            templateContent = templateContent.replaceAll("###address###", address);
            templateContent = templateContent.replaceAll("###phone###", phone);
            templateContent = templateContent.replaceAll("###preOrder###", preOrder);
            templateContent = templateContent.replaceAll("###bill###", bill);
            System.out.print("new html:\n" + templateContent);

            // 根据时间得文件名
            Calendar calendar = Calendar.getInstance();
            String fileame = String.valueOf(calendar.getTimeInMillis()) + ".html";
            fileame = FILE_PATH + fileame;// 生成的html文件保存路径。
            FileOutputStream fileoutputstream = new FileOutputStream(fileame);// 建立文件输出流
            System.out.print("文件输出路径:");
            System.out.print(fileame);

            byte tag_bytes[] = templateContent.getBytes();

            fileoutputstream.write(tag_bytes);
            fileoutputstream.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }


    }

}
