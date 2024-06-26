
Spring 是一个庞大的工厂，所有的对象实例化等辅助不要用户完成，用户只要引用对象完成业务工作

1.IOC(Inversion of Control):控制反转:
    DI:依赖注入
        基本类型以及String 这样有具体内容，直接用value
        应用类型，用ref


    内部bean 配置
    延迟加载 ，azy-init=""
    初始化， init-method=""，销毁destroy-method =""




资源访问

    各种资源的读取操作 ,整个操作关键在于org.springframework.core.io.Resource:


        boolean exists();// 判断资源是否存在，一般使不上

        boolean isReadable();

        boolean isOpen();//判断资源是否打开

        URL getURL() throws IOException;//渠道资源的完整网络路径

        URI getURI() throws IOException;//统一唯一标识苻

        File getFile() throws IOException; //取得对应资源的文件类信息

        long contentLength() throws IOException;//取得资源的数据长度

        long lastModified() throws IOException; //最后一次修改日期

        Resource createRelative(String var1) throws IOException;//创建操作资源

        String getFilename();

        String getDescription();


    org.springframework.core.io.InputStreamSource 是属于Resource接口 的父接口

        InputStream getInputStream() throws IOException;// 取得资源的输入流

    Resource 本身是接口， 使用需要用实现类，如：
         ByteArrayResource:内存读取
         ClassPathResource:CLASSPATH 读取
         FileSystemResource：文件读取







