export default [
    {
        path: '/',// 路由地址
        name: 'login',
        component: () => import('../views/LoginPage.vue'),// 文件地址
        children: []
    },
]