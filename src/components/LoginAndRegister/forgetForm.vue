<template>
    <el-card style="width: 30%;margin-left: 35%;margin-top: 10%;">
        <template #header>
            <div style="text-align: center;">
                <h2 style="line-height: 0px;">可视化学生管理系统</h2>
            </div>
        </template>
        <el-form label-width="8vh" style="padding: 10px;">
            <el-form-item label="账号" prop="pass">
                <el-input v-model.trim="account" autocomplete="off" />
            </el-form-item>
            <el-form-item label="身份证" prop="checkPass">
                <el-input v-model.trim="idCard" maxlength="18" autocomplete="off" />
            </el-form-item>
            <el-form-item label="密码" prop="checkPass">
                <el-input type="password" v-model.trim="password" autocomplete="off" />
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model.trim="congfirm" autocomplete="off" />
            </el-form-item>
            <el-alert style="margin-bottom: 10px;" v-show="formVerify" :title="tip" @close="down" type="warning" />
            <el-form-item>
                <el-radio-group v-model="identity" class="ml-4">
                    <el-radio value="1" size="large">我是老师</el-radio>
                    <el-radio value="2" size="large">我是学生</el-radio>
                </el-radio-group>
            </el-form-item>
        </el-form>
        <el-form-item>
            <el-button style="width: 100%;" @click="login" type="primary">修改</el-button>
        </el-form-item>
        <template #footer>
            <div style="text-align: center;">
                <el-button @click="router.back()" type="primary" link>返回</el-button>
            </div>
        </template>
    </el-card>
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { md5 } from 'js-md5'
const router = useRouter()
//登录逻辑判断，获取后端数据axios接口调用。
const identity = ref('1')
const account = ref('')
const password = ref('')
const congfirm = ref('')
const formVerify = ref(false)
const tip = ref('')
const idCard = ref('')
const login = async () => {
    if (account.value === '' || password.value === '' || congfirm.value === '' || idCard.value === '') {
        tip.value = '表单不能为空'
        formVerify.value = true;
    } else if (password.value !== congfirm.value) {
        tip.value = '密码不一致'
        formVerify.value = true;
        return
    } else if (identity.value === '1') {
        console.log({ teacher_id: account.value, idCard: idCard.value })
        await axios.post(`http://127.0.0.1:8083/teacher/all`, { teacher_id: account.value, idCard: idCard.value }).then(async (res) => {
            ElMessage(res.data.message)
            if (res.data.message !== '无用户信息') {

                await axios.post(`http://127.0.0.1:8083/teacher/updateTeacher`, { password: md5(password.value), teacher_id: account.value }).then((res) => {
                    ElMessage(res.data.message)
                    router.back()
                    return
                })
            }
        })
    } else {
        const regex = /^\d+$/
        if (regex.test(account.value) === false) {
            tip.value = '学生账号只能是数字'
            formVerify.value = true
            return
        }
        await axios.get(`http://127.0.0.1:8083/student/selectForLogin?student_id=${parseInt(account.value, 10)}`).then(async (res) => {
            const sdata = res.data.data
            if (sdata === null) {
                ElMessage("用户不存在")
            } else if (sdata[0].id_card !== idCard.value) {
                ElMessage("用户不存在")
            } else {
                await axios.post(`http://127.0.0.1:8083/student/updateStudent`, { student_id: account.value, password: md5(congfirm.value) }).then((res) => {
                    ElMessage(res.data.message)
                    router.back()
                    return
                })
            }
        })
    }
}
//关闭提示按钮方法
const down = () => {
    formVerify.value = false
}
</script>