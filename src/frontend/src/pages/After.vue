<template>
  <q-page class="flex flex-center">
    <h5>After</h5>
  </q-page>
</template>

<script>
export default {
  name: "PageAfter",
  // Ant Boot
  created() {
    if (!this.$store.getters.isAuth) {
      this.$router.push({ path: "login" });
    }
  },
};
</script>
