<template>
    <el-button style="width: 20vh;height: 20vh;font-size: 2vh;" type="primary" @click="userInfo = true"><el-icon>
            <UserFilled />
        </el-icon>个人信息</el-button>
    <el-drawer v-model="userInfo" size="45%" direction="ltr">
        <template #header>
            <h4 style="text-align: left;">个人信息</h4>
        </template>
        <template #default>
            <div>
                <infoForm v-if="typeof (baseData) !== 'undefined'" :baseData="baseData" />
            </div>
            <el-divider />
            <div v-if="identity === 'student'">
                <div v-if="pointData[0].a !== 1">
                    <h4>详细信息</h4>
                    <studentsDetailInfo v-if="typeof (pointData) !== 'undefined'" :pointData="pointData"
                        :student_id="student_id" :major="major" :studentName="studentName" :gender="gender" />
                </div>
            </div>
        </template>
        <template #footer>
            <div style="flex: auto">
                <el-button @click="cancelClick">cancel</el-button>
            </div>
        </template>
    </el-drawer>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios';
import infoForm from '@/components/userCenter/universalModule/inforForm.vue'
import studentsDetailInfo from '@/components/home/studentsAdmin/studentsDetailInfo.vue';
const userInfo = ref(false)
const pointData = ref()
const major = ref()
const baseData = ref()
const studentName = ref()
const gender = ref()
const student_id = ref(localStorage.getItem('token'))
const identity = ref(localStorage.getItem('identity'))
function cancelClick() {
    userInfo.value = false
}
onMounted(async () => {
    if (localStorage.getItem('identity') === 'student') {
        await axios.get(`http://127.0.0.1:8083/studentDetail/getStudentDetail?student_id=${localStorage.getItem('token')}`).then((res) => {
            if (res.data.message !== '无信息') {
                pointData.value = res.data.data
            } else {
                pointData.value = [{ a: 1 }]
            }
        })
        await axios.post(`http://127.0.0.1:8083/student/selectStudentByCounselor`, { student_id: localStorage.getItem('token') }).then((res) => {
            if (res.data.message === '查询成功') {
                baseData.value = res.data.data
                gender.value = res.data.data[0].gender
                major.value = res.data.data[0].major
                studentName.value = res.data.data[0].studentName
            }
        })
    } else {
        await axios.get(`http://127.0.0.1:8083/teacher/one?teacher_id=${localStorage.getItem('token')}`).then((res) => {
            baseData.value = res.data.data
        })
    }

})
</script>