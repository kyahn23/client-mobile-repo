import Vue from "vue";
import Vuex from "vuex";
import store from "../store";

const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        component: () => import("pages/Index.vue")
      },
      {
        path: "main",
        component: () => import("pages/Main.vue")
      },
      {
        path: "price",
        component: () => import("pages/Price.vue"),
        children: [
          {
            path: "SKT",
            component: () => import("pages/price/SKT.vue")
          },
          {
            path: "KT",
            component: () => import("pages/price/KT.vue")
          },
          {
            path: "LGU",
            component: () => import("pages/price/LGU.vue")
          }
        ]
      },
      {
        path: "status",
        component: () => import("pages/Status.vue"),
        meta: { requiresAuth: true }
      },
      {
        path: "ongoing/:dealno",
        component: () => import("pages/status/Ongoing.vue"),
        props: true,
        meta: { requiresAuth: true }
      },
      {
        path: "waiting/:dealno",
        component: () => import("pages/status/Waiting.vue"),
        props: true,
        meta: { requiresAuth: true }
      },
      {
        path: "layer/login",
        components: {
          default: () => import("pages/Main.vue"),
          dialog: () => import("pages/layers/Login.vue")
        }
      },
      {
        path: "layer/signup",
        components: {
          default: () => import("pages/Main.vue"),
          dialog: () => import("pages/layers/Signup.vue")
        }
      },
      {
        path: "layer/cancel/:dealno/:callno",
        components: {
          default: () => import("pages/status/Ongoing.vue"),
          dialog: () => import("pages/layers/Cancel.vue")
        },
        props: {
          default: route => ({ dealno: route.params.dealno }),
          dialog: route => ({
            callno: route.params.callno,
            bnno: route.query.bn
          })
        },
        meta: { requiresAuth: true }
      },
      {
        path: "layer/rating/:dealno/:callno",
        components: {
          default: () => import("pages/status/Ongoing.vue"),
          dialog: () => import("pages/layers/Rating.vue")
        },
        props: {
          default: route => ({ dealno: route.params.dealno }),
          dialog: route => ({
            dealno: route.params.dealno,
            callno: route.params.callno,
            bnno: route.query.bn
          })
        },
        meta: { requiresAuth: true }
      },
      {
        path: "layer/request/:dealno",
        components: {
          default: () => import("pages/status/Waiting.vue"),
          dialog: () => import("pages/layers/Request.vue")
        },
        props: {
          default: route => ({ dealno: route.params.dealno }),
          dialog: route => ({
            dealno: route.params.dealno,
            regdis: route.query.dis,
            reqno: route.query.req
          })
        },
        meta: { requiresAuth: true }
      },
      {
        path: "register",
        component: () => import("pages/Register.vue"),
        children: [
          {
            path: "SKT",
            component: () => import("pages/register/SKT.vue")
          },
          {
            path: "KT",
            component: () => import("pages/register/KT.vue")
          },
          {
            path: "LGU",
            component: () => import("pages/price/LGU.vue")
          }
        ]
      },
      {
        path: "customer",
        component: () => import("pages/Customer.vue"),
        children: [
          {
            path: "notice",
            component: () => import("pages/customer/notice.vue")
          },
          {
            path: "notice/:ntcNo",
            component: () => import("pages/customer/notice-detail.vue"),
            props: true
          },
          {
            path: "faq",
            component: () => import("pages/customer/faq.vue")
          },
          {
            path: "manual",
            component: () => import("pages/customer/manual.vue")
          }
        ]
      },
      {
        path: "mypage",
        component: () => import("pages/MyPage.vue"),
        meta: { requiresAuth: true }
      },
      {
        path: "social/:mode/:service/:id/:email/:name",
        component: () => import("components/SocialLogin.vue"),
        props: true
      },
      {
        path: "verify",
        component: () => import("components/VerifyEmail.vue"),
        props: route => ({ mail: route.query.mail })
      }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "*",
    component: () => import("pages/Error404.vue"),
    alias: "/404"
  }
];

export default routes;
