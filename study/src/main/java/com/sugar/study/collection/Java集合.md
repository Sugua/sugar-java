

##### Java集合

###### 集合概述

Java集合也叫容器，主要是由两个大接口派生而来：一个`Collection`接口，主要用于存放单一元素；另一个是`Map`接口，主要用于存放键值对。对于`Collection`接口下又有三个主要的子接口：`List`、`Set`、`Queue`

List、Set、Queue，Map的区别

- `List`（对付顺序的好帮手）：存储元素是有序的，可重复的。

  - `ArrayList`：`Object[]`数组。

  - `Vector`：`Object[]`数组

  - `LinkedList`：双向链表

- `Set`（注重独一无二的性质）：存储的元素不可重复。
  - `HashSet`（无序，唯一）：基于`HashMap`实现的，底层采用`HashMap`来保存元素。
  - `LinkedHashSet`：`LinkedHashSet`是`HashSet`的子类，并且内部是通过LinedHashMap来实现。
  - `TreeSet`(有序，唯一)：红黑树（自平衡的排序二叉树）。

- `Queue`（实现排队的叫号机）：按待定的排队规则来确定先后顺序，存储的元素是有序的、可重复的。
  - `PriorityQueue`：`Object[]`数组来实现小顶堆。
  - `DelayQueue`：`PriorityQueue`。
  - `ArrayQueue`：可扩容动态双向数组。

- `Map`（用key来搜索的专家）：使用键值对（key-value）来存储，类似于数学上的函数y=f(x)，"x"代表key，"y"代表value，key是无序的、不可重复的，value是无序的、可重复的，每个键有且只有一个value。
  - `HashMap`：在JDK1.8之前由数组加链表组成的，数组是`HashMap`的主体，链表则是解决哈希冲突的存在（“拉链法”解决冲突）。JDK1.8以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）（将链表转换成红黑树前判断，如果当前数组的长度小于64，那么会选择先进行数组扩容，而不是转为红黑树）时，将链表转为红黑树，以减少搜索时间。
  - `LinkedHashMap`：`LinkedHashMap`集成自`HashMap`，所以它的底层仍然是基于拉链时散列结构即由数组和链表或红黑树组成的。另外，`LinkedHashMap`在上面结构基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序，同时通过对链表进行操作，实现访问顺序相关逻辑。
  - `Hashtable`：数组+链表组成的，数组是`Hashtable`的主体，链表则是主要为了解决哈希冲突而存在的。
  - `TreeMap`：红黑树（自平衡的排序二叉树）。
  - 





