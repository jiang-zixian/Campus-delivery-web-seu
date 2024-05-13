<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品编号" prop="iId">
        <el-input
          v-model="queryParams.iId"
          placeholder="请输入商品编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="店家编号" prop="sId">-->
<!--        <el-input-->
<!--          v-model="queryParams.sId"-->
<!--          placeholder="请输入店家编号"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="商品名" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入商品名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="价格" prop="price">-->
<!--        <el-input-->
<!--          v-model="queryParams.price"-->
<!--          placeholder="请输入价格"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="库存数量" prop="amount">-->
<!--        <el-input-->
<!--          v-model="queryParams.amount"-->
<!--          placeholder="请输入库存数量"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['itemList:itemList:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['itemList:itemList:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['itemList:itemList:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['itemList:itemList:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemListList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品编号" align="center" prop="iId" />
      <el-table-column label="店家编号" align="center" prop="sId" />
      <el-table-column label="商品图像" align="center" prop="photo" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.photo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品名" align="center" prop="itemName" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="库存数量" align="center" prop="amount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['itemList:itemList:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['itemList:itemList:remove']">删除</el-button>
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

    <!-- 添加或修改商品列表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="itemListRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店家编号" prop="sId">
          <el-input v-model="form.sId" placeholder="请输入店家编号" />
        </el-form-item>
        <el-form-item label="商品图像" prop="photo">
          <image-upload v-model="form.photo"/>
        </el-form-item>
        <el-form-item label="商品名" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入商品名" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="库存数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入库存数量" />
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

<script setup name="ItemList">
import { listItemList, getItemList, delItemList, addItemList, updateItemList } from "@/api/itemList/itemList";
import {useRoute} from "vue-router";

const { proxy } = getCurrentInstance();

const itemListList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const route = useRoute();

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    iId: null,
    sId: route.params.sId || null,
    photo: null,
    itemName: null,
    price: null,
    amount: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询商品列表列表 */
function getList() {
  loading.value = true;
  listItemList(queryParams.value).then(response => {
    itemListList.value = response.rows;
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
    iId: null,
    sId: null,
    photo: null,
    itemName: null,
    price: null,
    amount: null
  };
  proxy.resetForm("itemListRef");
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
  ids.value = selection.map(item => item.iId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加商品列表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _iId = row.iId || ids.value
  getItemList(_iId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商品列表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["itemListRef"].validate(valid => {
    if (valid) {
      if (form.value.iId != null) {
        updateItemList(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItemList(form.value).then(response => {
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
  const _iIds = row.iId || ids.value;
  proxy.$modal.confirm('是否确认删除商品列表编号为"' + _iIds + '"的数据项？').then(function() {
    return delItemList(_iIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('itemList/itemList/export', {
    ...queryParams.value
  }, `itemList_${new Date().getTime()}.xlsx`)
}

getList();
</script>
