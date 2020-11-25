<template>
  <div class="q-py-md q-mt-lg">
    <q-list>
      <!--        <div v-for="(dealOne, index) in dealList" :key="index">-->
      <div>
        <q-item>
          <q-item-section>
            <q-item-label class="text-subtitle1 text-weight-bold" lines="1">
              <q-btn v-if="noticeInfo.ntcImpYn === 'Y'" unelevated label="공지" size="xs" color="primary" padding="xs"/>
              {{ noticeInfo.ntcTitle }}
            </q-item-label>
            <q-item-label caption>{{ noticeInfo.inpDt }} | {{ noticeInfo.ntcWriter }}</q-item-label>
          </q-item-section>
        </q-item>
        <q-separator/>
        <div class="q-ma-sm" style="min-height: 200px">
          <p class="full-width q-px-xs" style="word-break: keep-all;">
            {{ noticeInfo.ntcContent }}
          </p>
        </div>
        <q-separator/>
        <div class="q-mt-md full-width text-center">
          <q-btn unelevated color="primary" label="목록" @click="goToList"/>
        </div>
      </div>
    </q-list>

  </div>
</template>

<script>
import {scroll} from "quasar";

const {getScrollTarget, setScrollPosition} = scroll;

export default {
  name: "NoticeContent",
  props: {
    ntcNo: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      noticeInfo: {}
    }
  },
  mounted() {
    this.getNoticeInfo()
  },
  methods: {
    goToList() {
      this.$router.go(-1);
    },
    getNoticeInfo() {
      this.$cf.call(
        process.env.API + "/api/customer/noticeDetail",
        {
          ntcNo: this.ntcNo
        },
        this.getNoticeInfoCB,
        true
      );
    },
    getNoticeInfoCB(response) {
      this.noticeInfo = response
    }
  }
}
</script>

<style scoped>

</style>
