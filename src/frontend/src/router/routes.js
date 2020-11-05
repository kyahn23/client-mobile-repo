import Vue from 'vue'
import Vuex from 'vuex'
import store from '../store'

const routes = [{
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{
        path: '',
        component: () => import('pages/Index.vue')
      },
      {
        path: 'login',
        component: () => import('pages/Login.vue')
      },
      {
        path: 'main',
        component: () => import('pages/Main.vue'),
        /** for research */
        children: [{
            path: '1',
            component: () => import('pages/main/Page1.vue')
          },
          {
            path: '2',
            component: () => import('pages/main/Page2.vue')
          },
          {
            path: '3',
            component: () => import('pages/main/Page3.vue')
          },
          {
            path: '4',
            component: () => import('pages/main/Page4.vue')
          },
          {
            path: '5',
            component: () => import('pages/main/Page5.vue')
          }
        ]
      },
      {
        path: 'after',
        component: () => import('pages/After.vue'),
        meta: {
          requiresAuth: true
        }
      },
      {
        path: 'signup',
        component: () => import('pages/Signup.vue')
      },
      {
        path: 'social/:mode/:service/:id/:email/:name',
        component: () => import('components/SocialLogin.vue'),
        props: true
      }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue'),
    alias: '/404'
  }
]

export default routes
