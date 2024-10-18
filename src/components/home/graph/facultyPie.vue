<template>
    <el-card class="box-card">
        <template #header>
            <div style="text-align: center;">
                <h2 style="line-height: 0px;">各学院人数占比</h2>
            </div>
        </template>
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
    const res = await axios.get(`http://127.0.0.1:8083/student/collegeCount`)
    echartsData = res.data.data
    option = {
        backgroundColor: '#FFFFFF',
        tooltip: {
            trigger: 'item'
        },
        visualMap: {
            show: false,
            min: 0,
            max: 20,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series: [
            {
                name: '人数',
                type: 'pie',
                radius: '55%',
                center: ['50%', '50%'],
                data: echartsData.sort(function (a, b) {
                    return a.value - b.value;
                }),
                roseType: 'radius',
                label: {
                    color: '#303133'
                },
                labelLine: {
                    lineStyle: {
                        color: '#303133'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                },
                itemStyle: {
                    color: '#c23531',
                    shadowBlur: 20,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                },
                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
        ]
    };
    option && userEc.setOption(option);
});
</script>