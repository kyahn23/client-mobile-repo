<template>
  <q-page class="flex column flex-center">
    <h5 class="no-margin no-padding">Login</h5>

    <q-input v-model="userId" placeholder="E-Mail" dense class="q-px-xl q-py-sm fit" />
    <q-input
      v-model="userPw"
      placeholder="Password"
      :type="isPwd ? 'password' : 'text'"
      class="q-px-xl q-py-sm fit"
    >
      <template v-slot:append>
        <q-icon
          :name="isPwd ? 'visibility_off' : 'visibility'"
          class="cursor-pointer"
          @click="isPwd = !isPwd"
        />
      </template>
    </q-input>
    <div class="flex column q-px-xl q-py-lg q-gutter-y-md fit">
      <q-btn color="black" label="로그인" />
      <q-btn color="white" text-color="black" label="회원가입" to="/signup" />
      <q-btn color="primary" label="구글 로그인" type="a" :href="oAuthUrl('google')" />
      <q-btn color="positive" label="네이버 로그인" type="a" :href="oAuthUrl('naver')" />
      <q-btn color="amber" text-color="black" label="카카오 로그인" type="a" :href="oAuthUrl('kakao')" />
    </div>
  </q-page>
</template>

<script>
import { openURL } from "quasar";

export default {
  name: "PageLogin",
  data() {
    return {
      isPwd: true,
      userId: "",
      userPw: "",
    };
  },
  mounted() {
    if (this.$route.query.notify === "socialCan") {
      this.loginNotify(
        "warning",
        "dark",
        "소셜로그인이 취소되었습니다.",
        "회원가입 절차를 다시 시작해주세요."
      );
    }
    if (this.$route.query.notify === "emailAcc") {
      this.loginNotify(
        "warning",
        "dark",
        "이미 가입된 이메일 계정입니다.",
        "이메일 로그인으로 진행해주세요."
      );
    }
  },
  methods: {
    /** 로그인 이전 상태에 따라 notify */
    loginNotify: function (clr, txtClr, msg, cpt) {
      this.$q.notify({
        group: false,
        color: clr,
        textColor: txtClr,
        message: msg,
        caption: cpt,
      });
    },
    /** 서비스별 oAuth 처리 Url */
    oAuthUrl: function (service) {
      let apiRoot = "";
      if (process.env.DEV) apiRoot = "http://localhost:8080";
      return apiRoot + "/oauth2/authorization/" + service;
    },
  },
};
</script>
