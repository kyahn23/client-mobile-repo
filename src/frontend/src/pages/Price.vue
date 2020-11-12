<template>
  <div>
    <q-page-sticky
      position="top"
      expand
      class="bg-primary text-white"
      height-hint="98"
      style="z-index: 5000 !important;"
    >
      <q-tabs dense align="justify" class="bg-white text-black full-width">
        <q-route-tab label="SKT" to="/price/SKT" />
        <q-route-tab label="KT" to="/price/KT" />
        <q-route-tab label="LGU+" to="/price/LGU" />
      </q-tabs>
    </q-page-sticky>
      <router-view />
  </div>
</template>

<script>
export default {
  name: "Price"
};
</script>
<!--<style src="src/css/styles.css"></style>-->
