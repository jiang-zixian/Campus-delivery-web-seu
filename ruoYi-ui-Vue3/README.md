## 前言
- ruoyi-ui-Vue2和ruoyi-ui-Vue3文件夹为前端，这里我们选用vue3的。其余文件夹为后端，大家多看看代码。

## you must Know!
大家有什么想要记录的可以看留言板
留言板：https://typst.app/project/wTsaFeDxII7ht47qCAF0Ep

## how to run this project
### 后端
1. Redis下载
   下载和启动Redis服务器的链接
   https://www.runoob.com/redis/redis-install.html

这样即为启动成功，挂在后台即可
![alt text](readme_img/image.png)

2. 环境部署
```powershell
JDK >= 1.8 (jzx使用了22版本)
Mysql >= 5.7.0 (jzx忘了使用了什么版本，不过应该不重要)
Maven >= 3.0
```

高德api安装
```shell
npm install @vuemap/vue-amap --save
npm install @vuemap/vue-amap-loca --save
npm install @vuemap/vue-amap-extra --save
```

3. 将当前文件夹直接导入IDEA（vscode也可导入），将整个文件夹拖到IDEA的软件图标上即可导入

4. 数据库连接

   大家不用做什么了 ,直接在idea用可视化工具，方便查看数据库变化

   ```shell
   ip: 1.94.167.138
   
   username: root
   
   pwd: 123456
   ```
   再导入./sql文件夹下的两个sql文件，分别运行一下

5. 修改配置文件

   `.\Campus-delivery-web-seu\ruoyi-admin\src\main\resources\application-druid.yml`中修改刚刚创建的数据库的密码
   ![alt text](readme_img/image-2.png)

   `.\Campus-delivery-web-seu\ruoyi-admin\src\main\resources\application.yml`中
   ![alt text](readme_img/image-3.png)

   ![alt text](readme_img/image-4.png)

6. 运行`com.ruoyi.RuoYiApplication.java`即可

   若成功出现以下：

    ```txt
    (♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  
    .-------.       ____     __        
    |  _ _   \      \   \   /  /    
    | ( ' )  |       \  _. /  '       
    |(_ o _) /        _( )_ .'         
    | (_,_).' __  ___(_ o _)'          
    |  |\ \  |  ||   |(_,_)'         
    |  | \ `'   /|   `-'  /           
    |  |  \    /  \      /           
    ''-'   `'-'    `-..-'    
    ```

7. 数据库配置已经更改。项目jdbc可以连接数据库，记得更新推送

idea可以用可视化工具，方便查看数据库变化

ip: 47.99.193.146

username: root

pwd: 123

备注：jzx用
F:\Program Files\MySQL\MySQL Server 8.0\bin\mysqldump.exe


### 前端
```powershell
cd ruoyi-ui-Vue3

npm install

npm run dev
```

即可运行


**这时候可以看见连接了后端的网页，登录进去之后是这样**
![alt text](readme_img/image-5.png)

成功！大家要多看看代码怎么写的呀~



## 使用若依
* 前端采用Vue、Element UI。
* 后端采用Spring Boot、Spring Security、Redis & Jwt。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持加载动态权限菜单，多方式轻松权限控制。
* 高效率开发，使用代码生成器可以一键生成前后端代码。
* 提供了技术栈（[Vue3](https://v3.cn.vuejs.org) [Element Plus](https://element-plus.org/zh-CN) [Vite](https://cn.vitejs.dev)）版本[RuoYi-Vue3](https://github.com/yangzongzhuan/RuoYi-Vue3)，保持同步更新。

## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 缓存监控：对系统的缓存信息查询，命令统计等。
17. 在线构建器：拖动表单元素生成相应的HTML代码。
18. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

## 在线体验

- admin/admin123
- 在线体验
  演示地址：http://vue.ruoyi.vip  
  文档地址：http://doc.ruoyi.vip




