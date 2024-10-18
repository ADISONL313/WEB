<template>

    <el-card style="margin-top: 1vh;">
        <template #header>
            <div style="text-align: center;">
                <h2 style="line-height: 0px;">学生地区分布</h2>
            </div>
        </template>
        <div ref="info" style="width: 100%; height: 80vh"></div>
    </el-card>
</template>
<script setup>
import chinaMap from '@/assets/china.json'
import { onMounted, ref, inject } from "vue"
import axios from 'axios';
const echarts = inject("echarts")
const info = ref()
const total = ref(0)
const mapData = ref([])
var option
onMounted(async () => {
    var infoEl = info.value;
    let myMap = echarts.init(infoEl);
    await axios.get(`http://127.0.0.1:8083/student/getChinaMap`).then((res) => {
        mapData.value = res.data.data
    })
    await axios.get(`http://127.0.0.1:8083/student/all`).then((res) => {
        total.value = res.data.data
    })
    var map = document.querySelector('#map');
    myMap.showLoading();
    myMap.hideLoading();
    echarts.registerMap('china', chinaMap);
    myMap.setOption(
        (option = {
            title: {
                text: '各省人数分布',
                subtext: total.value + '人',
                sublink: ''
            },
            tooltip: {
                trigger: 'item',
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: { readOnly: false },
                    restore: {},
                    saveAsImage: {}
                }
            },
            visualMap: {
                min: 0,
                max: 4000,
                text: ['High', 'Low'],
                realtime: false,
                calculable: true,
                inRange: {
                    color: ['lightskyblue', 'yellow', 'orangered']
                }
            },
            series: [
                {
                    name: '学生人数',
                    type: 'map',
                    map: 'china',
                    roam: true,
                    label: {
                        show: true,
                        formatter: function (params) {
                            // 正则表达式用于去掉名称中的“省”、“市”、“自治区”等后缀
                            return params.name.replace(/省|市|自治区|自治州|特别行政区/g, '');
                        }
                    },
                    data: mapData.value
                }
            ]
        })
    );
    option && myMap.setOption(option);
})
</script>
