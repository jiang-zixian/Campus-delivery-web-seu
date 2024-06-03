<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单号" prop="recordId">
        <el-input
            v-model="queryParams.recordId"
            placeholder="请输入订单号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户号" prop="uId">
        <el-input
            v-model="queryParams.uId"
            placeholder="请输入客户号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商店号" prop="sId">
        <el-input
            v-model="queryParams.sId"
            placeholder="请输入商店号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="派送费" prop="deliveryPrice">
        <el-input
            v-model="queryParams.deliveryPrice"
            placeholder="请输入派送费"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="取货地址" prop="srcPosition">
        <el-input
            v-model="queryParams.srcPosition"
            placeholder="请输入取货地址"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="送达地址" prop="destPosition">
        <el-input
            v-model="queryParams.destPosition"
            placeholder="请输入送达地址"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下单时间" prop="srcTime">
        <el-date-picker clearable
                        v-model="queryParams.srcTime"
                        type="date"
                        value-format="YYYY-MM-DD"
                        placeholder="请选择下单时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="送达时间" prop="destTime">
        <el-date-picker clearable
                        v-model="queryParams.destTime"
                        type="date"
                        value-format="YYYY-MM-DD"
                        placeholder="请选择送达时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="订单类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择">
          <el-option :label="'校外外卖跑腿单'" :value="0"></el-option>
          <el-option :label="'校内商家跑腿单'" :value="2"></el-option>
          <el-option :label="'全部'" :value="null"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="takeOrdersList" @selection-change="handleSelectionChange">
      <el-table-column label="订单号" align="center" prop="recordId" />
      <el-table-column label="客户号" align="center" prop="uId" />
      <el-table-column label="商店号" align="center" prop="sId" />
      <el-table-column label="总价" align="center" prop="allItemPrice" />
      <el-table-column label="派送费" align="center" prop="deliveryPrice" />
      <el-table-column label="订单状态" align="center" prop="status">
        <template #default="scope">
          <span>{{ getStatusText(scope.row.status) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="取货地址" align="center" prop="srcPosition" />
      <el-table-column label="送达地址" align="center" prop="destPosition" />
      <el-table-column label="下单时间" align="center" prop="srcTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.srcTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="送达时间" align="center" prop="destTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.destTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单类型" align="center" prop="type"  width="180">
        <template #default="scope">
          <span>{{ getTypeText(scope.row.type) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <!--          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['rider:takeOrders:edit']">修改</el-button>-->
          <!--          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['rider:takeOrders:remove']">删除</el-button>-->
          <el-button link type="primary" icon="Select" @click="handleTakeOrder(scope.row)" >接单</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 当骑手有未完成的接单时，提示的对话框 -->
    <el-dialog :title="title" v-model="ifshow" width="500px" append-to-body>
      <h1>当前有未完成的接单</h1>
      <h1>请先完成订单号为{{currentOrder}}的接单</h1>
      <h1>再接下一单哦！</h1>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="gotoCurrentOrder">点击去完成当前接单</el-button>
        </div>
      </template>
    </el-dialog>


    <!-- 添加或修改我要接单对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="takeOrdersRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户号" prop="uId">
          <el-input v-model="form.uId" placeholder="请输入客户号" />
        </el-form-item>
        <el-form-item label="商店号" prop="sId">
          <el-input v-model="form.sId" placeholder="请输入商店号" />
        </el-form-item>
        <el-form-item label="总价" prop="allItemPrice">
          <el-input v-model="form.allItemPrice" placeholder="请输入总价" />
        </el-form-item>
        <el-form-item label="派送费" prop="deliveryPrice">
          <el-input v-model="form.deliveryPrice" placeholder="请输入派送费" />
        </el-form-item>
        <el-form-item label="取货地址" prop="srcPosition">
          <el-input v-model="form.srcPosition" placeholder="请输入取货地址" />
        </el-form-item>
        <el-form-item label="送达地址" prop="destPosition">
          <el-input v-model="form.destPosition" placeholder="请输入送达地址" />
        </el-form-item>
        <el-form-item label="下单时间" prop="srcTime">
          <el-date-picker clearable
                          v-model="form.srcTime"
                          type="date"
                          value-format="YYYY-MM-DD"
                          placeholder="请选择下单时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="送达时间" prop="destTime">
          <el-date-picker clearable
                          v-model="form.destTime"
                          type="date"
                          value-format="YYYY-MM-DD"
                          placeholder="请选择送达时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="TakeOrders">
import {
  listTakeOrders,
  getTakeOrders,
  delTakeOrders,
  addTakeOrders,
  updateTakeOrders,
  ifHaveOrder
} from "@/api/rider/takeOrders";

const { proxy } = getCurrentInstance();

const takeOrdersList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const currentOrder = ref(null);
const ifshow = ref(false)

const router = useRouter();

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
    status: 0,
    srcPosition: null,
    destPosition: null,
    srcTime: null,
    destTime: null,
    type: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);


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

const getTypeText = computed(() => {
  return (type) => {
    switch (type) {
      case 0:
        return '校外外卖跑腿单';
      case 1:
        return  '校内商家自提'
      case 2:
        return '校内商家跑腿单'
        // 添加其他状态对应的文字
      default:
        return '其他类型';
    }
  };
});

/** 查询我要接单列表 */
function getList() {
  loading.value = true;
  listTakeOrders(queryParams.value).then(response => {
    takeOrdersList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

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
    srcTime: null,
    destTime: null,
    type: null
  };
  proxy.resetForm("takeOrdersRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.recordId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加我要接单";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _recordId = row.recordId || ids.value
  getTakeOrders(_recordId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改我要接单";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["takeOrdersRef"].validate(valid => {
    if (valid) {
      if (form.value.recordId != null) {
        updateTakeOrders(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addTakeOrders(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _recordIds = row.recordId || ids.value;
  proxy.$modal.confirm('是否确认删除我要接单编号为"' + _recordIds + '"的数据项？').then(function() {
    return delTakeOrders(_recordIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('rider/takeOrders/export', {
    ...queryParams.value
  }, `takeOrders_${new Date().getTime()}.xlsx`)
}

function handleTakeOrder(row) {
  proxy.$modal.confirm('是否确认要接单编号为"' + row.recordId + '"的订单？').then(function () {
    ifHaveOrder().then(response => {
      if(response!=null){
        alert("当前有接单未完成");
        window.location.reload();
      }else{
        reset();
        getTakeOrders(row.recordId).then(response => {
          form.value = response.data;
          if (form.value.status === 1) {
            alert("手慢了，订单已被其他骑手接走啦");
            window.location.reload();
          } else {
            form.value.status = 1;
            updateTakeOrders(form.value).then(response => {
              getList();
            }).then(() => {
              router.push("/rider/currentOrder/" + row.recordId);
            })
          }
        });
      }
    })
  }).catch(() => {
  });

}

function getCurrentOrder(){
  ifHaveOrder().then(response => {
    console.log(response);
    currentOrder.value= response || null;
    console.log(currentOrder.value);
    if(currentOrder.value!=null){
      ifshow.value=true;
    }else{
      ifshow.value=false;
    }
    console.log(ifshow.value);
  })
}

function gotoCurrentOrder(){
  router.push("/rider/currentOrder/" + currentOrder.value);
  ifshow.value=false;
}


getList();
getCurrentOrder();
</script>