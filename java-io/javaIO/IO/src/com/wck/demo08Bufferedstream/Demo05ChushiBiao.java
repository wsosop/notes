package com.wck.demo08Bufferedstream;


/**
 * 所需要的词
 3.侍中、侍郎郭攸之、费祎、董允等，此皆良实，志虑忠纯，是以先帝简拔以遗陛下。愚以为宫中之事，事无大小，悉以咨之，然后施行，必得裨补阙漏，有所广益。
 8.愿陛下托臣以讨贼兴复之效，不效，则治臣之罪，以告先帝之灵。若无兴德之言，则责攸之、祎、允等之慢，以彰其咎；陛下亦宜自谋，以咨诹善道，察纳雅言，深追先帝遗诏，臣不胜受恩感激。
 4.将军向宠，性行淑均，晓畅军事，试用之于昔日，先帝称之曰能，是以众议举宠为督。愚以为营中之事，悉以咨之，必能使行阵和睦，优劣得所。
 2.宫中府中，俱为一体，陟罚臧否，不宜异同。若有作奸犯科及为忠善者，宜付有司论其刑赏，以昭陛下平明之理，不宜偏私，使内外异法也。
 1.先帝创业未半而中道崩殂，今天下三分，益州疲弊，此诚危急存亡之秋也。然侍卫之臣不懈于内，忠志之士忘身于外者，盖追先帝之殊遇，欲报之于陛下也。诚宜开张圣听，以光先帝遗德，恢弘志士之气，不宜妄自菲薄，引喻失义，以塞忠谏之路也。
 9.今当远离，临表涕零，不知所言。
 6.臣本布衣，躬耕于南阳，苟全性命于乱世，不求闻达于诸侯。先帝不以臣卑鄙，猥自枉屈，三顾臣于草庐之中，咨臣以当世之事，由是感激，遂许先帝以驱驰。后值倾覆，受任于败军之际，奉命于危难之间，尔来二十有一年矣。
 7.先帝知臣谨慎，故临崩寄臣以大事也。受命以来，夙夜忧叹，恐付托不效，以伤先帝之明，故五月渡泸，深入不毛。今南方已定，兵甲已足，当奖率三军，北定中原，庶竭驽钝，攘除奸凶，兴复汉室，还于旧都。此臣所以报先帝而忠陛下之职分也。至于斟酌损益，进尽忠言，则攸之、祎、允之任也。
 5.亲贤臣，远小人，此先汉所以兴隆也；亲小人，远贤臣，此后汉所以倾颓也。先帝在时，每与臣论此事，未尝不叹息痛恨于桓、灵也。侍中、尚书、长史、参军，此悉贞良死节之臣，愿陛下亲之信之，则汉室之隆，可计日而待也。
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 御香烤翅
 * @create 2020-03-26 0:58
 *
 * 练习:  分析:
 *     对文本的内容进行排序按照(1,23顺序排序)
 * 	1创建一个HashMap集合对象,可以:存储每行文本的序号(1,2,3...) value:存储每行的文本
 * 	2.创建字符缓冲输入流对象,构造方法中绑定字符输入流
 * 	3.创建字符缓冲输出流对象,构造方法中绑定字符输出流
 * 	4.使用字符缓冲输入流中的方法readLine,逐行读取文本
 * 	5.对读取到的文本进行切割获取行中的序号和文本内容
 * 	6.把切割好的序号和文本的内容存储到HashMap集合中(key序号是有序的会自动排序1,2,3,4)
 * 	7.遍历 HashMap集合,获取每一个键值对
 * 	8.把每一个键值对,拼接为一个文本行
 * 	9.把拼接好的文本,使用字符缓冲输出流中的方法rite写入到文件中
 * 	10.释放资源
 */
public class Demo05ChushiBiao {

    public static void main(String[] args) throws IOException {

        //1创建一个HashMap集合对象,可以:存储每行文本的序号(1,2,3...) value:存储每行的文本
        HashMap<String, String> map = new HashMap<>();
        //2.创建字符缓冲输入流对象,构造方法中绑定字符输入流
        BufferedReader br = new BufferedReader(new FileReader("IO/chushibiao.txt"));
        //3.创建字符缓冲输出流对象,构造方法中绑定字符输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter("IO/chushibiao2.txt"));
        //4.使用字符缓冲输入流中的方法readLine,逐行读取文本
        String line=null;
        while ((line=br.readLine())!=null){
            //5.对读取到的文本进行切割获取行中的序号和文本内容
            String[] arr = line.split("\\.");
            //6.把切割好的序号和文本的内容存储到HashMap集合中(key序号是有序的会自动排序1,2,3,4)
            map.put(arr[0],arr[1]);
        }

        //7.遍历 HashMap集合,获取每一个键值对
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            //8.把每一个键值对,拼接为一个文本行
            String key = entry.getKey();
            String value = entry.getValue();
            String str=key+value;
            //9.把拼接好的文本,使用字符缓冲输出流中的方法rite写入到文件中
            bw.write(str);
            bw.newLine();
        }

        bw.close();
        br.close();


    }
}
