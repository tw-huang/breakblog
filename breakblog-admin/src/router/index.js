import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Categories from '../components/category/categories.vue'
import Posts from '../components/post/posts.vue'
import Post from '../components/post/post.vue'
import Links from '../components/link/links.vue'
import Comments from '../components/comment/comments.vue'
import Reports from '../components/report/reports.vue'
import Admin from '../components/admin/admin.vue'
import About from '../components/admin/about.vue'

Vue.use(VueRouter)

//解决vue-router 在3.0版本以上重复点菜单报错的问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err)
}

const router = new VueRouter({
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
      path: '/home',
      component: Home,
      redirect: '/welcome',
      children: [
        {
          path: '/welcome',
          component: Welcome
        },
        {
          path: '/categories',
          component: Categories
        },
        {
          path: '/posts',
          component: Posts
        },
        {
          path: '/post',
          component: Post
        },
        {
          path: '/links',
          component: Links
        },
        {
          path: '/comments',
          component: Comments
        },
        {
          path: '/reports',
          component: Reports
        },
        {
          path: '/admin',
          component: Admin
        },
        {
          path: '/about',
          component: About
        }
      ]
    }
  ]
})

//挂载路由导航守卫
router.beforeEach((to, from, next) => {
  //to 将要访问的路径
  //from 代表从哪个路径跳转而来
  //next 函数，表示放行 eg： next() -> 放行  next('/login') -> 强制跳转
  if (to.path === '/login') return next()
  //获取token
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})

export default router
