<template>
    <div style="text-align: center;margin-top: 2vh;">
        <personInfo />
        <el-badge v-if="teacher_id !== 'admin'" :value="badge" class="item">
            <el-button style="width: 20vh;height: 20vh;font-size: 2vh;margin-left: 1vh;" type="primary"
                @click="showThings"><el-icon>
                    <Notebook />
                </el-icon>待办事项</el-button></el-badge>
    </div>
</template>
<script setup>
import personInfo from '@/components/userCenter/universalModule/personInfo.vue'
import axios from 'axios';
import { ref, onMounted } from 'vue';
const openDraw = ref(false)
import { useRouter } from 'vue-router'
const router = useRouter()
const badge = ref(0)
const thingsList = ref([])
const teacher_id = ref(localStorage.getItem('token'))
onMounted(async () => {
    await axios.get(`http://localhost:8083/affairs/selectAffairs?teacher_id=${localStorage.getItem('token')}`).then((res) => {
        badge.value = res.data.data.length
        thingsList.value = res.data.data
    })
})
const showThings = () => {
    router.push('/home/things')
}
</script>