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
        path: "login",
        components: {
          default: () => import("pages/Main.vue"),
          dialog: () => import("pages/Login.vue")
        }
      },
      {
        path: "signup",
        components: {
          default: () => import("pages/Main.vue"),
          dialog: () => import("pages/Signup.vue")
        }
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
