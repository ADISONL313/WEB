<template>
    <el-button style="width: 20vh;height: 20vh;font-size: 2vh;margin-left: 1vh;" type="primary"
        @click="dialogFormVisible = true"><el-icon>
            <Document />
        </el-icon>专业更换申请</el-button>

    <el-dialog v-model="dialogFormVisible" title="更换专业">
        <el-form label-width="120px">
            <el-form-item style="line-height: 0;">
                <h3><b>当前专业：</b>{{ college }}-{{ majorName }}</h3>
            </el-form-item>
            <el-divider />
            <el-form-item style="line-height: 0;">
                <h3>更换到：</h3>
            </el-form-item>
            <el-form-item label="学院">
                <el-select @change="chooseMajor" v-model.trim="toCollege" placeholder="请选择学院">
                    <el-option label="人工智能与大数据学院" value="人工智能与大数据学院" />
                    <el-option label="电气工程与智能制造学院" value="电气工程与智能制造学院" />
                    <el-option label="马克思主义学院" value="马克思主义学院" />
                    <el-option label="艺术与传媒学院" value="艺术与传媒学院" />
                    <el-option label="经济管理学院" value="经济管理学院" />
                    <el-option label="建筑与土木工程学院" value="建筑与土木工程学院" />
                </el-select>
            </el-form-item>
            <el-form-item label="专业">
                <el-select v-model.trim="toMajorName" placeholder="">
                    <el-option v-for="item in options" :label="item.majorName" :value="item.majorName + item.id" />
                </el-select>
            </el-form-item>
            <el-form-item label="申请理由">
                <el-input v-model.trim="reason" type="textarea" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="add">提交</el-button>
            </span>
        </template>
    </el-dialog>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios';
import { ElMessage } from 'element-plus';
const dialogFormVisible = ref(false)
const toMajorName = ref('');
const toCollege = ref('')
const college = ref('')
const majorName = ref('')
const options = ref([])
const teacher_id = ref('')
const reason = ref('')
const getCollegeAndMajor = async () => {
    await axios.post(`http://127.0.0.1:8083/student/selectStudentByCounselor`, { student_id: localStorage.getItem('token') }).then((res) => {
        college.value = res.data.data[0].college
        majorName.value = res.data.data[0].majorName
        teacher_id.value = res.data.data[0].teacherId
    })
}
onMounted(async () => {
    getCollegeAndMajor()
})
const chooseMajor = async () => {
    await axios.get(`http://127.0.0.1:8083/major/selectMajor?college=${toCollege.value}`).then((res) => {
        options.value = res.data.data
        toMajorName.value = ''
    })
}
const add = async () => {
    if (toCollege.value === '' || toCollege.value === null || toMajorName.value === '' || toMajorName.value === null || reason.value === '' || reason === null) {
        ElMessage('请填写全部表单内容')
        return
    } else if (toMajorName.value.match(/\D+/)[0] === majorName.value) {
        ElMessage('更换专业不能和原专业一样')
        return
    } else {
        const mData = {
            student_id: localStorage.getItem('token'),
            teacher_id: teacher_id.value,
            content: toCollege.value + '-' + toMajorName.value.match(/\D+/)[0],
            affairsType: 1,
            audits: 0,
            reason: reason.value,
            sourceRoomOrMajor: college.value + '-' + majorName.value,
            majorOrRoomId: parseInt(toMajorName.value.match(/\d+/g)[0])
        }
        console.log(mData)
        await axios.post(`http://localhost:8083/affairs/addAffairs`, mData).then((res) => {
            ElMessage(res.data.message)
            if (res.data.message === '添加成功') {
                dialogFormVisible.value = false
            }
        })
    }
}
</script>