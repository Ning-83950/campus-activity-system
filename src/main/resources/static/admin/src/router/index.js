import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
    // 主页
    {
        path: '/',
        name: 'index',
        component: index,
        meta: {
            index: 0,
            title: '首页'
        }
    },

    // 登录
    {
        path: '/login',
        name: 'login',
        component: login,
        meta: {
            index: 0,
            title: '登录'
        }
    },

    	
	
    // 忘记密码
    {
        path: '/forgot',
        name: "forgot",
        component: forgot,
        meta: {
            index: 0,
            title: '忘记密码'
        }
    },

    // 修改密码
    {
        path: '/user/password',
        name: "password",
        component: () => import("../views/user/password.vue"),
        meta: {
            index: 0,
            title: '修改密码'
        }
    },

    // 视频播放页
    {
        path: "/media/video",
        name: "video",
        component: () => import('../views/media/video.vue'),
        meta: {
            index: 0,
            title: "视频"
        }
    },

    // 音频播放页
    {
        path: "/media/audio",
        name: "audio",
        component: () => import('../views/media/audio.vue'),
        meta: {
            index: 0,
            title: "音频"
        }
    },

            // 权限路由
        {
            path: '/auth/table',
            name: 'auth_table',
            component: () => import('../views/auth/table.vue'),
            meta: {
                index: 0,
                title: '权限列表'
            }
        },
        {
            path: '/auth/view',
            name: 'auth_view',
            component: () => import('../views/auth/view.vue'),
            meta: {
                index: 0,
                title: '权限详情'
            }
        },
    
    
                // 轮播图路由
        {
            path: '/slides/table',
            name: 'slides_table',
            component: () => import('../views/slides/table.vue'),
            meta: {
                index: 0,
                title: '轮播图列表'
            }
        },
        {
            path: '/slides/view',
            name: 'slides_view',
            component: () => import('../views/slides/view.vue'),
            meta: {
                index: 0,
                title: '轮播图详情'
            }
        },
                    // 文章路由
            {
                path: '/article/table',
                name: 'article_table',
                component: () => import('../views/article/table.vue'),
                meta: {
                    index: 0,
                    title: '校园资讯列表'
                }
            },
            {
                path: '/article/view',
                name: 'article_view',
                component: () => import('../views/article/view.vue'),
                meta: {
                    index: 0,
                    title: '校园资讯详情'
                }
            },

            // 文章分类路由
            {
                path: '/article_type/table',
                name: 'article_type_table',
                component: () => import('../views/article_type/table.vue'),
                meta: {
                    index: 0,
                    title: '校园资讯分类列表'
                }
            },
            {
                path: '/article_type/view',
                name: 'article_type_view',
                component: () => import('../views/article_type/view.vue'),
                meta: {
                    index: 0,
                    title: '校园资讯分类详情'
                }
            },
                            
            // 留言板路由
        {
            path: '/message/table',
            name: 'message_table',
            component: () => import('../views/message/table.vue'),
            meta: {
                index: 0,
                title: '投诉反馈列表'
            }
        },
        {
            path: '/message/view',
            name: 'message_view',
            component: () => import('../views/message/view.vue'),
            meta: {
                index: 0,
                title: '投诉反馈详情'
            }
        },
    
    
            // 公告路由
        {
            path: '/notice/table',
            name: 'notice_table',
            component: () => import('../views/notice/table.vue'),
            meta: {
                index: 0,
                title: '公告信息列表'
            }
        },
        {
            path: '/notice/view',
            name: 'notice_view',
            component: () => import('../views/notice/view.vue'),
            meta: {
                index: 0,
                title: '公告信息详情'
            }
        },
            	    
            // 评论路由
        {
            path: '/comment/table',
            name: 'comment_table',
            component: () => import('../views/comment/table.vue'),
            meta: {
                index: 0,
                title: '评论列表'
            }
        },
        {
            path: '/comment/view',
            name: 'comment_view',
            component: () => import('../views/comment/view.vue'),
            meta: {
                index: 0,
                title: '评论详情'
            }
        },
        	            // 学生用户路由
        {
            path: '/student_users/table',
            name: 'student_users_table',
            component: () => import('../views/student_users/table.vue'),
            meta: {
                index: 0,
                title: '学生用户列表'
            }
        },
        {
            path: '/student_users/view',
            name: 'student_users_view',
            component: () => import('../views/student_users/view.vue'),
            meta: {
                index: 0,
                title: '学生用户详情'
            }
        },
						            // 教师用户路由
        {
            path: '/teacher_user/table',
            name: 'teacher_user_table',
            component: () => import('../views/teacher_user/table.vue'),
            meta: {
                index: 0,
                title: '教师用户列表'
            }
        },
        {
            path: '/teacher_user/view',
            name: 'teacher_user_view',
            component: () => import('../views/teacher_user/view.vue'),
            meta: {
                index: 0,
                title: '教师用户详情'
            }
        },
						            // 活动分类路由
        {
            path: '/activity_classification/table',
            name: 'activity_classification_table',
            component: () => import('../views/activity_classification/table.vue'),
            meta: {
                index: 0,
                title: '活动分类列表'
            }
        },
        {
            path: '/activity_classification/view',
            name: 'activity_classification_view',
            component: () => import('../views/activity_classification/view.vue'),
            meta: {
                index: 0,
                title: '活动分类详情'
            }
        },
						            // 活动信息路由
        {
            path: '/activity_information/table',
            name: 'activity_information_table',
            component: () => import('../views/activity_information/table.vue'),
            meta: {
                index: 0,
                title: '活动信息列表'
            }
        },
        {
            path: '/activity_information/view',
            name: 'activity_information_view',
            component: () => import('../views/activity_information/view.vue'),
            meta: {
                index: 0,
                title: '活动信息详情'
            }
        },
						            // 报名信息路由
        {
            path: '/enrollment_information/table',
            name: 'enrollment_information_table',
            component: () => import('../views/enrollment_information/table.vue'),
            meta: {
                index: 0,
                title: '报名信息列表'
            }
        },
        {
            path: '/enrollment_information/view',
            name: 'enrollment_information_view',
            component: () => import('../views/enrollment_information/view.vue'),
            meta: {
                index: 0,
                title: '报名信息详情'
            }
        },
						            // 活动签到路由
        {
            path: '/activity_checkin/table',
            name: 'activity_checkin_table',
            component: () => import('../views/activity_checkin/table.vue'),
            meta: {
                index: 0,
                title: '签到记录'
            }
        },
						            // 活动通知路由
        {
            path: '/activity_notification/table',
            name: 'activity_notification_table',
            component: () => import('../views/activity_notification/table.vue'),
            meta: {
                index: 0,
                title: '活动通知列表'
            }
        },
        {
            path: '/activity_notification/view',
            name: 'activity_notification_view',
            component: () => import('../views/activity_notification/view.vue'),
            meta: {
                index: 0,
                title: '活动通知详情'
            }
        },
						            // 活动费用路由
        {
            path: '/activity_costs/table',
            name: 'activity_costs_table',
            component: () => import('../views/activity_costs/table.vue'),
            meta: {
                index: 0,
                title: '活动费用列表'
            }
        },
        {
            path: '/activity_costs/view',
            name: 'activity_costs_view',
            component: () => import('../views/activity_costs/view.vue'),
            meta: {
                index: 0,
                title: '活动费用详情'
            }
        },
						            // 反馈评价路由
        {
            path: '/feedback_evaluation/table',
            name: 'feedback_evaluation_table',
            component: () => import('../views/feedback_evaluation/table.vue'),
            meta: {
                index: 0,
                title: '反馈评价列表'
            }
        },
        {
            path: '/feedback_evaluation/view',
            name: 'feedback_evaluation_view',
            component: () => import('../views/feedback_evaluation/view.vue'),
            meta: {
                index: 0,
                title: '反馈评价详情'
            }
        },
						    	    // 用户路由
    {
        path: '/user/table',
        name: 'user_table',
        component: () => import('../views/user/table.vue'),
        meta: {
            index: 0,
            title: '用户列表'
        }
    },
    {
        path: '/user/view',
        name: 'user_view',
        component: () => import('../views/user/view.vue'),
        meta: {
            index: 0,
            title: '用户详情'
        }
    },
    {
        path: '/user/info',
        name: 'user_info',
        component: () => import('../views/user/info.vue'),
        meta: {
            index: 0,
            title: '个人信息'
        }
    },
    // 用户组路由
    {
        path: '/user_group/table',
        name: 'user_group_table',
        component: () => import('../views/user_group/table.vue'),
        meta: {
            index: 0,
            title: '用户组列表'
        }
    },
    {
        path: '/user_group/view',
        name: 'user_group_view',
        component: () => import('../views/user_group/view.vue'),
        meta: {
            index: 0,
            title: '用户组详情'
        }
    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    let token = to.query.token;
    if (token) {
        $.db.set("token", token, 120);
    }
    next();
})

router.afterEach((to, from, next) => {
    let title = "校园活动报名系统-admin";
    document.title = title;
})

export default router
