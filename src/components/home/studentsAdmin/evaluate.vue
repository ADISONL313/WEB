<template>
    <el-row>
        <el-col :span="12">
            <el-form label-width="120px">
                <el-form-item label="道德">
                    <el-rate size="large" v-model="moral" low-threshold="2" high-threshold="4" show-score allow-half
                        :colors="['#ff0000', '#3cb371', '#3cb371']" text-color="#ff9900" score-template="{value} 分" />
                </el-form-item>
                <el-form-item label="成绩">
                    <el-rate size="large" v-model="course" low-threshold="2" high-threshold="4" show-score allow-half
                        :colors="['#ff0000', '#3cb371', '#3cb371']" text-color="#ff9900" score-template="{value} 分" />
                </el-form-item>
                <el-form-item label="出勤">
                    <el-rate size="large" v-model="attendance" low-threshold="2" high-threshold="4" show-score
                        allow-half :colors="['#ff0000', '#3cb371', '#3cb371']" text-color="#ff9900"
                        score-template="{value} 分" />
                </el-form-item>
                <el-form-item label="实践">
                    <el-rate size="large" v-model="practice" low-threshold="2" high-threshold="4" show-score allow-half
                        :colors="['#ff0000', '#3cb371', '#3cb371']" text-color="#ff9900" score-template="{value} 分" />
                </el-form-item>
                <el-form-item label="思政">
                    <el-rate size="large" v-model="ideology" low-threshold="2" high-threshold="4" show-score allow-half
                        :colors="['#ff0000', '#3cb371', '#3cb371']" text-color="#ff9900" score-template="{value} 分" />
                </el-form-item>
            </el-form>
        </el-col>
        <el-col :span="12" style="line-height: 30vh;">
            <el-button type="primary" @click="submitEvaluate">提交评价</el-button>
        </el-col>
    </el-row>
</template>
<script setup>
import { ref, inject, onMounted } from 'vue'
import axios from 'axios';
import { ElMessage } from 'element-plus';
const moral = ref(0)
const course = ref(0)
const practice = ref(0)
const ideology = ref(0)
const attendance = ref(0)
const props = defineProps({
    student_id: Number,
    pointData: {
        type: Array
    }
})
onMounted(async () => {
    moral.value = props.pointData[0].moral
    course.value = props.pointData[0].course
    practice.value = props.pointData[0].practice
    ideology.value = props.pointData[0].ideology
    attendance.value = props.pointData[0].attendance
})
const submitEvaluate = async () => {
    const eData = {
        moral: moral.value,
        course: course.value,
        practice: practice.value,
        ideology: ideology.value,
        attendance: attendance.value,
        student_id: props.student_id
    }
    await axios.post(`http://127.0.0.1:8083/studentDetail/updateStudentDetail`, eData).then((res) => {
        ElMessage(res.data.message)
    })
}
</script>