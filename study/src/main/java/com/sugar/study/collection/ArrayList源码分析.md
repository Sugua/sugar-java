

##### `ArrayList`源码分析

###### ArrayList简介
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

###### ArrayList 核心原码解读

JDK1.8，ArrayList底层代码

```java
package com.sugar.study.collection;

import sun.plugin2.main.client.WMozillaServiceDelegate;

import java.util.*;

public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

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
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            //如果传入的参数大于0，创建initialCapacity大小的数组
            this.elementData = new Object[initialCapacity];

        } else if (initialCapacity == 0) {
            //如果传入的参数等于0，创建空数组
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            //其他情况，抛出异常
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * 默认无参构造方法
     * DEAULTCAPACITY_EMPTY_ELEMENTDATA 为0，初始化为10，也就是说初始其实是空数组，当添加第一个元素的时候数组容量才变成10
     */
    public ArrayList() {

        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 构建一个包含指定集合的元素的列表，按照它们由集合的迭代器返回的顺序
     *
     * @param c
     */
    public ArrayList(Collection<? extends E> c) {
        //将指定集合转换为数组
        elementData = c.toArray();
        //如果elementData数组长度不为0
        if ((size = elementData.length) != 0) {
            //如果elementData不是Object类型的数据(c.toArray()可能返回的不是Object类型的数组所以加上下面的语句用于判断
            if (elementData.getClass() != Object[].class) {
                //将原来不是Object类型的elementData数组的内容，赋值给新的Object类型的elementData数组
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            }

        } else {
            //其他情况，用空数组代理
            this.elementData = EMPTY_ELEMENTDATA;
        }

    }

    /**
     * 修改这个ArrayList实例的容量是列表的当前大小，应用程序可以适用次操作来最小化ArrayList实例的存储
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0) ? EMPTY_ELEMENTDATA : Arrays.copyOf(elementData, size);
        }
    }

    //下面是ArrayList的扩容机制
    //ArrayList的扩容机制提高了性能，如果每次值扩充一个
    //那么频繁的插入会导致频繁的拷贝，降低性能，而ArrayList的扩容机制避免了这种情况


    /**
     * 如有必要，增加此ArrayList实例的容量，以确保它至少能容纳元素的数量
     *
     * @param minCapacity 所需最小容量
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) ? 0 : DEFAULT_CAPACITY;
        //如果最小容量大于已有的最大容量
        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    //根据给定的最小容量和当前数组原来来计算所需容量
    private static int calculateCapacity(Object[] elementData, int minCapacity) {

        //如果当前元素为空数组（初始情况），返回默认容量和最小容量中的较大值作为所需容量
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        //否则直接返回最小容量
        return minCapacity;
    }

    //确保内部容量达到指定的最小容量
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    //判断是否需要扩容
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        if (minCapacity - elementData.length > 0) {
            //调用此方法进行扩容，调用此方法代表已经开始扩容了
            grow(minCapacity);
        }
    }

    /**
     * 要分配最大数组的容量
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * ArrayList扩容核心方法
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        //oldCapacity为旧容量，newCapacity为新容量
        int oldCapacity = elementData.length;
        //oldCapacity右移一位，其效果相当于oldCapacity/2
        //我们知道位运算的速度远远快于整除运算，整句运算式的结果就是将容量更新为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //然后检查新容量是否大于最小需要的容量，若还是最小需要容量，那么就把最小容量当作数组的新容量
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
            //再检查新容量是否超出了ArrayList所定义的最大容量
        }

        //若超出了，则调用胡歌Capacity()来比较minCapacity和MAX_ARRAY_SIZE
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        elementData = Arrays.copyOf(elementData, newCapacity);

    }

    //比较minCapacity 和MAX_ARRAY_SIZE
    private static int hugeCapacity(int minCapacity) {

        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    /**
     * 返回此列表中的元素数
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 如果此列表不包含元素，则返回true
     *
     * @return
     */
    public boolean isEmpty() {
        //主义=和==的区别
        return size == 0;
    }

    /**
     * 如果此列表包含指定的元素，则返回true
     *
     * @param o element whose presence in this list is to be tested
     * @return
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * 返回此列表中指定元素的首次出现的索引， 如果此列表不包含此元素，则为-1
     *
     * @param o element to search for
     * @return
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                //equals()方法比较
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回此列表中指定元素的最后一次出现的索引，如果此列表不包含元素，则返回-1
     *
     * @param o element to search for
     * @return
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }

            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }

            }
        }
        return -1;
    }

    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            //Arrays.copyOf功能实现数组的复制，返回复制后的数组，参数被复制的数组和复制的长度
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            //这不应该发生，因为我们是可以克隆的
            throw new RuntimeException(e);
        }
    }

    /**
     * 以正确的顺序（从第一个到最后一个元素）返回一个包含此列表中所有元素的数组
     * 返回的数组将是“安全的”，因为该列表不保留对它的引用。（换句话说，这个方法必须分配一个新的数组）。
     * 因此，调用者可以自由地修改返回的数组，此方法从单个基于阵列和基于稽核的API之间的桥梁
     *
     * @return
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）
     * 返回的数组的运行时类型是指定数组的运行时的类型。如果列表适合指定的数组，则返回其中
     * 否则，将为指定数组的运行时类型和此列表大小分配一个新数组
     * 如果列表适用于指定的数组，其余空间（即数组的列表数量多余此元素），则紧跟着在集合借宿后的数组中的元素设置设置为null
     * （这仅在调用者知道列表不包含任何空元素的情况下才能确定列表的长度）。
     *
     * @param a   the array into which the elements of this list are to
     *            be stored, if it is big enough; otherwise, a new array of the
     *            same runtime type is allocated for this purpose.
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //新建一个运行时类型的数组，但是ArrayList数组的内容
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        //调用System提供的arrcycopy()方法实现数组之间的复制
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }


    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 返回此列表中指定位置的元素
     *
     * @param index index of the element to return
     * @return
     */
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    /**
     * 用指定的元素替换列表中指定的位置的元素
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return
     */
    public E set(int index, E element) {
        //对index进行界限检查
        rangeCheck(index);
        E oldValue = elementData(index);
        //返回原来在这个位置元素
        return oldValue;
    }

    /**
     * 将指定元素追加到此列表的末尾
     *
     * @param e element whose presence in this collection is to be ensured
     * @return
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        //这里看到ArrayList添加元素的实质就相当于为数组赋值
        elementData[size++] = e;
        return true;
    }

    /**
     * 在此列表中的指定位置插入指定的元素
     * 先调用rangeCheckForAdd对index进行界限检查；然后调用ensureCapacityInternal 方法保证capacity足够大
     * 再将从index开始之后的所有成员后后移一个位置；将element插入index位置；最后size+1
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        //arraycopy()这个实现数组之间复制的方法一定要看一下，下面就用到arraycopy()方法实现数组自己复制自己
        System.arraycopy(element, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;

    }

    /**
     * 删除该列表中指定位置的元素，将人格后序元素移动到左侧（从其索引中减去一个元素）
     *
     * @param index the index of the element to be removed
     * @return
     */
    public E remove(int index) {
        rangeCheck(index);
        modCount++;
        E oldValue = elementData(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;//clear to let GC do ints work
        return oldValue;
    }

    /**
     * 从列表中删除指定元素的第一个出现（如果存在），如果列表不包含该元素，则它不会更改
     *
     * @param o element to be removed from this list, if present
     * @return
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * @param index
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;

        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }

    /**
     * 从雷彪中删除所有元素
     */
    public void clear() {
        modCount++;

        //把数组中所有的元素的值设为null
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;

    }

    /**
     * 按指定集合的Iterator返回的顺序将指定结合中的所有元素追加到此列表的末尾。
     *
     * @param c collection containing elements to be added to this collection
     * @return
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);

        size += numNew;
        return numNew != 0;

    }

    /**
     * 将指定集合中的所有元素插入到此列表中，从指定的位置开始
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        }
        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 从此列表中删除所有索引formIndex(含）和toIndex之间的元素。
     * 将任何后续元素移动到左侧（减少其索引）。
     *
     * @param formIndex index of first element to be removed
     * @param toIndex   index after last element to be removed
     */
    public void removeRange(int formIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, formIndex, numMoved);

        int newSize = size - (toIndex - formIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    /**
     * 检查给定的索引是否子啊范围内。
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * add 和addAll适用rangeCheck的一个版本
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 返回IndexOutOfBoundsException细节信息
     *
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index:" + index + ",Size:" + size;
    }

    /**
     * 从此列表中删除指定集合中包含的所有元素
     *
     * @param c collection containing elements to be removed from this list
     * @return
     */
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        //如果此列表被修改则返回true
        return batchRemove(c, false);
    }

    /**
     * 仅保留此列表中包含在指定集合中的元素。
     * 换句话说，从此列表中删除其中不包含指定集合中的所有元素
     *
     * @param c collection containing elements to be retained in this list
     * @return
     */
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    /**
     * 从列表中的指定位置开始，返回列表中的元素（按正确顺序）的列表迭代器。
     * 指定的索引表示初始调用将返回的第一个元素为next。初始调用previous将返回指定索引减1的元素。
     * 返回的列表迭代器是fail-fast。
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return
     */
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index:" + index);
        }
        return new listItr(index);
    }

    /**
     * 返回列表中的列表迭代器（按时到的顺序）
     * 返回的列表迭代器是fail-fast
     *
     * @return
     */
    public ListIterator<E> listIterator() {
        return new listItr(0);
    }

    /**
     * 按正确的顺序返回该列表中的元素的迭代器
     * 返回的迭代器是fail-fast
     *
     * @return
     */
    public Iterator<E> iterator() {
        return new Itr();
    }


}

```





`int newCapacity = oldCapacity +(oldCapacity>>1)` ,所以ArrayList每次扩容之后容量变为原来1.5倍左右（oldCapacity我偶数就是1.5倍，否则是1.5倍左右）！奇偶不同，如10+10/2=15, 15+15/2=22。

">>"（位运算符号）：>>右移一位相当于除2，右移n为相当于除以2的n次方，这里oldCapacity明显右移了1位所相当于oldCapacity/2。对于大数据的2进制运算，位移运算符比那些普通运算的元素要快很多，因为程序仅仅移动一下而已，不去计算，这样提高了效率，节省了资源。



###### `System.arraycopy()`和`Arrays.copyOf()`方法



`System.arraycopy()`方法

源码：

```java
//我们发现arraycopy是一个native方法，接下来我们解释一下各个参数的具体意义
/**
* 复制数组
* @param src 源数组
* @param srcpos 原书组中的起始数据
* @param dest 目标数组
* @param destPos 目标数组中的起始位置
* @param length 要复制的数组元素的数量
*/
public static native void arraycopy(Object src, int srcPos, Object dest, int desctPost, int length);
```



`Arrays.copyOf()`方法

源码

```java
public static int[] copyOf(int ariginal, int newLength){
    int[] copy = new int[newLength];
    System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
    return copy
}
```



看起来`Arrays.copyOf()`方法主要是为了给原有的数组扩容

两者联系和区别：

联系：

看两者源代码可以发现`copyOf`()内部实际调用的`System.arraycopy()`方法

区别：

- `arrayof()`需要目标数组，将原数组拷贝到自定义的数组里或者原数组，而且可以选择拷贝起点和长度以及放入新数组中的位置
- `copyOf()`是系统自动在内部新建一个新数组，并返回该数组。
