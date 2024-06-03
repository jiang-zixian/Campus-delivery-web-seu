<template>
  <div class="app-container">
    <!-- 添加或修改跑腿对话框 -->
    <el-form ref="riderecordRef" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="跑腿费" prop="deliveryPrice">
        <el-input v-model="form.deliveryPrice" placeholder="请输入派送费" />
      </el-form-item>
      <el-form-item label="取货地址" prop="srcPosition">
        <el-input v-model="form.srcPosition" placeholder="请输入取货地址" />
      </el-form-item>
      <el-form-item label="送达地址" prop="destPosition">
        <el-input v-model="form.destPosition" placeholder="请输入送达地址" />
      </el-form-item>
      <el-form-item label="送达时间" prop="destTime">
        <el-date-picker clearable
                        v-model="form.destTime"
                        type="datetime"
                        value-format="YYYY-MM-DD HH:mm:ss"
                        placeholder="请选择送达时间">
        </el-date-picker>
      </el-form-item>
      <div class="dialog-footer">
        <el-button
            type="primary"
            @click="submitForm"
            v-hasPermi="['riderecord:riderecord:add']"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup name="Riderecord">
import { listRiderecord, getRiderecord, delRiderecord, addRiderecord, updateRiderecord } from "@/api/riderecord/riderecord";
import { ref, reactive, toRefs, computed, getCurrentInstance } from "vue";
import dayjs from "dayjs";

const { proxy } = getCurrentInstance();

const riderecordList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const validateDestTime = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请选择送达时间'))
  }
  if (value < form.value.srcTime) {
    return callback(new Error('送达时间必须晚于下单时间'))
  }
  callback()
}

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    recordId: null,
    uId: null,
    sId: null,
    allItemPrice: null,
    riderId: null,
    deliveryPrice: null,
    status: null,
    srcPosition: null,
    destPosition: null,
    srcTime: null,
    destTime: null,
    type: null
  },
  rules: {
    deliveryPrice: [
      { required: true, message: '请输入派送费', trigger: 'blur' }
    ],
    srcPosition: [
      { required: true, message: '请输入取货地址', trigger: 'blur' }
    ],
    destPosition: [
      { required: true, message: '请输入送达地址', trigger: 'blur' }
    ],
    destTime: [
      { required: true, message: '请选择送达时间', trigger: 'change' },
      { validator: validateDestTime, trigger: 'change' }
    ]
  }
});

const getStatusText = computed(() => {
  return (status) => {
    switch (status) {
      case 0:
        return '已下单';
      case 1:
        return  '骑手已接单'
      case 2:
        return '订单已送达'
        // 添加其他状态对应的文字
      default:
        return '其他状态';
    }
  };
});

const { queryParams, form, rules } = toRefs(data);

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    recordId: null,
    uId: null,
    sId: null,
    allItemPrice: null,
    riderId: null,
    deliveryPrice: null,
    status: null,
    srcPosition: null,
    destPosition: null,
    srcTime: dayjs().format("YYYY-MM-DD HH:mm:ss"),  // 设置为当前时间并格式化
    destTime: null,
    type: null
  };
  proxy.resetForm("riderecordRef");
}

// 提交按钮
function submitForm() {
  if (!form.value.srcTime) {
    form.value.srcTime = dayjs().format("YYYY-MM-DD HH:mm:ss");  // 确保下单时间已经设置为当前时间并格式化
  }
  proxy.$refs["riderecordRef"].validate(valid => {
    if (valid) {
      form.value.type = 0;
      form.value.status = 0;
      addRiderecord(form.value).then(response => {
        proxy.$modal.msgSuccess("新增成功");
        open.value = false;
        reset();
      });
    } else {
      proxy.$modal.msgError("表单填写有误，请检查后再提交");
    }
  });
}
</script>
