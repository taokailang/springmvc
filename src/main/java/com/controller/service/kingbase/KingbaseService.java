package com.controller.service.kingbase;

import com.controller.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class KingbaseService {

    private String url = PropertiesUtil.getDBUrl("kb");

    private String dbuser = PropertiesUtil.getDBUser("kb");

    private String dbpassword = PropertiesUtil.getDBPass("kb");

    static {
        try {
            Class<?> kingbase = Class.forName(PropertiesUtil.getDBDriver("kb"));
            System.out.println("kingbase = " + kingbase);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void runCRUD() throws Exception {

        Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
        System.out.println("connection = " + connection);
        System.out.println(connection.getMetaData().getDatabaseProductVersion());

        //带预编译的新增
        String sql2 = "insert into tb_user2(username,password) values(?,?)";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setString(1, "dahuang1");
        preparedStatement2.setString(2, "123456");
        preparedStatement2.executeUpdate();
        System.out.println("insert ok");
        preparedStatement2.close();


        //带预编译的查询
        String sql1 = "select count(username) from tb_user2 where username=? and password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        System.out.println("preparedStatement = " + preparedStatement.getClass());
        System.out.println("preparedStatement = " + preparedStatement.getClass());
        preparedStatement.setString(1, "dahuang1");
        preparedStatement.setString(2, "123456");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("query： " + resultSet.next());
        preparedStatement.close();


        //delete  execute
        String sql3 = "delete from tb_user2 where username='xadsdeq'";
        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        preparedStatement3.execute();
        System.out.println("delete ok");
        preparedStatement3.close();

        //update
        String sql4 = "update  tb_user2 set username=? where username=?";
        PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
        preparedStatement4.setString(1, "xxxxx");
        preparedStatement4.setString(2, "dahuang1");
        preparedStatement4.executeUpdate();
        System.out.println("update ok");
        preparedStatement4.close();

        //非预编译
        Statement statement1 = connection.createStatement();
        System.out.println(statement1.getClass().getName());
        ResultSet resultSet3 = statement1.executeQuery("select  * from tb_user2");
        resultSet3.next();
        statement1.close();
        System.out.println("statement1.executeQuery："+resultSet3);

        Statement statement2 = connection.createStatement();
        int resultInt = statement2.executeUpdate("update  tb_user2 set username='aaa'");
        System.out.println("statement2.executeUpdate："+resultInt);
        statement2.close();

        Statement statement3 = connection.createStatement();
        boolean resultSet6 = statement3.execute("select count(*) from tb_user2");
        System.out.println("statement3.execute select："+resultSet6);
        statement3.close();


        Statement statement4 = connection.createStatement();
        boolean resultSet8 = statement4.execute("insert into tb_user2(username,password) values('aaa','123455')");
        System.out.println("statement4.execute insert："+resultSet8);
        statement4.close();


        Statement statement5 = connection.createStatement();
        boolean resultSet7 = statement5.execute("update  tb_user2 set username='bbbbb' where username='aaa' and password='123455'");
        System.out.println("statement5.execute update："+resultSet7);
        statement5.close();

        Statement statement6 = connection.createStatement();
        boolean resultSet9 = statement6.execute("delete from tb_user2 where username='cccc'");
        System.out.println("statement6.execute delete："+resultSet9);
        statement6.close();

        connection.close();
    }



}
