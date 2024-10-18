<template>
    <affairsDetailByChangeMajor :id="props.id"/>
</template>
<script setup>
import affairsDetailByChangeMajor from './affairsDetailByChangeMajor.vue'
const props=defineProps({
    id:Number
})
</script>