<template>
  <div class="app-container">     
    <!-- <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商店编码" prop="sId">
        <el-input
          v-model="queryParams.sId"
          placeholder="请输入商店编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店长编码" prop="uId">
        <el-input
          v-model="queryParams.uId"
          placeholder="请输入店长编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商店名称" prop="sname">
        <el-input
          v-model="queryParams.sname"
          placeholder="请输入商店名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->
    <el-form @submit.native.prevent :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <div>
      <el-form-item>
        <el-input
          v-model="queryParams.sname"
          class="special-input"
          placeholder="请输入商店名称"
          clearable
          @keyup.enter="handleQuery"
        >
          <template #append>
            <el-button type="primary"  @click="handleQuery">搜索</el-button>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item>
      </el-form-item>
      </div>
    </el-form>

    <el-table v-loading="loading" :data="storeListList" stripe @selection-change="handleSelectionChange" @row-click="handlegoshopping" :show-headline="false">
      <!-- <el-table-column label="" type="expand"  width="100">
          <template #default="props">
            <p><b>商店地点: </b></p>
            <p>{{ splitDescription1(props.row.description) }}</p>
            <p><b>商店描述: </b></p>
            <p>{{ splitDescription2(props.row.description) }}</p>
          </template>
        </el-table-column> -->
        <el-table-column label="" width="100">
        </el-table-column>

      <el-table-column label="" width="200">
      <template #default="scope">
        <div class="store-info">
          <image-preview :src="scope.row.logo" alt="商店logo" class="store-logo"/>
        </div>
      </template>
    </el-table-column>
    
      <el-table-column label="外卖神店" width="200">
    <template #default="scope">
      <div class="delivery-info">
        <p class="store-name">{{ scope.row.sname }}</p>
        <p><span class="star">{{ generateRandomStars() }}</span> 月售 {{ getRangeArray() }}</p>
        <p>{{ getRandomTime() }}分钟 | {{ getRandomDistance() }}km</p>
        <p>起送 {{ getRandomMinOrder() }} | 配送 {{ getRandomDeliveryFee() }} | 人均 {{ getRandomAvgPrice() }}</p>
        <p>
        <img src="../png/fan.png" class="tiny-image">
          <img src="../png/jian.png" class="tiny-image">
          <img src="../png/piao.png" class="tiny-image">
        </p>

      </div>
    </template>
  </el-table-column>
  <el-table-column label="" width="50">
        </el-table-column>
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="110">
        <template #default="scope">
          <el-button link type="primary" icon="ShoppingCart" @click="handlegoshopping(scope.row)">购物</el-button>
        </template>
      </el-table-column> -->
    </el-table>

 
    
    <!-- <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    /> -->

    <!-- 添加或修改商家列表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="storeListRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商店名称" prop="sname">
          <el-input v-model="form.sname" placeholder="请输入商店名称" />
        </el-form-item>
        <el-form-item label="图标" prop="logo">
          <image-upload v-model="form.logo"/>
        </el-form-item>
        <el-form-item label="商店描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
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

<script setup name="StoreList">
import { listStoreList, getStoreList, delStoreList, addStoreList, updateStoreList} from "@/api/storeList/storeList";

const { proxy } = getCurrentInstance();

const storeListList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const router = useRouter();

// 递减数组
let array = [956,947,895,876,632,521,316,215,198,187,176,165,154,143,132,121,110,99,88,77,66,55,44,33,22,11,0];
let array_index = 0;

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    sId: null,
    uId: null,
    sname: null,
    logo: null,
    description: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询商家列表列表 */
function getList() {
  loading.value = true;
  listStoreList(queryParams.value).then(response => {
    storeListList.value = response.rows;
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
    sId: null,
    uId: null,
    sname: null,
    logo: null,
    description: null
  };
  proxy.resetForm("storeListRef");
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
  ids.value = selection.map(item => item.sId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加商家列表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _sId = row.sId || ids.value
  // console.log(row);
  getStoreList(_sId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商家列表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["storeListRef"].validate(valid => {
    if (valid) {
      if (form.value.sId != null) {
        updateStoreList(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addStoreList(form.value).then(response => {
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
  const _sIds = row.sId || ids.value;
  proxy.$modal.confirm('是否确认删除商家列表编号为"' + _sIds + '"的数据项？').then(function() {
    return delStoreList(_sIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 购物按钮跳转 */
function handlegoshopping(row) {
  const sid = row.sId;
  router.push("/goshopping/itemList/store/" + sid);
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('storeList/storeList/export', {
    ...queryParams.value
  }, `storeList_${new Date().getTime()}.xlsx`)
}

function splitDescription1(str) {
  const parts = str.split(' ');
  return parts[0];
}

function splitDescription2(str) {
  const parts = str.split(' ');
  return parts.slice(1).join(' ');
}

function getRandomTime() {
      // 生成随机分钟数，例如10到40分钟
      return Math.floor(Math.random() * (40 - 10 + 1)) + 10;
    }

function  getRandomDistance() {
      // 生成随机配送距离，例如1到5公里
      return Math.floor(Math.random() * (5 - 1 + 1)) + 1;
    }

function    getRandomMinOrder() {
      // 生成随机起送价，例如10到50元
      return Math.floor(Math.random() * (50 - 10 + 1)) + 10;
    }
function getRandomDeliveryFee() {
      // 生成随机配送费，例如2到10元
      return Math.floor(Math.random() * (10 - 2 + 1)) + 2;
    }
function   getRandomAvgPrice() {
      // 生成随机人均消费，例如20到50元
      return Math.floor(Math.random() * (50 - 20 + 1)) + 20;
    }

// 生成长度为500 的递减数组，每次调用函数时返回数组的下一个元素
function getRangeArray() {
    if (array_index >= array.length) {
      array_index  = 0;
    }
    else
      array_index = array_index + 1;
  
    return array[array_index];
}

function generateRandomStars() {
  const stars = [];
  const maxStars = 5; // 最大星级为5
  const minStars = 3; // 最低星级为3
  const rating = Math.floor(Math.random() * (maxStars - minStars + 1)) + minStars; // 随机生成最低星级到最大星级之间的整数作为评分
  for (let i = 0; i < rating; i++) {
    stars.push('★'); // 根据评分添加实心星
  }
  for (let i = rating; i < maxStars; i++) {
    stars.push('☆'); // 剩余的添加空心星
  }
  return stars.join('');
}

 function  getRandomSales() {
      return Math.floor(Math.random() * (4000 - 1000 + 1)) + 1000; // 随机生成1000到4000之间的月销售数量
    }



getList();
</script>

<style scoped>
.special-input ::v-deep .el-input__wrapper {
  border-bottom-left-radius: 50px;
  border-top-left-radius: 50px;
}
.special-input ::v-deep .el-input-group__append{
  border-bottom-right-radius: 50px;
  border-top-right-radius: 50px;
  background-color: #409eff;
  color: white;
}

.store-info {
  display: flex;
  align-items: center;
}
.store-logo {
  width: 95px; /* 根据需要调整大小 */
  height: auto;
  margin-right: 5px;
}


.star {
  color: gold; /* 将星星设置为金色 */
  font-size: 1.7em; /* 增大星星的字体大小 */
  margin-right: 3px; /* 为星星和月销售信息之间添加一些间距 */
}
.store-name {
  font-weight: bold; /* 加粗文本 */
  color: black; /* 设置文本颜色为黑色 */
  font-size: 16px; /* 设置字体大小 */
}
.delivery-info {
  display: flex;
  flex-direction: column;
  font-size: 12px; /* 设置字体大小 */
}
.delivery-info p {
  margin: 1px 0; /* 设置上边距和下边距，根据需要调整 */
}
.tiny-image {
  width: 15px; /* 设置图片宽度 */
  height: 15px; /* 设置图片高度 */
  margin-right: 4px; /* 图片之间的间距 */
}

.el-button {
  color: #ff9800; /* 根据需要调整颜色 */
}


</style>