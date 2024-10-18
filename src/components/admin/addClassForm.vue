<template>
    <!-- 添加表单 -->
    <el-form inline="true">
        <el-form-item label="学院">
            <el-select style="width: 50vh;" @change="chooseMajor" v-model.trim="college" placeholder="请选择学院">
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
        <el-form-item label="年级">
            <el-select style="width: 50vh;" v-model.trim="grade" placeholder="请选择年级">
                <el-option v-for="item in years" :label="item" :value="item" />
            </el-select>
        </el-form-item>
        <el-form-item label="班级">
            <el-input style="width: 50vh;" v-model.trim="classNum" type="number" maxlength="2" placeholder="请输入">
                <template #prepend>
                    <el-select style="width: 30vh;" v-model.trim="majorName" placeholder="请选择">
                        <el-option v-for="item in options" :label="item.majorName" :value="item.majorName + item.id" />
                    </el-select></template>
                <template #append>班</template>
            </el-input>
        </el-form-item>
        <el-form-item>
            <el-button @click="add" type="primary">添加</el-button>
        </el-form-item>
    </el-form>
    <el-alert style="margin-bottom: 10px;" v-show="formVerify" :title="tip" @close="down" type="warning" />
    <el-divider />
    <h3>班级管理</h3>
    <el-form inline="true">
        <el-form-item label="专业">
            <el-input v-model.trim="majorNameForSearch" />
        </el-form-item>
        <el-form-item label="年级">
            <el-select style="width: 50vh;" v-model.trim="gradeForSearch" placeholder="请选择学院">
                <el-option v-for="item in years" :label="item" :value="item" />
            </el-select>
        </el-form-item>
        <el-form-item label="班级名称">
            <el-input v-model.trim="classNameForSearch" />
        </el-form-item>
        <el-form-item>
            <el-button @click="search" type="primary">查询</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%">
        <el-table-column prop="college" label="学院" />
        <el-table-column prop="majorName" label="专业" />
        <el-table-column prop="grade" label="年级" />
        <el-table-column prop="className" label="班级" />
        <el-table-column fixed="right" label="操作" width="200">
            <template #default="scope">
                <el-button type="danger" size="small"
                    @click.prevent="deleteRow(scope.$index, scope.row.id)">删除</el-button>
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
const college = ref('')
const majorName = ref('')
const classNum = ref('')
const formVerify = ref(false)
const tip = ref('')
const tableData = ref([])
const options = ref([])
const classNameForSearch = ref('')
const majorNameForSearch = ref('')
const gradeForSearch = ref('')
const howLong = ref(1)
const currentPage = ref(1)
const pageSize = ref(10)
const grade = ref('')
const year = new Date().getFullYear()
const years = Array.from({ length: (year + 1) - (year - 4) }, (_, i) => i + year - 3)
//学院专业联动选择器
const chooseMajor = async () => {
    getMajor()
}
//获取专业
const getMajor = async () => {
    const res = await axios.get(`http://127.0.0.1:8083/major/selectMajor?college=${college.value}`).then((res) => {
        options.value = res.data.data
        majorName.value = ''
    })
    return res
}
//添加班级表单
const add = async () => {
    if (classNum.value === '0' || classNum.value === '' || college.value === '' || majorName.value === null) {
        formVerify.value = true
        tip.value = '请输入正确班级或检查表单是否填写完毕'
        return
    }
    const classData = {
        className: majorName.value.replace(/\d+/g, '') + classNum.value + '班',
        major_id: majorName.value.match(/\d+/)[0],
        grade: grade.value
    }
    await axios.post(`http://127.0.0.1:8083/classes/addClass`, classData).then((res) => {
        ElMessage(res.data.message)
        if (res.data.message === '添加成功') {
            getTableData()
        }
    })
}
const getTableData = async () => {
    const cData = {
        className: classNameForSearch.value,
        majorName: majorNameForSearch.value,
        grade: gradeForSearch.value
    }
    await axios.post(`http://127.0.0.1:8083/classes/getClassesTableData`, cData).then((res) => {
        tableData.value = res.data.data
        howLong.value = res.data.data.length
    })
}

const deleteRow = async (index, id) => {
    await axios.get(`http://127.0.0.1:8083/classes/delClass?id=${id}`)
    tableData.value.splice(index, 1)
}
getTableData()
const search = async () => {
    getTableData()
}


const handlePageChange = (page) => {
    currentPage.value = page
}



const down = () => {
    formVerify.value = false
}
</script>