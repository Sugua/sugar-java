### 安装make

对于Windows 10/11，可以首先安装WSL（Windows Subsystem for Linux）：

![image-20241113170412063](C:\Users\陈小刚\AppData\Roaming\Typora\typora-user-images\image-20241113170412063.png)



然后，在Windows应用商店，搜索Ubuntu 22.04，直接安装后运行，Windows会弹出PowerShell的窗口连接到Linux，在PowerShell中即可输入Linux命令，和SSH连接类似。

#### 解决WSL2的 0x800701bc错误

![img](https://pic1.zhimg.com/v2-9cf601e37ca5a71ca319145e2b6ca662_1440w.jpg)

**请以PowerShell（管理员）运行所有指令**

1. **开启Windows Subsystem for Linux**

   ```powershell
   dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
   ```

2. **开启虚拟机特性**

   ```powershell
   dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
   ```

3. **更新wsl2**

   ```text
   wsl --update
   ```

4. **将WSL2设置成默认**

   ```text
   wsl --set-default-version 2
   ```





以Ubuntu为例，在Linux命令行下，用`apt`命令安装`make`以及GCC工具链：

```shell
$ sudo apt install build-essential
```

这里会出现404错误，先更新软件包列表后再执行上一步

```shell
$ sudo apt update
```



成功后验证  `make -v`

```shell
$ make -v
GNU Make 4.3
Built for x86_64-pc-linux-gnu
Copyright (C) 1988-2020 Free Software Foundation, Inc.
License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.
```

输入`gcc --version`验证GCC工具链：

```shell
$ gcc --version
gcc (Ubuntu 11.4.0-1ubuntu1~22.04) 11.4.0
Copyright (C) 2021 Free Software Foundation, Inc.
This is free software; see the source for copying conditions.  There is NO
warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
```





