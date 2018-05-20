import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home'
import register from '@/components/register'
import manage from '@/components/manage'
import MySpace from '@/components/MySpace/MySpace'
import editUerInfo from '@/components/MySpace/edit_uerInfo'
import editPass from '@/components/MySpace/edit_pass'
import news from '@/components/news/news_main'
import inNew from '@/components/news/inNew'
import addNews from '@/components/news/addNews'
import allNews from '@/components/news/allNews'
import detailNews from '@/components/news/detailNews'
import society from '@/components/society/society_main'
import societyDetails from '@/components/society/societyDetails'
import addSociety from '@/components/society/addSociety'
import edtiSociety from '@/components/society/edtiSociety'
import changeMain from '@/components/society/changeMain'
import member from '@/components/member/member_main'
import applicatMember from '@/components/member/applicatMember'
import addMember from '@/components/member/addMember'
import inSociety from '@/components/member/inSociety'
import detailMember from '@/components/member/detailMember'
import student from '@/components/member/student'
import addStudent from '@/components/member/addStudent'
import teacher from '@/components/member/teacher'
import addTeacher from '@/components/member/addTeacher'
import materials from '@/components/materials/materials_main'
import mSociety from '@/components/materials/mSociety'
import addMaterials from '@/components/materials/addMaterials'
import detailMaterials from '@/components/materials/detailMaterials'
import appliMaterials from '@/components/materials/appliMaterials'
import discussion from '@/components/discussion/discussion_main'
import owerDis from '@/components/discussion/owerDis'
import activity from '@/components/activity/activity_main'
import inActivity from '@/components/activity/inActivity'
import addActivity from '@/components/activity/addActivity'
import appliActivity from '@/components/activity/appliActivity'
import detaileActivity from '@/components/activity/detaileActivity'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: home
    },{
      path: '/home',
      name: 'home',
      component: home
    },{
      path: '/MySpace',
      name: 'MySpace',
      component: MySpace
    },{
      path: '/register',
      name: 'register',
      component: register
    },{
      path: '/manage',
      name: 'manage',
      component: manage
    },{
      path: '/news',
      name: 'news',
      component: news
    },{
      path: '/society',
      name: 'society',
      component: society
    },{
      path: '/member',
      name: 'member',
      component: member
    },{
      path: '/materials',
      name: 'materials',
      component: materials
    },{
      path: '/discussion',
      name: 'discussion',
      component: discussion
    },{
      path: '/activity',
      name: 'activity',
      component: activity
    },{
      path: '/societyDetails',
      name: 'societyDetails',
      component: societyDetails
    },{
      path: '/addSociety',
      name: 'addSociety',
      component: addSociety
    },{
      path: '/applicatMember',
      name: 'applicatMember',
      component: applicatMember
    },{
      path: '/allNews',
      name: 'allNews',
      component: allNews
    },{
      path: '/owerDis',
      name: 'owerDis',
      component: owerDis
    },{
      path: '/editUerInfo',
      name: 'editUerInfo',
      component: editUerInfo
    },{
      path: '/editPass',
      name: 'editPass',
      component: editPass
    },{
      path: '/addMember',
      name: 'addMember',
      component: addMember
    },{
      path: '/addMaterials',
      name: 'addMaterials',
      component: addMaterials
    },{
      path: '/addActivity',
      name: 'addActivity',
      component: addActivity
    },{
      path: '/addNews',
      name: 'addNews',
      component: addNews
    },{
      path: '/edtiSociety',
      name: 'edtiSociety',
      component: edtiSociety
    },{
      path: '/changeMain',
      name: 'changeMain',
      component: changeMain
    },{
      path: '/detailMember',
      name: 'detailMember',
      component: detailMember
    },{
      path: '/detailMaterials',
      name: 'detailMaterials',
      component: detailMaterials
    },{
      path: '/detaileActivity',
      name: 'detaileActivity',
      component: detaileActivity
    },{
      path: '/detailNews',
      name: 'detailNews',
      component: detailNews
    },{
      path: '/appliMaterials',
      name: 'appliMaterials',
      component: appliMaterials
    },{
      path: '/appliActivity',
      name: 'appliActivity',
      component: appliActivity
    },{
      path: '/student',
      name: 'student',
      component: student
    },{
      path: '/teacher',
      name: 'teacher',
      component: teacher
    },{
      path: '/addStudent',
      name: 'addStudent',
      component: addStudent
    },{
      path: '/inSociety',
      name: 'inSociety',
      component: inSociety
    },{
      path: '/mSociety',
      name: 'mSociety',
      component: mSociety
    },{
      path: '/addTeacher',
      name: 'addTeacher',
      component: addTeacher
    },{
      path: '/inActivity',
      name: 'inActivity',
      component: inActivity
    },{
      path: '/inNew',
      name: 'inNew',
      component: inNew
    },
  ]
})
