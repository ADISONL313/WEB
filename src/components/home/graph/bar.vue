<template>
  <el-card class="box-card" style="margin-top: 1vh;">
    <template #header>
      <div style="text-align: center;">
        <h2 style="line-height: 0px;">各学院男女人数</h2>
      </div>
    </template>
    <div ref="info" style="width: 100%; height: 70vh"></div>
  </el-card>
</template>
<script setup>

import axios from "axios";
import { onMounted, ref, inject } from "vue"
const echarts = inject("echarts")
const info = ref()
const barList = ref([])
var option
const girl = ref([])
const boy = ref([])
const college = ref([])
onMounted(async () => {
  await axios.get(`http://127.0.0.1:8083/student/getGenderBar`).then((res) => {
    barList.value = res.data.data
  })
  for (let i = 0; i < barList.value.length; i++) {
    boy.value[i] = barList.value[i].value
    girl.value[i] = barList.value[i].value2
    college.value[i] = barList.value[i].name
  }
  var infoEl = info.value;
  var userEc = echarts.init(infoEl);
  option = {
    title: {
      text: '男女人数',
      subtext: '人数'
    },
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      data: ['男', '女']
    },
    toolbox: {
      show: true,
      feature: {
        dataView: { show: true, readOnly: false },
        magicType: { show: true, type: ['line', 'bar'] },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    calculable: true,
    xAxis: [
      {
        type: 'category',
        // prettier-ignore
        data: college.value
      }
    ],
    yAxis: [
      {
        type: 'value'
      }
    ],
    series: [
      {
        name: '男',
        type: 'bar',
        data: boy.value,
        markPoint: {
          data: [
            { type: 'max', name: 'Max' },
            { type: 'min', name: 'Min' }
          ]
        },
        markLine: {
          data: [{ type: 'average', name: 'Avg' }]
        }
      },
      {
        name: '女',
        type: 'bar',
        data: girl.value,
        markPoint: {
          data: [
            { name: 'Max', value: 182.2, xAxis: 7, yAxis: 183 },
            { name: 'Min', value: 2.3, xAxis: 11, yAxis: 3 }
          ]
        },
        markLine: {
          data: [{ type: 'average', name: 'Avg' }]
        }
      }
    ]
  };



  option && userEc.setOption(option);
})


</script>