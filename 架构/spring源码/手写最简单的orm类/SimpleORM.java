package com.wck.simple.orm;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 御香烤翅
 * @create 2020-02-23 22:52
 */
public class SimpleORM {

    public static void main(String[] args) throws Exception{
        System.out.println("SimpleORM...");

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/163course?characterEncoding=UTF-8&rewriteBatchedStatements=true",
                "root","123456");

        PreparedStatement statement=conn.prepareStatement("select * from t_book");
        ResultSet rs=statement.executeQuery();
//        private Integer bookid;
//        private String bookname;
//        private Double price;

        Class<?> book=Book.class;
        List<Object> listResult=new ArrayList<>();

        while(rs.next()){
//            System.out.println(rs.getMetaData());
            Object instance=book.newInstance();
            //所包含的列数
            int count=rs.getMetaData().getColumnCount();
            for(int i=1;i<=count;i++){
                String columnName=rs.getMetaData().getColumnName(i);
//                rs.getInt(rs.getMetaData().getColumnName(i));
                Field field=book.getDeclaredField(columnName);
                field.setAccessible(true);
//                System.out.println("field.getName()："+field.getName());
                Object type=field.getType();

//                System.out.println("返回的类型："+type+",列名:"+columnName);

                if(type == Integer.class){
                    field.set(instance,rs.getInt(columnName));
                }else if(type == String.class){
                    field.set(instance,rs.getString(columnName));
                }else if(type == Double.class){
                    field.set(instance,rs.getDouble(columnName));
                }else{
                    //其他的类型
                }
            }
            listResult.add(instance);
            //System.out.println("bookid:"+rs.getInt("bookid"));
        }
        System.out.println(listResult);
        rs.close();
        statement.close();
        conn.close();
    }



}
