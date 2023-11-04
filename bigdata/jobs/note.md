#command

# kafka   --zookeeper/--bootstrap-server
## 查#command
    
    # kafka   --zookeeper/--bootstrap-server
    ## 查看topic
    kafka-topics --bootstrap-server 10.2.98.128:9092 --list
    kafka-topics --zookeeper 10.2.98.129:2181,10.2.98.130:2181,10.2.98.128:2181 --describe --topic test4
    ## 创建一个topic	
    kafka-topics --create --zookeeper 10.2.98.129:2181,10.2.98.130:2181,10.2.98.128:2181 --replication-factor 1 --partitions 3 --topic assettopic7
    ## 在命令行打开生产者
    kafka-console-producer --broker-list 10.2.98.129:9092,10.2.98.130:9092,10.2.98.131:9092 --topic test4
    kafka-console-producer --broker-list 10.2.111.54:9092 --topic test4
    ## 修改topic的分区数量,只能增加，不能减少
    kafka-topics --zookeeper 10.2.98.129:2181,10.2.98.130:2181,10.2.98.128:2181 --topic ldd-test --partitions 5
    ## 命令行打开消费者
    kafka-console-consumer --bootstrap-server 10.2.98.129:9092,10.2.98.130:9092,10.2.98.131:9092 --topic test4 [--from-beginning]
    kafka-console-consumer --bootstrap-server 10.2.111.54:9092 --topic test4
    
    
    
    
    -------------
    # hive
    ## 开启动态分区
    set hive.exec.dynamic.partition=true;
    set hive.exec.dynamic.partition.mode=nonstrict; 
    
    set hive.auto.convert.join = false;  -- 取消把小表加载到内存中
    set hive.cli.print.current.db=true;  -- 显示hive当前所在的数据库
    
    ## 中文问题
    hive默认是UTF8编码,如果从文件中导入数据,文件不是UTF8的,会出现中文乱码，
    这时可以使用以下的方法解决问题
    -- 建表时指定编码集,之后再把数据导入到目标表
    CREATE EXTERNAL TABLE student8(id STRING, name STRING) 
    ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe' 
    WITH SERDEPROPERTIES("field.delim"=',',"serialization.encoding"='GBK')
    
    
    -------------
    # hbase
    ## 查询列族
    scan 'historyfundrec',{COLUMN=>'07',LIMIT=>10}
    ## 查询列包含指定字符串
    scan 'historyfundrec',{FILTER=>"QualifierFilter(=,'substring:07')",LIMIT=>8}
    ## 查询列族:列
    scan 'historyfundrec',{COLUMN=>'07:053',LIMIT=>10}
    ## 查询指定名称开头的列
    scan 'historyfundrec', {FILTER=>"ColumnPrefixFilter('053')",LIMIT=>10}
    ## 查询以多个名称开头的列, 053 or 041
    scan 'historyfundrec', {FILTER=>"MultipleColumnPrefixFilter('053','041')",LIMIT=>10}
    ## 查询包含指定字符串的rowkey
    scan 'historyfundrec', {FILTER=>"RowFilter(=,'substring:00')",LIMIT=>10}
    ## 查询>=< 指定字符串的rowkey
    scan 'historyfundrec', {FILTER=>"RowFilter(>,'binary:00')",LIMIT=>10}
    ## 查询值包含指定字符串的数据
    scan 'historyfundrec', {FILTER=>"ValueFilter(=,'substring:00')",LIMIT=>10}
    ## 查询值与指定字符串相等的数据
    scan 'historyfundrec', {FILTER=>"ValueFilter(>,'binary:00')",LIMIT=>10}
    
    scan 'historyfundrec',{FILTER=>"QualifierFilter(=,'substring:07')",LIMIT=>8}
    
    
    # git
    # 把暂存区的文件撤销到工作区 *.java 表示操作所有java文件， . 表示操作当前目录的全部文件
    git restore --staged <file>/*.java/.
    # 提交暂存区的的文件到本地仓库   # -a 会把暂存区的的文件全部提交到本地仓库,否则只提交新增的文件,修改的文件不会被提交
    git commit -m 'message' [-a]  
    
    
    
    
    
    
    <!--记录maven常用插件-->
    <pluginManagement>
    
        <plugins>
            <plugin>
                <!--spring-boot-maven-plugin 插件以Maven的方式为Springboot应用提供支持，
                能够将Springboot应用打包为可执行的jar或war文件，进行相应部署后即可启动Springboot应用 -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <mainClass>
                        <!--com.star.DemoSpringBootServerApplication-->   <!-- springboot 项目的主类 -->
                    </mainClass>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
    
            <!-- 指定maven编译的jdk版本,如果不指定,maven3默认用jdk 1.5 maven2默认用jdk1.3-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <!-- 一般而言，target与source是保持一致的，但是，有时候为了让程序能在其他版本的jdk中运行(对于低版本目标jdk，源代码中不能使用低版本jdk中不支持的语法)，会存在target不同于source的情况 -->
                    <source>1.8</source> <!-- 源代码使用的JDK版本 -->
                    <target>1.8</target> <!-- 需要生成的目标class文件的编译版本 -->
                    <encoding>UTF-8</encoding><!-- 字符集编码 -->
                    <skip>true</skip><!-- 跳过测试 -->
                    <showWarnings>true</showWarnings>
                    <meminitial>128m</meminitial><!-- 编译器使用的初始内存 -->
                    <maxmem>512m</maxmem><!-- 编译器使用的最大内存 -->
                </configuration>
            </plugin>
    
        </plugins>
    
    </pluginManagement>看topic
kafka-topics --bootstrap-server 10.2.98.128:9092 --list
kafka-topics --zookeeper 10.2.98.129:2181,10.2.98.130:2181,10.2.98.128:2181 --describe --topic test4
## 创建一个topic	
kafka-topics --create --zookeeper 10.2.98.129:2181,10.2.98.130:2181,10.2.98.128:2181 --replication-factor 1 --partitions 3 --topic assettopic7
## 在命令行打开生产者
kafka-console-producer --broker-list 10.2.98.129:9092,10.2.98.130:9092,10.2.98.131:9092 --topic test4
kafka-console-producer --broker-list 10.2.111.54:9092 --topic test4
## 修改topic的分区数量,只能增加，不能减少
kafka-topics --zookeeper 10.2.98.129:2181,10.2.98.130:2181,10.2.98.128:2181 --topic ldd-test --partitions 5
## 命令行打开消费者
kafka-console-consumer --bootstrap-server 10.2.98.129:9092,10.2.98.130:9092,10.2.98.131:9092 --topic test4 [--from-beginning]
kafka-console-consumer --bootstrap-server 10.2.111.54:9092 --topic test4




-------------
# hive
## 开启动态分区
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict; 

set hive.auto.convert.join = false;  -- 取消把小表加载到内存中
set hive.cli.print.current.db=true;  -- 显示hive当前所在的数据库

## 中文问题
hive默认是UTF8编码,如果从文件中导入数据,文件不是UTF8的,会出现中文乱码，
这时可以使用以下的方法解决问题
-- 建表时指定编码集,之后再把数据导入到目标表
CREATE EXTERNAL TABLE student8(id STRING, name STRING) 
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe' 
WITH SERDEPROPERTIES("field.delim"=',',"serialization.encoding"='GBK')


-------------
# hbase
## 查询列族
scan 'historyfundrec',{COLUMN=>'07',LIMIT=>10}
## 查询列包含指定字符串
scan 'historyfundrec',{FILTER=>"QualifierFilter(=,'substring:07')",LIMIT=>8}
## 查询列族:列
scan 'historyfundrec',{COLUMN=>'07:053',LIMIT=>10}
## 查询指定名称开头的列
scan 'historyfundrec', {FILTER=>"ColumnPrefixFilter('053')",LIMIT=>10}
## 查询以多个名称开头的列, 053 or 041
scan 'historyfundrec', {FILTER=>"MultipleColumnPrefixFilter('053','041')",LIMIT=>10}
## 查询包含指定字符串的rowkey
scan 'historyfundrec', {FILTER=>"RowFilter(=,'substring:00')",LIMIT=>10}
## 查询>=< 指定字符串的rowkey
scan 'historyfundrec', {FILTER=>"RowFilter(>,'binary:00')",LIMIT=>10}
## 查询值包含指定字符串的数据
scan 'historyfundrec', {FILTER=>"ValueFilter(=,'substring:00')",LIMIT=>10}
## 查询值与指定字符串相等的数据
scan 'historyfundrec', {FILTER=>"ValueFilter(>,'binary:00')",LIMIT=>10}

scan 'historyfundrec',{FILTER=>"QualifierFilter(=,'substring:07')",LIMIT=>8}


# git
# 把暂存区的文件撤销到工作区 *.java 表示操作所有java文件， . 表示操作当前目录的全部文件
git restore --staged <file>/*.java/.
# 提交暂存区的的文件到本地仓库   # -a 会把暂存区的的文件全部提交到本地仓库,否则只提交新增的文件,修改的文件不会被提交
git commit -m 'message' [-a]  






<!--记录maven常用插件-->
<pluginManagement>

    <plugins>
        <plugin>
            <!--spring-boot-maven-plugin 插件以Maven的方式为Springboot应用提供支持，
            能够将Springboot应用打包为可执行的jar或war文件，进行相应部署后即可启动Springboot应用 -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <mainClass>
                    <!--com.star.DemoSpringBootServerApplication-->   <!-- springboot 项目的主类 -->
                </mainClass>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

        <!-- 指定maven编译的jdk版本,如果不指定,maven3默认用jdk 1.5 maven2默认用jdk1.3-->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.1</version>
            <configuration>
                <!-- 一般而言，target与source是保持一致的，但是，有时候为了让程序能在其他版本的jdk中运行(对于低版本目标jdk，源代码中不能使用低版本jdk中不支持的语法)，会存在target不同于source的情况 -->
                <source>1.8</source> <!-- 源代码使用的JDK版本 -->
                <target>1.8</target> <!-- 需要生成的目标class文件的编译版本 -->
                <encoding>UTF-8</encoding><!-- 字符集编码 -->
                <skip>true</skip><!-- 跳过测试 -->
                <showWarnings>true</showWarnings>
                <meminitial>128m</meminitial><!-- 编译器使用的初始内存 -->
                <maxmem>512m</maxmem><!-- 编译器使用的最大内存 -->
            </configuration>
        </plugin>

    </plugins>

</pluginManagement>