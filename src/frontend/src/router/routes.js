import Vue from "vue";
import Vuex from "vuex";
import store from "../store";

const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    meta: { titleLabel: "펜타폰" },
    children: [
      {
        path: "",
        component: () => import("pages/Index.vue")
      },
      {
        path: "main",
        component: () => import("pages/Main.vue"),
        meta: { titleLabel: "펜타폰" }
      },
      {
        path: "price",
        component: () => import("pages/Price.vue"),
        meta: { titleLabel: "최저가 조회" },
        children: [
          {
            path: "search/:carrier",
            component: () => import("pages/PriceSearch.vue"),
            props: true
          }
        ]
      },
      {
        path: "status",
        component: () => import("pages/Status.vue"),
        meta: {
          requiresAuth: true,
          titleLabel: "상담현황"
        }
      },
      {
        path: "ongoing/:dealno",
        component: () => import("pages/status/Ongoing.vue"),
        props: true,
        meta: {
          requiresAuth: true,
          titleLabel: "상담현황"
        }
      },
      {
        path: "waiting/:dealno",
        component: () => import("pages/status/Waiting.vue"),
        props: true,
        meta: {
          requiresAuth: true,
          titleLabel: "상담현황"
        }
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
        path: "layer/findpw",
        components: {
          default: () => import("pages/Main.vue"),
          dialog: () => import("pages/layers/FindPassword.vue")
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
        meta: {
          requiresAuth: true,
          titleLabel: "상담등록"
        },
        children: [
          {
            path: "form/:carrier",
            component: () => import("pages/RegisterForm.vue"),
            props: true
          }
        ]
      },
      {
        path: "customer",
        component: () => import("pages/Customer.vue"),
        meta: { titleLabel: "고객센터" },
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
        meta: {
          requiresAuth: true,
          titleLabel: "마이페이지"
        }
      },
      {
        path: "social/:mode/:service/:id/:email/:name",
        component: () => import("components/SocialLogin.vue"),
        props: true
      },
      {
        path: "verify",
        component: () => import("components/VerifyEmail.vue"),
        props: route => ({
          mbr: route.query.mbr,
          cue: route.query.cue
        })
      },
      {
        path: "layer/reset",
        components: {
          default: () => import("pages/Main.vue"),
          dialog: () => import("pages/layers/ResetPassword.vue")
        },
        props: {
          dialog: route => ({
            mbr: route.query.mbr,
            cue: route.query.cue
          })
        }
      }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "*",
    component: () => import("pages/Error404.vue"),
    alias: "404"
  }
];

export default routes;
