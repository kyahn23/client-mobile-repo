<template>
  <q-layout>
    <q-header class="bg-primary text-white" height-hint="98">
      <q-tabs dense class="bg-white text-black fixed full-width">
        <q-route-tab label="SKT" to="/price/SKT" />
        <q-route-tab label="KT" to="/price/KT" />
        <q-route-tab label="LGU+" to="/price/LGU" />
      </q-tabs>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "Price",
};
</script>
<style src="src/css/styles.css"></style>
