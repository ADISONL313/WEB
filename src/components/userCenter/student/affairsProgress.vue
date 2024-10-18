<template>
    <div class="divPage">
        <majorProgress />
        <el-divider />
        <roomProgress />
    </div>
</template>
<script setup>
import majorProgress from './majorProgress.vue'
import roomProgress from './roomProgress.vue';
</script>

<style>
.divPage {
    box-shadow: 2px 2px 2px 2px lightgray;
    padding: 2vh;
}
</style>