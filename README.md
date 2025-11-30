

## 运行测试
```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=LoginTest

# 在headless模式下运行
# 修改config.properties中的headless=true

## Allure报告集成

框架已集成Allure报告系统，提供详细的测试报告和可视化分析。

### 生成报告步骤：

1. 运行测试：
   ```bash
   mvn clean test

# 运行特定测试套件
mvn test -DsuiteXmlFile=src/test/resources/testng.xml

# 只运行登录测试
mvn test -Dtest=LoginTest

# 生成并查看报告
mvn test allure:report allure:serve

# 运行测试
mvn clean test

# 生成Allure报告
mvn allure:report

# 打开Allure报告
mvn allure:serve

# 运行特定测试套件
mvn test -DsuiteXmlFile=src/test/resources/testng.xml

# 只运行登录测试
mvn test -Dtest=LoginTest

# 生成并查看报告
mvn test allure:report allure:serve

## 🔄 并行执行和重试机制

### 并行执行特性
- **多线程支持**: 同时启动多个浏览器实例
- **线程安全**: 使用ThreadLocal确保线程隔离
- **灵活配置**: 可配置线程数量和并行模式
- **跨浏览器测试**: 支持同时测试多种浏览器

### 重试机制特性
- **自动重试**: 失败的测试用例自动重试
- **可配置重试次数**: 通过配置文件设置重试次数
- **智能重试**: 只在测试失败时重试

### 配置示例
```properties
# 并行配置
parallel.execution=true
parallel.mode=methods
thread.count=3

# 重试配置
retry.count=2

# 浏览器配置
browser=chrome
headless=false

# 运行重试演示测试
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-retry-demo.xml

# 运行所有测试（包含重试机制）
mvn clean test

# 设置特定的重试次数
mvn test -Dretry.count=3

# 运行单个重试测试类
mvn test -Dtest=RetryDemoTest

