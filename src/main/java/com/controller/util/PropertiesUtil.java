package com.controller.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties properties;

    public static String getDBUrl(String dbType){
       return parseData(dbType,"url");
    }
    public static String getDBUser(String dbType){
       return parseData(dbType,"username");
    }
    public static String getDBPass(String dbType){
       return parseData(dbType,"password");
    }
    public static String getDBDriver(String dbType){

       return parseData(dbType,"drivername");
    }
    public static int getDBPort(String dbType){

        return Integer.parseInt(parseData(dbType,"port"));
    }

    public static String getRemoteUrl(String remoteType){
        return parseData(remoteType,"url");
    }


    private static String parseData(String dbType,String key){
        if (properties==null) {
            loadProperties();
        }
       return properties.getProperty(dbType+"."+key);
    }
    private static void loadProperties() {
        //InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties");
        //InputStream resourceAsStream = PropertiesUtil.class.getResourceAsStream("db.properties"); //拿不到资源
        //InputStream resourceAsStream = PropertiesUtil.class.getResourceAsStream("/" +"db.properties"); // 拿到资源
        InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties"); //拿到资源

        if (properties==null) {
            properties=new Properties();
        }
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        /*int port = PropertiesUtil.getDBPort("redis");
        System.out.println(port);*/
        //System.out.println(PropertiesUtil.class.getClassLoader().getResource("/com/xxj/utility/PropertiesUtil.class").getFile());
        String s1 = PropertiesUtil.getRemoteUrl("httpClientServlet");
        String s2 = PropertiesUtil.getRemoteUrl("httpUrlConnectionServlet");
        System.out.println(s1);
        System.out.println(s2);
    }
}
