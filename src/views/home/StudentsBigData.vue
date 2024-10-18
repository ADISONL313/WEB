<template>
  <el-row>
    <el-col :span="12" style="padding-right: 0.5vh;">
      <facultyPie />
    </el-col>
    <el-col :span="12" style="padding-left: 0.5vh;">
      <crousel />
    </el-col>
  </el-row>
  <bar />
  <ChinaMap />
</template>
<script setup>
import facultyPie from '@/components/home/graph/facultyPie.vue';
import bar from '@/components/home/graph/bar.vue';
import ChinaMap from '@/components/home/graph/ChinaMap.vue'
import crousel from '@/components/home/graph/crousel.vue';
</script>