<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <el-row>
                    <el-col :span="12">
                        <b>{{ studentName }}的专业更换申请</b>
                    </el-col>
                    <el-col :span="12" style="text-align: right;">
                        <el-button class="button" type="primary" @click="router.back()">返回</el-button>
                    </el-col>
                </el-row>
            </div>
        </template>
        <h1 style="text-align: center;" v-if="audits === 2 || audits === 3">
            {{ audits === 2 ? "已通过" : "已驳回" }}
        </h1>
        <div v-if="audits !== 2 && audits !== 3">
            <el-row class="rowMargin">
                <el-col :span="12">
                    <div><b>原专业：</b>{{ sourceRoomOrMajor }}</div>
                </el-col>
                <el-col :span="12">
                    <div><b>更换到：</b>{{ content }}</div>
                </el-col>
            </el-row>
            <el-row class="rowMargin">
                <el-col :span="12">
                    <div><b>申请理由：</b>{{ reason }}</div>
                </el-col>
                <el-col :span="12">
                    <el-button type="primary" @click="isClass = true, isRefuse = false">拟确认</el-button>
                    <el-button type="danger" @click="isClass = false, isRefuse = true">取消拟确认</el-button>
                </el-col>
            </el-row>
            <el-row v-if="isClass" class="rowMargin">
                <el-col :span="12">
                    <div><b>现班级：</b>{{ grade + "级，" + oldClassName }}</div>
                </el-col>
                <el-col :span="12">选择新班级：
                    <el-select v-model.trim="class_id" style="width: 40vh;">
                        <el-option v-for="item in majorList" :label="item.className" :value="item.id"></el-option>
                    </el-select>
                </el-col>
            </el-row>
        </div>

        <template v-if="audits !== 2 && audits !== 3" #footer>
            <el-row>
                <el-col :span="12">
                    <div v-if="isRefuse">
                        <el-input placeholder="请输入驳回理由" v-model.trim="refuseReason" style="width: 70vh;" />
                        <el-button type="danger" @click="refuse">驳回</el-button>
                    </div>
                </el-col>
                <el-col :span="12" style="text-align: right;">
                    <el-button v-if="isClass" type="primary" @click="pass">通过</el-button>
                </el-col>
            </el-row>
        </template>
    </el-card>
</template>
<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter()
const props = defineProps({
    id: Number
})
const isRefuse = ref(true)
import { ElMessage } from 'element-plus';
import axios from 'axios'
const isClass = ref(false)
const affairsList = ref([])
const student_id = ref()
const studentName = ref()
const content = ref()
const sourceRoomOrMajor = ref()
const reason = ref()
const oldClassName = ref()
const grade = ref()
const majorList = ref()
const refuseReason = ref('')
const class_id = ref(null)
const audits = ref()
const getAffairsById = async () => {
    const aData = {
        id: props.id
    }
    await axios.post(`http://127.0.0.1:8083/affairs/queryAllAffairs`, aData).then((res) => {
        if (res.data.message === '成功') {
            affairsList.value = res.data.data
            student_id.value = res.data.data[0].student_id
            studentName.value = res.data.data[0].studentName
            content.value = res.data.data[0].content
            sourceRoomOrMajor.value = res.data.data[0].sourceRoomOrMajor
            reason.value = res.data.data[0].reason
            oldClassName.value = res.data.data[0].className
            grade.value = res.data.data[0].grade
            audits.value = res.data.data[0].audits
            console.log(affairsList.value)
            axios.get(`http://127.0.0.1:8083/classes/getClassesByMajor?grade=${grade.value}&major_id=${res.data.data[0].majorOrRoomId}`).then((ress) => {
                majorList.value = ress.data.data
            })
        }
    })
}
getAffairsById()
const pass = async () => {
    if (class_id.value === '' || class_id.value === null) {
        ElMessage('班级不能为空')
        return
    }
    await axios.post(`http://localhost:8083/affairs/updateAffairs`, { audits: 2, id: props.id }).then((res) => {
        if (res.data.message === '成功') {
            axios.post(`http://localhost:8083/student/updateStudent`, { student_id: student_id.value, majorName: content.value.split('-')[1] }).then((rea) => {
                ElMessage('专业修改' + rea.data.message)
            })
            axios.post(`http://localhost:8083/studentDetail/updateStudentDetail`, { student_id: student_id.value, class_id: class_id.value }).then((ress) => {
                ElMessage('班级' + ress.data.message)
            })
        }
    })
}
const refuse = async () => {
    if (refuseReason.value === '' || refuseReason.value === null) {
        ElMessage('请填写驳回理由')
        return
    }
    const aData = {
        refuseReason: refuseReason.value,
        audits: 3,
        id: props.id
    }
    await axios.post(`http://localhost:8083/affairs/updateAffairs`, aData).then((res) => {
        ElMessage(res.data.message)
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
