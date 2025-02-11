### JVM知识

![](C:\sugar\me\运行时数据区域（JDK1.7）.png)

![运行时数据区域（JDK1.8）](C:\sugar\me\运行时数据区域（JDK1.8）.png)

线程私有的：

- 程序计数器
- 虚拟机栈
- 本地方法栈

线程共享的：

- 堆
- 方法区
- 直接内存（非运行时数据区的一部分）



##### 程序计数器

程序计数器是一块较小的内存空间，可以看作是当前线程所执行的字节码的行号执行器。字节码解释器工作时改变这个计数器的值来选取下一条需要执行的字节码指令，分支分支、循环、跳转、异常处理、线程恢复等功能都需要依赖这个计数器来完成。

另外，为了线程切换后能恢复到正确的执行位置，每条线程都需要有一个独立的程序计数器，哥线程之间计数器互不影响，独立存储，我们称这类内存区域为“线程私有”的内存

两个作用：

字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制，如：顺序执行、选择、循环、异常处理

在多线程的情况下，程序计数器用于记录当前线程的位置，从而当线程被切换以来的时候能够知道该线程上次运行到哪儿了

注意：程序计数器是唯一一个不会出现OutOfMemoryError的内存区域，它的生命周期随着现场的创建而创建，随着现场的结束而死亡





### 对象的创建

#### Step1：类加载检查

虚拟机遇到一条new指令时，首先将去检查这个指令的参数是否能在常量池中定位到这个类的符号引用，并且检查这个符号引用带代表的类是否已被加载过。如果没有，那必须先执行相应的类加载过程

#### Step2：分配内存

在类加载通过后，接下来虚拟机将为新生对象分配内存。对象所需的内存大小在类加载完成后便可确定，为对象分配空间任务等同于把一块确定大小的内存从Java堆中划分出来。分配方式有“指针碰撞”和“空闲列表”两种，选择哪种分配方式由Java堆是否规整决定，而Java堆是否规整又由所采用的垃圾收集器是否带有压缩整理功能决定。

##### 内存分配的两种方式（补充内容，需要掌握）：

- 指针碰撞：

  - 适用场合：堆内规整（即没有内存碎片）的情况下

  - 原理：用过的内存全部整合到一边，没有用过的内存放在另一边，中间有一个分界指针，只需要向着没有用的内存方向将该指针移动对象内存大小位置即可

  - 使用该分配大小GC收集器：Serial，ParNew

- 空闲列表：
  - 适用场合：堆内不规整的情况下
  - 原理：虚拟机会维护一个列表，该列表中会记录哪些内存块是可用的，在分配的时候，找一块足够大的内存块儿来划分给对象实例，最后更新列表记录
  - 使用该分配大小GC收集器：CMS

选择以上两种方式种哪一种，取决于Java堆内存是否规整。而Java堆内存是否规则， 取决于GC收集器的算法是“标记-清除”，还是“标记-整理”（也称作“标记-压缩”），值得注意的是复制算法内存也是规整的。

##### 内存分配并发问题（补充内容，需要掌握）

在创建对象的时候有一个很重要的问题，就是线程安全，因为在实际开发过程中，创建对象是很频繁的事情，作为虚拟机来说，必须要保证线程是安全的，通常来讲，虚拟机采用两种方式来保证线程安全：

- **CAS+失败重试：**CAS是乐观锁的一种实现方式。所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。**虚拟机采用CAS配上失败重试的方式保证更新操作的原子性。**
- **TLAB**：为每一格线程预先在Eden区分配一块儿内存，JVM在给线程中的对象分配内存时，首先在TLAB分配，当对象大于TLAB中剩余内存或TLAB的内存用尽是，再采用上述的CAS进行内存分配

#### Step3：初始化零值

内存分配完成后，虚拟机需要将分配到的内存空间都初始化零值（不包含对象头）这一步操作保证了对象实例字段在Java代码中可以不赋初始值就直接使用，程序能访问到这些字段的数据类型所对应的零值。

#### Step4:设置对象头

初始化零值完成之后，虚拟机要对对象进行必要的设置，例如这个对象是哪个类的实例、如何才能才找到类的元数据信息、对象的哈希码、对象的GC分带年龄等信息。这些信息存放在对象头中。另外根据虚拟机当前运行状态的不同，如是否启用偏向锁等，对象头会有不同的设置方式。

#### Step5：执行init方法

在上面工作完成之后，从虚拟机视角来看，一个新的对象已经产生了，但从Java程序的角度来看，对象创建才刚开始，<init>方法还没有执行，所有的字段还为零。所以一般来说，执行new指令之后会接着执行<init>方法，把对象按照程序员的意愿进行初始化，这样一个真正可用对象才算完全产生出来。

