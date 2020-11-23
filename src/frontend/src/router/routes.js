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
        component: () => import("pages/Main.vue"),
        /** for research */
        children: [
          {
            path: "1",
            component: () => import("pages/main/Page1.vue")
          },
          {
            path: "2",
            component: () => import("pages/main/Page2.vue")
          },
          {
            path: "3",
            component: () => import("pages/main/Page3.vue")
          },
          {
            path: "4",
            component: () => import("pages/main/Page4.vue")
          },
          {
            path: "5",
            component: () => import("pages/main/Page5.vue")
          }
        ]
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
        component: () => import("pages/Status.vue")
      },
      {
        path: "ongoing/:dealno",
        component: () => import("pages/status/Ongoing.vue"),
        props: true
      },
      {
        path: "waiting/:dealno",
        component: () => import("pages/status/Waiting.vue"),
        props: true
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
        }
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
        }
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
        }
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
        path: "after",
        component: () => import("pages/After.vue"),
        meta: {
          requiresAuth: true
        }
      },
      {
        path: "social/:mode/:service/:id/:email/:name",
        component: () => import("components/SocialLogin.vue"),
        props: true
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
