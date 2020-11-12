<template>
  <div>
    <q-page-sticky
      position="top"
      expand
      class="bg-primary text-white"
      height-hint="98"
      style="z-index: 5000 !important;"
    >
      <q-tabs
        dense
        align="justify"
        class="bg-white text-black full-width"
        active-color="primary"
      >
        <q-route-tab label="PAGE1" to="/main/1" />
        <q-route-tab label="PAGE2" to="/main/2" />
        <q-route-tab label="PAGE3" to="/main/3" />
        <q-route-tab label="PAGE4" to="/main/4" />
        <q-route-tab label="PAGE5" to="/main/5" />
      </q-tabs>
    </q-page-sticky>
    <q-page-container>
      <router-view />
    </q-page-container>
  </div>
</template>

<script>
export default {
  name: "PageMain"
};
</script>
