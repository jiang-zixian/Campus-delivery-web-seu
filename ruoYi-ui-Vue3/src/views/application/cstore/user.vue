<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['application:cstore:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['application:cstore:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['application:cstore:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cstoreList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="申请ID" align="center" prop="cstoreId" />
      <el-table-column label="用户ID" align="center" prop="uId" />
      <el-table-column label="电话" align="center" prop="tel" />
      <el-table-column label="身份证" align="center" prop="cardId" />
      <el-table-column label="姓名" align="center" prop="sname" />
      <el-table-column label="营业执照" align="center" prop="license" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.license" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <span v-if="scope.row.status === 0">未处理</span>
          <span v-else-if="scope.row.status === 1">通过</span>
          <span v-else-if="scope.row.status === 2">未通过</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
              v-if="scope.row.status===0"
              link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['application:cstore:edit']">修改</el-button>
          <el-button
              v-if="scope.row.status===0"
              link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['application:cstore:remove']">删除</el-button>
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

    <!-- 添加或修改商家提交申请对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="cstoreRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="身份证" prop="cardId">
          <el-input v-model="form.cardId" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="姓名" prop="sname">
          <el-input v-model="form.sname" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="营业执照" prop="license">
          <image-upload v-model="form.license"/>
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

<script setup name="Cstore">
import { listCstore, getCstore, delCstore, addCstore, updateCstore } from "@/api/application/cstore";

const { proxy } = getCurrentInstance();

const cstoreList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    cstoreId: null,
    uId: null,
    tel: null,
    cardId: null,
    sname: null,
    license: null,
    status: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询商家提交申请列表 */
function getList() {
  loading.value = true;
  listCstore(queryParams.value).then(response => {
    cstoreList.value = response.rows;
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
    cstoreId: null,
    uId: null,
    tel: null,
    cardId: null,
    sname: null,
    license: null,
    status: null
  };
  proxy.resetForm("cstoreRef");
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
  ids.value = selection.map(item => item.cstoreId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加商家提交申请";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _cstoreId = row.cstoreId || ids.value
  getCstore(_cstoreId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商家提交申请";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["cstoreRef"].validate(valid => {
    if (valid) {
      if (form.value.cstoreId != null) {
        updateCstore(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCstore(form.value).then(response => {
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
  const _cstoreIds = row.cstoreId || ids.value;
  proxy.$modal.confirm('是否确认删除商家提交申请编号为"' + _cstoreIds + '"的数据项？').then(function() {
    return delCstore(_cstoreIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('application/cstore/export', {
    ...queryParams.value
  }, `cstore_${new Date().getTime()}.xlsx`)
}

getList();
</script>
