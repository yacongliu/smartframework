# smartframework
本 project 为根据黄勇《架构探险》一书中Code 搭建！<br>
ClassUtil:加载基础包名下的所有类，比如使用了某注解的类，或者实现了某接口的类，再或者继承了某父类的所有子类<br>
annotation包中的注解释义：<br>
# <h4>Java元注解：@Retention @Target @Document @Inherited；<br>
　　@Target:注解的作用目标<br>
　　　　　　　　@Target(ElementType.TYPE)   //接口、类、枚举、注解<br>
　　　　　　　　@Target(ElementType.FIELD) //字段、枚举的常量<br>
　　　　　　　　@Target(ElementType.METHOD) //方法<br>
　　　　　　　　@Target(ElementType.PARAMETER) //方法参数<br>
　　　　　　　　@Target(ElementType.CONSTRUCTOR)  //构造函数<br>
　　　　　　　　@Target(ElementType.LOCAL_VARIABLE)//局部变量<br>
　　　　　　　　@Target(ElementType.ANNOTATION_TYPE)//注解<br>
　　　　　　　　@Target(ElementType.PACKAGE) ///包   <br>
<br>
　　@Retention：注解的保留位置<br>　　　　　
         @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含<br>
　　　　　@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，<br>
　　　　　@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到<br><br>
        @Document：说明该注解将被包含在javadoc中<br>　 
　       @Inherited：说明子类可以继承父类中的该注解<br>　
<br>