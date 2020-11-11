<template>
  <div>
    <q-page-sticky
      position="top"
      expand
      class="bg-primary text-white z-top"
      height-hint="98"
    >
      <q-tabs dense align="justify" class="bg-white text-black full-width">
        <q-route-tab label="SKT" to="/price/SKT" />
        <q-route-tab label="KT" to="/price/KT" />
        <q-route-tab label="LGU+" to="/price/LGU" />
      </q-tabs>
    </q-page-sticky>
    <q-page-container>
      <router-view />
    </q-page-container>
  </div>
</template>

<script>
export default {
  name: "Price"
};
</script>
<style src="src/css/styles.css"></style>
