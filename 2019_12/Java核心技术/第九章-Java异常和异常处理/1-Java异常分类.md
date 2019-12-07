## Java异常分类

#### 一：

------

**• 异常：程序不正常的行为或者状态。**

**–int a = 5/0;–数组越界访问**

**–读取文件，结果该文件不存在**

**• 异常处理**

**–程序返回到安全状态**

**–允许用户保存结果，并以适当方式关闭**



#### 二[本目录下有一张异常分类图]：

------

**• Throwable:所有错误的祖先**
**• Error:系统内部错误或者资源耗尽。不管。**
**• Exception: 程序有关的异常。重点关注。**
    **–RuntimeException: 程序自身的错误**
**• 5/0，空指针，数组越界…**
    **–非RuntimeException：外界相关的错误**
**• 打开一个不存在文件**
**• 加载一个不存在的类...**



#### 三：

------

**• Unchecked Exception : (编译器不会辅助检查的，需要程序员自己管的)异常，包括Error子类和RuntimeException子类。**
**• 非RuntimeException的Exception的子类: (编译器会辅助检查的)异常，checked exception。**
**• 注意: 编译器会检查程序是否为checked exception 配置了处理。如果没有处理，会报错。**

**• Checked Exception(非RuntimeException的Exception的子类)，程序员必须处理，以发生后处理为主。编译器会**
**辅助检查。**
**• Unchecked Exception中的RuntimeException子类，程序必须处理，以预防为主。编译器不关心此类异常，也**
**不会辅助检查。**
**• Error的子类，可以不用处理。**

#### 四：

------

**• 总结**
    **–异常是程序发生不正常的行为或出现不正常的状态**
    **–Java异常分成Exception(程序相关)和Error(系统相关)**
    **–Java程序相关的异常又分成unchecked 异常和checked异常，掌握其不同的处理原则**

​    **–编译器会辅助检查checked 异常**

