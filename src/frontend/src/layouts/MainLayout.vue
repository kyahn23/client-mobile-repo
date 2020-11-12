<template>
  <q-layout view="hHh lpr fFf">
    <q-header class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title class="text-center">펜타폰</q-toolbar-title>
        <q-btn
          icon="power_settings_new"
          :label="loginLabel"
          stack
          unelevated
          color="standard"
          size="sm"
          @click="loginToggle"
        />
      </q-toolbar>

      <q-tabs indicator-color="transparent" dense align="left">
        <q-route-tab to="/main" label="홈" />
        <q-route-tab to="/price" label="최저가 조회" />
        <q-route-tab to="/after" label="상담등록" />
        <q-route-tab to="/after" label="고객센터" />
        <q-route-tab to="/after" label="마이페이지" />
      </q-tabs>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer class="bg-grey-8 text-white">
      <div class="q-gutter-y-md">
        <q-tabs
          indicator-color="transparent"
          class="bg-grey-1 text-black"
          dense
          align="justify"
          active-color="primary"
        >
          <q-route-tab to="/main" icon="home" label="홈" />
          <q-route-tab to="/price" icon="monetization_on" label="최저가 조회" />
          <q-route-tab to="/after" icon="post_add" label="상담등록" />
          <q-route-tab to="/after" icon="person" label="마이페이지" />
        </q-tabs>
      </div>
    </q-footer>

    <q-dialog
      v-model="dialogLogin"
      persistent
      maximized
      transition-show="slide-up"
      transition-hide="slide-down"
    >
      <q-card class="bg-white text-black">
        <q-bar class="bg-white">
          <q-space />

          <q-btn dense flat icon="close" v-close-popup>
            <q-tooltip content-class="bg-white text-black">Close</q-tooltip>
          </q-btn>
        </q-bar>

        <q-card-section class="q-pt-none">
          <q-page-container class="no-padding">
            <router-view name="dialog" />
          </q-page-container>
        </q-card-section>
      </q-card>
    </q-dialog>

    <q-inner-loading :showing="isLoading" class="z-max">
      <q-spinner color="primary" size="3em" />
    </q-inner-loading>
  </q-layout>
</template>

<script>
export default {
  name: "MainLayout",
  data() {
    return {
      loginLabel: "",
      dialogLogin: false
    };
  },
  mounted() {
    this.setLoginLabel();
  },
  watch: {
    $route(to, from) {
      if (to.path.includes("/login") || to.path.includes("/signup")) {
        this.dialogLogin = true;
      } else {
        this.dialogLogin = false;
      }
    }
  },
  methods: {
    /** 로그인 라벨 셋팅 */
    setLoginLabel() {
      this.loginLabel = "로그아웃";
      if (!this.isLogin) {
        this.loginLabel = "로그인";
      }
    },
    /** 로그인 테스트 용 토글 */
    loginToggle() {
      this.$router.push({ path: "/login" });
      // this.$store.commit("setAuth");

      // let msg = "";
      // let clr = "";
      // if (this.isLogin) {
      //   msg = "로그인 되었습니다.";
      //   clr = "positive";
      // } else {
      //   msg = "로그아웃 되었습니다.";
      //   clr = "negative";
      //   this.$router.push({ path: "main" });
      // }

      // this.$q.notify({
      //   group: false,
      //   color: clr,
      //   message: msg,
      // });
      // this.setLoginLabel();
    }
  },
  computed: {
    isLogin: {
      get() {
        return this.$store.getters.isAuth;
      }
    },
    isLoading: {
      get() {
        return this.$store.getters.isLoading;
      }
    }
  }
};
</script>
