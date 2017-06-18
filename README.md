# smartframework
本 project 为根据黄勇《架构探险》一书中Code 搭建！<br>
ClassUtil:加载基础包名下的所有类，比如使用了某注解的类，或者实现了某接口的类，再或者继承了某父类的所有子类<br>
annotation包中的注解释义：<br>
# <h3>Java元注解：@Retention @Target @Document @Inherited；<br>
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
# <h3> Smart-Framework 框架注解方式
.@Controller : 控制器 <br>
.@Action: 控制器类方法注解<br>
.@Service: 服务类注解<br>
.@Inject: 依赖注入注解，在控制器类中将服务类依赖注入进来<br>
# <h5> ClassHelper 类操作助手类 <br>
    1. 由于在smart.properties 配置文件中制定了应用的基础包名，通过ClassUtil加载的类都需要基于该基础包名.<br>
    2. 所以有必要提供一个类操作助手类，让它分别获取应用包名下的所有类，所有Controller类，Service类.<br>
    3. 此外，我们可以将带有@Controller @Service 注解的类所产生的对象，让Smart框架进行管理Bean.<br>
