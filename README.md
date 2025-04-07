# JMeter Groovy Library 使用指南

## 项目简介

JMeter Groovy Library 是一个为 JMeter 用户提供的 Groovy 脚本工具库，旨在简化测试脚本的编写和管理。通过提供一系列常用的工具类，用户可以更高效地处理 JSON、加密、HTTP 请求、断言、日志记录、时间处理、随机数生成等常见任务。

## 目录结构

- `lib/`: 包含所有的Groovy工具类文件。
- `src/main/groovy/`: 包含具体的工具类文件，如`com.jmeter.utils.JsonUtils.groovy`、`com.jmeter.utils.EncryptUtils.groovy`等。
- `src/main/example/`: 包含示例文件，如`demo_case.groovy`。
- `build/`: 存放构建生成的文件。
- `gradle/`: Gradle相关的配置文件。

## 使用方法

1. **下载项目**
   
   克隆此项目到本地：
   ```bash
   git clone <repository-url>
   ```

2. **配置JMeter**

   将`lib`目录下的所有Groovy文件复制到JMeter的`lib`目录中，通常路径为：
   ```
   JMETER_HOME/lib/
   ```

3. **在JMeter中使用Groovy脚本**

   在JMeter的测试计划中，您可以通过以下方式调用这些工具类：
   
   ```groovy
   import static your.package.name.ClassName
   
   // 使用工具类中的方法
   ClassName.methodName()
   ```

   请根据具体的工具类和方法进行替换。

## 常用工具类

- `com.jmeter.utils.JsonUtils.groovy`: 提供JSON处理的实用方法。
- `com.jmeter.utils.EncryptUtils.groovy`: 提供加密相关的实用方法。
- `com.jmeter.utils.HttpUtils.groovy`: 提供HTTP请求的实用方法。
- `com.jmeter.utils.AssertUtils.groovy`: 提供断言相关的实用方法。
- `com.jmeter.utils.LogUtils.groovy`: 提供日志记录的实用方法。
- `com.jmeter.utils.TimeUtils.groovy`: 提供时间处理的实用方法。
- `com.jmeter.utils.RandomUtils.groovy`: 提供随机数生成的实用方法。
- `com.jmeter.utils.CommonUtils.groovy`: 提供通用的实用方法。

## 贡献

欢迎提交Pull Request来贡献代码，或报告问题。

## 打包使用方式

要打包此项目，可以使用以下命令：

```bash
# 进入项目目录
cd jmeter_groovy_lib

# 使用 Gradle 进行打包
./gradlew build

# 打包完成后，所有的 jar 文件将位于 build/libs 目录中
```

将`build/libs`目录下的所有JAR文件复制到JMeter的`lib`目录中，通常路径为：
```
JMETER_HOME/lib/
```
在JMeter的测试计划中，您可以通过以下方式调用这些工具类：
```groovy
   // 使用工具类中的方法
   // 生成当前时间戳（毫秒）
   def timestamp = System.currentTimeMillis();
   // 将值存入 JMeter 变量
   vars.put("timestamp",timestamp.toString());
   
   import com.jmeter.utils.EncryptUtils;
   
   // 使用 EncryptUtils 的方法
   String hash = EncryptUtils.md5("test");
   
   vars.put("hash_data",hash.toString());
   
   import com.jmeter.utils.RandomUtils;
   
   
   String phone = RandomUtils.randomPhone();
   String uuid = RandomUtils.uuid();
   
   
   vars.put("phone",phone.toString());
   vars.put("uuid",uuid);

```

打包后的文件可以直接用于 JMeter 的 lib 目录中，以便在测试计划中调用。 
