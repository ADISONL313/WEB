<template>
    <el-card class="box-card">
        <div ref="info" style="width: 100%; height: 50vh"></div>
    </el-card>
</template>

<script setup>
import axios from "axios";
import { onMounted, ref, inject } from "vue"
const echarts = inject("echarts")
const info = ref()
var option

onMounted(async () => {
    var infoEl = info.value;
    var userEc = echarts.init(infoEl);
    let echartsData = []
    const res = await axios.get(`http://127.0.0.1:8083/studentDetail/getStudentDetail?student_id=${localStorage.getItem('token')}`)
    if (res.data.message !== '无信息') {
        echartsData.push(res.data.data[0].moral)
        echartsData.push(res.data.data[0].course)
        echartsData.push(res.data.data[0].practice)
        echartsData.push(res.data.data[0].ideology)
        echartsData.push(res.data.data[0].attendance)
    }
    option = {
        title: {
            text: '个人综合得分雷达图'
        },
        legend: {
            data: ['综合评定']
        },
        radar: {
            // shape: 'circle',
            indicator: [
                { name: '德育', max: 5 },
                { name: '成绩', max: 5 },
                { name: '实践', max: 5 },
                { name: '思政', max: 5 },
                { name: '出勤', max: 5 }
            ]
        },
        series: [
            {
                name: 'Budget vs spending',
                type: 'radar',
                data: [
                    {
                        value: echartsData.length === 0 ? [0, 0, 0, 0, 0] : echartsData,
                        name: '综合评定'
                    }
                ]
            }
        ]
    };
    option && userEc.setOption(option);
});
</script>