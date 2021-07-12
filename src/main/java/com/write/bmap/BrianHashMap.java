package com.write.bmap;

/**
 * 手写HashMap
 * @param <K>
 * @param <V>
 */
public class BrianHashMap<K,V>  implements  BrianMap<K,V>{

    //定义table 存放HashMap 初始为空  用到时再new
    Node<K,V>[] table = null;

    //table的大小
    private int size;

    //负载因子 负载因子的值越小，hash冲突的概率越小
    float DEFAULT_LOAD_FACTOR = 0.75f;

    //table初始大小
    private int DEFAULT_INITIAL_CAPACITY = 16;

    @Override
    public V put(K k, V v) {
        //1.判断table是否为空，为空就new一个
        if(table == null){
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        //2.判断数组是否需要扩容

        //扩容条件 实际大小 >= 负载因子 * 初始大小
        if(size >= DEFAULT_LOAD_FACTOR*DEFAULT_INITIAL_CAPACITY){
                rsetSize();
        }


        //3.计算hash值指定下标位置
        int index = getIndex(k, table.length);
        Node<K, V> node = table[index];
        if(node == null){
            //没有下标index冲突,也即是没有node节点
            node = new Node<>(k,v,null);
            size++;
        }else{
            Node<K,V> newNode = node;
            while(newNode != null){
                //有下标index冲突
                if(newNode.getKey().equals(k) || newNode.getKey() == k){
                    //hashCode相同 key equals也相同表示同一个对象
                    return newNode.setValue(v);
                }else{
                    //hashCode相同 key不同表示不同的对象
                    //新的node的next节点就是原来的node
                    //遍历到最后一个node
                    if(newNode.next == null){
                        node = new Node<>(k,v,node);
                        size++;
                    }

                }
                newNode = newNode.next;
            }
        }
        table[index] = node;
        return null;
    }

    //table扩容
    private void rsetSize(){
        //1.生成新的table是原来的2倍
        Node<K,V>[] newTables = new Node[table.length << 1];
        //2.重新计算index的索引，存放发哦新的table里面
        for (int i = 0; i < table.length ; i++) {
            Node<K, V> oldNode = table[i];

            while(oldNode != null){
                table[i] = null;//通知回收垃圾
                //重新计算index
                int newIndex = getIndex(oldNode.key, newTables.length);
                Node<K, V> oldNext = oldNode.next;
                //如果index在新的table中发生index冲突，则以链表存储
                oldNode.next = newTables[newIndex];
                //将之前table的node赋值给新的table
                newTables[newIndex] = oldNode;
                //判断是否继续遍历
                oldNode = oldNext;

            }
        }
        //3.给新的table赋值
        table = newTables;
        DEFAULT_INITIAL_CAPACITY = newTables.length;
        newTables = null;


    }

     //打印hashMap全部元素
     public void print(){
         for (int i = 0; i < table.length; i++) {
             System.out.print("Map集合下标位置:" + i + " <<>> ");
             Node<K, V> node = table[i];
             while(node != null){
                 System.out.print(" {key: " + node.key + "- value: " + node.value + "} ");
                 node = node.next;
             }
             System.out.println();
         }
     }

    private int getIndex(K k,int length){
        int hashCode = k.hashCode();
        return  hashCode % length;

    }

    @Override
    public V get(K k) {
        Node<K, V> node = table[getIndex(k, table.length)];

        Node<K, V> resNode = getNode(node, k);
        return resNode == null ? null: resNode.value;

    }

    private Node<K,V> getNode(Node<K,V> node,K k){
        while(node != null){
            if(node.key.equals(k) || node.key == k){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }


    //定义节点 单向链表
    class Node<K,V> implements Entry<K,V>{

        //集合的key
        private K key;
        //集合的value
        private V value;
        //下一个节点
        private Node<K,V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            //设置新值的时候返回老的值
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
