<template>
  <el-form label-width="120px">
    <el-form-item label="学生姓名">
      <el-input v-model.trim="studentName" />
    </el-form-item>
    <el-form-item label="学院">
      <el-select @change="chooseMajor" v-model.trim="college" placeholder="请选择学院">
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
      <el-select v-model.trim="majorName" placeholder="请选择">
        <el-option v-for="item in options" :label="item.majorName" :value="item.id" />
      </el-select>
    </el-form-item>
    <el-form-item v-if="isShow" label="指定辅导员">
      <el-select v-model.trim="counselor" placeholder="请选择">
        <el-option v-for="item in counselorList" :label="item.teacherName" :value="item.teacher_id" />
      </el-select>
    </el-form-item>
    <el-form-item label="身份证">
      <el-input maxlength="18" v-model.trim="idCard" placeholder="最后一位为x请输入大写X" />
    </el-form-item>
    <el-form-item label="手机号">
      <el-input maxlength="11" v-model.trim="mobile" />
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="gender" class="ml-4">
        <el-radio label="男" size="large">男</el-radio>
        <el-radio label="女" size="large">女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-alert style="margin-bottom: 10px;" v-show="formVerify" :title="tip" @close="down" type="warning" />
    <el-form-item>
      <el-button type="primary" @click="addStudent">添加</el-button>
    </el-form-item>
  </el-form>
</template>
<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { md5 } from 'js-md5'
const studentName = ref('')
const idCard = ref('')
const mobile = ref('')
const counselor = ref('')
const counselorList = ref([])
const college = ref('')
const gender = ref('男')
const tip = ref('')
const isShow = ref(false)
if (localStorage.getItem('token') === 'admin') {
  isShow.value = true
}
const formVerify = ref(false)
const options = ref([])
const majorName = ref('')
//添加学生点击事件
const addStudent = async () => {
  if (studentName.value === '' || idCard.value === '' || mobile.value === '' || college.value === '' || majorName.value === '') {
    tip.value = '表单必须全部填写'
    formVerify.value = true
  } else {
    const studentData = {
      studentName: studentName.value,
      id_card: idCard.value,
      mobile: mobile.value,
      college: college.value,
      gender: gender.value,
      major: parseInt(majorName.value),
      counselor: localStorage.getItem('token') === 'admin' ? (counselor.value !== '' ? counselor.value : localStorage.getItem('token')) : localStorage.getItem('token'),
      password: md5(idCard.value.slice(-6))
    }
    axios.post(`http://127.0.0.1:8083/student/insertOne`, studentData).then((res) => {
      if (res.data.data === 1) {
        alert('添加成功')
      } else {
        alert(res.data.message)
      }
    })
  }
}

const getCounselors = async () => {
  await axios.post(`http://127.0.0.1:8083/teacher/all`,{college:college.value}).then((res) => {
    counselorList.value = res.data.data
  })
}
//学院和专业选择器连东莞
const chooseMajor = async () => {
  getCounselors()
  //根据学院查询专业接口
  await axios.get(`http://127.0.0.1:8083/major/selectMajor?college=${college.value}`).then((res) => {
    options.value = res.data.data
    majorName.value = ''
  })
}
const down = () => {
  formVerify.value = false
}
</script>