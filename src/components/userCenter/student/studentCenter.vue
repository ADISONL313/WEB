<template>
    <el-row style="text-align: center;">
        <el-col :span="24">
            <personInfo />
            <changeMajor />
            <changeRoom />
            <el-button style="width: 20vh;height: 20vh;font-size: 2vh;margin-left: 1vh;" type="primary"
                @click="router.push('/home/affairsProgress')"><el-icon>
                    <Timer />
                </el-icon>申请进度查看</el-button>
        </el-col>
    </el-row>
    <div style="margin-top: 1vh;text-align: center;">
        <personRadar />
    </div>
</template>
<script setup>
import personInfo from '@/components/userCenter/universalModule/personInfo.vue'
import personRadar from '@/components/home/graph/personRadar.vue';
import changeMajor from './changeMajor.vue';
import changeRoom from './changeRoom.vue'
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter()
</script>