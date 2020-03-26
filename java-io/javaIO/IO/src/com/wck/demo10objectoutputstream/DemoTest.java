package com.wck.demo10objectoutputstream;

import org.ietf.jgss.Oid;

import java.io.*;
import java.util.ArrayList;

/**
 * @author 御香烤翅
 * @create 2020-03-26 18:11
 *
 * 1. 将存有多个自定义对象的集合序列化操作，保存到`list.txt`文件中。
 * 2. 反序列化`list.txt` ，并遍历集合，打印对象信息。
 *
 * ### 分析
 *
 * 1. 把若干学生对象 ，保存到集合中。
 * 2. 把集合序列化。
 * 3. 反序列化读取时，只需要读取一次，转换为集合类型。
 * 4. 遍历集合，可以打印所有的学生信息
 */
public class DemoTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person("wck1",18));
        personArrayList.add(new Person("wck2",19));
        personArrayList.add(new Person("wck20",20));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IO/list.txt"));
        oos.writeObject(personArrayList);


        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("IO/list.txt"));
        Object object = ois.readObject();
        ArrayList<Person> list= (ArrayList<Person>) object;
        System.out.println(list);
        ois.close();
        oos.close();

    }
}
