import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import Ebook from '../views/admin/Ebook.vue'
import Category from '../views/admin/Category.vue'
import Doc from '../views/admin/Doc.vue'
import Documentation from '../views/Documentation.vue'
import User from '../views/admin/User.vue'
import store from "@/store";
import {Tool} from "@/util/tool";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      title: "Mind Palace"
    }
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/admin/ebook',
    name: 'Ebook',
    component: Ebook,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/admin/category',
    name: 'Category',
    component: Category,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/admin/doc',
    name: 'Doc',
    component: Doc,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/admin/user',
    name: 'User',
    component: User,
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/documentation',
    name: 'Documentation',
    component: Documentation
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    console.log(item, "是否需要登录校验：", item.meta.loginRequire);
    return item.meta.loginRequire
  })) {
    const loginUser = store.state.user;
    if (Tool.isEmpty(loginUser)) {
      console.log("用户未登录！");
      next('/');
    } else {
      next();
    }
  } else {
    next();
  }
});


export default router
