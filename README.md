# Mind-Palace
Mind Palace--Personal Knowledge Storage Center
# Mind Palace个人知识库系统开发日志

## 1 技术选型

1.1 前端技术选型

​	Vue CLI  +  Vue3 + Vue Router + VueX + AXIOS + Ant Design Vue + JavaScript + TypeScript

1.2 后端技术选型

​	Spring + Spring MVC + Mybatis + Mybatis-generator + SpringBoot

## 2 开发环境

| 开发工具                      | 说明                |
| ----------------------------- | ------------------- |
| IDEA 2021.3.2                 | Java后端开发工具IDE |
| WebStorm 2021.3.2             | 前端开发工具IDE     |
| Navicat                       | MySQL远程连接工具   |
| Another Redis Desktop Manager | Redis远程连接工具   |
| X-shell                       | Linux远程连接工具   |
| Xftp                          | Linux文件上传工具   |
| XMind                         | 项目思维导图        |
| Postman                       | 接口调试工具        |
| 腾讯云                        | 项目部署            |

| 开发环境   | 版本    |
| ---------- | ------- |
| JDK        | 1.8     |
| SpringBoot | 2.6.4   |
| MySQL      | 8.0.26  |
| Redis      | 6.0.5   |
| Git        | 2.32.0  |
| Node.js    | 14.17.4 |

## 3 开发流程

设计 --> 开发 --> 打包 --> 部署


## 4 目录结构

SQL文件位于根目录下的mind palace.sql，需要MYSQL8以上版本。

请先运行后端项目，再启动前端项目，前端项目配置由后端动态加载。

## 5 项目技术点
  使用SpringBoot2.6X开发
  1.使用Mybatis-Generator插件生成单表查询代码，简化开发流程
  2.遵循阿里巴巴Java开发规范
