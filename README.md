<div align="center">
<h1>JFlow:超轻量级流程编排引擎</h1>
</div>

## 概念

## 特性

### AOP
在流程引擎使用时，用户常常有这样的诉求：在流程开始时通知或发个消息，在流程结束关闭一些资源，发一下通知；或在某个任务执行前校验下权限，执行后打印日志。
这些诉求如果是在编写代码时，容易想到的是使用AOP技术来增强，流程引擎将这种概念用编排配置的方式进行实现。
![image]("https://github.com/neason-cn/JFlow/blob/main/doc/images/aop.jpg")
## 使用