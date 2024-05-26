<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <h1 style="text-align: center;">您当前处在商店:  <span class="store-name">{{storeName}}</span></h1>
      <el-divider/>
      <el-form-item label="评论号" prop="commentId">
        <el-input
            v-model="queryParams.commentId"
            placeholder="请输入评论号"
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
      <!--      <el-form-item label="商店号" prop="sId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.sId"-->
      <!--          placeholder="请输入商店号"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="订单号" prop="recordId">
        <el-input
            v-model="queryParams.recordId"
            placeholder="请输入订单号"
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
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="Plus"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['store:myComment:add']"-->
      <!--        >新增</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Eleme"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['store:myComment:edit']"
        >回复</el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="danger"-->
      <!--          plain-->
      <!--          icon="Delete"-->
      <!--          :disabled="multiple"-->
      <!--          @click="handleDelete"-->
      <!--          v-hasPermi="['store:myComment:remove']"-->
      <!--        >删除</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['store:myComment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="myCommentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评论号" align="center" prop="commentId" />
      <el-table-column label="客户号" align="center" prop="uId" />
      <el-table-column label="订单号" align="center" prop="recordId" />
      <!--      <el-table-column label="商店号" align="center" prop="sId" />-->
      <el-table-column label="评论" align="center"  width="500" prop="comment" />
      <el-table-column label="回复" align="center"  width="500" prop="recomment" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <!--          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['store:myComment:edit']">修改</el-button>-->
          <!--          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['store:myComment:remove']">删除</el-button>-->
          <el-button link type="primary" icon="Eleme" @click="handleUpdate(scope.row)" v-hasPermi="['store:myComment:edit']">回复</el-button>
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

    <!-- 添加或修改我的评论对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="myCommentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单号" prop="recordId">
          <el-input v-model="form.recordId" disabled placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="客户号" prop="uId">
          <el-input v-model="form.uId" disabled placeholder="请输入客户号" />
        </el-form-item>
        <el-form-item label="商店号" prop="sId">
          <el-input v-model="form.sId" disabled placeholder="请输入商店号" />
        </el-form-item>
        <el-form-item label="评论" prop="comment">
          <el-input v-model="form.comment" disabled type="textarea" placeholder="请输入内容" :autosize="{ minRows: 2, maxRows: 10 }"/>
        </el-form-item>
        <el-form-item label="回复" prop="recomment">
          <el-input v-model="form.recomment" type="textarea" placeholder="请输入内容" :autosize="{ minRows: 2, maxRows: 10 }"/>
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

<script setup name="MyComment">
import { listMyComment, getMyComment, delMyComment, addMyComment, updateMyComment } from "@/api/store/myComment";
import {getMyStore} from "@/api/store/myStore.js";

const { proxy } = getCurrentInstance();

const myCommentList = ref([]);
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
    commentId: null,
    uId: null,
    sId: route.params.sId || null,
    comment: null,
    recomment: null,
    recordId: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询我的评论列表 */
function getList() {
  loading.value = true;
  listMyComment(queryParams.value).then(response => {
    myCommentList.value = response.rows;
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
    commentId: null,
    uId: null,
    sId: null,
    comment: null,
    recomment: null,
    recordId: null
  };
  proxy.resetForm("myCommentRef");
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
  ids.value = selection.map(item => item.commentId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加我的评论";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _commentId = row.commentId || ids.value
  getMyComment(_commentId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改我的评论";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["myCommentRef"].validate(valid => {
    if (valid) {
      if (form.value.commentId != null) {
        updateMyComment(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMyComment(form.value).then(response => {
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
  const _commentIds = row.commentId || ids.value;
  proxy.$modal.confirm('是否确认删除我的评论编号为"' + _commentIds + '"的数据项？').then(function () {
    return delMyComment(_commentIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('store/myComment/export', {
    ...queryParams.value
  }, `myComment_${new Date().getTime()}.xlsx`)
}

function getStoreName() {
  const _sId = route.params.sId;
  getMyStore(_sId).then(response => {
    storeName.value = response.data.sname;
  });
}

getList();
getStoreName();
</script>