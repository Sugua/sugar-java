#### HashMap源码分析

##### HashMap简介

HashMap主要用来存放键值对，它基于哈希表的Map接口实现，是常用的Java集合之一，是非线程安全的。

`HashMap`可以存储null的key和value，但null作为键只能有一个，null作为值可以有多个。

在JDK1.8之前HashMap有数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。JDK1.8以后的HashMap在解决哈希冲突时有了较大的变化，当来拿表长度大于等于阈值（默认为8）（将链表转成成红黑树前会判断，如果当前数组的长度小于64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间。

HashMap默认的初始化大小为16。之后每次扩充，容量变为原来的2倍。并且，HashMap总是适用2的幂作为哈希表的大小。



##### 底层数据结构分析

###### JDK1.8之前

JDK1.8之前HashMap底层是数组和链表结合在一起使用也就是链表散列。

HashMap通过key的hashCode经过扰动函数处理过后hash值，然后通过(n-1)&hash判断当前元素存放的位置（这里的n指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的hash值以及key是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。

所谓扰动函数指的是HashMap的hash方法。适用hash方法也就是扰动函数式为了防止一些实现比较差的hashCode()方法，换句话说适用扰动函数之后减少碰撞。

###### JDK1.8HashMap的hash方法源码：

JDK1.8的hash方法相比于JDK1.7hash方法更加简化，但是原理不变。

```java
static final int hash(Object key){
    int h;
    //key.hashCode();返回山列支也就是hashCode
    //^:按位异或
    //>>>:无符号右移，忽略符号位，空位都以0补齐
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

对比一下JDK1.7de HashMap的hash方法源码：

```java
static int hash(int h){
    h ^= (h>>>20) ^ (h >>> 12);
    return h ^ (h>>>7) ^(h >>>4 );
}
```



相比于JDK1.8的hash方法，JDK1.7的hash方法的性能会稍差一点点，因为毕竟扰动了4次。



所谓“拉链法”就是：将链表和数组相结合。也就是说创建一个链表数组，数组中每一格就是一个链表。若遇到哈希冲突，则将冲突的值加到链表中即可



###### JDK1.8之后

相比于之前的版本，JDK以后解决哈希冲突时有了较大的变化。

当链表长度大于阈值（默认为8）时，会首先调用treeifyBin()方法。这个方法根据HashMap数组来决定是否转换为红黑树。只有当数组长度大于或者等于64的情况下，才会执行转换红黑树操作，以减少搜索时间。否则，就是只执行resize()方法对数组扩容。重点关注treeifyBin().



**类的属性**：

```java

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
    transient Node<K,V>[] table;

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

  
}
```



resize
