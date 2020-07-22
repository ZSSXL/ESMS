### 雇员薪酬管理系统
### Employee Salary Management System

---

**Author:**  ZSS

**UpdateTime:** 2020-07-10

##### Description:
```TXT
根据指定的需求设计并实现该系统
```

#### 问题与总结

1. 打包问题：

   将api的模块打jar包失败，因为其中依赖了base子模块，需要先将base子模块进行打jar包，并且要使用**mvn install:install-file**的命令将该jar包导入到本地仓库。

2. 还是打包问题：

   基于上面的方法api和base模块都打jar包成功，且已经导入到本地仓库。然后使用*mvn package*命令将service和web模块也打包，成功是成功了，但是使用*java -jar .jar*y运行jar的时候一直提示找不到**主清单属性**

   解决：因为使用的是自定义的parent，而不是spring-boot-starter-parent，所以在service和web模块的pom.xml文件的maven-plugin中添加如下内容：

   ````XML
   <build>
       <plugins>
           <plugin>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-maven-plugin</artifactId>
               <!-- 需要添加的内容 -->
               <!-- Repackages existing JAR and WAR archives so that they can be executed from the command line using  java -jar.  -->
               <executions>
                   <execution>
                       <goals>
                           <goal>repackage</goal>
                       </goals>
                   </execution>
               </executions>
           </plugin>
       </plugins>
   </build>
   ````

   