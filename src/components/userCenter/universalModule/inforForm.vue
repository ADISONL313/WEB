<template>
    <el-descriptions class="margin-top" :column="2" size="large" border>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <user />
                    </el-icon>
                    {{ identity === "student" ? "学号" : "工号" }}
                </div>
            </template>
            {{ user_id }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <user />
                    </el-icon>
                    姓名
                </div>
            </template>
            <el-input v-model.trim="userName" :disabled="isEdit" />
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item"><el-icon>
                        <Male />
                    </el-icon>
                    <el-icon>
                        <Female />
                    </el-icon>
                    性别
                </div>
            </template>
            <el-select v-model.trim="gender" :disabled="isEdit">
                <el-option label="男" value="男" />
                <el-option label="女" value="女" />
            </el-select>
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <Postcard />
                    </el-icon>
                    身份证
                </div>
            </template>
            <el-input v-model.trim="idCard" maxlength="18" :disabled="isEdit" />
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <iphone />
                    </el-icon>
                    电话
                </div>
            </template>
            <el-input v-model.trim="mobile" maxlength="11" :disabled="isEdit" />
        </el-descriptions-item>
        <el-descriptions-item v-if="identity === 'student'">
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <User />
                    </el-icon>
                    辅导员
                </div>
            </template>
            {{ counselor }}
        </el-descriptions-item>
        <el-descriptions-item v-if="identity === 'student'">
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <location />
                    </el-icon>
                    省份
                </div>
            </template>
            <el-select v-model.trim="province" :disabled="isEdit">
                <el-option v-for="item in ChinaProvince" :label="item.lable" :value="item.value" />
            </el-select>
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <OfficeBuilding />
                    </el-icon>
                    学院
                </div>
            </template>
            {{ college }}
        </el-descriptions-item>
        <el-descriptions-item v-if="identity === 'student'">
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <Notebook />
                    </el-icon>
                    专业
                </div>
            </template>
            {{ majorName }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <EditPen />
                    </el-icon>
                    操作
                </div>
            </template>
            <el-button @click="isEdit = false, addBtnIsShow = true">开始修改</el-button>
            <el-button v-if="addBtnIsShow" type="primary" @click="updateInfo">提交</el-button>
        </el-descriptions-item>
    </el-descriptions>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ChinaProvince } from '@/assets/util.js'
import { ElMessage } from 'element-plus';
import axios from 'axios';
const addBtnIsShow = ref(false)
const province = ref('')
const userName = ref('')
const gender = ref('')
const idCard = ref('')
const mobile = ref('')
const counselor = ref('')
const college = ref('')
const majorName = ref('')
const isEdit = ref(true)
const identity = ref(localStorage.getItem('identity'))
const user_id = ref(localStorage.getItem('token'))
const props = defineProps({
    baseData: Array
})
onMounted(async () => {
    console.log(props.baseData)
    if (localStorage.getItem('identity') === 'student') {
        province.value = props.baseData[0].province
        userName.value = props.baseData[0].studentName
        idCard.value = props.baseData[0].id_card
        counselor.value = props.baseData[0].counselor
        majorName.value = props.baseData[0].majorName
    } else {
        userName.value = props.baseData[0].teacherName
        idCard.value = props.baseData[0].idCard
    }
    college.value = props.baseData[0].college
    gender.value = props.baseData[0].gender
    mobile.value = props.baseData[0].mobile
})
// const getStudentInfo = async () => {
//     await axios.post(`http://127.0.0.1:8083/student/selectStudentByCounselor`, { student_id: localStorage.getItem('token') }).then((res) => {
//         if (res.data.message === '查询成功') {
//             uerInfoList.value = res.data.data
//             gender.value = uerInfoList.value != null ? uerInfoList.value[0].gender : '数据错误'
//             province.value = uerInfoList.value != null ? uerInfoList.value[0].province : '数据错误'
//             mobile.value = uerInfoList.value != null ? uerInfoList.value[0].mobile : '数据错误'
//             userName.value = uerInfoList.value != null ? uerInfoList.value[0].studentName : '数据错误'
//         }
//     })
// }
const updateInfo = async () => {
    if (identity.value === 'student') {
        const stData = {
            studentName: userName.value,
            gender: gender.value,
            mobile: mobile.value,
            id_card: idCard.value,
            province: province.value,
            student_id: localStorage.getItem('token')
        }
        console.log(stData)
        await axios.post(`http://127.0.0.1:8083/student/updateStudent`, stData).then((res) => {
            ElMessage(res.data.message)
            isEdit.value = true

        })
    } else {
        const tData = {
            teacher_id: localStorage.getItem('token'),
            gender: gender.value,
            mobile: mobile.value,
            idCard: idCard.value,
            teacherName: userName.value
        }
        await axios.post(`http://127.0.0.1:8083/teacher/updateTeacher`, tData).then((res) => {
            ElMessage(res.data.message)
            isEdit.value = true
            addBtnIsShow.value = false
        })
    }
}
</script>