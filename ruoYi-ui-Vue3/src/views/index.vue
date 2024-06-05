<template>
  <div class="app-container home">
    <el-carousel :interval="4000" type="card" height="220px">
      <el-carousel-item v-for="item in urls" :key="item">
        <el-image style="width: 100%; height: 100%" :src="item" fit="none" />
      </el-carousel-item>
    </el-carousel>
    <el-divider />
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>校园跑腿送系统</h2>
        <p>
          校园跑腿送系统旨在为校园内的学生和教职员工提供在线物品订购和定点配送服务。整体需求包括用户能够方便选择校内商品、下单跑腿服务并实时跟踪订单状态，商家能够管理菜单、处理订单和进行收款结算，以及建立一个智能的配送系统以确保商品能够及时、定点送达。此外，平台还提供审核商家、管理用户和订单、数据分析等管理功能。
        </p>
<!--        <p>-->
<!--          <b>当前版本:</b> <span>v{{ version }}</span>-->
<!--        </p>-->
<!--        <p>-->
<!--          <el-tag type="danger">&yen;免费开源</el-tag>-->
<!--        </p>-->

        <br>
        <p>作者团队：</p>
        <div class="demo-type">
          <div>
            <el-avatar
                :src="avatar1"
            />
            <p>江紫弦</p>
          </div>
          <div>
            <el-avatar
                :src="avatar2"
            />
            <p>薛沛林</p>
          </div>
          <div>
            <el-avatar
                :src="avatar3"
            />
            <p>石倬凡</p>
          </div>
          <div>
            <el-avatar
                :src="avatar4"
            />
            <p>徐国伟</p>
          </div>
          <div>
            <el-avatar
                :src="avatar5"
            />
            <p>吴洁</p>
          </div>
          <div>
            <el-avatar
                :src="avatar6"
            />
            <p>孙彦林</p>
          </div>
        </div>
        <br>
        <p>
          <el-button
            type="primary"
            icon="Cloudy"
            plain
            @click="goTarget('https://github.com/jiang-zixian/Campus-delivery-web-seu')"
            >访问码云</el-button
          >
          <el-button
            icon="HomeFilled"
            plain
            @click="goTarget('https://github.com/jiang-zixian')"
            >是江紫弦不是江紫贤的主页</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>联系信息</span>
            </div>
          </template>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> github仓库链接：<el-link
                href="https://github.com/jiang-zixian/Campus-delivery-web-seu"
                target="_blank"
                >https://github.com/jiang-zixian/Campus-delivery-web-seu</el-link
              >
            </p>
            <p>
              <i class="el-icon-s-promotion"></i> 开发文档资料共享云盘：<el-link
                href="https://pan.seu.edu.cn:443/link/791447DEE217818E35A5808002F1C957"
                target="_blank"
            >https://pan.seu.edu.cn:443/link/791447DEE217818E35A5808002F1C957</el-link
            >
            </p>
            <p>
              <i class="el-icon-user-solid"></i> QQ群：
              <a href="http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=UPt9TWlsueT6dqIdNqpJokQsrD-GJCMz&authKey=y0tcYuEnrmrSDyhYOCGqvboJ5Q9tA1fLfUsQ%2FPeg%2BxxJuUNbDYU%2FpQ655Sc2Jo%2FH&noverify=0&group_code=715829886" target="_blank">715829886
                </a>
            </p>
<!--            <p>-->
<!--              <i class="el-icon-chat-dot-round"></i> 微信：<a-->
<!--                href="javascript:;"-->
<!--                >/ *若依</a-->
<!--              >-->
<!--            </p>-->
<!--            <p>-->
<!--              <i class="el-icon-money"></i> 支付宝：<a-->
<!--                href="javascript:;"-->
<!--                class="支付宝信息"-->
<!--                >/ *若依</a-->
<!--              >-->
<!--            </p>-->
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>项目进度</span>
            </div>
          </template>
          <div class="demo-progress">
            <span>项目计划（3.23-3.25）</span>
            <el-progress :percentage="100" status="success" />
            <span>可行性分析（3.24-3.30）</span>
            <el-progress :percentage="100" status="success" />
            <span>需求分析（3.24-3.30）</span>
            <el-progress :percentage="100" status="success" />
            <span>数据库设计说明书（3.31-4.7）</span>
            <el-progress :percentage="100" status="success" />
            <span>功能设计说明书（3.31-4.14）</span>
            <el-progress :percentage="100" status="success" />
            <span>代码文件开发（4.12-5.18）</span>
            <el-progress :percentage="100" status="success" />
            <span>测试计划（5.9-5.11）</span>
            <el-progress :percentage="100" status="success" />
            <span>测试用例（5.12-5.15）</span>
            <el-progress :percentage="100" status="success" />
            <span>测试报告（5.15-5.25）</span>
            <el-progress :percentage="100" status="success" />
<!--            <span>用户手册（5.9-5.26）</span>-->
<!--            <el-progress :percentage="10"  />-->

          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>捐赠支持</span>
            </div>
          </template>
          <div class="body">
            <img
              src="@/assets/images/pay.jpg"
              alt="donate"
              style="width:100%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >你可以请作者喝杯咖啡表示鼓励</span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script setup name="Index">
import {UserFilled} from "@element-plus/icons-vue";

const version = ref('3.8.7')
import jpg1 from '@/assets/indexBackground/indexBackground1.jpg'
import jpg2 from '@/assets/indexBackground/indexBackground2.jpg'
import jpg3 from '@/assets/indexBackground/indexBackground3.jpg'
import jpg4 from '@/assets/indexBackground/indexBackground4.jpg'
import jpg5 from '@/assets/indexBackground/indexBackground5.jpg'
import jpg6 from '@/assets/indexBackground/indexBackground6.jpg'

import avatar1 from '@/assets/avatar/jzx.jpg'
import avatar2 from '@/assets/avatar/xpl.jpg'
import avatar3 from '@/assets/avatar/szf.jpg'
import avatar4 from '@/assets/avatar/xgw.jpg'
import avatar5 from '@/assets/avatar/wj.jpg'
import avatar6 from '@/assets/avatar/syl.jpg'

const urls = [
  jpg1,
  jpg2,
  jpg3,
  jpg4,
  jpg5,
  jpg6,
]



function goTarget(url) {
  window.open(url, '__blank')
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.demo-type {
  display: flex;
}
.demo-type > div {
  flex: 1;
  text-align: center;
}

.demo-type > div:not(:last-child) {
  border-right: 1px solid var(--el-border-color);
}
.demo-progress .el-progress--line {
  margin-bottom: 15px;
  max-width: 600px;
}

</style>

