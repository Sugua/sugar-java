package com.sugar.study.collection;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    // 序列号
    private static final long serialVersionUID = 362498820763181265L;

    //默认初始化容量是16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    //最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;

    //默认的负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //当桶(bucket)上的节点数大于等于这个值时会转成红黑树
    static final int TREEIFY_THRESHOLD = 8;

    //当桶(bucket)上的节点数小于等于这个值时树转成链表
    static final int UNTREEIFY_THRESHOLD = 6;

    //桶中结构转化wield红黑树对应的table的最小容量
    static final int MIN_TREEIFY_CAPACITY = 64;

    //存储元素的数组，总是2 的幂次倍t
//    todo 不用打开，这个Node是HashMap的内部类
//    transient Node<K,V>[] table;

    //一个包含了映射中所有键值对的集合视图
    transient Set<Entry<K,V>> entrySet;

    //存放元素的个数，注意这个不等于数组的长度；
    transient int size;

    //每次扩容和更改map结构的计数器
    transient int modCount;

    //阈值（容量*负载因子）当实际大小超过阈值时，会进行扩容
    int threshold;

    //负载因子





    final float loadFactor;
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
