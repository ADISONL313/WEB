<template>
  <div class="boxClass">
    <addStudentForm />
    <el-divider />
    <el-row>
      <el-col :span="12" style="line-height: 0;">
        <h3>使用excel批量导入</h3>
      </el-col>
      <el-col style="line-height: 0;text-align: right;" :span="12"><el-button type="success "
          @click="download">下载模板</el-button></el-col>
    </el-row>
    <fileImportStudent />
  </div>
</template>
<script setup>
import { exportToExcel } from '@/assets/util.js'
import addStudentForm from '@/components/home/addStudent/addStudentForm.vue';
import fileImportStudent from '@/components/home/addStudent/fileImportStudent.vue';
const download = () => {
  exportToExcel()
}
</script>
<style>
.boxClass {
  box-shadow: 2px 2px 2px 2px lightgrey;
  padding: 3vh;

}
</style>