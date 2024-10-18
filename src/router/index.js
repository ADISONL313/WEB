import { createRouter, createWebHistory,RouterView } from 'vue-router'
import login from '@/views/Login_Register/login.vue'
import forgetPassword from '@/views/Login_Register/forgetPassword.vue'
import StudentsBigData from '@/views/home/StudentsBigData.vue'
import home from '@/views/home/home.vue'
import userCenter from '@/views/userCenter/userCenter.vue'
import addTeacher from '@/views/superAdmin/addTeacher.vue'
import addStudent from '@/views/home/addStudents.vue'
import studentsAdmin from '@/views/home/studentsAdmin.vue'
import addMajor from '@/views/superAdmin/addMajor.vue'
import studentDetail from '@/components/home/studentsAdmin/studentDetail.vue'
import addClass from '@/views/superAdmin/addClass.vue'
import addDormitory from '@/views/superAdmin/addDormitory.vue'
import things from '@/components/userCenter/teacher/things.vue'
import dealAffairs from '@/views/superAdmin/dealAffairs.vue'
import affairsDetail from '@/components/admin/affairsDetail.vue'
import affairsProgress from '@/components/userCenter/student/affairsProgress.vue'
import { h } from 'vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      redirect:'/login'
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path:'/forget',
      name:'forgetPassword',
      component:forgetPassword
    },
    {
      path:'/home',
      name:'home',
      component:home,
      redirect:'/home/stuBigData',
      children:[
        {
          path:'stuBigData',
          component:StudentsBigData
        },
        {
          path:'addTeacher',
          component:addTeacher
        },
        {
          path:'userCenter',
          component:userCenter,
        },{
          path:'addMajor',
          component:addMajor
        },
        {
          path:'addStudent',
          component:addStudent
        },
        {
          path:'studentsAdmin',
          component:{ render: () => h(RouterView) },
          children:[
            {
              path:'',
              component:studentsAdmin
            },
            {
              path:':student_id',
              name:'studentDetail',
              component:studentDetail,
              props:true
            }
          ]
        },
        {
          path:'addClass',
          component:addClass
        },
        {
          path:'addDormitory',
          component:addDormitory
        },
        {
          path:'things',
          component:things
        },
        {
          path:'dealAffairs',
          component:{render:()=>h(RouterView)},
          children:[
            {
              path:'',
              component:dealAffairs
            },
            {
              path:':id',
              name:'affairsDetail',
              component:affairsDetail,
              props:true
            }
          ]
        },
        {
          path:'affairsProgress',
          component:affairsProgress
        }
      ]
    }
  ]
})
router.beforeEach((to, from, next) => {
  if (to.path === '/login'||to.path==='/forget') {
    return next();
  }
  const token = localStorage.getItem('token');

  if (!token) {
    return next('/login');
  }
  next();
});
export default router
