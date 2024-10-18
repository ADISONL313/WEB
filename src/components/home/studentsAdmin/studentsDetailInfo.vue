<template>
    <el-descriptions class="margin-top" :column="2" size="large" border>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <user />
                    </el-icon>
                    学号
                </div>
            </template>
            {{ props.student_id }}
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
            {{ props.studentName }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <location />
                    </el-icon>
                    家庭地址
                </div>
            </template>
            <el-input style="width: 30vh;" :disabled="isEdit" v-model.trim="homeAddress" />
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <Star />
                    </el-icon>
                    生日
                </div>
            </template>
            <el-date-picker value-format="YYYY-MM-DD" format="YYYY-MM-DD" :disabled="isEdit" v-model.trim="birthday"
                type="date" placeholder="Pick a day" size="default" />
        </el-descriptions-item>
        <el-descriptions-item>
            <template #label>
                <div class="cell-item"><el-icon>
                        <Finished />
                    </el-icon>
                    年级
                </div>
            </template>
            <el-select v-if="identity === 'teacher'" @change="chooseClass" :disabled="isEdit" style="width: 30vh;"
                v-model.trim="level" placeholder="请选择年级">
                <el-option v-for="item in years" :label="item" :value="item" />
            </el-select>
            <div v-if="identity === 'student'">{{ level }}</div>
        </el-descriptions-item>
        <el-descriptions-item span="1">
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <Reading />
                    </el-icon>
                    班级
                </div>
            </template>
            <el-select v-if="identity === 'teacher'" :disabled="isEdit" v-model.trim="class_id" value-key="classSelect">
                <el-option v-for="item in classList" :label="item.className" :value="item.id" />
            </el-select>
            <div v-if="identity === 'student'">{{ class_id }}</div>
        </el-descriptions-item>
        <el-descriptions-item span="2">
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <House />
                    </el-icon>
                    寝室
                </div>
            </template>
            <el-select v-if="identity === 'teacher'" :disabled="isEdit" style="width: 30vh;" @change="chooseRoom"
                v-model.trim="buildingName">
                <el-option v-for="item in buildingNameList" :label="item" :value="item" />
            </el-select>
            <el-select v-if="identity === 'teacher'" :disabled="isEdit" style="width: 20vh;" v-model="room_id"
                value-key="roomSelect">
                <el-option v-for="item in roomList" :label="item.room_id" :value="item.id" />
            </el-select>
            <div v-if="identity === 'student'">{{ buildingName + room_id }}</div>
        </el-descriptions-item>
        <el-descriptions-item span="2">
            <template #label>
                <div class="cell-item">
                    <el-icon>
                        <ChatLineRound />
                    </el-icon>
                    评价
                </div>
            </template>
            <el-input v-if="identity === 'teacher'" :disabled="isEdit" v-model.trim="evaluate" autosize type="textarea"
                placeholder="Please input" />
            <div v-if="identity === 'student'">{{ evaluate }}</div>
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
            <el-button @click="isEdit = false">开始修改</el-button>
            <el-button v-if="isEdit === true ? false : true" @click="updateDetail" type="primary">修改</el-button>
        </el-descriptions-item>
    </el-descriptions>
</template>

<script setup>
import axios from 'axios'
import { ElMessage } from 'element-plus';
import { ref, onMounted } from 'vue'
const isEdit = ref(true)
const identity = localStorage.getItem('identity')
const year = new Date().getFullYear()
const years = Array.from({ length: (year + 1) - (year - 4) }, (_, i) => i + year - 3)
const level = ref('')
const birthday = ref('')
const class_id = ref()
const buildingName = ref('')
const homeAddress = ref('')
const room_id = ref('')
const roomList = ref([])
const evaluate = ref('')
const props = defineProps({
    studentName: String,
    student_id: Number,
    major: Number,
    gender: String,
    pointData: {
        type: Array
    }
})
onMounted(async () => {
    if (props.pointData[0].a === 1) {
        return
    }
    level.value = props.pointData[0].level
    birthday.value = props.pointData[0].birthday
    homeAddress.value = props.pointData[0].homeAddress
    buildingName.value = props.pointData[0].buildingName
    room_id.value = props.pointData[0].room_id
    class_id.value = props.pointData[0].className
    evaluate.value = props.pointData[0].evaluate

})
// const getStudentDetail = async () => {
//     await axios.get(`http://127.0.0.1:8083/studentDetail/getStudentDetail?student_id=${props.student_id}`).then((res) => {
//         if (res.data.message != '无信息') {
//             detailData.value = res.data.data
//             level.value = res.data.data[0].level
//             birthday.value = res.data.data[0].birthday
//             homeAddress.value = res.data.data[0].homeAddress
//             buildingName.value = res.data.data[0].buildingName
//             room_id.value = res.data.data[0].room_id
//             class_id.value = res.data.data[0].className
//             evaluate.value = res.data.data[0].evaluate
//         }
//     })
// }
const buildingNameList = props.gender == '男' ? ['男生公寓1栋', '男生公寓2栋', '男生公寓3栋', '男生公寓4栋'] : ['女生公寓1栋', '女生公寓2栋', '女生公寓3栋', '女生公寓4栋']
const chooseRoom = async () => {
    room_id.value = null
    await axios.post(`http://127.0.0.1:8083/dormitory/getDormitories`, { buildingName: buildingName.value }).then((res) => {
        roomList.value = res.data.data
    })
}

const classList = ref([])
const chooseClass = async () => {
    class_id.value = null
    await axios.get(`http://127.0.0.1:8083/classes/getClassesByMajor?grade=${level.value}&major_id=${props.major}`).then((res) => {
        classList.value = res.data.data
    })
}
const emit = defineEmits(['data-sent']);
const updateDetail = async () => {
    isEdit.value = false
    if (level.value === '' || level.value === null || class_id.value === null || class_id.value === '' || room_id.value === '' || room_id.value === null) {
        ElMessage('年级,班级,寝室为必填')
        return
    }
    const tdData = {
        level: level.value,
        evaluate: evaluate.value,
        class_id: typeof (class_id.value) === 'string' ? props.pointData[0].class_id : class_id.value,
        homeAddress: homeAddress.value,
        birthday: birthday.value,
        roomId: typeof (room_id.value) === 'string' ? props.pointData[0].roomId : room_id.value,
        student_id: props.student_id
    }
    const sdData = {
        homeAddress: homeAddress.value,
        birthday: birthday.value,
        student_id: props.student_id
    }
    console.log(sdData)
    await axios.post(`http://127.0.0.1:8083/studentDetail/updateStudentDetail`, localStorage.getItem('identity') === 'teacher' ? tdData : sdData).then((res) => {
        isEdit.value = true
        ElMessage(res.data.message)
        emit('data-sent', { message: '查询成功' })
    })
}
</script>