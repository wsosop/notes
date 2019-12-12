## Maven

**Maven基础概念**
**• Maven开发流程**
**–新建Maven项目**
**–在中央仓库查找第三方jar的依赖文本**
**–拷贝依赖文本至项目的pom.xml**
**–执行maven build，编译/构建整个项目**
**• Maven是一个软件，http://maven.apache.org/ 下载**
**• Maven也有一个中心仓库，https://mvnrepository.com/。**
**–包含很多第三方软件。可以有很多第三方的Maven仓库。**
**• Maven是一个构建工具，自动下载中心仓库的jar文件，存在本地进行管理，编译、测试、运行、和打包发布Java项目。**



**POM( Project Object Model )**
**• XML格式**
**• 包含了项目信息、依赖信息、构建信息**
**• 构件信息(artifact)**
**–groupId：组织**
**–artifactId：产品名称**
**–version：版本**



**Maven项目的目录结构**
**• 基本目录结构**
**–src**
**• main**
**–java/ 存放java文件**
**–resources/ 存放程序资源文件**
**• test/**
**–java/ 存放测试程序**
**–resources/ 存放测试程序资源文件**
**–pom.xml**

**• 设置信息**
**–groupId：组织名**
**–artifactId：作品(项目)名**
**–Name：别名(optional)**
**–Description: 描述(optional)**

*OpenCC4j 可以简体中文转成 繁体中文。这个是一个库 可以在maven仓库上找到*

**Maven构建生命周期**
**• 清理**
**• 编译**
**• 测试**
**• 打包**
**• 安装**
**• 部署**



**Maven总结**
**• 总结**
**–理解Maven基础概念，如构件 、仓库等**
**–理解Maven在项目中的作用**
**–掌握Maven项目创建、编译和运行**

