<template>
  <div>
    <q-card id="top" flat>
      <q-card-section horizontal class="q-ml-md q-my-sm">
        <q-img
          class="col-4"
          style="max-width: 100px;"
          :src="dealOne.pnImg"
          contain
        />
        <q-card-section class="self-center full-width q-pl-sm">
          <div class="row">
            <div class="col text-subtitle1 text-weight-bold" style="font-size: 1em;">
              {{ dealOne.pnMdlNm }}
            </div>
          </div>
          <div class="row items-center" style="height: 1.6em;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">통신사</div>
            <div class="col" style="font-size: 0.8em;">{{ dealOne.mntCarr }}</div>
          </div>
          <div class="row items-center" style="height: 1.6em;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">가입유형</div>
            <div class="col" style="font-size: 0.8em;">{{ dealOne.pnRegDis }}</div>
          </div>
          <div class="row items-center" style="height: 100%;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">요금제</div>
            <div class="col" style="font-size: 0.8em;">{{ dealOne.pnMntRtNm }}</div>
          </div>
          <div class="row items-center" style="height: 1.4em;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">예상개통일</div>
            <div class="col" style="font-size: 0.8em;">{{ dealOne.dealDt }}</div>
          </div>
        </q-card-section>
      </q-card-section>
      <q-card-section class="q-py-none bg-blue-2" horizontal>
        <q-card-section class="q-py-none text-caption text-weight-bold">
          기타 요청사항
        </q-card-section>
        <q-card-section class="q-py-none text-caption">
          {{ dealOne.dealReq }}
        </q-card-section>
      </q-card-section>
    </q-card>
    <q-tabs
      dense
      align="justify"
      class="bg-white text-black full-width"
      active-color="primary"
      style="height: 3em;"
    >
      <q-route-tab label="상담진행" :to="'/ongoing/' + this.dealno" />
      <q-route-tab label="상담대기" :to="'/waiting/' + this.dealno" />
    </q-tabs>
    <div class="q-px-sm q-mb-lg q-pb-xl col">
      <q-card
        flat
        class="q-mt-sm q-mb-md"
        v-for="(consultOne, index) in consultList"
        :key="index"
      >
        <q-card-section class="q-pb-sm">
          <div class="row items-center">
            <div
              class="q-mr-sm text-subtitle2 text-weight-bold"
              style="font-size: 1em;"
            >
              {{ consultOne.bnNm }}
            </div>
            <div class="text-primary" style="font-size: 0.8em;">
              (<q-icon name="star_rate" /> {{ consultOne.bnRtn | ifNullNumber }} / 5.0)
            </div>
          </div>
        </q-card-section>
        <q-card-section class="q-py-none">
          <div
            class="row q-py-xs text-caption text-grey-5 justify-evenly"
            style="border: 1px solid gray; border-radius: 4px; font-size: 0.8em;"
          >
            <span :class="callStatus(consultOne.callStCd === 'R')">상담접수중</span>
            <span :class="callStatus(consultOne.callStCd === 'A')">방문예정</span>
            <span :class="callStatus(consultOne.callStCd === 'B')">상담완료</span>
            <span :class="callStatus(consultOne.callStCd === 'C')">상담취소</span>
          </div>
        </q-card-section>
        <q-card-section class="q-py-sm">
          <div class="row items-center" style="height: 1.2em;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">위치</div>
            <div class="col" style="font-size: 0.8em;">{{ consultOne.bnLoca }}</div>
          </div>
          <div class="row items-center" style="height: 2em;">
            <div class="col-4 text-subtitle2 text-weight-bold self-center" style="font-size: 0.8em;">실구매가</div>
            <div class="col text-h6 text-weight-bold text-primary" style="font-size: 1.2em;">
              {{ lowestPrice(consultOne.buyingPriceDv, consultOne.buyingPriceRt) 
              | showMeTheMoney }}
              </div>
          </div>
          <div class="row items-center q-mb-xs" style="height: 1.2em;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">부가서비스</div>
            <div class="col" style="font-size: 0.8em;">{{ consultOne.oldExtServYn | isIncluded }}</div>
          </div>
        </q-card-section>
        <q-card-section class="row q-col-gutter-xs q-py-none">
          <div class="col">
            <q-btn
              unelevated
              rounded
              color="primary"
              icon-right="chevron_right"
              label="리뷰보기"
              class="full-width"
              style="height: 3.2em; font-size: 0.8em;"
            />
          </div>
          <div class="col">
            <q-btn
              unelevated
              rounded
              color="primary"
              icon-right="chevron_right"
              label="위치보기"
              class="full-width"
              style="height: 3.2em; font-size: 0.8em;"
            />
          </div>
        </q-card-section>
        <q-card-section class="q-pt-sm">
          <div class="row q-mb-sm items-start text-subtitle2 text-weight-bold">
            <div class="q-mr-xs">
              <q-icon name="error" style="font-size: 1.2em;" />
            </div>
            <div style="padding-top: 0.2em; font-size: 0.8em;">비고</div>
          </div>
          <div class="row bg-grey-2">
            <p class="q-pa-md q-ma-none">
              <pre
                class="q-my-none text-body2"
                style="font-family: inherit !important; font-size: 0.8em;" 
                >{{ consultOne.oldEtc | ifNullString }}</pre>
            </p>
          </div>
        </q-card-section>
        <q-card-section class="q-py-none">
          <div>
            <q-btn
              unelevated
              rounded
              color="orange-8"
              label="상담취소"
              class="full-width"
              style="height: 3.2em; font-size: 0.8em;"
              @click="clientCancelLayer(consultOne.callNo)"
            />
          </div>
        </q-card-section>
      </q-card>
      <q-card flat class="q-py-lg" v-if="this.pageInit && this.consultList.length < 1">
        <q-card-section class="text-grey-5 content-center">
          <div class="self-center">
            <q-icon name="error" size="xl" class="full-width" />
          </div>
          <div class="q-pt-sm full-width text-center text-subtitle1">
              아직 진행 중인 상담이 없습니다
          </div>
        </q-card-section>
      </q-card>
    </div>
    <q-page-sticky position="bottom-right" :offset="[18, 18]" v-if="this.pageInit && this.consultList.length > 1">
      <q-btn fab icon="expand_less" color="grey-2" text-color="black" @click="scrollTop" />
    </q-page-sticky>
    <q-dialog v-model="canceledDialog" persistent>
      <q-card style="width: 300px">
        <q-card-section>
          <div class="text-subtitle1 text-weight-bold">상담거절</div>
        </q-card-section>

        <q-card-section class="q-pt-none text-caption text-weight-bold" style="font-size: 1em;">
          {{ canceledOne.bnNm }}
        </q-card-section>

        <q-card-section class="q-pt-none text-caption" style="font-size: 0.8em;">
          거절사유 : {{ canceledOne.cnclCmnt | ifNullString }}
        </q-card-section>

        <q-card-actions align="evenly">
          <q-btn
            unelevated
            rounded
            color="primary"
            label="확인"
            class="full-width"
            @click="iterateCanceledList"
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import { scroll } from "quasar";
const { getScrollTarget, setScrollPosition } = scroll;

export default {
  name: "Ongoing",
  props: {
    dealno: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      /** 페이지 초기화 여부 */
      pageInit: false,
      /** 선택 deal 정보 */
      dealOne: {},
      /** 상담 list */
      consultList: [],
      /** 상담거절 팝업 표시 */
      canceledDialog: false,
      /** 상담거절 list */
      canceledList: [],
      /** 상담거절 정보 */
      canceledOne: {}
    };
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });
    this.getDealOne();
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
    /** 상담취소 버튼 클릭 이벤트 */
    clientCancelLayer(callNo) {
      this.$router.push({ path: "/layer/cancel/" + this.dealno + "/" + callNo });
    },
    /** deal 호출 함수 */
    getDealOne() {
      this.$cf.call(
        process.env.API + "/api/deal/one",
        { dealNo: this.dealno },
        this.dealOneCb,
        true
      );
    },
    /** deal 콜백 함수 */
    dealOneCb(result) {
      this.dealOne = result;
      this.getConsult();
    },
    /** ongoing 호출 함수 */
    getConsult() {
      this.$cf.call(
        process.env.API + "/api/deal/consult",
        { dealNo: this.dealno },
        this.consultCb,
        true
      );
    },
    /** ongoing 콜백 함수 */
    consultCb(result) {
      this.consultList = result.consultList;
      for (let n in this.consultList) {
        if (this.consultList[n].callStCd === "E") {
          this.canceledList.push(this.consultList[n]);
        }
      }
      if (this.canceledList.length > 0) {
        this.iterateCanceledList();
      }
      this.pageInit = true;
    },
    /** 상담거절 팝업 표시 함수 */
    iterateCanceledList() {
      this.canceledDialog = false;
      setTimeout(() => {
        if (this.canceledList.length > 0) {
          this.canceledOne = this.canceledList.pop();
          this.canceledDialog = true;
        }
      }, 500);
    },
    /** 상담상태 표시 함수 */
    callStatus(value) {
      if (value) return "text-weight-bold text-primary";
    },
    /** 최저가 비교 함수 */
    lowestPrice(value1, value2) {
      if (parseInt(value1, 10) <= parseInt(value2, 10)) {
        return value1;
      }
      return value2;
    }
  }
};
</script>
