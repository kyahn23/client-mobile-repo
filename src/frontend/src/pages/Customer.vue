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
        <q-route-tab label="공지사항" to="/customer/notice" />
        <q-route-tab label="FAQ" to="/customer/faq" />
        <q-route-tab label="사용방법" to="/customer/manual" />
      </q-tabs>
    </q-page-sticky>
      <router-view />
  </div>
</template>

<script>
export default {
  name: "Customer"
};
</script>
