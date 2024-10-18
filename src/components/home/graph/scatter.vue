<template>
    <el-card class="box-card" style="margin-top: 1vh;">
        <template #header>
            <div style="text-align: center;">
                <h2 style="line-height: 0px;">各学院人数占比</h2>
            </div>
        </template>
        <div ref="info" style="width: 100%; height: 80vh"></div>
    </el-card>
</template>

<script setup>
import { aa } from '@/assets/life-expectancy.js'
import axios from "axios";
import { onMounted, ref, inject } from "vue"
const echarts = inject("echarts")
const info = ref()
var option

onMounted(async () => {
    var infoEl = info.value;
    var userEc = echarts.init(infoEl);
    userEc.hideLoading();
    let echartsData = []
    // const res = await axios.get(`https://echarts.apache.org/examples/data/asset/data/life-expectancy.json`)
    // echartsData = res.data.data
    var schema = [
        { name: 'Income', index: 0, text: '人均收入', unit: '美元' },
        { name: 'LifeExpectancy', index: 1, text: '人均寿命', unit: '岁' },
        { name: 'Population', index: 2, text: '总人口', unit: '' },
        { name: 'Country', index: 3, text: '国家', unit: '' }
    ];
    var itemStyle = {
        opacity: 0.8
    };
    var sizeFunction = function (x) {
        var y = Math.sqrt(x / 5e8) + 0.1;
        return y * 80;
    };
    option = {
        baseOption: {
            timeline: {
                axisType: 'category',
                orient: 'vertical',
                autoPlay: true,
                inverse: true,
                playInterval: 1000,
                left: null,
                right: 0,
                top: 20,
                bottom: 20,
                width: 55,
                height: null,
                symbol: 'none',
                checkpointStyle: {
                    borderWidth: 2
                },
                controlStyle: {
                    showNextBtn: false,
                    showPrevBtn: false
                },
                data: []
            },
            title: [
                {
                    text: aa.timeline[0],
                    textAlign: 'center',
                    left: '63%',
                    top: '55%',
                    textStyle: {
                        fontSize: 100
                    }
                },
                {
                    text: '各国人均寿命与GDP关系演变',
                    left: 'center',
                    top: 10,
                    textStyle: {
                        fontWeight: 'normal',
                        fontSize: 20
                    }
                }
            ],
            tooltip: {
                padding: 5,
                borderWidth: 1,
                formatter: function (obj) {
                    var value = obj.value;
                    // prettier-ignore
                    return schema[3].text + '：' + value[3] + '<br>'
                        + schema[1].text + '：' + value[1] + schema[1].unit + '<br>'
                        + schema[0].text + '：' + value[0] + schema[0].unit + '<br>'
                        + schema[2].text + '：' + value[2] + '<br>';
                }
            },
            grid: {
                top: 100,
                containLabel: true,
                left: 30,
                right: '110'
            },
            xAxis: {
                type: 'log',
                name: '人均收入',
                max: 100000,
                min: 300,
                nameGap: 25,
                nameLocation: 'middle',
                nameTextStyle: {
                    fontSize: 18
                },
                splitLine: {
                    show: false
                },
                axisLabel: {
                    formatter: '{value} $'
                }
            },
            yAxis: {
                type: 'value',
                name: '平均寿命',
                max: 100,
                nameTextStyle: {
                    fontSize: 18
                },
                splitLine: {
                    show: false
                },
                axisLabel: {
                    formatter: '{value} 岁'
                }
            },
            visualMap: [
                {
                    show: false,
                    dimension: 3,
                    categories: aa.counties,
                    inRange: {
                        color: (function () {
                            // prettier-ignore
                            var colors = ['#51689b', '#ce5c5c', '#fbc357', '#8fbf8f', '#659d84', '#fb8e6a', '#c77288', '#786090', '#91c4c5', '#6890ba'];
                            return colors.concat(colors);
                        })()
                    }
                }
            ],
            series: [
                {
                    type: 'scatter',
                    itemStyle: itemStyle,
                    data: aa.series[0],
                    symbolSize: function (val) {
                        return sizeFunction(val[2]);
                    }
                }
            ],
            animationDurationUpdate: 1000,
            animationEasingUpdate: 'quinticInOut'
        },
        options: []
    };
    for (var n = 0; n < aa.timeline.length; n++) {
        option.baseOption.timeline.data.push(aa.timeline[n]);
        option.options.push({
            title: {
                show: true,
                text: aa.timeline[n] + ''
            },
            series: {
                name: aa.timeline[n],
                type: 'scatter',
                itemStyle: itemStyle,
                data: aa.series[n],
                symbolSize: function (val) {
                    return sizeFunction(val[2]);
                }
            }
        });
    }
    userEc.setOption(option);
    option && userEc.setOption(option);
});
</script>