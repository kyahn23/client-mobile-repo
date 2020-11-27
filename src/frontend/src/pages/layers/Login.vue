<template>
  <q-page class="flex column flex-center">
    <q-img
      class="q-pt-xl q-mt-lg q-pb-md"
      src="/images/pentas_logo_320.png"
      style="width: 60%; max-width: 240px;"
      contain
    />

    <q-form class="flex column fit" ref="loginForm" @submit.prevent="onLogin">
      <q-input
        v-model="userId"
        label="아이디 (이메일)"
        rounded
        outlined
        stack-label
        class="q-px-lg q-pt-sm q-mb-xs fit"
        :rules="[
          $rules.required('아이디를 입력해주세요.'),
          $rules.email('올바른 이메일이 아닙니다.')
        ]"
      />
      <q-input
        v-model="userPw"
        label="비밀번호"
        rounded
        outlined
        stack-label
        :type="isPwd ? 'password' : 'text'"
        class="q-px-lg q-py-sm fit"
        :rules="[$rules.required('비밀번호를 입력해주세요.')]"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>
      <div class="flex column q-px-lg q-pt-lg q-gutter-y-md fit">
        <q-btn
          unelevated
          rounded
          color="primary"
          label="로그인"
          type="submit"
        />
      </div>
    </q-form>
    <div
      class="row q-px-md q-mt-md q-mb-md full-width justify-around items-center"
      style="font-size: 0.8em; vertical-align: middle; line-height: 1em;"
    >
      <q-btn
        class="q-px-xs"
        unelevated
        rounded
        flat
        dense
        color="black"
        label="이용방법"
        style="font-size: 0.8em;"
        to="/customer/manual"
      />
      <q-icon
        name="horizontal_rule"
        color="grey"
        style="font-size: 1.4em; transform: rotate(90deg);"
      />
      <q-btn
        class="q-px-xs"
        unelevated
        rounded
        flat
        dense
        color="black"
        label="아이디 찾기"
        style="font-size: 0.8em;"
      />
      <q-icon
        name="horizontal_rule"
        color="grey"
        style="font-size: 1.4em; transform: rotate(90deg);"
      />
      <q-btn
        class="q-px-xs"
        unelevated
        rounded
        flat
        dense
        color="black"
        label="비밀번호 찾기"
        style="font-size: 0.8em;"
      />
      <q-icon
        name="horizontal_rule"
        color="grey"
        style="font-size: 1.4em; transform: rotate(90deg);"
      />
      <q-btn
        class="q-px-xs"
        unelevated
        rounded
        flat
        dense
        color="black"
        label="회원가입"
        style="font-size: 0.8em;"
        to="/layer/signup"
      />
    </div>
    <q-separator />
    <div class="flex column q-px-lg q-py-lg q-gutter-y-md fit">
      <q-btn
        unelevated
        rounded
        outline
        color="black"
        icon="app:google"
        label="구글 로그인"
        type="a"
        :href="oAuthUrl('google')"
      />
      <q-btn
        unelevated
        rounded
        color="positive"
        icon="app:naver"
        id="naverLoginBtn"
        label="네이버 로그인"
        type="a"
        :href="oAuthUrl('naver')"
      />
      <q-btn
        unelevated
        rounded
        color="amber"
        text-color="black"
        icon="app:kakao"
        label="카카오 로그인"
        type="a"
        :href="oAuthUrl('kakao')"
      />
    </div>
  </q-page>
</template>

<script>
import sha256 from "crypto-js/sha256";
import { openURL } from "quasar";

export default {
  name: "LayerLogin",
  data() {
    return {
      isPwd: true,
      userId: "",
      userPw: ""
    };
  },
  mounted() {
    if (this.$route.query.notify === "socialCan") {
      this.$store.commit("setNotification", {
        color: "warning",
        textColor: "dark",
        message: "소셜로그인이 취소되었습니다.",
        caption: "회원가입 절차를 다시 시작해주세요."
      });
    }
    if (this.$route.query.notify === "emailAcc") {
      this.$store.commit("setNotification", {
        color: "warning",
        textColor: "dark",
        message: "이미 이메일로 가입된 계정입니다.",
        caption: "이메일 로그인으로 진행해주세요."
      });
    }
  },
  methods: {
    /** 로그인 폼 초기화 함수 */
    resetLogin() {
      this.isPwd = true;
      this.userId = "";
      this.userPw = "";
      this.$refs.loginForm.reset();
    },
    /** 로그인 버튼 클릭 이벤트 */
    onLogin() {
      if (!this.$cf.isEmpty(this.userId) && !this.$cf.isEmpty(this.userPw)) {
        this.$cf.call(
          process.env.API + "/api/auth/signin",
          {
            email: this.userId,
            bibun: sha256(this.userPw).toString()
          },
          this.afterLogin,
          true
        );
      }
    },
    /** 로그인 콜백 함수 */
    afterLogin(response) {
      if (response.rsltStat === "SSO") {
        this.$store.commit("setNotification", {
          color: "warning",
          textColor: "dark",
          message: "이미 소셜로그인으로 가입된 계정입니다.",
          caption: "소셜로그인으로 진행해주세요."
        });
        this.resetLogin();
        return;
      } else if (response.rsltStat === "MAIL") {
        this.$store.commit("setNotification", {
          color: "warning",
          textColor: "dark",
          message: "이메일 인증이 완료되지 않았습니다.",
          caption: "이메일 인증 후 로그인 해주세요."
        });
        this.resetLogin();
        return;
      } else if (response.rsltStat === "CHK") {
        this.$store.commit("setNotification", {
          color: "negative",
          textColor: "white",
          message: "로그인에 실패했습니다.",
          caption: "이메일 및 비밀번호를 확인해주세요."
        });
        this.resetLogin();
        return;
      } else if (response.rsltStat === "DND") {
        this.$store.commit("setNotification", {
          color: "negative",
          textColor: "white",
          message: "로그인에 5회 이상 실패했습니다.",
          caption: "비밀번호 찾기를 이용해주세요."
        });
        this.resetLogin();
        return;
      } else if (response.rsltStat === "SUCC") {
        this.$store.commit("setAuth", { isAuth: true });
        this.$store.commit("setCurrentUser", { currentUser: this.userId });
        this.$store.commit("setNotification", {
          color: "primary",
          textColor: "white",
          message: "로그인 되었습니다."
        });
        this.$router.push({ path: "/main" });
      }
    },
    /** 서비스별 oAuth 처리 Url */
    oAuthUrl(service) {
      return process.env.API + "/oauth2/authorization/" + service;
    }
  }
};
</script>
<style>
#naverLoginBtn .q-icon {
  font-size: 1.4em;
}
</style>
