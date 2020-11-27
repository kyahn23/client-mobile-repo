<template></template>

<script>
export default {
  name: "VerifyEmail",
  props: {
    mbr: {
      type: String,
      required: true
    },
    cue: {
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
          mbr: this.mbr,
          cue: this.cue
        },
        this.verifyCb,
        true
      );
    },
    verifyCb(response) {
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "이메일 인증이 완료되었습니다.",
        caption: "이용을 위해 로그인 해주세요."
      });
      this.$router.push({ path: "/layer/login" });
    }
  }
};
</script>
