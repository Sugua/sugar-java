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
        if ((size=elementData.length)!=0){

        }

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
