<template>
  <div id="q-app">
    <router-view />
  </div>
</template>
<script>
const myIcons = {
  "app:google": "img:icons/google_logo.svg",
  "app:naver": "img:icons/naver_logo.svg",
  "app:kakao": "img:icons/kakao_logo.svg",
  "app:step1": "img:icons/contract.svg",
  "app:step2": "img:icons/mobile.svg",
  "app:step3": "img:icons/checklist.svg",
  "app:step4": "img:icons/email.svg"
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
