<template>
  <el-menu default-active="1" mode="horizontal">
    <router-link to="/home/userCenter">
      <el-menu-item index="0">{{ username }}!
      </el-menu-item></router-link>
    <router-link to="/home/stuBigData">
      <el-menu-item index="1">学生大数据
      </el-menu-item></router-link>
    <router-link v-if="studentAuthority" to="/home/studentsAdmin">
      <el-menu-item index="2">学生基本信息管理</el-menu-item></router-link>
    <router-link to="/home/addStudent" v-if="studentAuthority"><el-menu-item index="3">添加学生</el-menu-item></router-link>
    <router-link v-if="adminAuthority" to="/home/dealAffairs">
      <el-menu-item index="4">学生申请管理</el-menu-item>
    </router-link>
    <router-link v-if="adminAuthority" to="/home/addTeacher">
      <el-menu-item index="5">添加教师账号
      </el-menu-item></router-link>
    <router-link v-if="adminAuthority" to="/home/addMajor">
      <el-menu-item index="6">专业添加
      </el-menu-item></router-link>

    <router-link v-if="adminAuthority" to="/home/addClass">
      <el-menu-item index="7">班级添加
      </el-menu-item></router-link>

    <router-link v-if="adminAuthority" to="/home/addDormitory">
      <el-menu-item index="8">宿舍添加
      </el-menu-item></router-link>
    <el-menu-item index="9"><el-button type="danger" @click="logOut">退出登录</el-button></el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'
const router = useRouter()
const adminAuthority = ref(true)
const studentAuthority = ref(false)
if (localStorage.getItem('token') !== 'admin') {
  adminAuthority.value = false
}
if (localStorage.getItem('identity') !== 'student') {
  studentAuthority.value = true
}
import { ref } from 'vue'
const username = ref(localStorage.getItem('token'))
const logOut = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('identity')
  router.push('/login')
}
</script>
<style>
a {
  text-decoration: none;
}
</style>