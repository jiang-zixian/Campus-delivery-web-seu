<template>
  <div class="app-container">

    <h1 ><span class="text" style="padding-left: 550px">当前订单：{{route.params.recordId}} </span><span class="text" style="padding-left: 50px">{{ open ? "配送完成" : marqueeText}}</span></h1>
    <el-divider/>
    <span class="label">客户ID：</span>
    <span class="value">{{ currentOrder.uId }}</span>
    <el-divider direction="vertical" style="margin-left: 25px ;margin-right: 25px"/>
    <span class="label">商家ID：</span>
    <span class="value">{{ currentOrder.sId || "校外商家"}}</span>
    <el-divider direction="vertical" style="margin-left: 25px ;margin-right: 25px" />
    <span class="label">取货地址：</span>
    <span class="value">{{ currentOrder.srcPosition }}</span>
    <el-divider direction="vertical" style="margin-left: 25px ;margin-right: 25px" />
    <span class="label">送达地址：</span>
    <span class="value">{{ currentOrder.destPosition }}</span>
    <el-divider direction="vertical" style="margin-left: 25px ;margin-right: 25px" />
    <span class="label">下单时间：</span>
    <span class="value">{{ currentOrder.srcTime }}</span>
    <el-divider direction="vertical" style="margin-left: 25px ;margin-right: 25px" />
    <span class="label">预期送达时间：</span>
    <span class="value">{{ currentOrder.destTime }}</span>
    <br>
    <br>
    <br>


    <div :style="{ display: open ? 'block' : 'none' }">
      <el-alert  style="height: 50px" title="当前接单已完成,请返回接单界面" type="error" effect="dark" />
      <br>
      <br>
    </div>

    <div style="height: 570px ">
      <el-amap
          :min-zoom="10"
          :max-zoom="22"
          :center="center"
          :zoom="zoom"
      >
        <el-amap-control-scale />
        <el-amap-control-tool-bar />
      </el-amap>

    </div>
    <br>
    <el-button type="success" round @click="confirmOrder" v-hasPermi="['rider:takeOrders:list']">订单已送达</el-button>

  </div>
</template>

<script setup name="currentOrder">
import {ElAmap} from "@vuemap/vue-amap";

import {getTakeOrders, updateTakeOrders} from "@/api/rider/takeOrders.js";
import {Back, Edit, Picture, Upload} from "@element-plus/icons-vue";
const { proxy } = getCurrentInstance();

const route = useRoute();

const currentOrder = ref({});

const marqueeText = ref("正在配送中");
const dots = ref(1);
const maxDots = 7;
let intervalId = null;

const zoom = ref(16);
const center = ref([118.8254, 31.8890204]);

const router = useRouter();

const open = ref(false);


function getCurrentOrder(){
  getTakeOrders(route.params.recordId).then(response => {
    currentOrder.value = response.data;
    console.log(currentOrder.value);
    open.value = currentOrder.value.status == 2;
  });
}
const startMarquee = () => {
  intervalId = setInterval(() => {
    updateDots();
  }, 500);
};

const updateDots = () => {
  if (dots.value < maxDots) {
    dots.value++;
    marqueeText.value=marqueeText.value+'.';
  } else {
    dots.value = 1;
    marqueeText.value="正在配送中"
  }
};

onMounted(() => {
  startMarquee();
});

onBeforeUnmount(() => {
  clearInterval(intervalId);
});

function confirmOrder(){
  proxy.$modal.confirm('是否确认订单已经送达？').then(function(){
    currentOrder.value.status=2;
    updateTakeOrders(currentOrder.value);
    open.value=true;
  }).then(() => {
    router.push("/rider/takeOrders");
  }).catch(() => {});

}


getCurrentOrder();
</script>

<style scoped>
.label {
  font-size: 18px;
  font-weight: bold;
  color: #666;
}

.value {
  font-size: 18px;
  margin-left: 10px;
  color: #333;
}

</style>