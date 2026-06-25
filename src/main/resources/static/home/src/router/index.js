import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
		// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 媒体视频
	{
		path: '/user_center/index',
		name: 'user_center_index',
		component: () => import('../views/user_center/index.vue')
	},
	// 文章路由
	{
		path: '/article/list',
		name: 'article_list',
		component: () => import('../views/article/list.vue')
	},
	{
		path: '/article/details',
		name: 'article_details',
		component: () => import('../views/article/details.vue')
	},
	// 浏览网站
	// 收藏路由
	{
		path: '/user/collect',
		name: 'collect_list',
		component: () => import('../views/user/collect.vue')
	},


	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue')
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue')
	},

	


	 // 留言路由
	 {
	 	path: '/message/list',
	 	name: 'message_list',
	 	component: () => import('../views/message/list.vue')
	 },
	 {
		path: '/message/edit',
		name: 'message_edit',
		component: () => import('../views/message/edit.vue')
	 },
	// 留言板路由
	{
		path: '/message/table',
		name: 'message_table',
		component: () => import('../views/message/table.vue')
	},
	{
		path: '/message/view',
		name: 'message_view',
		component: () => import('../views/message/view.vue')
	},

	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	// 学生用户表格路由
	{
		path: '/student_users/table',
		name: '/student_users_table',
		component: () => import('../views/student_users/table.vue')
	},
	// 学生用户详情路由
	{
		path: '/student_users/view',
		name: '/student_users_view',
		component: () => import('../views/student_users/view.vue')
	},
	
	
		
		
		
	// 教师用户表格路由
	{
		path: '/teacher_user/table',
		name: '/teacher_user_table',
		component: () => import('../views/teacher_user/table.vue')
	},
	// 教师用户详情路由
	{
		path: '/teacher_user/view',
		name: '/teacher_user_view',
		component: () => import('../views/teacher_user/view.vue')
	},
	
	
		
		
		
	// 活动分类表格路由
	{
		path: '/activity_classification/table',
		name: '/activity_classification_table',
		component: () => import('../views/activity_classification/table.vue')
	},
	// 活动分类详情路由
	{
		path: '/activity_classification/view',
		name: '/activity_classification_view',
		component: () => import('../views/activity_classification/view.vue')
	},
	
	
		
		
		
	// 活动信息表格路由
	{
		path: '/activity_information/table',
		name: '/activity_information_table',
		component: () => import('../views/activity_information/table.vue')
	},
	// 活动信息详情路由
	{
		path: '/activity_information/view',
		name: '/activity_information_view',
		component: () => import('../views/activity_information/view.vue')
	},
	
		// 活动信息列表路由
	{
		path: '/activity_information/list',
		name: '/activity_information_list',
		component: () => import('../views/activity_information/list.vue')
	},
	
		// 活动信息详情路由
	{
		path: '/activity_information/details',
		name: '/activity_information_details',
		component: () => import('../views/activity_information/details.vue')
	},
		
		
		
	// 报名信息表格路由
	{
		path: '/enrollment_information/table',
		name: '/enrollment_information_table',
		component: () => import('../views/enrollment_information/table.vue')
	},
	// 报名信息详情路由
	{
		path: '/enrollment_information/view',
		name: '/enrollment_information_view',
		component: () => import('../views/enrollment_information/view.vue')
	},
		// 报名信息添加路由
	{
		path: '/enrollment_information/edit',
		name: '/enrollment_information_edit',
		component: () => import('../views/enrollment_information/edit.vue')
	},
	// 活动扫码签到路由
	{
		path: '/activity_checkin/scan',
		name: '/activity_checkin_scan',
		component: () => import('../views/activity_checkin/scan.vue')
	},
	
	
		
		
		
	// 活动通知表格路由
	{
		path: '/activity_notification/table',
		name: '/activity_notification_table',
		component: () => import('../views/activity_notification/table.vue')
	},
	// 活动通知详情路由
	{
		path: '/activity_notification/view',
		name: '/activity_notification_view',
		component: () => import('../views/activity_notification/view.vue')
	},
	
	
		
		
		
	// 活动费用表格路由
	{
		path: '/activity_costs/table',
		name: '/activity_costs_table',
		component: () => import('../views/activity_costs/table.vue')
	},
	// 活动费用详情路由
	{
		path: '/activity_costs/view',
		name: '/activity_costs_view',
		component: () => import('../views/activity_costs/view.vue')
	},
	
	
		
		
		
	// 反馈评价表格路由
	{
		path: '/feedback_evaluation/table',
		name: '/feedback_evaluation_table',
		component: () => import('../views/feedback_evaluation/table.vue')
	},
	// 反馈评价详情路由
	{
		path: '/feedback_evaluation/view',
		name: '/feedback_evaluation_view',
		component: () => import('../views/feedback_evaluation/view.vue')
	},
	
	
		
		
		

	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "校园活动报名系统-home";
	document.title = title;
	document.logo = "校园活动报名系统"
})

export default router
