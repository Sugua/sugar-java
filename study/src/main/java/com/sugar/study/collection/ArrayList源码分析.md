

##### `ArrayList`源码分析

##### ArrayList简介
ArrayList继承于AbstractList，实现了List，RandomAccess，Cloneable，java.io.Serializable 

```java
public class ArrayList<E> extends AbstractList<E> 
implements List<E>,RandomAccess, Cloneable, java.io.Serializable

```

* List:表明它是一个列表，支持添加，删除，查找等操作
* RandomAccess：这是一个标志接口，表明实现这个接口的List集合是支持快速随机访问的。在ArrayList中，我们可以通过元素需要快速获取元素对象，这就是快速随机访问。
* Cloneable：表明它具有拷贝能力，可以进行深拷贝或浅拷贝
* Serializable：表明它可以进行序列化操作，也就是将对象转换为字节流进行持久化存储或网络传播。

###### `ArrayList `与`LinkedList`区别
* **是否保证现场安全：** `ArrayList`和`LinkedList`都是不同步的，也就是不保证线程安全。
* **底层数据结构：**`ArrayList`底层使用的是`Obeject`数组；`LinkedList`底层使用的是双向链表数据结构（JDK1.6之前为循环链表，JDK1.7取消了循环。注意双向链表和双向循环链表的区别）。
* **插入和删除收否受元素位置的影响：**
	* `ArrayList`采用数组存储，所以插入和删除元素的时间复杂度受元素位置影响，比如：执行`add(E e)`方法的时候，`ArrayList`会默认在将指定的元素追加到此列表的末尾，这种的时间复杂度就是O(1)。但是如果要在指定位置i上插入和删除元素的话(`add(int index, E e)`),时间复杂度就为O(n)。因为在进行上述操作的时候集合中第i和第i个元素之后的(n-i)个元素都要执行向后/向前移一位的操作。
	* `LinkedList`采用链表存储，所以在在头尾插入或者删除元素不受元素位置的影响`add(E e)`、`addFirst(E e)`、`addLast(E. e)`、`removeFirst()`、`removeLast()`,时间复杂度为O(1),如果在指定位置i插入和删除元素的话(`add(int index, E e)`，`remove(int index)`、`remove(Object o)`)，时间复杂度为O(n),因为需要先移动到指定位置再插入和删除。
* **是否支持快速随机访问：**`LinkedList`不支持高效的随机元素访问，而`ArrayList`(实现了`RandomAccess`接口）支持。快速随机访问就是通过元素的序号快速获取元素对象（对应于`get(int index)`方法）。
* **内存空间占用：**`ArrayList`的空间浪费主要体现在在list列表的会预留一部分容量空间；而`LinkedList`的空间主要花费则体现在它的每个元素需要比`ArrayList`更多的空间（因为要存放直接后继和直接前驱以及数据）
