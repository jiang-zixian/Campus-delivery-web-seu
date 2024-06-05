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
   ip: 47.99.193.146
   
   username: root
   
   pwd: 123
   
   备注：jzx用
   F:\Program Files\MySQL\MySQL Server 8.0\bin\mysqldump.exe
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



## 借鉴若依框架
* 前端采用Vue、Element UI。
* 后端采用Spring Boot、Spring Security、Redis & Jwt。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持加载动态权限菜单，多方式轻松权限控制。
* 高效率开发，使用代码生成器可以一键生成前后端代码。


