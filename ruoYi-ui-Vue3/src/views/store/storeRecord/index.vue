<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <h1 style="text-align: center;">您当前处在商店:  <span class="store-name">{{storeName}}</span></h1>
      <el-divider/>
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
      <el-form-item label="骑手号" prop="riderId">
        <el-input
          v-model="queryParams.riderId"
          placeholder="请输入骑手号"
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
      <el-form-item label="订单状态" prop="destTime">
        <el-select
            v-model="queryParams.status"
            placeholder="请选择订单状态"
            size="large"
            style="width: 240px"
        >
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="Plus"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['store:storeRecord:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="Edit"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['store:storeRecord:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="Delete"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['store:storeRecord:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['store:storeRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="storeRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单号" align="center" prop="recordId" />
      <el-table-column label="客户号" align="center" prop="uId" />
<!--      <el-table-column label="商店号" align="center" prop="sId" />-->
      <el-table-column label="总价" align="center" prop="allItemPrice" />
      <el-table-column label="骑手号" align="center" prop="riderId" />
      <el-table-column label="派送费" align="center" prop="deliveryPrice" />
      <el-table-column label="订单状态" align="center" prop="status" >
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
      <el-table-column label="订单类型" align="center" prop="type" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template #default="scope">-->
<!--          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['store:storeRecord:edit']">修改</el-button>-->
<!--          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['store:storeRecord:remove']">删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商店订单记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="storeRecordRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户号" prop="uId">
          <el-input v-model="form.uId" placeholder="请输入客户号" />
        </el-form-item>
        <el-form-item label="商店号" prop="sId">
          <el-input v-model="form.sId" placeholder="请输入商店号" />
        </el-form-item>
        <el-form-item label="总价" prop="allItemPrice">
          <el-input v-model="form.allItemPrice" placeholder="请输入总价" />
        </el-form-item>
        <el-form-item label="骑手号" prop="riderId">
          <el-input v-model="form.riderId" placeholder="请输入骑手号" />
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

<script setup name="StoreRecord">
import { listStoreRecord, getStoreRecord, delStoreRecord, addStoreRecord, updateStoreRecord } from "@/api/store/storeRecord";
import {getMyStore} from "@/api/store/myStore.js";

const { proxy } = getCurrentInstance();

const storeRecordList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const storeName = ref("");
const route = useRoute();

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    recordId: null,
    uId: null,
    sId: route.params.sId || null,
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

const options = [
  {
    value: '0',
    label: '已下单',
  },
  {
    value: '1',
    label: '骑手已接单',
  },
  {
    value: '2',
    label: '订单已送达',
  },
  {
    value: null,
    label: '其他状态',
  },
]

/** 查询商店订单记录列表 */
function getList() {
  loading.value = true;
  listStoreRecord(queryParams.value).then(response => {
    storeRecordList.value = response.rows;
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
  proxy.resetForm("storeRecordRef");
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
  title.value = "添加商店订单记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _recordId = row.recordId || ids.value
  getStoreRecord(_recordId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商店订单记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["storeRecordRef"].validate(valid => {
    if (valid) {
      if (form.value.recordId != null) {
        updateStoreRecord(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addStoreRecord(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除商店订单记录编号为"' + _recordIds + '"的数据项？').then(function() {
    return delStoreRecord(_recordIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('store/storeRecord/export', {
    ...queryParams.value
  }, `storeRecord_${new Date().getTime()}.xlsx`)
}
function getStoreName(){
  const _sId = route.params.sId;
  getMyStore(_sId).then(response => {
    storeName.value = response.data.sname;
  });
}

getList();
getStoreName();
</script>
