package com.wck.mybatis.test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;

public class SelectFileList {
	
	public static void main(String[] args) {
		File file=new File("G:\\mybatis\\视频2");
		File[] files=file.listFiles();
		Map<String, String> maps=new HashMap<String, String>();
//		System.out.println(Arrays.toString(files));
		for(File f:files) {
			String name=f.getName();
//			System.out.println(s.substring(0, s.indexOf(".",s.indexOf(".")+1 )));
			String key=name.substring(0, name.indexOf(".", name.indexOf(".")));
			maps.put(key, name);
		}
		
		System.out.println(sortMapByKey(maps));
		
	}
	
    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
//        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);//升序排序
            }
        });
        sortMap.putAll(map);
        return sortMap;
    }
    

}
