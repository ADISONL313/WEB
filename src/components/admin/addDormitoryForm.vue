<template>
    <!-- 添加表单 -->
    <el-form inline="true">
        <el-form-item label="楼栋">
            <el-select style="width: 20vh;" v-model.trim="gender">
                <el-option value="男生公寓" lable="0" />
                <el-option value="女生公寓" lable="0" />
            </el-select>-
            <el-select style="width: 20vh;" v-model.trim="buildingName">
                <el-option v-for="item in 4" :value="item" lable="0" />
            </el-select>
        </el-form-item>
        <el-form-item label="房间号">
            <el-input v-model.trim="roomId" type="number" />
        </el-form-item>
        <el-form-item>
            <el-button @click="add" type="primary">添加</el-button>
        </el-form-item>
    </el-form>
    <el-divider />
    <h3>宿舍管理</h3>
    <el-form inline="true">
        <el-form-item label="楼栋">
            <el-input v-model.trim="buildingNameForSearch" />
        </el-form-item>
        <el-form-item label="房间号">
            <el-input v-model.trim="roomIdForSearch" type="number" />
        </el-form-item>
        <el-form-item>
            <el-button @click="search" type="primary">查询</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%">
        <el-table-column prop="buildingName" label="楼栋" />
        <el-table-column prop="room_id" label="房间" />
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
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue'
const gender = ref('')
const buildingName = ref('')
const roomId = ref('')
const buildingNameForSearch = ref('')
const roomIdForSearch = ref('')
const howLong = ref(1)
const currentPage = ref(1)
const pageSize = ref(10)
const tableData = ref([])


const getTableData = async () => {
    const tData = {
        buildingName: buildingNameForSearch.value,
        room_id: roomIdForSearch.value
    }
    await axios.post(`http://127.0.0.1:8083/dormitory/getDormitories`, tData).then((res) => {
        tableData.value = res.data.data
    })
}


const deleteRow = async (index, id) => {
    await axios.get(`http://127.0.0.1:8083/dormitory/delDormitory?id=${id}`)
    tableData.value.splice(index, 1)
}

//添加寝室方法
const add = async () => {
    const dData = {
        buildingName: gender.value + buildingName.value + '栋',
        room_id: roomId.value,
        gender: gender.value === '男生公寓' ? 0 : 1
    }
    await axios.post(`http://127.0.0.1:8083/dormitory/addDormitory`, dData).then((res) => {
        ElMessage(res.data.message)
        if (res.data.message === '添加成功') {
            getTableData()
        }
    })
}

const search = () => {
    getTableData()
}

getTableData()

const handlePageChange = (page) => {
    currentPage.value = page
}


</script>