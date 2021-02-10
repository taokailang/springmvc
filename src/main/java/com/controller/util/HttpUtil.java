package com.controller.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * Created by lubov on 2018/10/13.
 */
public class HttpUtil {

    public static String httpPost(String url, String param) {
        String result = null;
    	PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("Charset","GBK");

//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 添加超时时间
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            System.out.println(Arrays.toString(param.getBytes()));
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常: "+e);
            result = null;
        }
        finally{
            try{
                if(out!=null){out.close();}
                if(in!=null){in.close();}
            }
            catch(IOException ex){
                System.out.println(ex);
            }
        }
        return result;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String encoding = "GB2312";
        String testParameter = JSON.toJSONString("{'app_name':'中文主机流'}");
        System.out.println(Arrays.toString(testParameter.getBytes("UTF-8")));

        testParameter = new String(testParameter.getBytes("UTF-8"),encoding);
        testParameter = new String(testParameter.getBytes(encoding),"UTF-8");
//        System.out.println(Arrays.toString(testParameter.getBytes(encoding)));
//        System.out.println(URLEncoder.encode(testParameter));
//        System.out.println(URLDecoder.decode(testParameter,"utf-8"));
        System.out.println(Arrays.toString(testParameter.getBytes(encoding)));
//        String returnJson = httpPost("http://localhost:8080/test/post",testParameter);
//        System.out.println(returnJson);
    }




}
