<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['application:crider:add']"
        >新增</el-button>
      </el-col>
       <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['application:crider:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['application:crider:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="criderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请编号" align="center" prop="criderId" />
      <el-table-column label="用户id" align="center" prop="uId" />
      <el-table-column label="电话" align="center" prop="tel" />
      <el-table-column label="身份证号" align="center" prop="cardId" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="是否通过" align="center" prop="pass">
        <template #default="{ row }">
          {{ row.pass === 0 ? '未通过' : '已通过' }}
        </template>
      </el-table-column>
      <el-table-column label="是否处理" align="center" prop="status">
        <template #default="{ row }">
          {{ row.status === 0 ? '未处理' : '已处理' }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['application:crider:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['application:crider:remove']">删除</el-button>
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

    <!-- 添加或修改骑手申请对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="criderRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="cardId">
          <el-input v-model="form.cardId" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入电话" />
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

<script setup name="Crider">
import { listCrider, getCrider, delCrider, addCrider, updateCrider, listCriderByUser} from "@/api/application/crider";
import Cookies from "js-cookie";

const { proxy } = getCurrentInstance();

const criderList = ref([]);
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
    criderId: null,
    uId: null,
    tel: null,
    cardId: null,
    name: null,
    pass: null,
    status: null
  },
  rules: {
    uId: [
      { required: true, message: "用户id不能为空", trigger: "blur" }
    ],
    tel: [
      { required: true, message: "电话不能为空", trigger: "blur" }
    ],
    cardId: [
      { required: true, message: "身份证号不能为空", trigger: "blur" }
    ],
    name: [
      { required: true, message: "姓名不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询骑手申请列表 */
function getList() {
  loading.value = true;
  listCriderByUser(queryParams.value).then(response => {
    criderList.value = response.rows;
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
    criderId: null,
    uId: null,
    tel: null,
    cardId: null,
    name: null,
    pass: null,
    status: null
  };
  proxy.resetForm("criderRef");
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
  ids.value = selection.map(item => item.criderId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加骑手申请";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _criderId = row.criderId || ids.value
  getCrider(_criderId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改骑手申请";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["criderRef"].validate(valid => {
    if (valid) {
      if (form.value.criderId != null) {
        updateCrider(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCrider(form.value).then(response => {
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
  const _criderIds = row.criderId || ids.value;
  proxy.$modal.confirm('是否确认删除骑手申请编号为"' + _criderIds + '"的数据项？').then(function() {
    return delCrider(_criderIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('application/crider/export', {
    ...queryParams.value
  }, `crider_${new Date().getTime()}.xlsx`)
}

getList();
</script>
