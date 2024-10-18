<template>
    <el-tabs tab-position="left" class="demo-tabs">
        <el-tab-pane label="专业更换">
            <majorChangeDeal v-if="typeof (majorChangeList) !== 'undefined'" :majorChangeList="majorChangeList" />
        </el-tab-pane>
        <el-tab-pane label="寝室更换">
            <roomChangeDeal v-if="typeof (roomChangeList) !== 'undefined'" :roomChangeList="roomChangeList" />
        </el-tab-pane>
    </el-tabs>

</template>
<script setup>
import roomChangeDeal from './roomChangeDeal.vue'
import majorChangeDeal from './majorChangeDeal.vue'
import { ref, onMounted } from 'vue';
import axios from 'axios';
const thingsList = ref([])
const majorChangeList = ref()
const roomChangeList = ref()
onMounted(async () => {
    await axios.get(`http://localhost:8083/affairs/selectAffairs?teacher_id=${localStorage.getItem('token')}`).then((res) => {
        thingsList.value = res.data.data
        if (res.data.message !== '暂无信息') {
            majorChangeList.value = res.data.data.filter(item => item.affairsType === 1)
            roomChangeList.value = res.data.data.filter(item => item.affairsType === 2)

        }
    })
})
</script>
<style>
.el-tabs {
    box-shadow: 2px 2px 2px 2px lightgray;
    padding: 2vh;
}
</style>