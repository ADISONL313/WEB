<template>
  <el-upload drag class="upload-demo" accept=".xlsx,.xls" action :limit="1" :on-change="handle" :auto-upload="false">
    <el-icon class="el-icon--upload">
      <Plus />
    </el-icon>
    <div class="el-upload__text">点击或拖拽文件到此处上传</div>
    <div class="el-upload__text">支持xls、xlsx</div>
  </el-upload>
  <el-button style="width: 100%;" class="ml-3" type="success" @click="submitUpload">
    添加
  </el-button>
</template>

<script setup>
import * as XLSX from 'xlsx'
import { readExcel, character } from '@/assets/util.js'
import { ref } from 'vue'
import axios from 'axios';
const studetsData = ref([])
const handle = async (files) => {
  const data = await readExcel(files.raw)
  const workBook = XLSX.read(data, {
    type: 'binary'
  })
  const workSheet = workBook.Sheets[workBook.SheetNames[0]]
  let jsonData = XLSX.utils.sheet_to_json(workSheet)
  jsonData.forEach(async item => {
    await axios.get(`http://127.0.0.1:8083/major/selectMajors?college=${item.学院}&majorName=${item.专业}`).then((res) => {
      item.专业 = res.data.data[0].id
    })
    let obj = {}
    for (let key in character) {
      if (!character.hasOwnProperty(key)) {
        break;
      }
      let v = character[key], text = v.text, type = v.type
      v = item[text] || ''
      type === 'string' ? (v = String(v)) : null
      type === 'number' ? (v = Number(v)) : null
      obj[key] = v
    }
    studetsData.value.push(obj)
  })
  console.log(studetsData.value)
}
const submitUpload = async () => {
  axios.post(`http://127.0.0.1:8083/student/insertStudents`, studetsData.value).then((res) => {
    if (res.data.data === 0) {
      alert(res.data.message)
    } else {
      if (res.data.data < studetsData.value.length) {
        alert('添加成功:有' + (studetsData.value.length - res.data.data) + '个学生数据重复')
      } else {
        alert('添加成功' + res.data.data + '个学生  ')
      }
    }
  })
}
</script>