package com.write.blinklist;

/**
 * 手写LinkList
 * ####实现简单得LinkList
 */
public class BrianLinkList<E> {

    //链表大小
    private int size;
    //第一个元素(头节点)，查询从头节点开始
    private Node first;
    //最后一个元素(尾节点)，添加节点从尾节点开始
    private Node last;

    //add
    public void add(E e){
        //创建节点
        Node node = new Node();
        node.object = e;
        if(first == null) {
            //给第一个元素赋值node节点赋值
            first = node;
        }else {
            //添加第二个或以上元素
            node.prev = last;
            last.next = node;
        }
        last = node;
        size++;
    }
    //带下标添加
    public void add(int index,E e){
        Node newNode = new Node();
        newNode.object = e;
        Node node = getNode(index);
        if(node != null){

            if(isLast(node)){
                //尾部添加
                last = newNode;
                last.prev = node;
                node.next = last;

            }else if(isFirst(node)){
                //头部添加
                first = newNode;
                first.next = node;
                node.prev = first;
            }else{
                //
                newNode.prev = node.prev;
                newNode.next = node;
                newNode.prev.next = newNode;
                newNode.next.prev = newNode;
            }
            size++;
        }

    }

    //从头开始查效率比较低--linklist底层是用二分法查找
    public Object get(int index){
        return getNode(index).object;
    }

    private Node getNode(int index){
        //验证下标是否有越界
        checkElementIndex(index);
        Node node = null;
        if(first != null){
            node = first;//默认取第0个
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    private Node getNodeofAdd(int index){
        //验证下标是否有越界
        if(index <0 && index > size){
            throw new IndexOutOfBoundsException("添加下标越界");
        }
        Node node = null;
        if(first != null){
            node = first;//默认取第0个
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    private void checkElementIndex(int index){
        if(!isElementIndex(index)){
            throw new IndexOutOfBoundsException("查询下标越界了！");
        }
    }

    private boolean isElementIndex(int index){
        return index >= 0 && index < size;
    }

    //指定下标删除
    private void remove(int index){
        //获取需要删除的元素节点
        Node node = getNode(index);
        if(node != null){
            if(isFirst(node)){
                //如果是头节点
                first.next.prev = null;
                first = first.next;
            }else if(isLast(node)){
                //如果是尾节点
                last.prev.next = null;
            } else{
                //获取节点的上下节点
                Node next = node.next;
                Node prev = node.prev;
                next.prev = prev;
                prev.next = next;
                node.object = null;//将当前值置为null方便垃圾回收器回收
            }
            size--;
        }
    }

    private boolean isFirst(Node node){
       return node == first;
    }

    private boolean isLast(Node node){
           return node == last;
        }

    //链表节点元素
    class Node {
        //存放元素的值
        private Object object;
        //上一个节点
        private  Node prev;
        //下一个节点
        private Node next;
    }

    public static void main(String[] args) {
        BrianLinkList<String> list = new BrianLinkList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        //list.remove(0);
        list.add("牛");
       // list.add(4,"牛");
        for (int i = 0; i < list.size; i++) {
            System.out.println(list.get(i));
        }
    }


}
