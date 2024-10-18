<template>
    <studentCenter v-if="identity === 'student'" />
    <teacherCenter v-if="identity === 'teacher'" />
</template>
<script setup>
import studentCenter from '@/components/userCenter/student/studentCenter.vue';
import teacherCenter from '@/components/userCenter/teacher/teacherCenter.vue';
import { ref } from 'vue'
const identity = ref(localStorage.getItem('identity'))
</script>