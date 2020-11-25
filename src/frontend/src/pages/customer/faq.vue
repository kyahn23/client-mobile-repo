<template>
  <div id="top" class="q-mt-xl q-pt-sm">
    <div class="q-px-md q-mb-sm row justify-between text-weight-bold">
      <div class="column">FAQ</div>
      <div class="column">총 {{ pageInfo.totalCount }}건</div>
    </div>
    <q-separator class="full-width" />
    <div v-for="(faqOne, index) in faqList" :key="index">
      <q-expansion-item
        group="faqAccordion"
        :label="'Q. ' + faqOne.faqQuestion"
        style="font-weight: bold;"
      >
        <q-card
          style="background-color: #E9F0F8;
            border-top: 1px solid rgba(0, 0, 0, 0.12);
            font-weight: normal;"
        >
          <q-card-section>
            <div class="q-mb-xs">{{ faqOne.faqAnswer }}</div>
            <div class="caption" style="font-size: 0.9em;">
              {{ faqOne.faqDetail }}
            </div>
          </q-card-section>
        </q-card>
      </q-expansion-item>
      <q-separator />
    </div>
    <div
      class="q-my-md q-mx-md"
      v-if="this.pageInit && this.pageInfo.hasNextPage"
    >
      <q-btn
        flat
        class="full-width text-weight-bold"
        color="primary"
        label="더 보기"
        icon-right="add"
        style="background-color: #E9F0F8;"
        @click="onFaqMore"
      />
    </div>
    <q-item class="q-py-lg" v-if="this.pageInit && this.faqList.length < 1">
      <q-item-section class="text-grey-5">
        <q-item-label class="self-center">
          <q-icon name="error" size="xl" />
        </q-item-label>
        <q-item-label class="q-pt-sm self-center text-subtitle1">
          아직 등록된 글이 없습니다
        </q-item-label>
      </q-item-section>
    </q-item>
    <div style="height: 72px;"></div>
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <q-btn
        fab
        icon="expand_less"
        color="grey-2"
        text-color="black"
        @click="scrollTop"
      />
    </q-page-sticky>
  </div>
</template>

<script>
import { scroll } from "quasar";
const { getScrollTarget, setScrollPosition } = scroll;

export default {
  name: "PageFaq",
  data() {
    return {
      /** 페이지 초기화 여부 */
      pageInit: false,
      /** 페이징 처리 정보 */
      pageInfo: {},
      /** faq 리스트 */
      faqList: []
    };
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });
    this.getList(1);
  },
  methods: {
    /** 맨 위로 돌아가기 이벤트 */
    scrollTop() {
      const ele = document.getElementById("top");
      const target = getScrollTarget(ele);
      const offset = ele.offsetTop - 1000;
      const duration = 200;
      setScrollPosition(target, offset, duration);
    },
    /** list 호출 함수 */
    getList(page) {
      this.$cf.call(
        process.env.API + "/api/customer/faq",
        { page: page + "" },
        this.listCb,
        true
      );
    },
    /** list 콜백 함수 */
    listCb(response) {
      this.pageInfo = response.pageInfo;
      for (let n in response.faqList) {
        this.faqList.push(response.faqList[n]);
      }
      this.pageInit = true;
    },
    /** 더 보기 버튼 클릭 이벤트 */
    onFaqMore() {
      this.getList(this.pageInfo.nextPage);
    }
  }
};
</script>
