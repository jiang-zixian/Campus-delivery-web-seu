<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品编号" prop="iId">
        <el-input v-model="queryParams.iId" placeholder="请输入商品编号" clearable @keyup.enter="handleQuery" />
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
        <el-input v-model="queryParams.itemName" placeholder="请输入商品名" clearable @keyup.enter="handleQuery" />
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
        <el-button type="primary" plain icon="ShoppingCart" :disabled="multiple" @click="addmultipleCart">加入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
         >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
         >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="shopping-cart" @click="openCart">购物车</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>



    <el-table v-loading="loading" :data="itemListList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品编号" align="center" prop="iId" />
      <el-table-column label="店家编号" align="center" prop="sId" />
      <el-table-column label="商品图像" align="center" prop="photo" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.photo" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="商品名" align="center" prop="itemName" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="库存数量" align="center" prop="amount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['itemList:itemList:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['itemList:itemList:remove']">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="fixed-width">
        <template #default="scope">
          <el-button type="primary" icon="ShoppingCart" plain circle @click="open1(scope.row)"
            title="加入购物车"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />


    <el-drawer v-model="drawer" title="我的购物车" :with-header="true" :before-close="handleClose" :bottom="0" size="65%">
    <el-row :gutter="8">
      <el-col :span="8">
        <el-form :model="cart" ref="cartRef" :inline="true" label-width="68px">
          <el-form-item label="总价格">
            <el-input v-model="allPrice" disabled></el-input>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="applyallCart">提交订单</el-button>
      </el-col>
    </el-row>
      <el-row>
        <el-table :data="cartList" border :height="tableHeight" >
          <el-table-column type="index" :index="fun1"/>
          <el-table-column label="商品编号" align="center" prop="iId" />
          <el-table-column label="商品图像" align="center" width="100">
            <template #default="scope">
              <image-preview :src="scope.row.photo" :width="50" :height="50" />
            </template>
          </el-table-column>
          <el-table-column label="商品名" align="center" prop="itemName" />
          <el-table-column label="价格" align="center" prop="price" />
          <el-table-column label="购买数量" align="center" prop="num">
            <template #default="scope">
              <el-row>
              <el-input-number v-model="scope.row.num" @change="changeNum(scope.row.iId, scope.row.num)"
                :min="1"></el-input-number>
              </el-row>
            </template>
          </el-table-column>
        <el-table-column label="操作" align="center"> 
          <template #default="scope">
              <el-button type="danger" plain @click="changeNum(scope.row.iId, 0),deleoneitem(scope.row.iId)" title="删除">删除</el-button>
            </template>
        </el-table-column>
        </el-table>
      </el-row>
    </el-drawer>

    <!-- 添加或修改商品列表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="itemListRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店家编号" prop="sId">
          <el-input v-model="form.sId" placeholder="请输入店家编号" />
        </el-form-item>
        <el-form-item label="商品图像" prop="photo">
          <image-upload v-model="form.photo" />
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
  

  <!-- 提交订单的dialog -->
  <el-dialog :title="title" v-model="applyall" width="500px" append-to-body>
    <el-form ref="orderformRef" :model="orderform" :rules="rules" label-width="100px">
      <!-- 这里选择自提或者外卖会出现不同的表单 -->
      <el-form-item label="配送方式" prop="delivery">
        <el-radio-group v-model="orderform.type">
          <el-radio label="2">外卖</el-radio>
          <el-radio label="1">自提</el-radio>
        </el-radio-group>
      </el-form-item>

      <template v-if="orderform.type === '1'">
        <el-form-item label="商家地址" prop="srcPosition">
          <el-input v-model="orderform.srcPosition" placeholder="请输入商家地址" />
        </el-form-item>
      </template>

      <template v-else-if="orderform.type === '2'">
        <el-form-item label="商家地址" prop="srcPosition">
          <el-input v-model="orderform.srcPosition" placeholder="请输入商家地址" />
        </el-form-item>
        <el-form-item label="目的地址" prop="destPosition">
          <el-input v-model="orderform.destPosition" placeholder="请输入目的地址" />
        </el-form-item>
        <el-form-item label="预计送达时间" prop="destTime">
          <el-date-picker v-model="orderform.destTime" type="datetime" placeholder="请选择预计送达时间" />
        </el-form-item>
        <el-form-item label="配送费" prop="deliveryPrice">
          <el-input v-model="orderform.deliveryPrice" placeholder="请输入配送费" />
        </el-form-item>
      </template>

      <el-form-item label="总价" prop="allItemPrice">
        <el-input v-model="computepriceplusde" disabled></el-input>
      </el-form-item>

    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary"  @click="payallitem" >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </template>
    
  </el-dialog>
  
  </div>
</template>

<script setup name="ItemList">
import { listItemList, getItemList, delItemList, addItemList, updateItemList } from "@/api/itemList/itemList";
import {addRecord} from "@/api/record/record";
import { useRoute } from "vue-router";
import { ElNotification } from 'element-plus'

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

  cartList.value = [];
  allPrice.value = 0;

  //根据cart中的商品id赋值给cartList
  //若为空不执行
  if (Object.keys(cart.value).length === 0) {
    return;
  }

  //若在itemlistList中不存在对应的商品id，则删除cart中的该商品
  //若存在，则把itemListList对应的商品id,数量,图片,商品名,g赋值给cartList
  //并计算总价格
  for (let i = 0; i < itemListList.value.length; i++) {
    if (cart.value[itemListList.value[i].iId]) {
      cartList.value.push({
        iId: itemListList.value[i].iId,
        photo: itemListList.value[i].photo,
        itemName: itemListList.value[i].itemName,
        price: itemListList.value[i].price,
        num: cart.value[itemListList.value[i].iId]
      });
      allPrice.value += itemListList.value[i].price * cart.value[itemListList.value[i].iId];
    }
  }
}

// 取消按钮
function cancel() {
  open.value = false;
  applyall.value = false;
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

  orderform.value = {
    recordId: null,
    uId: null,
    sId: route.params.sId || null,
    allItemPrice: null,
    riderId: null,
    deliveryPrice: null,
    status: "0",
    srcPosition: null,
    destPosition: null,
    srcTime: null,
    destTime: null,
    type:"2"
  };
  proxy.resetForm("orderformRef");
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
  proxy.$modal.confirm('是否确认删除商品列表编号为"' + _iIds + '"的数据项？').then(function () {
    return delItemList(_iIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => { });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('itemList/itemList/export', {
    ...queryParams.value
  }, `itemList_${new Date().getTime()}.xlsx`)
}





//购物车
const cart = ref([]);
const drawer = ref(false);
const cartList = ref([]);
const allPrice = ref(0);

//订单对话框
const applyall = ref(false);

const orderform=ref({});

// 添加商品到购物车
const addToCart = (itemId) => {
  if (cart.value[itemId]) {
    cart.value[itemId]++;
  } else {
    cart.value[itemId] = 1;
  }
};

//成功加入购物车
function open1(row){
  ElNotification({
    title: 'Success',
    message: '成功加入购物车',
    type: 'success',
  })
  addToCart(row.iId);
}

function addmultipleCart(row)
{
  const _iIds = row.iId || ids.value;
  proxy.$modal.confirm('是否确认将商品编号为"' + _iIds + '"的商品加入购物车？').then(() => {
    for (let i = 0; i < _iIds.length; i++) {
      addToCart(_iIds[i]);
    }
    ElNotification({
    title: 'Success',
    message: '成功加入购物车',
    type: 'success',
  })
  }).catch(() => { });

}

//打开购物车界面
const openCart = () => {
  getList();
  drawer.value = true;
};

//删除一个商品
const deleoneitem = (itemId) => {
  delete cart.value[itemId];
  allPrice.value -= cartList.value.find(item => item.iId === itemId).price * cartList.value.find(item => item.iId === itemId).num;
  cartList.value = cartList.value.filter(item => item.iId !== itemId);
};

//关闭购物车界面
const handleClose = () => {
  drawer.value = false;
};

//修改数目
const changeNum = (itemId, num) => {

  let old=cart.value[itemId];
  cart.value[itemId] = num;
  allPrice.value += (num-old) * cartList.value.find(item => item.iId === itemId).price;

  cartList.value.find(item => item.iId === itemId).num = num;


};

//支付界面
const applyallCart = () => {
  reset();
  applyall.value = true;
};


//支付订单
const payallitem = () => {
  proxy.$refs["orderformRef"].validate(valid => {
    if (valid) {
      orderform.value.allItemPrice = allPrice.value;
      orderform.value.srcTime = Date.now();

      console.log(orderform.value);
      addRecord(orderform.value).then(response => {
      cart.value = [];
      cartList.value = [];
      allPrice.value = 0;

      applyall.value = false;
      proxy.$modal.msgSuccess("下单成功");

      getList();
     });
   
    }
  })};

  //一个计算属性，计算总价格,转成int类型
  const computepriceplusde = computed(() => {
    return parseInt(allPrice.value) + parseInt(orderform.value.deliveryPrice || 0);
  });

  const fun1 = (index) => {
    return index;
  };
getList();
</script>
