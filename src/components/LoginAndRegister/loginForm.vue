<template>
    <el-card style="width: 30%;margin-left: 35%;margin-top: 14%;">
        <template #header>
            <div style="text-align: center;">
                <h2 style="line-height: 0px;">可视化学生管理系统</h2>
            </div>
        </template>
        <el-form label-width="60px" style="padding: 10px;">
            <el-form-item label="账号" prop="pass">
                <el-input v-model.trim="account" autocomplete="off" />
            </el-form-item>
            <el-form-item label="密码" prop="checkPass">
                <el-input type="password" v-model.trim="password" autocomplete="off" />
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
            <el-button style="width: 100%;" @click="login" type="primary">登录</el-button>
        </el-form-item>
        <template #footer>
            <div style="text-align: center;">
                <el-button @click="forgetPwd" type="primary" link>忘记密码</el-button>
            </div>
        </template>
    </el-card>
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { md5 } from 'js-md5'
const router = useRouter()
//登录逻辑判断，获取后端数据axios接口调用。
const identity = ref('1')
const account = ref('')
const password = ref('')
const formVerify = ref(false)
const tip = ref('')
const login = async () => {
    if (account.value === '' || password.value === '') {
        tip.value = '表单不能为空'
        formVerify.value = true;
    } else {
        if (identity.value === '1') {

            await axios.get(`http://127.0.0.1:8083/teacher/one?teacher_id=${account.value}`).then((res) => {
                const sdata = res.data.data
                if (sdata === null) {
                    alert("用户不存在")
                } else if (sdata[0].password === md5(password.value)) {
                    localStorage.setItem('identity', 'teacher')
                    localStorage.setItem('name', res.data.data[0].teacherName)
                    localStorage.setItem('token', account.value)
                    router.push('/home')
                } else {
                    tip.value = '密码错误'
                    formVerify.value = true
                }
            })
        } else {

            const regex = /^\d+$/
            if (regex.test(account.value) === false) {
                tip.value = '学生账号只能是数字'
                formVerify.value = true
                return
            }
            await axios.get(`http://127.0.0.1:8083/student/selectForLogin?student_id=${parseInt(account.value, 10)}`).then((res) => {
                const sdata = res.data.data
                console.log(sdata)
                if (sdata === null) {
                    alert("用户不存在")
                } else if (sdata[0].password === md5(password.value)) {
                    localStorage.setItem('identity', 'student')
                    localStorage.setItem('name', res.data.data[0].studentName)
                    localStorage.setItem('token', account.value)
                    router.push('/home')
                } else {
                    tip.value = '密码错误'
                    formVerify.value = true
                }
            })
        }
    }
}
//关闭提示按钮方法
const down = () => {
    formVerify.value = false
}
const forgetPwd = () => {
    router.push('/forget')
}
</script>