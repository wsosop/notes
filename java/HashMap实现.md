## HashMap 

hashMap 实际上是一个数组加上链表实现的

实现的有三个 java 文件 ,分别为 :

1. Map.java ：Map的接口
2. HashMap.java ：HashMap的简单实现类
3. TestHahsMap.java：测试hashMap的类

```java
package com.wck;
/**
 * @author 御香烤翅
 * @create 2019-11-29 22:41
 * Map的接口
 */
public interface Map<K,V> {
    //put 方法
    public V put(K k,V v);
    //get 方法
    public V get(K k);
    //返回大小
    public int size();
    //内部类 Entry
    public interface Entry<K,V>{
        //返回key
        public K getKey();
        //返回value
        public V getValue();
   }
}

```

```Java
package com.wck;
/**
 * @author 御香烤翅
 * @create 2019-11-29 22:45
 * hashMap的具体简单实现
 */
public class HashMap<K,V> implements Map<K,V>{
    //容量大小
    private static  int defaultLength=16;
    //默认的扩容因子
    private static  double defaultLoader=0.75;
    private Entry<K,V>[] table=null;
    private int size=0;
    public HashMap(int defaultLength,double defaultLoader){
        defaultLength=defaultLength;
        defaultLoader=defaultLoader;
        table=new Entry[defaultLength];
    }
    public HashMap(){
       this(defaultLength,defaultLoader);
    }
    //获取数组的下标
    public int getIndex(K k){
        int m=defaultLength;
        int index=k.hashCode()%m;
        return index>=0?index:-index;
    }
    //创建新的Entry
    public Entry<K,V> newEntry(K k,V v,Entry<K,V> next){

        return new Entry<K,V>(k,v,next);
    }
    @Override
    public V put(K k, V v) {
        int index=getIndex(k);
        Entry entry=table[index];
        //当entry 为空的时候，没有出现冲突的问题，直接可以赋值
        if(entry == null){
            table[index]= newEntry(k,v,null);//把当前的Entry放入进数组中
            size++;//数组的size 加1
            System.out.println("第一次走进来");
        }else {//出现了hash碰撞
            //把原来的Entry放入到next中去，把新的值放入到数组对用的index中去
            table[index]= newEntry(k,v,entry);
            System.out.println("第二次走进来:"+table[index].getValue());
            return table[index].getValue();
        }
        return null;
    }
    //通过遍历Entry 查找value
    public V findValueByEqualKey(K k,Entry<K,V> entry){
       if(k==entry.getKey() || k.equals(entry.getKey())){
           return entry.getValue();
       }else if(entry.next !=null){
           return findValueByEqualKey(k,entry.next);
       }
        return null;

    }
    @Override
    public V get(K k) {
        int index=getIndex(k);
        if(table[index] == null){//没有值返回 null
            return null;
        }
        return findValueByEqualKey(k,table[index]);
    }
    @Override
    public int size() {
        return 0;
    }
    class Entry<K,V> implements Map.Entry{
        K k;
        V v;
        Entry<K,V> next;
        public Entry(K k,V v,Entry<K,V> next){
            this.k=k;
            this.v=v;
            this.next=next;
        }
        @Override
        public K getKey() {
            return k;
        }
        @Override
        public V getValue() {
            return v;
        }
    }
}

```

