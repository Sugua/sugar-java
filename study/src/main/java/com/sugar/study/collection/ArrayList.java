package com.sugar.study.collection;

import java.util.*;
import java.util.function.UnaryOperator;

public class ArrayList<E> extends AbstractList implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    /**
     * 默认初始化容量大小
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 空数组（用于空实例）
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    //用于默认大小实例的共享空数组实例
    //我们把它从EMPTY_ELEMENTDATA数组中区分出来，以知道在添加第一个元素时容量需要增加多少
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * ArrayList 所包含的元素
     */
    transient Object[] elementData;//non-private to simpify nested class access
    /**
     * ArrayList 所包含元素的个数
     */
    private int size;

    /**
     * 带初始化容量参数的构造方法（用户可以在创建ArrayList对象时自己指定集合的初始化大小）
     */
    public ArrayList(int initialCapacity){
        if (initialCapacity>0){
            //如果传入的参数大于0，创建initialCapacity大小的数组
            this.elementData=new Object[initialCapacity];

        }else if (initialCapacity==0){
            //如果传入的参数等于0，创建空数组
            this.elementData=EMPTY_ELEMENTDATA;
        }else {
            //其他情况，抛出异常
            throw new IllegalArgumentException("Illegal Capacity: "+initialCapacity);
        }
    }

    /**
     * 默认无参构造方法
     * DEAULTCAPACITY_EMPTY_ELEMENTDATA 为0，初始化为10，也就是说初始其实是空数组，当添加第一个元素的时候数组容量才变成10
     */
    public ArrayList(){

        this.elementData=DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 构建一个包含指定集合的元素的列表，按照它们由集合的迭代器返回的顺序
     * @param c
     */
    public ArrayList(Collection<? extends E> c){
        //将指定集合转换为数组
        elementData=c.toArray();
        //如果elementData数组长度不为0
        if ((size=elementData.length)!=0){
            //如果elementData不是Object类型的数据(c.toArray()可能返回的不是Object类型的数组所以加上下面的语句用于判断
            if(elementData.getClass()!=Object[].class){
                //将原来不是Object类型的elementData数组的内容，赋值给新的Object类型的elementData数组
                elementData=Arrays.copyOf(elementData,size,Object[].class);
            }

        }else{
            //其他情况，用空数组代理
            this.elementData=EMPTY_ELEMENTDATA;
        }

    }

    /**
     * 修改这个ArrayList实例的容量是列表的当前大小，应用程序可以适用次操作来最小化ArrayList实例的存储
     */
    public void trimToSize(){
        modCount++;
        if (size<elementData.length){
            elementData=(size==0)?EMPTY_ELEMENTDATA:Arrays.copyOf(elementData,size);
        }
    }

    //下面是ArrayList的扩容机制
    //ArrayList的扩容机制提高了性能，如果每次值扩充一个
    //那么频繁的插入会导致频繁的拷贝，降低性能，而ArrayList的扩容机制避免了这种情况


    /**
     * 如有必要，增加此ArrayList实例的容量，以确保它至少能容纳元素的数量
     * @param minCapacity   所需最小容量
     */
    public void ensureCapacity(int minCapacity){
        int minExpand=(elementData!=DEFAULTCAPACITY_EMPTY_ELEMENTDATA) ?0:DEFAULT_CAPACITY;
        //如果最小容量大于已有的最大容量
        if (minCapacity>minExpand){
            ensureExplicitCapacity(minCapacity);
        }
    }

    //根据给定的最小容量和当前数组原来来计算所需容量
    private static  int calculateCapacity(Object[] elementData, int minCapacity){

        //如果当前元素为空数组（初始情况），返回默认容量和最小容量中的较大值作为所需容量
        if (elementData==DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            return Math.max(DEFAULT_CAPACITY,minCapacity);
        }

        //否则直接返回最小容量
        return minCapacity;
    }

    //确保内部容量达到指定的最小容量
    private void ensureCapacityInternal(int minCapacity){
        ensureExplicitCapacity(calculateCapacity(elementData,minCapacity));
    }
    //判断是否需要扩容
    private void ensureExplicitCapacity(int minCapacity){
        modCount++;
        if (minCapacity-elementData.length>0){
            //调用此方法进行扩容，调用此方法代表已经开始扩容了
            grow(minCapacity);
        }
    }

    /**
     *要分配最大数组的容量
     */
    private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;

    /**
     * ArrayList扩容核心方法
     * @param minCapacity
     */
    private void grow(int minCapacity){
        //oldCapacity为旧容量，newCapacity为新容量
        int oldCapacity=elementData.length;
        //oldCapacity右移一位，其效果相当于oldCapacity/2
        //我们知道位运算的速度远远快于整除运算，整句运算式的结果就是将容量更新为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //然后检查新容量是否大于最小需要的容量，若还是最小需要容量，那么就把最小容量当作数组的新容量

    }



    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void replaceAll(UnaryOperator operator) {
        super.replaceAll(operator);
    }

    @Override
    public ArrayListCode<E> clone() {
        try {
            return (ArrayListCode) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void sort(Comparator c) {
        super.sort(c);
    }

    @Override
    public Spliterator<E> spliterator() {
        return super.spliterator();
    }
}
