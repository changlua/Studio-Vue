<p align="center">
	<img alt="logo" style="width:80px;height:80px" src="document/images/logo.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Studio-Vue V1.0.0</h1>
<p align="center">
    <a href="https://github.com/changlua/studio-vue/releases/tag/v1.0.0"><img src="document/images/head/version.svg"></a>
	<a href="https://github.com/changlua/studio-vue/blob/master/LICENSE"><img src="document/images/head/MIT.svg"></a>
</p>




# 前言

去年在工作室偶然听到其他工作室要做一个官网，接着趁着一时兴起然后也找了个模板来做了一个自己的官网以及一个后台管理系统，当时的后台系统只有一个登录、注册还有一个提交个人信息的页面，主要为了方便展示动态汇总团队成员。

想要将这个工作室管理系统继续做下去我自己总结了几个原因：①再此之前我也在gitee、github上搜索了相关学校工作室、实验室管理系统，没有发现比较完善的，较好的。②为了在简历上有些亮点。③恰逢大专毕业设计；目前转本后进入本科还有两年的时间，相对空余时间较多。④将自己所学到的一个技术能够有一个应用场景，助推自己的技术学习与进步。

今年也正好趁毕业设计这个为主题继续进行了开发，耗时近一个月将后台管理系统进行了初步完善。

该工作室系统主要面向校园工作室，可供个人及工作室团队学习使用。

**鸣谢**：

- 感谢 [RUOYI](https://gitee.com/y_project/RuoYi) 提供的后台管理系统模板以及后台路由、权限管理。【项目功能引用了若依的角色、菜单管理】
- 感谢项目logo：[来源网站地址](https://www.flaticon.com/search?word=deer)、作者[Hery Mery](https://www.flaticon.com/authors/hery-mery)

# 项目介绍
## 项目演示

### 官网

<table>
    <tr>
        <td><img src="document/images/offical-1.png"/></td>
        <td><img src="document/images/offical-2.png"/></td>
    </tr>
    <tr>
        <td><img src="document/images/offical-3.png"/></td>
        <td><img src="document/images/offical-4.png"/></td>
    </tr>
    <tr>
        <td><img src="document/images/offical-5.png"/></td>
        <td><img src="document/images/offical-6.png"/></td>
    </tr>
	<tr>
        <td><img src="document/images/offical-7.png"/></td>
    </tr>	 
</table>



### 后台管理系统

<table>
    <tr>
        <td><img src="document/images/bg-1.png"/></td>
        <td><img src="document/images/bg-2.png"/></td>
    </tr>
    <tr>
        <td><img src="document/images/bg-3.png"/></td>
        <td><img src="document/images/bg-4.png"/></td>
    </tr>
    <tr>
        <td><img src="document/images/bg-5.png"/></td>
        <td><img src="document/images/bg-6.png"/></td>
    </tr>
	<tr>
        <td><img src="document/images/bg-7.png"/></td>
    <td><img src="document/images/bg-8.png"/></td>
    </tr>	 
    <tr>
        <td><img src="document/images/bg-9.png"/></td>
    <td><img src="document/images/bg-10.png"/></td>
    </tr>	 
    <tr>
        <td><img src="document/images/bg-11.png"/></td>
    <td><img src="document/images/bg-12.png"/></td>
    </tr>	 
    <tr>
        <td><img src="document/images/bg-13.png"/></td>
    <td><img src="document/images/bg-14.png"/></td>
    </tr>	 
    <tr>
        <td><img src="document/images/bg-15.png"/></td>
    </tr>	 
</table>

## 技术选型

### 后端技术

|      技术      |            说明             |                          官网                          |
| :------------: | :-------------------------: | :----------------------------------------------------: |
|   SpringBoot   | 快速集成框架，使用web启动器 |         https://spring.io/projects/spring-boot         |
|  MybatisPlus   |           ORM框架           |                 https://baomidou.com/                  |
| SpringSecurity |           log4j2            |    https://spring.io/projects/spring-security#learn    |
|      JWT       |          登录鉴权           |              https://github.com/jwtk/jjwt              |
|     log4j2     |          日志框架           | https://logging.apache.org/log4j/2.x/manual/index.html |
|   PageHelper   |          分页插件           |             https://pagehelper.github.io/              |
|      OSS       |       第三方对象存储        |     https://github.com/aliyun/aliyun-oss-java-sdk      |
|     Lombok     |      简化对象封装工具       |               https://projectlombok.org/               |
|      poi       |          Excel工具          |                https://poi.apache.org/                 |
|  easy-captcha  |       验证码生成工具        |        https://gitee.com/ele-admin/EasyCaptcha         |
|     redis      |         缓存中间件          |                   https://redis.io/                    |

### 前端技术

|    技术    |            说明             |                             官网                             |
| :--------: | :-------------------------: | :----------------------------------------------------------: |
|    Vue2    |        前端主流框架         |                      https://vuejs.org/                      |
| Element-UI |        饿了吗UI框架         |                  https://element.eleme.io/                   |
|  Echarts   |       Echarts图表框架       |           https://echarts.apache.org/zh/index.html           |
|   Axios    |        前端HTTP框架         |                   http://www.axios-js.com/                   |
| js-cookie  |       cookie管理工具        |            https://github.com/js-cookie/js-cookie            |
| jsencrypt  | 加密解密工具，非对称加密RSA |             https://github.com/travist/jsencrypt             |
| nprogress  |         进度条控件          |            https://github.com/rstacruz/nprogress             |
|   live2d   |           看板娘            | 集成vue教程：https://blog.csdn.net/hk1052606583/article/details/122718918 |

### 运维部署
|  技术  |            说明            |              官网              |
| :----: | :------------------------: | :----------------------------: |
| Nginx  |       静态资源服务器       | https://github.com/nginx/nginx |
| Docker | 应用容器引擎(快速隔离部署) |    https://www.docker.com/     |

### 开发进度

![image-20220610224906634](document/images/development-pogress.png)    

# 内置功能

> 工作室官网

前台页面：

+ 首页：banner图展示、证书展示、
+ 时光轴页
+ 团队页(动态数据获取展示)

> 工作室后台管理系统

首页（Echarts数据展示）：工作室数据统计；依据年级，专业获奖证书以及竞赛统计图表展示；

个人信息页：更新个人信息、修改密码

个人管理模块：

+ 信息录入：成员信息录入，用于官网展示
+ 获奖证书：查询、新增、编辑、修改、删除
+ 个人竞赛：查询、新增、编辑、修改、删除
+ 个人心得：查询、新增、编辑、修改、删除

工作室管理模块：

+ 专业管理：查询、新增、编辑、修改、删除
+ 年级管理：查询、新增、编辑、修改、删除
+ 获奖证书管理：查询、修改、导出
+ 竞赛管理（录入团队比赛）：新增、修改、删除、导出
+ 成员管理：查询、编辑、修改、删除、注销，激活，删除账号、新建账号、导出用户、转让负责人、重置密码、数据备份

系统管理模块（引用若依）：

+ 菜单管理：查询、添加、修改、删除
+ 角色管理：查询、添加、修改、删除、修改状态；
  + 分配用户：查询、批量授权

> 其他

文件上传接口：支持本地或者阿里云OSS存储，仅需配置下即可。

# 项目文档

文档地址：待更新

API文档：https://www.apifox.cn/apidoc/shared-456aee99-6931-48e5-bdd4-89c012d0178c

数据库表关系设计：

![image-20220612200752632](document/images/table-rel.png)  

数据库表字段设计：

![image-20220612200813685](document/images/table-filed.png)  

# 环境搭建

## 本地运行

> 本地环境

环境：Windows系统

开发工具：IDEA2020

项目构建工具：Maven

数据库：MySQL 5.7、Redis

> 本地环境构建运行

1、克隆本项目

```shell
# github仓库
git clone https://github.com/changlua/Studio-Vue.git

# gitee仓库
git clone https://gitee.com/changluJava/studio-vue.git
```

2、





## Linux服务器部署

待更新...

# 交流学习

