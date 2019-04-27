package com.write.bmap;

/**
 *手写JDK1.7 HashMap 数组+单向链表
 */
public interface BrianMap<K,V> {

    //向集合中插入数据
    public V put(K k, V v);

    //从集合中取数据
    public V get(K k);

    //集合大小
    public int size();


    //Entry 
    interface Entry<K,V>{
        K getKey();

        V getValue();

        V setValue(V value);
    }

}
