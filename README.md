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
.@Action: 控制器类方法注解 eg:@Action("get:/customer_create) @Action("post:/customer_create)<br>
.@Service: 服务类注解<br>
.@Inject: 依赖注入注解，在控制器类中将服务类依赖注入进来<br>
# <h4> ClassHelper 类操作助手类 <br>
    1. 由于在smart.properties 配置文件中制定了应用的基础包名，通过ClassUtil加载的类都需要基于该基础包名.
    2. 所以有必要提供一个类操作助手类，让它分别获取应用包名下的所有类，所有Controller类，Service类.
    3. 此外，我们可以将带有@Controller @Service 注解的类所产生的对象，让Smart框架进行管理Bean.
# <h4> ReflectionUtil 反射工具类 <br>
    1. 使用ClassHelper 类可以获取所加载的类，但无法通过类来实例化对象
    2. 因此，需要提供一个反射工具类，封装Java反射API，对外提供更好的工具方法
# <h4> BeanHelper Bean助手类 <br>
    1. BeanHelper 相当于一个‘Bean容器’.
    2. Bean Map 中存放了Bean类与Bean 实例的映射关系，我们只需要通过getBean方法，传入一个Bean类，就能获取到Bean实例.
# <h4> IocHelper DI(依赖注入)助手类 <br>
    1. 我们在Controller中定义Service成员变量，然后再Controller的Action方法中调用Service的成员变量，使用@Inject注入Service实例.
    2. 不是通过开发者 new 的方式来实例化，而是通过框架本身来进行实例化，这种实例化过程被称为IOC(控制反转)，控制不是开发者来决定，而是反传给了框架.
    3. 一般地，也将控制反转成为DI（依赖注入），理解为将某个类需要依赖的成员注入到这个类中.
# <h5> 如何实现依赖注入呢？<br>
        1. 通过BeanHelper 获取所有的Bean Map (是一个Map(Class<?>,Object)类与实例对象的映射关系).
        2. 遍历Bean Map 分别取出Bean类与Bean 实例，进而通过反射获取类中的成员变量.
        3. 继续遍历这些成员变量，在循环中判断当前成员变量是否带有@Inject注解，若带有该注解，则从Bean Map 中根据Bean 类获取Bean 实例.
        4. 通过ReflectionUtil#setField方法修改当前成员变量的值.

# <h4> 加载Controller？<br>
处理逻辑：<br>
1.通过ClassHelper，我们可以获取所有定义了Controller注解的类，可以通过反射获取该类中所有带有@Action注解的方法，获取@Action中请求表达式<br>
2.进而通过表达式获取请求方法与请求路径，封装请求对象（Request），处理对象（Handler）,最后将Request与Handler建立一个映射关系，放入ActionMa怕，并提供一个可根据请求方法与请求路径获取处理对象的方法.<br>
# <h4> ControllerHelper控制器助手类<br>
1. ControllerHelper中封装了一个Action Map, 通过它来存放Request 与Handler 的映射关系，然后通过ClassUtil获取所有带有@Controller的类.<br>
2. 遍历Controller类 从带有@Action的方法的注解中提取URL，最后初始化Request与Handler之间的映射关系.

# <h3> 初始化框架<br>
我们已经创建了ClassHelper、BeanHelper、IocHelper、ControllerHelp， 这四个Helper类需要通过一个入口程序来加载他们，实际上就是加载他们的静态代码块！<br>
# <h4> HelperLoader 加载Helper助手类<br>
使用HelperLoader中的init()加载Helper类。实际上，当我们第一次访问类时，就会记载static代码块，这里只是为了让加载更为集中，所以才构建了HelperLoader！<br>
# <h3> 请求转发器<br>
1. 现在需要编写一个Servlet,让它来处理所有的请求。<br>
2. 从HttpServletRequest对象中获取请求方法和请求路径，通过ControllerHelper#getHandler方法获取Handler对象。<br>
3. 当拿到Handler对象以后，我们可以方便的获取Controller的类，进而通过BeanHelper.getBean方法获取Controller的实例对象。<br>
4. 从HttpServletRequest对象中获取所有的请求参数，并将其初始化到一个名为Param的参数对象中。<br>
