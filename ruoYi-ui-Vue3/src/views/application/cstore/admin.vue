<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请ID" prop="cstoreId">
        <el-input
          v-model="queryParams.cstoreId"
          placeholder="请输入申请ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="uId">
        <el-input
          v-model="queryParams.uId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="tel">
        <el-input
          v-model="queryParams.tel"
          placeholder="请输入电话"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证" prop="cardId">
        <el-input
          v-model="queryParams.cardId"
          placeholder="请输入身份证"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="sname">
        <el-input
          v-model="queryParams.sname"
          placeholder="请输入姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态">
          <el-option label="未处理" value="0"></el-option>
          <el-option label="通过" value="1"></el-option>
          <el-option label="未通过" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="multiple"
          @click="accept"
          v-hasPermi="['application:crider:edit']"
        >同意</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="refuse"
          v-hasPermi="['application:crider:edit']"
        >拒绝</el-button>
      </el-col>
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
      <el-table-column label="状态" align="center" prop="status" >
        <template #default="scope">
          <span v-if="scope.row.status === 0">未处理</span>
          <span v-else-if="scope.row.status === 1">通过</span>
          <span v-else-if="scope.row.status === 2">未通过</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="accept(scope.row)" v-hasPermi="['application:cstore:edit']">同意</el-button>
          <el-button link type="primary" icon="Delete" @click="refuse(scope.row)" v-hasPermi="['application:cstore:remove']">拒绝</el-button>
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
        <el-form-item label="用户ID" prop="uId">
          <el-input v-model="form.uId" placeholder="请输入用户ID" />
        </el-form-item>
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
        <el-form-item label="状态" prop="status">
          <el-input v-model="form.status" placeholder="请输入状态" />
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
import { listCstore, getCstore, delCstore, addCstore, updateCstore,agreeCstore,refuseCstore } from "@/api/application/cstore";

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

/** 同意按钮 */
function accept(row) {
  const _cstoreId = row.cstoreId || ids.value;
  proxy.$modal.confirm('是否确认同意商家提交申请编号为"' + _cstoreId + '"的数据项？').then(function() {
    return agreeCstore(_cstoreId);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("操作成功");
  }).catch(() => {});
}

/* refuse按钮 */
function refuse(row) {
  const _cstoreId = row.cstoreId || ids.value;
  proxy.$modal.confirm('是否确认拒绝商家提交申请编号为"' + _cstoreId + '"的数据项？').then(function() {
    return refuseCstore(_cstoreId);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("操作成功");
  }).catch(() => {});
}

getList();

</script>
