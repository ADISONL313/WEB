<template>
    <el-form label-width="120px" :inline="true">
        <el-form-item label="学生姓名">
            <el-input v-model.trim="studentName" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="专业名称">
            <el-input v-model.trim="major" style="width: 30vh;" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="省份">
            <el-input v-model.trim="province" style="width: 30vh;" placeholder="请输入" />
        </el-form-item>
        <el-form-item>
            <el-button @click="search" type="primary">查询</el-button>
        </el-form-item>
    </el-form>
    <el-divider />
    <!-- 表格 -->
    <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%">
        <el-table-column prop="student_id" label="学号" />
        <el-table-column prop="studentName" label="姓名" />
        <el-table-column width="100" prop="gender" label="性别" />
        <el-table-column prop="id_card" label="身份证" />
        <el-table-column prop="mobile" label="电话" />
        <el-table-column prop="counselor" label="辅导员" />
        <el-table-column prop="college" label="学院" />
        <el-table-column prop="majorName" label="专业" />
        <el-table-column label="省份">
            <template #default="scope">
                <el-select v-model.lazy.trim="scope.row.province" placeholder="">
                    <el-option v-for="item in ChinaProvince" :label="item.label" :value="item.value" :key="item.key" />
                </el-select>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
                <el-button type="primary" size="small" @click="goDetail(scope.row)">详情</el-button>
                <el-button type="primary" size="small" @click="handleEdit(scope.row)">修改</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination :current-page="currentPage" @current-change="handlePageChange" background layout="prev, pager, next"
        :page-count="Math.ceil(howLong / 10)" :page-size="pageSize" style="margin-top: 1vh;margin-left: 45%;" />
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ChinaProvince } from '@/assets/util.js'
const major = ref('')
const studentName = ref('')
const router = useRouter()
const majorList = ref()
const tableData = ref([])
const province = ref('')
const howLong = ref(1)
const currentPage = ref(1)
const pageSize = ref(10)
const getStudentsData = async () => {
    const stData = {
        studentName: studentName.value,
        major: major.value,
        province: province.value,
        counselor: localStorage.getItem('token')
    }
    await axios.post(`http://127.0.0.1:8083/student/selectStudentByCounselor`, stData).then((res) => {
        tableData.value = res.data.data
        howLong.value = res.data.data.length
    })
}
getStudentsData()
const handleDelete = async (index, row) => {
    tableData.value.splice(index, 1)
    await axios.get(`http://127.0.0.1:8083/student/delStudent?student_id=${row.student_id}`)
}

const chooseMajor = async (row) => {
    await axios.get(`http://127.0.0.1:8083/major/selectMajor?college=${row.college}`).then((res) => {
        majorList.value = res.data.data
    })
}

const handleEdit = async (row) => {
    const stData = {
        province: row.province,
        student_id: row.student_id
    }
    console.log(stData)
    await axios.post(`http://127.0.0.1:8083/student/updateStudent`, stData)
}
const search = () => {
    getStudentsData()
}

const goDetail = (row) => {
    router.push({
        path: '/home/studentsAdmin/' + row.student_id,
        query: {
            studentName: row.studentName,
            major: row.major,
            gender: row.gender
        }
    })
}
const handlePageChange = (page) => {
    currentPage.value = page
}
</script>