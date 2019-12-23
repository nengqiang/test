 
 ## 基本概念
 ### 1.1 
    properties 文件，存储格式 键=值
    properties 文件特点：
        * 键值对格式
        * "="后面，值前面的空格会自动忽略 
        * 值后面德德空格，不会忽略
        * "="后面的双引号，不会忽略
        * "#"后面的内容为注释
        
 ### Java的Properties类属性映射（property map）
    是一种存储键/值对的数据结构。属性映射经常被用来存放配置信息。
    他的三个特性：
        * 键和值都是字符串
        * 键/值对可以很容易写入文件或从文件读出
        * 用二级表存放默认值
    实现属性映射的Java类被称为Properties（java.util.properties），此类是Java中比较重要的类。
    主要用于读取Java的配置文件，各种语言都有自己所支持的配置文件，配置文件中很多变量是经常改变的。
    这样做也是为了方便用户，让用户能够脱离程序本省去修改相关的变量设置，提高程序的可扩展性。
    此类是线程安全的：多个线程可以共享单个Properties对象而无需进行外部同步。
    
    构造方法：
    * Properties() 创建一个默认值的空属性列表。
    * Properties(Properties defaults) 创建一个带有指定默认值的空属性列表。
    它提供了几个主要方法：
        getProperty(String key)：用指定的键在此属性列表中搜索属性。也就是通过参数key，得到key所对应的value。
        load(InputStream inStream)：从输入流中读取属性列表（键和元素对）。通过对指定的文件进行装载来
    获取该文件中多有的键-值对。以供getProperty(String key)来搜索。
        setProperty(String key, String value)：底层调用HashTable的方法put。通过调用基类的put方法来设置键-值对。
        store(OutputStream out, String Comments)：以适合使用load方法加载到Properties表中的格式，将此Properties
    表中的属性列表（键和元素对）写入输出流。与load方法相反，该方法将键-值写入到指定文件中去。
        clear()：清除所有装载的键-值对。该方法在基类中提供。
    
    注意：因为Properties继承与HashTable，所以Properties对象可以使用put和putAll方法。但不建议使用这两个方法，
    因为它们允许调用者插入其键或值不是String的项。相反，应该使用setProperty方法。
    
    如果在"不安全"的Properties对象（即包含非String类型的键或值）上调用store或save方法，该调用会失败。
    类似地，如果在"不安全"的Properties对象（即包含非String的键）上调用propertyNames或list方法，该调用会失败。