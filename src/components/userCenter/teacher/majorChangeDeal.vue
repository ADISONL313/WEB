<template>
    <el-card v-for="item in affairsList" style="margin: 2vh;">
        <template #header>
            <div class="card-header">
                <b> 申请人：{{ item.studentName }}</b>
            </div>
        </template>
        <el-row class="rowMargin">
            <el-col :span="12">
                <b>原专业：</b>{{ item.sourceRoomOrMajor }}
            </el-col>
            <el-col :span="12">
                <b>更换到：</b>{{ item.content }}
            </el-col>
        </el-row>
        <el-row class="rowMargin">
            <el-col :span="24">
                <b>理由：</b>{{ item.reason }}
            </el-col>
        </el-row>
        <template #footer>
            <el-row style="line-height: 0;">
                <el-col :span="12">
                    <el-input v-model.trim="item.refuseReason" placeholder="请输入驳回理由" :value="item.refuseReason"
                        style="width: 70vh;" />
                    <el-button type="danger" @click="refuse(item)">驳回</el-button>
                </el-col>
                <el-col :span="12" style="text-align: right;">
                    <el-button type="primary" @click="sendToLeader(item)">提交校级领导审核</el-button>
                </el-col>
            </el-row></template>
    </el-card>
</template>
<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import axios from 'axios';
const props = defineProps({
    majorChangeList: Array
})
const affairsList = ref(props.majorChangeList)
const decreaseAffairs = async (me, id) => {
    if (me === '成功') {
        let index = props.majorChangeList.findIndex(els => els.id === id)
        props.majorChangeList.splice()
        if (index !== -1) {
            props.majorChangeList.splice(index, 1);
        }
    }
}
const refuse = async (item) => {
    if (item.refuseReason === '' || item.refuseReason === null) {
        ElMessage('请填写驳回理由')
        return
    }
    const aData = {
        refuseReason: item.refuseReason,
        audits: 3,
        id: item.id
    }
    await axios.post(`http://localhost:8083/affairs/updateAffairs`, aData).then((res) => {
        ElMessage(res.data.message)
        decreaseAffairs(res.data.message, item.id)
    })
}
const sendToLeader = async (item) => {
    const aData = {
        audits: 1,
        id: item.id
    }
    await axios.post(`http://localhost:8083/affairs/updateAffairs`, aData).then((res) => {
        ElMessage(res.data.message)
        decreaseAffairs(res.data.message, item.id)
    })
}
</script>
<style>
.rowMargin {
    margin-bottom: 1.5vh;
    padding: 2vh;
    border-radius: 1vh;
    background-color: #f4f4f5;
}
</style>