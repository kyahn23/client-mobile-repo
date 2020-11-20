<template>
  <q-page class="flex column">
    <h5>상담취소</h5>

    <!-- <q-form class="flex column fit" ref="loginForm" @submit.prevent="onLogin">
      <q-input
        v-model="userId"
        placeholder="아이디 (이메일)"
        rounded
        outlined
        class="q-px-lg q-py-sm fit"
        :rules="[
          $rules.required('아이디를 입력해주세요.'),
          $rules.email('올바른 이메일이 아닙니다.')
        ]"
      />
      <q-input
        v-model="userPw"
        placeholder="비밀번호"
        rounded
        outlined
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
      <div class="flex column q-px-lg q-py-lg q-gutter-y-md fit">
        <q-btn
          unelevated
          rounded
          color="primary"
          label="로그인"
          type="submit"
        />
      </div>
    </q-form>
    <q-separator />
    <div class="flex column q-px-lg q-py-lg q-gutter-y-md fit">
      <q-btn
        unelevated
        rounded
        color="black"
        label="회원가입"
        to="/layer/signup"
      />
      <q-btn
        unelevated
        rounded
        color="primary"
        label="구글 로그인"
        type="a"
        :href="oAuthUrl('google')"
      />
      <q-btn
        unelevated
        rounded
        color="positive"
        label="네이버 로그인"
        type="a"
        :href="oAuthUrl('naver')"
      />
      <q-btn
        unelevated
        rounded
        color="amber"
        text-color="black"
        label="카카오 로그인"
        type="a"
        :href="oAuthUrl('kakao')"
      />
    </div> -->
  </q-page>
</template>

<script>
export default {
  name: "LayerCancel",
  props: {
    callno: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      // isPwd: true,
      // userId: "",
      // userPw: ""
    };
  },
  mounted() {
    // if (this.$route.query.notify === "socialCan") {
    //   this.$store.commit("setNotification", {
    //     color: "warning",
    //     textColor: "dark",
    //     message: "소셜로그인이 취소되었습니다.",
    //     caption: "회원가입 절차를 다시 시작해주세요."
    //   });
    // }
    // if (this.$route.query.notify === "emailAcc") {
    //   this.$store.commit("setNotification", {
    //     color: "warning",
    //     textColor: "dark",
    //     message: "이미 이메일로 가입된 계정입니다.",
    //     caption: "이메일 로그인으로 진행해주세요."
    //   });
    // }
  }
  // methods: {
  //   /** 로그인 버튼 클릭 이벤트 */
  //   onLogin: function() {
  //     if (!this.$cf.isEmpty(this.userId)) {
  //       this.$cf.call(
  //         process.env.API + "/api/auth/signin",
  //         { email: this.userId },
  //         this.afterLogin,
  //         true
  //       );
  //     }
  //   },
  //   afterLogin(response) {
  //     if (response.rsltStat === "SSO") {
  //       this.$store.commit("setNotification", {
  //         color: "warning",
  //         textColor: "dark",
  //         message: "이미 소셜로그인으로 가입된 계정입니다.",
  //         caption: "소셜로그인으로 진행해주세요."
  //       });
  //       this.isPwd = true;
  //       this.userId = "";
  //       this.userPw = "";
  //       this.$refs.loginForm.reset();
  //       return;
  //     }
  //   },
  //   /** 서비스별 oAuth 처리 Url */
  //   oAuthUrl: function(service) {
  //     return process.env.API + "/oauth2/authorization/" + service;
  //   }
  // }
};
</script>
