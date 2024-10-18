<template>
    <h3>专业更换申请</h3>
    <el-steps :process-status="audits === 3 ? 'error' : 'success'" :active="audits === 0 ? 0 : (audits === 1 ? 1 : 2)">
        <el-step title="辅导员审核" description="辅导员审核中" />
        <el-step title="校级领导审核" description="校领导审核中" />
        <el-step :title="audits === 3 ? '已被驳回' : '通过'" :description="audits === 3 ? '驳回原因:' + refuseReason : '通过'" />
    </el-steps>
</template>
<script setup>
import { ref } from 'vue';
import axios from 'axios';
const audits = ref()
const refuseReason = ref()
const getProgress = async () => {
    await axios.post(`http://127.0.0.1:8083/affairs/queryAllAffairs`, { student_id: parseInt(localStorage.getItem('token')), affairsType: 1 }).then((res) => {
        if (res.data.message !== '暂无信息') {
            audits.value = res.data.data[0].audits
            refuseReason.value = res.data.data[0].refuseReason
        }
    })
}
getProgress()
</script>