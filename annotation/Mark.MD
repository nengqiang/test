
 ###注解的基础

  1. 注解的定义：  
      * Java文件叫做Annotation，用`@interface`来表示。  
      * 注解的属性也叫成员变量。注解只有成员变量，没有方法。
  
  2. 元注解：`@interface`上面需要注解上一些东西，包括`@Retention`、`@Target`、`@Documented`、`@Inherited`、`@Repeatable`五种。  
  
  - **@Retention**：  
      注解的保留策略，解释说明了注解的存活时间：  
      ~~~
      * @Retention(RetentionPolicy.SOURCE)  注解仅存在于源码中，在class字节码文件中不存在。
      * @Retention(RetentionPolicy.CLASS)   默认的保留策略，注解会在class字节码文件中存在，但不会被加载到JVM中，所以运行时无法获得。
      * @Retention(RetentionPolicy.RUNTIME) 注解会在class字节码文件中存在，会被加载进入JVM中，所以能在运行时通过反射获得。
      ~~~
      
  - **@Target**  
      指定了注解的作用目标：
      ~~~~
      * @Target(ElementType.TYPE)            接口、类、枚举、注解
      * @Target(ElementType.FIELD)           字段、枚举的常量
      * @Target(ElementType.METHOD)          方法
      * @Target(ElementType.PARAMETER)       方法参数
      * @Target(ElementType.CONSTRUCTOR)     构造函数
      * @Target(ElementType.LOCAL_VARIABLE)  局部变量
      * @Target(ElementType.ANNOTATION_TYPE) 注解
      * @Target(ElementType.PACKAGE)         包
      ~~~~
      
  - **@Documented**：  
      将注解中的元素包含到javadoc中
      
  - **@Inherited**  
      这个注解表示子类能继承父类的注解
      
  - **@Repeatable**：  
      注解的值可以同时取多个
      ~~~      
      @Repeatable(Persons.class)
      @interface Person {
          String role default "";
      }
      
      @Person(role="artist")
      @Person(role="coder")
      @Person(role="PM")
      public class SuperMan {
      }
      ~~~