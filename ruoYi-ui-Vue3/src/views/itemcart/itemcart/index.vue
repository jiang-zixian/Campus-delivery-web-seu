<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="购物车号" prop="itemcartId">
        <el-input
          v-model="queryParams.itemcartId"
          placeholder="请输入购物车号"
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
      <el-form-item label="商品号" prop="iId">
        <el-input
          v-model="queryParams.iId"
          placeholder="请输入商品号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品数目" prop="number">
        <el-input
          v-model="queryParams.number"
          placeholder="请输入商品数目"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['itemcart:itemcart:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['itemcart:itemcart:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['itemcart:itemcart:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['itemcart:itemcart:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['itemcart:itemcart:export']"
        >购买</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemcartList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="购物车号" align="center" prop="itemcartId" />
      <el-table-column label="客户号" align="center" prop="uId" />
      <el-table-column label="商品号" align="center" prop="iId" />
      <el-table-column label="商品数目" align="center" prop="number" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['itemcart:itemcart:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['itemcart:itemcart:remove']">删除</el-button>
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

    <!-- 添加或修改购物车对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="itemcartRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户号" prop="uId">
          <el-input v-model="form.uId" placeholder="请输入客户号" />
        </el-form-item>
        <el-form-item label="商品号" prop="iId">
          <el-input v-model="form.iId" placeholder="请输入商品号" />
        </el-form-item>
        <el-form-item label="商品数目" prop="number">
          <el-input v-model="form.number" placeholder="请输入商品数目" />
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

<script setup name="Itemcart">
import { listItemcart, getItemcart, delItemcart, addItemcart, updateItemcart } from "@/api/itemcart/itemcart";

const { proxy } = getCurrentInstance();

const itemcartList = ref([]);
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
    itemcartId: null,
    uId: null,
    iId: null,
    number: null
  },
  rules: {
    itemcartId: [
      { required: true, message: "购物车号不能为空", trigger: "blur" }
    ],
    uId: [
      { required: true, message: "客户号不能为空", trigger: "blur" }
    ],
    iId: [
      { required: true, message: "商品号不能为空", trigger: "blur" }
    ],
    number: [
      { required: true, message: "商品数目不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询购物车列表 */
function getList() {
  loading.value = true;
  listItemcart(queryParams.value).then(response => {
    itemcartList.value = response.rows;
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
    itemcartId: null,
    uId: null,
    iId: null,
    number: null
  };
  proxy.resetForm("itemcartRef");
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
  ids.value = selection.map(item => item.itemcartId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加购物车";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _itemcartId = row.itemcartId || ids.value
  getItemcart(_itemcartId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改购物车";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["itemcartRef"].validate(valid => {
    if (valid) {
      if (form.value.itemcartId != null) {
        updateItemcart(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItemcart(form.value).then(response => {
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
  const _itemcartIds = row.itemcartId || ids.value;
  proxy.$modal.confirm('是否确认删除购物车编号为"' + _itemcartIds + '"的数据项？').then(function() {
    return delItemcart(_itemcartIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('itemcart/itemcart/export', {
    ...queryParams.value
  }, `itemcart_${new Date().getTime()}.xlsx`)
}

/** 购买按钮操作 */
function handlePurchase() {
  const _itemcartIds = row.itemcartId;
  const _uId = row.uId;
  var date = new Date().getTime();

  //购买商品的逻辑

  console.log("购买商品，购物车编号为: " + _itemcartIds + "客户号为："+ _uId);
}


getList();
</script>
