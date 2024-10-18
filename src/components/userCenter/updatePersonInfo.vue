<template>
  <el-form label-width="120px">
    <el-form-item label="账号">
      <el-input />
    </el-form-item>
    <el-form-item label="姓名">
      <el-input />
    </el-form-item>
    <el-form-item label="学院">
      <el-select @change="chooseMajor" v-model.trim="college" placeholder="请选择学院">
        <el-option label="人工智能与大数据学院" value="人工智能与大数据学院" />
        <el-option label="电气工程与智能制造学院" value="电气工程与智能制造学院" />
        <el-option label="马克思主义学院" value="马克思主义学院" />
        <el-option label="艺术与传媒学院" value="艺术与传媒学院" />
        <el-option label="经济管理学院" value="经济管理学院" />
        <el-option label="建筑与土木工程学院" value="建筑与土木工程学院" />
      </el-select>
    </el-form-item>
    <el-form-item label="专业">
      <el-select v-model.trim="majorName" placeholder="">
        <el-option v-for="item in options" :label="item.majorName" :value="item.majorName" />
      </el-select>
    </el-form-item>
    <el-form-item label="身份证">
      <el-input />
    </el-form-item>
    <el-form-item label="手机号">
      <el-input />
    </el-form-item>
  </el-form>
</template>
<script setup>
import { ref } from 'vue'
import axios from 'axios';
let options = ref([])
const college = ref('')
const majorName = ref('')
const chooseMajor = async () => {
  await axios.get(`http://127.0.0.1:8083/major/selectMajor?college=${college.value}`).then((res) => {
    options.value = res.data.data
    majorName.value = ''
  })
}
</script>