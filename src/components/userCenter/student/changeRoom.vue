<template>
    <el-button style="width: 20vh;height: 20vh;font-size: 2vh;margin-left: 1vh;" type="primary"
        @click="isChangeRoom"><el-icon>
            <House />
        </el-icon>寝室更换申请</el-button>

    <el-dialog v-model="dialogFormVisible" style="text-align: left;" title="更换寝室">
        <el-form label-width="120px">
            <el-form-item style="line-height: 0;">
                <h3><b>当前寝室：</b>{{ buildingName }}-{{ room_id }}</h3>
            </el-form-item>
            <el-divider />
            <el-form-item style="line-height: 0;">
                <h3>更换到：</h3>
            </el-form-item>
            <el-form-item label="楼栋">
                <el-select style="width: 20vh;" v-model.trim="gender">
                    <el-option value="男生公寓" lable="0" />
                    <el-option value="女生公寓" lable="0" />
                </el-select>-
                <el-select style="width: 20vh;" @change="chooseRoom" v-model.trim="toBuildingName">
                    <el-option v-for="item in 4" :value="item" />
                </el-select>
            </el-form-item>
            <el-form-item label="房间号">
                <el-select v-model.trim="toRoom" placeholder="">
                    <el-option v-for="item in options" :label="item.room_id" :value="item.id + '-' + item.room_id" />
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
const toRoom = ref('');
const toBuildingName = ref('')
const buildingName = ref('')
const room_id = ref('')
const options = ref([])
const teacher_id = ref('')
const reason = ref('')
const gender = ref('')
const getRoom = async () => {
    await axios.post(`http://127.0.0.1:8083/studentDetail/getRoomAndTeacher?student_id=${localStorage.getItem('token')}`).then((res) => {
        if (res.data.message !== '失败') {
            buildingName.value = res.data.data[0].buildingName
            room_id.value = res.data.data[0].room_id
            teacher_id.value = res.data.data[0].teacher_id
        }
    })
}
onMounted(async () => {
    getRoom()
})
const chooseRoom = async () => {
    await axios.post(`http://127.0.0.1:8083/dormitory/getDormitories`, { buildingName: gender.value + toBuildingName.value + "栋" }).then((res) => {
        options.value = res.data.data
    })
}
const add = async () => {
    if (toBuildingName.value === '' || toBuildingName.value === null || toRoom.value === '' || toRoom.value === null || reason.value === '' || reason === null) {
        ElMessage('请填写全部表单内容')
        return
    } else if (gender.value + toBuildingName.value + '栋' + toRoom.value.split('-')[1] === buildingName.value + room_id.value) {
        ElMessage('更换寝室不能和原寝室一样')
        return
    } else {
        const mData = {
            student_id: localStorage.getItem('token'),
            teacher_id: teacher_id.value,
            content: gender.value + toBuildingName.value + '栋' + '-' + toRoom.value.split('-')[1],
            affairsType: 2,
            audits: 0,
            reason: reason.value,
            sourceRoomOrMajor: buildingName.value + '-' + room_id.value,
            majorOrRoomId: parseInt(toRoom.value.split('-')[0])
        }
        await axios.post(`http://localhost:8083/affairs/addAffairs`, mData).then((res) => {
            ElMessage(res.data.message)
            if (res.data.message === '添加成功') {
                dialogFormVisible.value = false
            }
        })
    }
}
const isChangeRoom = () => {
    if (teacher_id.value !== '') {
        dialogFormVisible.value = true
    }
    else {
        ElMessage('你还没有被分配寝室，请联系辅导员')
    }
}
</script>