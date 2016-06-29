package com.study.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.onemenu.server.util.AwsS3Utils;
import com.onemenu.server.util.PropertiesUtils;

// /Users/simonliu/Documents/workspace/OneMenuProjectWorkspace/OneMenuServerProject/src/AwsCredentials.properties
// /Users/simonliu/Documents/workspace/OneMenuProjectWorkspace/OneMenuServerProject/AwsCredentials.properties
public class TestProperties {

    public static void main(String[] args) {



        // String fileName = "/AwsCredentials.properties";
        // URL classPath = Object.class.getResource(fileName);
        // System.out.println(classPath);
        // InputStream is = Object.class.getResourceAsStream(fileName);
        // Properties props = new Properties();
        // props.load(is);
        // System.out.println(props.getProperty("secretKey"));
        // System.out.println(props.getProperty("accessKey"));
        //
        // String fileName2 = "AwsCredentials.properties";
        // URL classPath2 = ClassLoader.getSystemResource(fileName2);
        // System.out.println(classPath2);
        // InputStream is2 = ClassLoader.getSystemResourceAsStream(fileName2);
        // Properties props2 = new Properties();
        // props2.load(is2);
        // System.out.println(props2.getProperty("secretKey"));
        // System.out.println(props2.getProperty("accessKey"));

        // TODO Auto-generated method stub
        String prepertiesFile = "AwsCredentials.properties";
        String secretKey = PropertiesUtils.getProperties("secretKey", prepertiesFile);
        System.out.println("result: " + secretKey);


    }
}
