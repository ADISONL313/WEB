<template>
  <!-- 添加表单 -->
  <el-form inline="true">
    <el-form-item label="所属学院">
      <el-select v-model.trim="college" style="width: 30vh;" placeholder="请选择">
        <el-option label="人工智能与大数据学院" value="人工智能与大数据学院" />
        <el-option label="电气工程与智能制造学院" value="电气工程与智能制造学院" />
        <el-option label="马克思主义学院" value="马克思主义学院" />
        <el-option label="艺术与传媒学院" value="艺术与传媒学院" />
        <el-option label="经济管理学院" value="经济管理学院" />
        <el-option label="建筑与土木工程学院" value="建筑与土木工程学院" />
        <el-option label="体育与大健康学院" value="体育与大健康学院" />
        <el-option label="建筑管理学院" value="建筑管理学院" />
        <el-option label="人文学院" value="人文学院" />
      </el-select>
    </el-form-item>
    <el-form-item label="专业名称">
      <el-input v-model.trim="majorName" style="width: 30vh;" placeholder="请输入" />
    </el-form-item>
    <el-form-item>
      <el-button @click="add" type="primary">添加</el-button>
    </el-form-item>
  </el-form>
  <el-alert style="margin-bottom: 10px;" v-show="formVerify" :title="tip" @close="down" type="warning" />
  <el-divider />
  <h3>专业管理</h3>
  <!-- 查询表单 -->
  <el-form inline="true">
    <el-form-item label="学院">
      <el-select v-model.trim="college2" style="width: 30vh;" placeholder="请选择">
        <el-option label="请选择" value="" />
        <el-option label="人工智能与大数据学院" value="人工智能与大数据学院" />
        <el-option label="电气工程与智能制造学院" value="电气工程与智能制造学院" />
        <el-option label="马克思主义学院" value="马克思主义学院" />
        <el-option label="艺术与传媒学院" value="艺术与传媒学院" />
        <el-option label="经济管理学院" value="经济管理学院" />
        <el-option label="建筑与土木工程学院" value="建筑与土木工程学院" />
        <el-option label="体育与大健康学院" value="体育与大健康学院" />
        <el-option label="建筑管理学院" value="建筑管理学院" />
        <el-option label="人文学院" value="人文学院" />
      </el-select>
    </el-form-item>
    <el-form-item label="专业">
      <el-input v-model.trim="majorName2" style="width: 30vh;" placeholder="请输入" />
    </el-form-item>
    <el-form-item>
      <el-button @click="search" type="primary">查询</el-button>
    </el-form-item>
  </el-form>
  <el-divider />

  <!-- 表格 -->
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%">
    <el-table-column prop="college" label="学院" />
    <el-table-column label="专业">
      <template #default="scope">
        <el-input v-model="scope.row.majorName" :value="scope.row.majorName" />
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="200">
      <template #default="scope">
        <el-button type="primary" size="small" @click.prevent="updateMajor(scope.row)">修改</el-button>
        <el-button type="danger" size="small" @click.prevent="deleteRow(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination :current-page="currentPage" @current-change="handlePageChange" background layout="prev, pager, next"
    :page-count="Math.ceil(howLong / 10)" :page-size="pageSize" style="margin-top: 1vh;margin-left: 45%;" />
</template>
<script setup>
import { ref } from 'vue'
import axios from 'axios';
import { ElMessage } from 'element-plus';
// 变量
const college = ref('')
const majorName = ref('')
const formVerify = ref(false)
const college2 = ref('')
const majorName2 = ref('')
const tableData = ref([])
const isEdit = ref(false)
const tip = ref('')
const howLong = ref(1)
const currentPage = ref(1)
const pageSize = ref(10)
// 添加专业方法
const add = async () => {
  if (college.value === '' || majorName.value === '') {
    tip.value = '学院和专业为必填项'
    formVerify.value = true
  } else {

    const majorData = {
      college: college.value,
      majorName: majorName.value
    }
    await axios.post(`http://127.0.0.1:8083/major/insert`, majorData).then((res) => {
      const sdata = res.data.data
      if (sdata === '专业已存在') {
        alert("专业已存在")
      } else {
        getTableData()
      }
    })
  }
}
// 查询数据方法
const getTableData = async () => {
  await axios.get(`http://127.0.0.1:8083/major/selectMajors?college=${college2.value}&majorName=${majorName2.value}`).then((res) => {
    tableData.value = res.data.data
    howLong.value = res.data.data.length
  })
}
// 初始调用查询数据方法
getTableData()
// 删除某个专业数据方法
const deleteRow = async (index, row) => {
  await axios.get(`http://127.0.0.1:8083/major/delMajor?id=${row.id}`).then((res) => {
    ElMessage(res.data.message)
    if (res.data.message === '删除成功') {
      tableData.value.splice(index, 1)
    }
  })
}
// 查询按钮
const search = () => {
  getTableData()
}
//修改按钮
const updateMajor = async (row) => {
  console.log(row.id)
  await axios.get(`http://127.0.0.1:8083/major/updateMajor?majorName=${row.majorName}&id=${row.id}`).then((res) => {
    ElMessage(res.data.message)
  })
}
// 关闭提示
const down = () => {
  formVerify.value = false
}


const handlePageChange = (page) => {
  currentPage.value = page
}
</script>