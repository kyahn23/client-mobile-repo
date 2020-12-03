<template>
  <div id="q-app">
    <router-view />
  </div>
</template>
<script>
const myIcons = {
  "app:google": "img:icons/google_logo.svg",
  "app:naver": "img:icons/naver_logo.svg",
  "app:kakao": "img:icons/kakao_logo.svg"
};

export default {
  name: "App",
  created() {
    this.$q.iconMapFn = iconName => {
      const icon = myIcons[iconName];
      if (icon !== void 0) {
        return { icon: icon };
      }
    };
  }
};
</script>
