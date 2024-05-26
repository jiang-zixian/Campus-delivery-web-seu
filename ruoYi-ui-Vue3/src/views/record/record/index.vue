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
<!--      <el-form-item label="客户号" prop="uId">-->
<!--        <el-input-->
<!--          v-model="queryParams.uId"-->
<!--          placeholder="请输入客户号"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="商店号" prop="sId">
        <el-input
          v-model="queryParams.sId"
          placeholder="请输入商店号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="总价" prop="allItemPrice">-->
<!--        <el-input-->
<!--          v-model="queryParams.allItemPrice"-->
<!--          placeholder="请输入总价"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="骑手号" prop="riderId">
        <el-input
          v-model="queryParams.riderId"
          placeholder="请输入骑手号"
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
<!--          v-hasPermi="['record:record:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="Edit"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['record:record:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="Delete"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['record:record:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['record:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="订单号" align="center" prop="recordId" />
<!--      <el-table-column label="客户号" align="center" prop="uId" />-->
      <el-table-column label="商店号" align="center" prop="sId" />
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

      <!--      <el-table-column label="订单类型" align="center" prop="type" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
              v-if="scope.row.status === 0 || scope.row.status === 1"
              link
              type="primary"
              icon="Edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['record:record:edit']"
          >
            修改
          </el-button>

          <el-button
              v-if="scope.row.status === 0 || scope.row.status === 1"
              link
              type="primary"
              icon="Delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['record:record:remove']"
          >取消
          </el-button>

          <el-button
              v-else
              link
              type="primary"
              icon="Edit"
              @click.once="openCommentDialog(scope.row)"
              v-hasPermi="['record:record:comment']"
          >
            评价
          </el-button>
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

    <!-- 添加或修改我的订单对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="recordRef" :model="form" :rules="rules" label-width="80px">
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
<!--        <el-form-item label="下单时间" prop="srcTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.srcTime"-->
<!--            type="date"-->
<!--            value-format="YYYY-MM-DD "-->
<!--            placeholder="请选择下单时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="送达时间" prop="destTime">
          <el-date-picker clearable
            v-model="form.destTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
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
    <!-- 评价 -->
      <el-dialog :title="title" v-model="opencomment" width="500px" append-to-body>
        <el-form ref="recordRef" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="评论" prop="comment">
            <el-input v-model="form.comment" type="textarea" placeholder="请输入评论内容"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button type="primary" @click="Comment">确 定</el-button>
            <el-button @click="ccancel">取 消</el-button>
          </div>
        </template>
      </el-dialog>
  </div>
</template>

<script setup name="Record">
import {listRecord, getRecord, delRecord, addRecord, updateRecord, commentrecord} from "@/api/record/record";

const { proxy } = getCurrentInstance();

const recordList = ref([]);
const open = ref(false);
const opencomment = ref(false);
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
    type: null,
    comment: null
  },
  rules: {
    destTime: [
      { required: true, message: '请选择送达时间', trigger: 'change' },
      { validator: validateDestTime, trigger: 'change' }
    ],
    comment: [
      { required: true, message: '请输入评论内容', trigger: 'blur' }
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

function openCommentDialog(row)
{
  const _recordId = row.recordId || ids.value
  getRecord(_recordId).then(response => {
    form.value = response.data;
    opencomment.value = true;
    title.value = "评价我的外卖订单";
  });
}

function Comment()
{
  proxy.$refs["recordRef"].validate(valid => {
    if (valid) {
      commentrecord(form.value).then(response =>
          {
            proxy.$modal.msgSuccess("评价成功");
            opencomment.value = false;
          }
      )
    }
    else
    {
      proxy.$modal.msgSuccess("请填写评价内容");
      return false;
    }

  });
}

/** 查询我的订单列表 */
function getList() {
  loading.value = true;
  listRecord(queryParams.value).then(response => {
    recordList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

function ccancel() {
  opencomment.value = false;
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
  proxy.resetForm("recordRef");
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
  title.value = "添加我的订单";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _recordId = row.recordId || ids.value
  getRecord(_recordId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改我的订单";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["recordRef"].validate(valid => {
    if (valid) {
      if (form.value.recordId != null) {
        updateRecord(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addRecord(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认取消该外卖订单？').then(function() {
    return delRecord(_recordIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("取消成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('record/record/export', {
    ...queryParams.value
  }, `record_${new Date().getTime()}.xlsx`)
}

getList();
</script>
