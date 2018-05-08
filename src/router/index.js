import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home'
import register from '@/components/register'
import manage from '@/components/manage'
import MySpace from '@/components/MySpace/MySpace'
  import editUerInfo from '@/components/MySpace/edit_uerInfo'
import news from '@/components/news/news_main'
import allNews from '@/components/news/allNews'
import society from '@/components/society/society_main'
import societyDetails from '@/components/society/societyDetails'
import addSociety from '@/components/society/addSociety'
import member from '@/components/member/member_main'
import applicatMember from '@/components/member/applicatMember'
import materials from '@/components/materials/materials_main'
import discussion from '@/components/discussion/discussion_main'
import owerDis from '@/components/discussion/owerDis'
import activity from '@/components/activity/activity_main'


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
    },
  ]
})
