<template>
  <el-affix class="topbar" :offset="0">
    <topBar />
  </el-affix>
  <div style="margin-top: 1vh;">
    <RouterView />
  </div>
</template>
<script setup>
import topBar from '@/components/home/menu/topBar.vue';
</script>
<style>
.topbar {
  box-shadow: 2px 2px 2px lightgray;
}
</style>