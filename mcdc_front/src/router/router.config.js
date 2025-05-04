export default [
    {
        path: '/',// 路由地址
        name: 'index',
        component: () => import('../views/indexPage.vue'),// 文件地址
        children: []
    },
]