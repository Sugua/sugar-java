

##### `ArrayList`源码分析

###### `ArrayList `与`LinkedList`区别
* 是否保证现场安全： `ArrayList`和`LinkedList`都是不同步的，也就是不保证线程安全
* 底层数据结构：`ArrayList`底层使用的是`Obeject`数组；`LinkedList`底层使用的是双向链表数据结构（JDK1.6之前为循环链表，JDK1.7取消了循环。注意双向链表和双向循环链表的区别）
* 插入和删除收否受元素位置的影响：
	* `ArrayList`
