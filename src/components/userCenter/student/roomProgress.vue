<template>
    <h3>寝室更换申请</h3>
    <el-steps :process-status="audits === 2 ? 'error' : 'success'" :active="audits === 0 ? 0 : (audits === 1 ? 1 : 1)">
        <el-step title="辅导员审核" description="辅导员审核中" />
        <el-step :title="audits === 2 ? '已被驳回' : '通过'" :description="audits === 2 ? '驳回原因:' + refuseReason : '通过'" />
    </el-steps>
</template>
<script setup>
import { ref } from 'vue';
import axios from 'axios';
const audits = ref()
const refuseReason = ref()
const getProgress = async () => {
    await axios.post(`http://127.0.0.1:8083/affairs/queryAllAffairs`, { student_id: parseInt(localStorage.getItem('token')), affairsType: 2 }).then((res) => {
        if (res.data.message !== '暂无信息') {
            audits.value = res.data.data[0].audits
            refuseReason.value = res.data.data[0].refuseReason
        }
    })
}
getProgress()
</script>