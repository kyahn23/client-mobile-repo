<template>
  <div class="q-py-md q-mt-lg">
    <q-list>
      <!--        <div v-for="(dealOne, index) in dealList" :key="index">-->
      <div>
        <q-item>
          <q-item-section>
            <q-item-label class="text-subtitle1 text-weight-bold" lines="1">
              <q-btn unelevated label="공지" size="xs" color="primary" padding="xs"/>
              제목제목제목제목
            </q-item-label>
            <q-item-label caption>2020.09.12 | 시스템관리자</q-item-label>
          </q-item-section>
        </q-item>
        <q-separator/>
        <div class="q-ma-sm" style="min-height: 200px">
          <p class="full-width q-px-xs" style="word-break: keep-all;">
            펜타에스는 모두가 만족할 수 있는 소프트웨어 개발 및 IT 시스템 구축을 수행하고 있습니다.<br/>
            변화하는 IT 환경과 기술에 능동적으로 적응하며, 고객과의 약속과 가치 창출을 위하여 최선을 다하겠습니다.
          </p>

        </div>
        <q-separator/>
        <div class="q-mt-md full-width text-center">
          <q-btn unelevated color="primary" label="목록" @click="goToList"/>
        </div>
      </div>


<!--      <q-item class="q-py-md" v-if="this.pageInfo.hasNextPage">-->
<!--        <q-item-section>-->
<!--          <q-spinner color="primary" size="sm" class="self-center"/>-->
<!--        </q-item-section>-->
<!--      </q-item>-->

<!--      <q-item-->
<!--        clickable-->
<!--        class="q-py-md"-->
<!--        v-else-if="this.pageInfo.lastPage && this.pageInfo.totalPages > 1"-->
<!--        @click="scrollTop">-->
<!--        <q-item-section>-->
<!--          <q-item-label class="self-center">-->
<!--            <q-icon name="expand_less" size="md"/>-->
<!--          </q-item-label>-->
<!--          <q-item-label class="q-pb-md self-center text-caption">-->
<!--            맨 위로 돌아가기-->
<!--          </q-item-label>-->
<!--        </q-item-section>-->
<!--      </q-item>-->
    </q-list>

  </div>
</template>

<script>
import {scroll} from "quasar";

const {getScrollTarget, setScrollPosition} = scroll;

export default {
  name: "Notice",
  data() {
    return {
      /** 페이지 초기화 여부 */
      pageInit: false,
      /** 페이징 처리 정보 */
      pageInfo: {},
      /* 공지사항리스트 */
      noticeList: [],
    }
  },
  methods: {
    goToList(){
      // this.$router.push({ path: "/notice/" + dealNo });
      this.$router.go(-1);
    }
  }
}
</script>


<style scoped>

</style>
