<template></template>

<script>
export default {
  name: "VerifyEmail",
  props: {
    token: {
      type: String,
      required: true
    }
  },
  mounted() {
    this.verifyMail();
  },
  methods: {
    verifyMail() {
      this.$cf.call(
        process.env.API + "/api/auth/verify",
        {
          token: this.token,
          tgt: "MAIL"
        },
        this.verifyCb,
        true
      );
    },
    verifyCb(response) {
      if (response.success === "Y") {
        this.$store.commit("setNotification", {
          color: "positive",
          textColor: "white",
          message: "이메일 인증이 완료되었습니다.",
          caption: "이용을 위해 로그인 해주세요."
        });
        this.$router.push({ path: "/layer/login" });
      } else if (response.success === "E") {
        this.$store.commit("setNotification", {
          color: "warning",
          textColor: "dark",
          message: "인증링크가 만료되었습니다.",
          caption: "인증메일을 다시 요청해주세요."
        });
        this.$router.push({ path: "/layer/login" });
      } else {
        this.$store.commit("setNotification", {
          color: "negative",
          textColor: "white",
          message: "인증에 실패했습니다.",
          caption: "고객센터에 연락 바랍니다."
        });
        this.$router.push({ path: "/" });
      }
    }
  }
};
</script>
