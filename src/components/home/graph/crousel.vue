<template>
    <el-card class="box-card">
        <template #header>
            <div style="text-align: center;">
                <h2 style="line-height: 0px;">校园动态</h2>
            </div>
        </template>
        <el-carousel :interval="4000" type="card" style="height: 50vh">
            <el-carousel-item v-for="item in imageList" :key="item">
                <el-image justify="center" :src="item.url" fit="cover" />
                <p>{{ item.text }}</p>
            </el-carousel-item>
        </el-carousel>
    </el-card>
</template>
<script setup>
import { ref } from 'vue'
const imageList = ref()
imageList.value = [
    {
        url: 'https://www.cqcst.edu.cn/wp-content/uploads/sites/16/2024/10/2.jpg', text: '9月29日上午，学校在第一行政楼101会议室召开2024年学位评定委员会全体会议，' +
            '校学位评定委员会主任、校长黄宗明出席并主持会议，校学位评定委员会委员参加会议。'
    },
    { url: 'https://www.cqcst.edu.cn/wp-content/uploads/sites/16/2024/10/11-1.jpg', text: '9月27日上午，2024年高等教育事业统计调查表填报工作部署和培训会议在第二行政楼101会议室召开。教学质量监控与评估中心、党政办、人事处、教务处、学生处、招生处、财务处、后勤保卫处、图书馆、继续教育学院、现代教育技术中心、基建工程指挥部、体育与大健康学院共计13个相关单位的部门负责人、统计人员参加会议，会议由教学质量监控与评估中心主任姚韵主持。' },
    { url: 'https://www.cqcst.edu.cn/wp-content/uploads/sites/16/2024/09/%E5%A4%A7%E5%90%88%E7%85%A7-1.jpg', text: '  为全面了解新生开学首日教学运行情况，保障教学秩序平稳运行，9月23日，重庆城市科技学院校领导刘学民、黄宗明、王银峰、童精明、贾瑞、田妍妮、崔继昌分别带队深入教学一线' },
    { url: 'https://www.cqcst.edu.cn/wp-content/uploads/sites/16/2024/09/1-3.png', text: '为确保新学期教学工作有序进行，切实提升教育教学管理水平，重庆城市科技学院校领导刘学民、黄宗明、王银峰、余林、贾瑞、田妍妮、崔继昌分别带队深入教学一线在永川校区和巴南校区开展期初教学检查工作，重点对教师到岗、学生到课、教师备课、教材到位、教学环境、课堂秩序、教学设备运行等情况进行了全面检查。' }
]
</script>
<style scoped>
.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: white;
    border: 1px;
    border: gray;
    box-shadow: 3px 3px 5px lightslategray;
    height: 49vh;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: white;
    border: 1px;
    border: gray;
    box-shadow: 3px 3px 5px lightslategray;
    height: 49vh;
}
</style>