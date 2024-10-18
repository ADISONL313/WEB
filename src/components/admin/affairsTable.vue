<template>
    <el-form label-width="120px" :inline="true">
        <el-form-item label="姓名">
            <el-input v-model.trim="studentName" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="申请类型">
            <el-select v-model.trim="affairsType" style="width: 20vh;">
                <el-option lable="请选择" value="" />
                <el-option lable="专业更换" value="专业更换" />
                <el-option lable="寝室更换" value="寝室更换" />
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button @click="search" type="primary">查询</el-button>
        </el-form-item>
    </el-form>
    <el-divider />
    <el-table :data="affairsTableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%">
        <el-table-column prop="student_id" label="学号" width="120" />
        <el-table-column prop="studentName" label="姓名" width="100" />
        <el-table-column prop="sourceRoomOrMajor" label="原:专业/寝室" />
        <el-table-column prop="content" label="更换到:专业/寝室" />
        <el-table-column prop="reason" label="更换理由" />
        <el-table-column label="申请类型" width="100">
            <template #default="scope">
                <el-tag>{{ scope.row.affairsType === 1 ? "专业更换" : "寝室更换" }}</el-tag>
            </template>
        </el-table-column>
        <el-table-column label="状态">
            <template #default="scope">
                <el-tag v-if="scope.row.audits === 0">等待辅导员审核</el-tag>
                <el-tag v-if="scope.row.audits === 1 && scope.row.affairsType === 1">等待校级领导审核</el-tag>
                <el-tag v-if="scope.row.audits === 2 && scope.row.affairsType === 1">审核通过</el-tag>
                <el-tag v-if="scope.row.audits === 2 && scope.row.affairsType === 2">申请未通过</el-tag>
                <el-tag v-if="scope.row.audits === 1 && scope.row.affairsType === 2">审核通过</el-tag>
                <el-tag v-if="scope.row.audits === 3 && scope.row.affairsType === 1">申请未通过</el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作" width="140">
            <template #default="scope">
                <el-button type="primary" v-if="scope.row.affairsType !== 2" size="small"
                    @click="router.push('/home/dealAffairs/' + scope.row.id)">详情</el-button>
                <el-button size="small" type="danger" @click="delAffairs(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination :current-page="currentPage" @current-change="handlePageChange" background layout="prev, pager, next"
        :page-count="Math.ceil(howLong / 10)" :page-size="pageSize" style="margin-top: 1vh;margin-left: 45%;" />
</template>
<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
const router = useRouter()
const affairsTableData = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const howLong = ref(1)
const studentName = ref('')
const affairsType = ref('')
const getAffairsTableData = async () => {
    const aData = {
        studentName: studentName.value,
        affairsType: affairsType.value === '' ? 0 : affairsType.value === '专业更换' ? 1 : 2
    }
    await axios.post(`http://127.0.0.1:8083/affairs/queryAllAffairs`, aData).then((res) => {
        affairsTableData.value = res.data.data
        howLong.value = res.data.data.length
        console.log(affairsTableData.value)
    })
}
getAffairsTableData()
const handlePageChange = (page) => {
    currentPage.value = page
}

const search = () => {
    getAffairsTableData()
}
const delAffairs = async (index, row) => {
    await axios.get(`http://127.0.0.1:8083/affairs/delAffairs?id=${row.id}`).then((res) => {
        ElMessage(res.data.message)
        if (res.data.message === '成功') {
            affairsTableData.value.splice(index, 1)
        }
    })
}
</script>