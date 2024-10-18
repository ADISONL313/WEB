<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header" style="line-height: 0;">
                <el-row>
                    <el-col :span="12">
                        <h3>{{ props.student_id + $route.query.studentName + ":的详细信息" }}</h3>
                    </el-col>
                    <el-col style="text-align: right;" :span="12">
                        <el-button type="primary" @click="router.back()">返回</el-button>
                    </el-col>
                </el-row>
            </div>
        </template>
        <studentsDetailInfo @data-sent="handleMessageFromChild" :studentName="$route.query.studentName"
            :student_id="props.student_id" :major="$route.query.major" :gender="$route.query.gender"
            v-if="typeof (pointData) !== 'undefined'" :pointData="pointData" />
        <el-divider />
        <div v-show="detailMessage === '无信息' ? false : true">
            <evaluate v-if="typeof (pointData) !== 'undefined'" :pointData="pointData" :student_id="props.student_id" />
        </div>
    </el-card>
</template>
<script setup>
import evaluate from './evaluate.vue';
import studentsDetailInfo from './studentsDetailInfo.vue'
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from 'axios';
const router = useRouter()
const pointData = ref()
const detailMessage = ref('')
const props = defineProps({
    student_id: Number
})
onMounted(async () => {
    await axios.get(`http://127.0.0.1:8083/studentDetail/getStudentDetail?student_id=${props.student_id}`).then((res) => {
        detailMessage.value = res.data.message
        if (res.data.message !== '无信息') {
            pointData.value = res.data.data
        } else {
            pointData.value = [{ a: 1 }]
        }
    })
})
const handleMessageFromChild = (data) => {
    detailMessage.value = data.message
}
</script>