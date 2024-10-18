<template>
  <el-form label-width="120px">
    <el-form-item label="教师姓名">
      <el-input v-model.trim="teacherName" />
    </el-form-item>
    <el-form-item label="身份证">
      <el-input maxlength="18" v-model.trim="idCard" placeholder="最后一位为x请输入大写X" />
    </el-form-item>
    <el-form-item label="手机号">
      <el-input maxlength="11" v-model.trim="mobile" />
    </el-form-item>
    <el-form-item label="所属学院">
      <el-select v-model.trim="college" placeholder="请选择">
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
    <el-form-item label="性别">
      <el-radio-group v-model="gender" class="ml-4">
        <el-radio label="男" size="large">男</el-radio>
        <el-radio label="女" size="large">女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="备注">
      <el-input v-model.trim="remarks" type="textarea" />
    </el-form-item>
    <el-form-item>
      <el-alert style="margin-bottom: 10px;" v-show="formVerify" :title="tip" @close="down" type="warning" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addOneTeacher">添加</el-button>
    </el-form-item>
  </el-form>
</template>
<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { md5 } from 'js-md5'
const teacherName = ref('')
const idCard = ref('')
const mobile = ref('')
const college = ref('')
const gender = ref('男')
const remarks = ref('')
const tip = ref('')
const formVerify = ref(false)
const addOneTeacher = async () => {
  if (teacherName.value === '' || idCard.value === '') {
    tip.value = '姓名和身份证为必填项'
    formVerify.value = true
  } else {
    const nowTime = new Date()
    const account = nowTime.getFullYear().toString() + (nowTime.getMonth() + 1) + nowTime.getDay() + nowTime.getHours() + nowTime.getMinutes() + nowTime.getSeconds()
    const teacherData = {
      teacherName: teacherName.value,
      idCard: idCard.value,
      mobile: mobile.value,
      college: college.value,
      gender: gender.value,
      teacher_id: account,
      password: md5(account),
      remarks: remarks.value
    }
    console.log(teacherData)
    await axios.post(`http://127.0.0.1:8083/teacher/addTeacher`, teacherData).then((res) => {
      const sdata = res.data.data
      if (sdata === '用户已存在') {
        alert("用户已存在")
      } else {
        alert('添加成功')
      }
    })
  }
}
const down = () => {
  formVerify.value = false
}
</script>