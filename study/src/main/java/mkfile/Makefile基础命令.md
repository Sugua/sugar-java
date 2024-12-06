### MakeFile基础

首先在`idea`中安装`Makefile`插件，这样在编辑的时候不回出现`Tab键`时会出现变成了4个空格，因为`Makefile`对`Tab键`变成4个空格是不能执行的，会出现如下错误：

```shell
$ make
Makefile:3: *** missing separator (did you mean TAB instead of 8 spaces?).  Stop.
```

安装插件很方便，在`file->setting` 中找到`plugins`，然后输入`Makefile`搜索安装即可。

一条规则的格式为`目标文件: 依赖文件1 依赖文件2 ...`

```makefile
# 目标文件: 依赖文件1 依赖文件2
m.txt: a.txt b.txt
	cat a.txt b.txt > m.txt
```

```!
注意：Makefile的规则中，命令必须以Tab开头，不能是空格。
```

![image-20241206112746896](C:\Users\陈小刚\AppData\Roaming\Typora\typora-user-images\image-20241206112746896.png)

```makefile
# 目标文件: 依赖文件1 依赖文件2
x.txt: m.txt c.txt
	cat m.txt c.txt > x.txt

m.txt: a.txt b.txt
	cat a.txt b.txt > m.txt

```

`make`默认执行第一条规则，也就是创建`x.txt`，但是由于`x.txt`依赖的文件`m.txt`不存在（另一个依赖`c.txt`已存在），故需要先执行规则`m.txt`创建出`m.txt`文件，再执行规则`x.txt`。执行完成后，当前目录下生成了两个文件`m.txt`和`x.txt`。

```shell
$ make
cat a.txt b.txt > m.txt
cat m.txt c.txt > x.txt
```

可见，`Makefile`定义了一系列规则，每个规则在满足依赖文件的前提下执行命令，就能创建出一个目标文件，这就是英文Make file的意思。

把默认执行的规则放第一条，其他规则的顺序是无关紧要的，因为`make`执行时自动判断依赖。



此外，`make`会打印出执行的每一条命令，便于我们观察执行顺序以便调试。



如果我们再次运行`make`，输出如下：

```shell
$ make
make: 'x.txt' is up to date.
```

`make`检测到`x.txt`已经是最新版本，无需再次执行，因为`x.txt`的创建时间晚于它依赖的`m.txt`和`c.txt`的最后修改时间。

```?
make使用文件的创建和修改时间来判断是否应该更新一个目标文件。
```

修改`c.txt`后，运行`make`，会触发`x.txt`的更新：

```shell
$ vi c.txt
$ make
cat m.txt c.txt > x.txt
```

但并不会触发`m.txt`的更新，原因是`m.txt`的依赖`a.txt`与`b.txt`并未更新，所以，`make`只会根据`Makefile`去执行那些必要的规则，并不会把所有规则都无脑执行一遍。

在编译大型程序时，全量编译往往需要几十分钟甚至几个小时。全量编译完成后，如果仅修改了几个文件，再全部重新编译完全没有必要，用`Makefile`实现增量编译就十分节省时间。

当然，是否能正确地实现增量更新，取决于我们的规则写得对不对，`make`本身并不会检查规则逻辑是否正确。

### 伪目标

因为`m.txt`与`x.txt`都是自动生成的文件，所以，可以安全地删除。

删除时，我们也不希望手动删除，而是编写一个`clean`规则来删除它们：

```makefile
clean:
    rm -f m.txt
    rm -f x.txt
```

`clean`规则与我们前面编写的规则有所不同，它没有依赖文件，因此，要执行`clean`，必须用命令`make clean`：

```shell
$ make clean
rm -f m.txt
rm -f x.txt
```

然而，在执行`clean`时，我们并没有创建一个名为`clean`的文件，所以，因为目标文件`clean`不存在，每次运行`make clean`，都会执行这个规则的命令。

如果我们手动创建一个`clean`的文件，这个`clean`规则就不会执行了！

```shell
sugar@DESKTOP-C8KEOE6:~/makefile$ ll
total 24
drwxr-xr-x 2 sugar sugar 4096 Dec  6 15:01 ./
drwxr-x--- 8 sugar sugar 4096 Dec  6 15:01 ../
-rw-r--r-- 1 sugar sugar  181 Dec  6 15:00 Makefile
-rw-r--r-- 1 sugar sugar   11 Dec  6 10:52 a.txt
-rw-r--r-- 1 sugar sugar   11 Dec  6 10:52 b.txt
-rw-r--r-- 1 sugar sugar   18 Dec  6 14:12 c.txt
-rw-r--r-- 1 sugar sugar    0 Dec  6 15:01 clean
sugar@DESKTOP-C8KEOE6:~/makefile$ make clean
make: 'clean' is up to date.
```



```shell
$ make clean
make: 'clean' is up to date.
```



如果我们希望`make`把`clean`不要视为文件，可以添加一个标识：

```makefile
# 当clean文件存在时 会被认为生成clean文件，需要加这个标记
.PHONY: clean
# clean 是一个伪目标:
clean:
	rm -f m.txt
	rm -f x.txt
```





### 执行多条命令

一个规则可以有多条命令，例如：

```makefile
cd:
	pwd
	cd ..
	pwd
```

```shell
$ make cd
pwd
/home/sugar/makefile
cd ..
pwd
/home/sugar/makefile
```

观察输出，发现`cd ..`命令执行后，并未改变当前目录，两次输出的`pwd`是一样的，这是因为`make`针对每条命令，都会创建一个独立的Shell环境，类似`cd ..`这样的命令，并不会影响当前目录。

解决办法是把多条命令以`;`分隔，写到一行：

```makefile

cd_ok:
	pwd;cd ..;pwd
```

再执行`cd_ok`目标就得到了预期结果：

```shell
$ make cd_ok
pwd;cd ..;pwd
/home/sugar/makefile
/home/sugar
```

可以使用`\`把一行语句拆成多行，便于浏览：

```makefile
cd_ok_ok:
	pwd;\
	cd ..;\
	pwd
```

另一种执行多条命令的语法是用`&&`，它的好处是当某条命令失败时，后续命令不会继续执行：

```makefile
cd_ok_ok_ok:
	pwd&&cd ..&&pwd
```

### 控制打印

默认情况下，`make`会打印出它执行的每一条命令。如果我们不想打印某一条命令，可以在命令前加上`@`，表示不打印命令（但是仍然会执行）：

```makefile
no_output:
	@echo 'not display'
	echo 'will display'
```

执行结果如下：

```shell
$ make no_output
not display
echo 'will display'
will display
```

注意命令`echo 'not display'`本身没有打印，但命令仍然会执行，并且执行的结果仍然正常打印。

### 控制错误

`make`在执行命令时，会检查每一条命令的返回值，如果返回错误（非0值），就会中断执行。

例如，不使用`-f`删除一个不存在的文件会报错：

```makefile
has_error:
	rm zzz.txt
	echo 'ok'
```

执行`make has_error`会出现：

```shell
$ make has_error
rm zzz.txt
rm: cannot remove 'zzz.txt': No such file or directory
make: *** [Makefile:34: has_error] Error 1
```

由于命令`rm zzz.txt`报错，导致后面的命令`echo 'ok'`并不会执行，`make`打印出错误，然后退出。

有些时候，我们想忽略错误，继续执行后续命令，可以在需要忽略错误的命令前加上`-`：

```makefile
ignore_error:
	-rm zzz.txt
	echo 'ok啦'
```

执行`make ignore_error`会输入`ok啦`:

```shell
$ make ignore_error
rm zzz.txt
rm: cannot remove 'zzz.txt': No such file or directory
make: [Makefile:38: ignore_error] Error 1 (ignored)
echo 'ok啦'
ok啦
```

`make`检测到`rm zzz.txt`报错，并打印错误，但显示`(ignored)`，然后继续执行后续命令。

对于执行可能出错，但不影响逻辑的命令，可以用`-`忽略。