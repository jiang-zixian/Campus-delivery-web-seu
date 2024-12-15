<template>
  <div style="display: flex; width: 100%; height: 300%;">
    <!-- 左侧饼图 -->
    <div ref="pieChart" style="width: 50%; height: 300%;"></div>

    <!-- 右侧折线图 -->
    <div ref="lineChart" style="width: 50%; height: 300%;"></div>
  </div>

  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商店编号" prop="sId">
        <el-input
            v-model="queryParams.sId"
            placeholder="请输入商店号"
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
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['store:myStore:add']"
        >创建商店</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['store:myStore:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['store:myStore:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['store:myStore:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="myStoreList" @selection-change="handleSelectionChange">
      <el-table-column type="expand">
        <template #default="props">
          <p><b>商店地点: </b></p>
          <p>{{ splitDescription1(props.row.description) }}</p>
          <p><b>商店描述: </b></p>
          <p>{{ splitDescription2(props.row.description) }}</p>
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商店号" align="center" prop="sId" />
      <el-table-column label="店家号" align="center" prop="uId" />
      <el-table-column label="商店名称" align="center" prop="sname" />
      <el-table-column label="图标" align="center" prop="logo" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.logo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width"  width="500">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['store:myStore:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['store:myStore:remove']">删除</el-button>
          <el-button link type="primary" icon="Memo" @click="handleEditStore(scope.row)" v-hasPermi="['store:myStore:EditStore']">管理商品</el-button>
          <el-button link type="primary" icon="Document" @click="handleCheckRecord(scope.row)" v-hasPermi="['store:myStore:storeRecord']">查看订单记录</el-button>
          <el-button link type="primary" icon="ChatDotRound" @click="handleCheckComment(scope.row)" v-hasPermi="['store:myStore:myComment']">查看评论</el-button>
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



    <!-- 添加或修改我的商店对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="myStoreRef" :model="form" :rules="rules" label-width="80px">
        <!--
        <el-form-item label="商店号" prop="sId">
          <el-input v-model="form.sId" placeholder="请输入商店号" />
        </el-form-item>
        <el-form-item label="店家号" prop="uId">
          <el-input v-model="form.uId" placeholder="请输入店家号" />
        </el-form-item>
        -->
        <el-form-item label="商店名称" prop="sname">
          <el-input v-model="form.sname" placeholder="请输入商店名称" />
        </el-form-item>
        <el-form-item label="图标" prop="logo">
          <image-upload v-model="form.logo"/>
        </el-form-item>
        <el-form-item label="商店描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" :autosize="{ minRows: 2, maxRows: 10 }" />
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

<script setup name="MyStore">
import {listMyStore, getMyStore, delMyStore, addMyStore, updateMyStore} from "@/api/store/myStore";
import {StarFilled} from "@element-plus/icons-vue";
import { onMounted, ref, inject } from "vue";

// 通过inject使用echarts
const echarts = inject("echarts");

// 通过ref获取两个图表的HTML元素
const pieChart = ref();
const lineChart = ref();

onMounted(() => {
  // 获取父元素
  const pieEl = pieChart.value;
  const lineEl = lineChart.value;

  // 初始化饼图
  const pieEchart = echarts.init(pieEl, "light");

  // 初始化折线图
  const lineEchart = echarts.init(lineEl, "light");

  // 饼图配置项
  const pieOption = {
    title: {
      text: '各商店总营业额占比',
      left: 'center'
    },
    legend: {
      left: 'center',
      top: 'bottom',
      data: ["天猫超市", "711便利店", "茶百道"],
      icon: "rect",
    },
    tooltip: {
      trigger: "item",
      formatter: function (params) {
        return `${params.name}: ${params.value} 元 (${params.percent.toFixed(2)}%)`; // 显示总额和占比，保留两位小数
      }
    },
    series: [
      {
        type: "pie",
        radius: ['30%', '70%'],
        data: [
          { value: 10170, name: "天猫超市" },
          { value: 17570, name: "711便利店" },
          { value: 16670, name: "茶百道" },
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
        color: [
          "#516b91",
          "#59c4e6",
          "#edafda",
          "#93b7e3",
          "#a5e7f0",
          "#cbb0e3",
          "#c12e34",
          "#e6b600",
          "#0098d9",
          "#2b821d",
          "#005eaa",
          "#339ca8",
          "#cda819",
          "#32a487",
        ],
      },
    ],
  };


// 折线图配置项
  const lineOption = {
    title: {
      text: '各商店月度营业额数据总表',
      left: 'center'
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["天猫超市", "711便利店", "茶百道"], // 添加三个商店的名称
      left: 'center',
      top: 'bottom'
    },
    xAxis: {
      type: "category",
      data: ["2024-1", "2024-2","2024-3","2024-4","2024-5","2024-6","2024-7"], // 日期轴
    },
    yAxis: {
      type: "value",
      axisLabel: {
        formatter: '¥{value}', // 格式化 y 轴的标签为货币
      },
    },
    series: [
      {
        name: "天猫超市", // 第一个商店
        type: "line",
        data: [1200, 1320, 1010, 1340, 900, 2300, 2100], // 商店A的营业额数据
        smooth: true, // 平滑曲线
      },
      {
        name: "711便利店", // 第二个商店
        type: "line",
        data: [2200, 1820, 1910, 2340, 2900, 3300, 3100], // 商店B的营业额数据
        smooth: true, // 平滑曲线
      },
      {
        name: "茶百道", // 第三个商店
        type: "line",
        data: [1500, 2320, 2010, 1540, 1900, 3300, 4100], // 商店C的营业额数据
        smooth: true, // 平滑曲线
      },
    ],
  };


  // 设置图表选项
  pieEchart.setOption(pieOption);
  lineEchart.setOption(lineOption);
});

const router = useRouter();

const {proxy} = getCurrentInstance();

const myStoreList = ref([]);
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
    sId: null,
    uId: null,
    sname: null,
    logo: null,
    description: null
  },
  rules: {}
});

const {queryParams, form, rules} = toRefs(data);

/** 查询我的商店列表 */
function getList() {
  loading.value = true;
  listMyStore(queryParams.value).then(response => {
    myStoreList.value = response.rows;
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
  proxy.resetForm("myStoreRef");
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
  title.value = "添加我的商店";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _sId = row.sId || ids.value
  getMyStore(_sId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改我的商店";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["myStoreRef"].validate(valid => {
    if (valid) {
      if (form.value.sId != null) {
        updateMyStore(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMyStore(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除我的商店编号为"' + _sIds + '"的数据项？').then(function () {
    return delMyStore(_sIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('store/myStore/export', {
    ...queryParams.value
  }, `myStore_${new Date().getTime()}.xlsx`)
}

function handleEditStore(row) {
  const sid = row.sId;
  router.push("/store/EditStore/Edit/" + sid);
}

function handleCheckRecord(row) {
  const sid = row.sId;
  router.push("/store/storeRecord/" + sid);
}

function handleCheckComment(row) {
  const sid = row.sId;
  router.push("/store/myComment/" + sid);
}

function splitDescription1(str) {
  const parts = str.split(' ');
  return parts[0];
}

function splitDescription2(str) {
  const parts = str.split(' ');
  return parts.slice(1).join(' ');
}

getList();
</script>