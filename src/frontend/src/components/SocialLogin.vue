<template></template>

<script>
export default {
  name: "SocialLogin",
  props: {
    mode: {
      type: String,
      required: true
    },
    service: {
      type: String,
      required: true
    },
    id: {
      type: String,
      required: true
    },
    email: {
      type: String,
      default: ""
    },
    name: {
      type: String,
      default: ""
    }
  },
  mounted() {
    if (this.mode === "login") {
      this.$store.commit("setAuth", { isAuth: true });
      this.$store.commit("setCurrentUser", { currentUser: this.email });
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "로그인 되었습니다.",
        caption: ""
      });
      this.$router.push({ path: "/main" });
    } else if (this.mode === "signup") {
      this.$store.commit("setNewSocialUser", {
        service: this.service,
        id: this.id,
        email: this.email,
        name: this.name
      });
      this.$store.commit("setNotification", {
        color: "primary",
        textColor: "white",
        message: "소셜계정으로 로그인 되었습니다.",
        caption: "서비스 이용을 위해 회원가입을 진행해주세요."
      });
      this.$router.push({ path: "/layer/signup" });
    } else {
      this.$router.push({ path: "/" });
    }
  }
};
</script>
