<template>
  <q-layout view="hHh lpr fFf">
    <q-header class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-btn
          :icon="leftBtnIcon"
          unelevated
          color="standard"
          :text-color="leftBtnColor"
          size="md"
          @click="goBackBtn"
        />
        <q-toolbar-title
          class="text-center text-weight-bold"
          style="font-size: 1.2em;"
        >
          {{ titleLabel }}
        </q-toolbar-title>
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
        <q-route-tab to="/register" label="상담등록" />
        <q-route-tab to="/status" label="상담현황" />
        <q-route-tab to="/customer" label="고객센터" />
        <q-route-tab to="/mypage" label="마이페이지" />
      </q-tabs>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer id="mainFooter" class="bg-grey-8 text-white">
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
          <q-route-tab to="/register" icon="post_add" label="상담등록" />
          <q-route-tab to="/mypage" icon="person" label="마이페이지" />
        </q-tabs>
      </div>
    </q-footer>

    <q-dialog
      v-model="isLayer"
      persistent
      maximized
      transition-show="slide-up"
      transition-hide="slide-down"
    >
      <q-card class="bg-white text-black">
        <q-bar class="bg-white">
          <q-space />

          <q-btn
            id="layerCloseBtn"
            dense
            flat
            icon="close"
            @click="closeLayer"
            style="font-size: 0.8em; top: 10px; z-index: 6000;"
          />
        </q-bar>

        <q-card-section class="q-pt-none q-px-none">
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
      isMain: true,
      isBackBtn: false,
      titleLabel: "펜타폰",
      loginLabel: "로그인",
      dialogFrom: "/main",
      pageFrom: []
    };
  },
  mounted() {
    if (this.$route.path.includes("/layer")) {
      this.$store.commit("setLayer", { isLayer: true });
    }
  },
  watch: {
    $route(to, from) {
      if (to.path.includes("/layer")) {
        this.$store.commit("setLayer", { isLayer: true });
        if (!from.path.includes("/layer")) {
          this.dialogFrom = from.path;
        }
      } else {
        this.$store.commit("setLayer", { isLayer: false });
        const matchedRoutes = this.$router.currentRoute.matched;
        let routeIndex = matchedRoutes.length;
        let labelExists = false;
        while (!labelExists) {
          routeIndex -= 1;
          if (matchedRoutes[routeIndex].meta.titleLabel !== undefined) {
            this.titleLabel = matchedRoutes[routeIndex].meta.titleLabel;
            labelExists = true;
          }
        }
        if (!from.path.includes("/layer") && !this.isBackBtn) {
          this.pageFrom.push(from.path);
        }
      }
      if (to.path.includes("/main")) {
        this.isMain = true;
      } else {
        this.isMain = false;
      }
      this.isBackBtn = false;
    },
    notification(newNotification) {
      this.$q.notify(newNotification);
    },
    isLogin(newLogin) {
      if (newLogin) {
        this.loginLabel = "로그아웃";
      } else {
        this.loginLabel = "로그인";
      }
    }
  },
  methods: {
    /** 백 버튼 클릭 이벤트 */
    goBackBtn() {
      console.log(this.pageFrom);
      if (!this.isMain) {
        this.isBackBtn = true;
        this.$router.push({ path: this.pageFrom.pop() });
      }
    },
    /** 로그인 토글 */
    loginToggle() {
      if (!this.isLogin) {
        this.$router.push({ path: "/layer/login" });
      } else {
        this.$store.commit("setAuth", { isAuth: false });
        this.$store.commit("setCurrentUser", { currentUser: "" });
        this.$store.commit("setNotification", {
          color: "primary",
          textColor: "white",
          message: "로그아웃 되었습니다.",
          caption: ""
        });
        this.$router.push({ path: "/" });
      }
    },
    /** 레이어 팝업 닫기 함수 */
    closeLayer() {
      this.$router.push({ path: this.dialogFrom });
      this.$store.commit("setLayer", { isLayer: false });
      this.dialogFrom = "";
    }
  },
  computed: {
    isLoading: {
      get() {
        return this.$store.getters.isLoading;
      }
    },
    isLayer: {
      get() {
        return this.$store.getters.isLayer;
      }
    },
    notification: {
      get() {
        return this.$store.getters.notification;
      }
    },
    isLogin: {
      get() {
        return this.$store.getters.isAuth;
      }
    },
    leftBtnIcon: {
      get() {
        if (
          this.isMain ||
          (this.isLayer && this.dialogFrom.includes("/main"))
        ) {
          return "check_box_outline_blank";
        }
        return "arrow_back_ios";
      }
    },
    leftBtnColor: {
      get() {
        if (
          this.isMain ||
          (this.isLayer && this.dialogFrom.includes("/main"))
        ) {
          return "primary";
        }
        return "white";
      }
    }
  }
};
</script>
<style>
#mainFooter .q-tabs {
  min-height: 5em;
}
#mainFooter .q-tab__label {
  font-size: 0.8em;
}
</style>
