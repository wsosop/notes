package com.wck.demo07Prop;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * @author 御香烤翅
 * @create 2020-03-25 21:06
 *
 * java.util.Properties extends Hashtable<K,V> implements Map<K,V>
 *     Properties类表示一组持久的属性。 Properties可以保存到流中或从流中加载。
 *     Properties集合是一个唯一和IO流相结合的集合
 *          可以使用Properties集合中的方法store,把集合中的临时数据，持久化写入到硬盘中存储
 *          可以使用Properties集合中的方法load,把硬盘中保存的文件（键值对），读取到集合中使用
 *
 *     属性列表中的每个键及其对应的值都是一个字符串。
 *          Properties集合是一个双列集合，key和value默认都是字符串
 *
 */
public class Demo01Properties {

    public static void main(String[] args) throws IOException {
        show03();
    }

    /**
     * 可以使用Properties集合中的方法load,把硬盘中保存的文件（键值对），读取到集合中使用
     *      void load(InputStream inStream)
     *      void load(Reader reader)
     *              参数：
     *                  InputStream inStream：字节输入流，不能读取含有中文的键值对
     *                  Reader reader：字符输入流，能读取含有中文的键值对
     *      使用步骤：
     *          1.创建Properties集合对象
     *          2.使用Properties集合对象的load方法读取保存键值对的文件
     *          3.遍历Properties集合
     *              注意：
     *                  1.存储键值对的文件中，键与值默认的连接符号可以使用 = ，空格 或者其他符号
     *                  2.存储键值对的文件中，可以使用# 进行注释，被注释的键值对不会再被读取
     *                  3.存储键值对的文件中，键与值默认都是字符串，不用再加双引号
     */
    private static void show03() throws IOException {

        //1.创建Properties集合对象
        Properties properties = new Properties();
        //2.使用Properties集合对象的load方法读取保存键值对的文件
        properties.load(new FileReader("IO/g.txt"));
        //3.遍历Properties集合
        Set<String> set = properties.stringPropertyNames();
        for (String key : set) {
            String value = properties.getProperty(key);
            System.out.println("key:"+key+",value:"+value);
        }
    }

    /**
     * 可以使用Properties集合中的方法store,把集合中的临时数据，持久化写入到硬盘中存储
     *      void store(OutputStream out, String comments)
     *      void store(Writer writer, String comments)
     *      参数：
     *         OutputStream out:字节输出流，不能传入中文
     *         Writer writer: 字符输出流，可以写中文
     *         String comments：注释，用来解释，这个保存的文件是干甚么的用的，
     *                          不能使用中文，会产生乱码，默认是Unicode的编码
     *                          一般使用空字符串
     *  使用步骤：
     *          1.创建Properties集合对象，添加数据
     *          2.创建字节输出流、字符输出流对象，构造方法中传入所要保存的目的地
     *          3.使用Properties集合中的方法store,把集合中的临时数据，持久化写入到硬盘中存储
     *          4.关闭释放资源
     */
    private static void show02() throws IOException {
        //1.创建Properties集合对象，添加数据
        Properties properties = new Properties();
        properties.setProperty("wck","1");
        properties.setProperty("wck2","2");
        properties.setProperty("wck3","3");
        properties.setProperty("wck4","4");
        properties.setProperty("中文","5");
        //2创建字节输出流、字符输出流对象，构造方法中传入所要保存的目的地
//        FileOutputStream fos = new FileOutputStream("IO/g.txt");
        FileWriter fw = new FileWriter("IO/g.txt", true);
        //3使用Properties集合中的方法store,把集合中的临时数据，持久化写入到硬盘中存储
        properties.store(fw,"Common2");
        //4关闭释放资源
        fw.close();


    }


    /**
     * 使用Properties集合存储数据，遍历取出Properties集合中的数据
     * Properties集合是一个双列集合，key和value默认都是字符串
     *      Properties集合有一些操作字符串的特有方法
     *          Object setProperty(String key, String value) 调用 Hashtable方法 put 。
     *          String getProperty(String key) 通过key找到value值，此方法相当于Map的get(key)方法
     *          Set<String> stringPropertyNames() 返回此属性列表中的一组键，其中键及其对应的值为字符串，此方法相当于Map集合的keySet方法
     *
     */
    private static void show01() {

        Properties properties = new Properties();
        //使用setProperty设置值
        properties.setProperty("张三","170");
        properties.setProperty("李四","171");
        properties.setProperty("王五","172");
        properties.setProperty("赵六","173");

        //使用stringPropertyNames把Properties集合中的键取出，存储到一个Set集合中
        Set<String> set = properties.stringPropertyNames();

        //遍历set集合，取出properties集合的每一个键

        for (String string : set) {
            //使用 String getProperty(String key) 来获取值
            String property = properties.getProperty(string);
            System.out.println(string +"="+ property);

        }

    }


}
