package com.write.blist;

import java.util.Arrays;

/**
 * 手写ArrayList集合--底层是数组存放
 *
 * 核心API
 * ####1.Arrays.copyOf 数组扩容
 * ####2.System.arraycopy 数组拷贝
 */
public class BrianArrayList<E>  implements BrianList<E>{

    //底层是数组存放
    private Object[] elementData;

    //集合实际大小
    private int size;

    //初始化数组大小
    public BrianArrayList(int initialCapacity) {
        if(initialCapacity <= 0){
            try {
                throw  new Exception("初始化大小不能小于0！！！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.elementData = new Object[initialCapacity];
    }

    //默认的初始大小为10
    public BrianArrayList() {
        this(10);
    }

    public void add(E e){
        //1.判断是否需要扩容大小
        ensureExplicitCapacity(size+1);
        //2.通过下标赋值
        elementData[size++] = e;
    }

    public void add(int index,E e){
        rangeCheckAdd(index);
        //1.判断是否需要扩容大小
        ensureExplicitCapacity(size+1);

        System.arraycopy(elementData,index,elementData,index+1,size-index);
        //2.通过下标赋值
        elementData[index] = e;
        size++;
    }

    public E get(int index){
        rangeCheck(index);
        return elementData(index);
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    public int size(){
        return size;
    }

    private void rangeCheck(int index) {
        if(index >= size) {
            try {
                throw new Exception("大小越界了！！！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void rangeCheckAdd(int index) {
        if(index > size) {
            try {
                throw new Exception("添加数据下标越界了！！！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //minCapacity最小扩容量
    private void ensureExplicitCapacity(int minCapacity) {
        //数组满了 就扩容
        if(size == elementData.length){
            int oldCapacity = elementData.length;
            //扩容1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            //初始容量为1的扩容需要单独处理
            if(newCapacity - minCapacity <0){
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public E remove(int index){
        rangeCheck(index);

        E e = get(index);
        //计算删除元素后面剩余元素的的长度
        int removeLastlenght = size - index -1;

        //removeLastlenght==0 表示删除末尾的元素，此时不需要复制新数组
        if(removeLastlenght > 0){
            System.arraycopy(elementData,index+1,elementData,index,removeLastlenght);
        }

        elementData[--size] = null;
        return e;

    }

    public boolean remove(E e){
        for (int i = 0; i < elementData.length; i++) {
            if(e.equals(elementData[i])){
                remove(i);
                return true;
            }
        }
        return false;
    }

}
