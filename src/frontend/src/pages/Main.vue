<template>
  <q-layout>
    <q-header class="bg-primary text-white" height-hint="98">
      <q-tabs dense class="bg-white text-black" align="left">
        <q-route-tab label="PAGE1" to="/main/1" />
        <q-route-tab label="PAGE2" to="/main/2" />
        <q-route-tab label="PAGE3" to="/main/3" />
        <q-route-tab label="PAGE4" to="/main/4" />
        <q-route-tab label="PAGE5" to="/main/5" />
      </q-tabs>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "PageMain",
};
</script>
