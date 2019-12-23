 ## test

 
 * 代码练习
 * 项目测试
 * 学习草稿
 
 ---
 
 ### Junit测试代码编写命名规范
 
 * 测试类的命名定义规范
     * Junit自动生成测试类的命名如下：被测试的业务+Test、被测试的接口+Test、被测试的类+Test  
       类的名字必须由大写字母开头而单词中的其他字母均为小写；  
       如果类名称由多个单词组成，则每个单词的首字母均应为大写，如TestMobileBind。  
       如果类名称中包含单词缩写，则这个所写词的每个字母均应大写，如：XMLExample。
     * 比如你需要测试业务MobileBind ，那么它的测试类的命名就是TestMobileBind  
     * 比如你需要测试接口GetMobileBind ，那么的测试类的命名就是TestGetMobileBind  
     * 比如你需要测试类SetMobileBind.class,那么他的测试类的命名就是TestSetMobileBind  
     
 * 测试用例的命名定义规范
     * 测试用例的命名规则是：test+用例操作。  
     * 单词的约定与测试类命名相同。如：testSetMobileBind  
     * 比如要测试的用例是“数据库用户信息不存在时,获取Mobile绑定消息”，  
     那么它的测试用例名称就是testSetMobileBind  
 
 ---
 
 #### Java常见技术术语缩写、全称及其解释
 
 | 缩写 | 全称 | 简单解释 |
 | --- | --- | --- |
 | J2SE | Java SE(Java platform, Standard Edition) | Java标准版 |
 | J2EE | Java EE(Java Platform, Enterprise Edition) | Java企业版 |
 | J2ME | Java ME(Java Platform, Micro Edition) | Java微缩版 |
 | JRE | Java Runtime Environment | Java运行环境 |
 | JDK | Java Development Kit | Java开发工具，包含JRE |
 | JSR | Java Specification Requests | Java规范提案 |
 | JCP | Java Community Process | 由Java开发者以及被授权者组成的开放的国际组织，职能是发展和更新 |
 | JAR | Java Archive | Java归档文件，许多文件组合成的一个压缩包 |
 | WAR | Web Application Archive | 许多文件组合成的一个压缩包 |
 | EJB | Enterprise Java Bean | Java企业的Bean，是Java的核心代码，分别是会话Bean、实体Bean和消息驱动Bean |
 | EAR | Enterprise Archive File | 除了包含JAR、WAR以外，还包含EJB组件 |
 | JPA | Java Persistence API | Java持久层API，是JDK 5.0注解或XML描述对象-关系表的映射关系，并将运行期的实体对象持久化到数据库中 |
 | XML | Extensible Markup Language | 可扩展标记语言，标准通用标记语言的子集 |
 | JVM | Java Virtual Machine | Java虚拟机，JVM是一种用于计算机设备的规范，它是一个虚构出来的计算机 |
 | POJO | Plain Ordinary Java Object | 简单的Java对象 |
 | DAO | Data Access Object | 表示一个数据访问对象，访问数据库 |
 | PO | Persistence Object | 持久对象，类似于DAO |
 | DO | Domain Object | 领域对象，类似于DAO |
 | DTO | Data Transform Object | 数据传输对象 |
 | BO | Business Object | 业务对象，由Service层输出的封装的业务逻辑对象 |
 | AO | Application Object | 应用对象，贴近展示层，复用度不高 |
 | VO | View Object | 显示层对象，通常是Web向模板渲染引擎传输的对象 |
 | POM | Project Object Model | 项目对象模型 |
 | JDBC | Java DataBase Connectivity | Java数据库连接 |
 | ORM | Object Relational Mapping | 对象关系映射，用来描述对象-关系的映射细节 |
 | IOC | Inversion of Control | 控制反转，面向对象编程中的一种设计原则，用来减低计算机代码之间的耦合度 |
 | AOP | Aspect Oriented Programming | 面向切面编程 |
 | DI | Dependency Injection | 依赖注入 |
 | IO | Input/Output | 输入/输出 |
 | BIO | Block IO | 阻塞IO |
 | NIO | Non-Block IO | 非阻塞IO |
 | | |
 | AWT | Abstract Windows Toolkit | 抽象窗口工具箱 |
 | AJAX | Asynchronous JavaScript and XML | 异步JavaScript及XML |
 | B2B | Business-to-Business | 业务对业务 |
 | C2B | Customer-to-Business | 客户对业务 |
 | CRUD | create, read, update, delete | 专指数据库操作 |
 | CVS | Concurrent Version Control | 版本控制器 |
 | CGLIB | Code Generation Library | 代码生成类库 |
 | DBMS | Database Management System | 数据库管理系统 |
 | DDL | Data Definition Language | 数据定义语言 |
 | DML | Data Manipulation Language | 数据操作语言 |
 | DNS | Internet Domain Name System | 因特网域名系统 |
 | DNS | Domain Name Service | 域名服务 |
 | DOM | Document Object Model | 文档对象模型 |
 | DBCP | Database Connection Pool | 数据库连接池 |
 | FIFO | First in, First out | 先进先出 |
 | FTP | File Transfer Protocol | 文件传输协议 |
 | GUI | Graphic User Interface | 图形用户接口 |
 | GUID | Globally Unique Identifier | 全球唯一标识符 |
 | HTTP | HyperText Transfer Protocol | 超文本传输协议 |
 | HTML | HyperText Markup Language | 超文本标记语言 |
 | HREF | HyperText Reference | 超文本链接 |
 | IDE | Integrated Development Environment | 集成开发环境 |
 | JMS | Java Message Service | Java消息服务 |
 | JSP | Java Server Pages | Java服务器页面 |
 | LAN | Local Area Network | 局域网 |
 | SQL | Structured Query Language | 结构化查询语言 |
 | SMTP | Simple Mail Transfer Protocol |  简单邮件传输协议 |
 | TCP/IP | Transmission Control Protocol/Internet Protocol | 传输控制协议和互联网协议 |
 | UI | User Interface | 用户界面 |
 | UT | Unit Testing | 单元测试 |
 | URI | Uniform Resource Identifier | 统一资源标识符 |
 | URL | Uniform Resource Location | 统一资源定位符 |
 | | |
 | LRU | Least Recently Used | 最近最久未使用，一种缓存调度算法 |
 
 