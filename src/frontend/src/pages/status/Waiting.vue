<template>
  <div>
    <q-card id="top" flat>
      <q-card-section horizontal class="q-ml-md q-my-sm">
        <q-img
          class="col-4"
          style="max-width: 100px;"
          :src="$cf.imagePath(dealOne.pnImg)"
          contain
        />
        <q-card-section class="self-center full-width q-pl-sm">
          <div class="row">
            <div
              class="col text-subtitle1 text-weight-bold"
              style="font-size: 1em;"
            >
              {{ dealOne.pnMdlNm }}
            </div>
          </div>
          <div class="row items-center" style="height: 1.6em;">
            <div
              class="col-4 text-subtitle2 text-weight-bold"
              style="font-size: 0.8em;"
            >
              통신사
            </div>
            <div class="col" style="font-size: 0.8em;">
              {{ dealOne.mntCarr }}
            </div>
          </div>
          <div class="row items-center" style="height: 1.6em;">
            <div
              class="col-4 text-subtitle2 text-weight-bold"
              style="font-size: 0.8em;"
            >
              가입유형
            </div>
            <div class="col" style="font-size: 0.8em;">
              {{ dealOne.pnRegDisTxt }}
            </div>
          </div>
          <div class="row items-center" style="height: 100%;">
            <div
              class="col-4 text-subtitle2 text-weight-bold"
              style="font-size: 0.8em;"
            >
              요금제
            </div>
            <div class="col" style="font-size: 0.8em;">
              {{ dealOne.pnMntRtNm }}
            </div>
          </div>
          <div class="row items-center" style="height: 1.4em;">
            <div
              class="col-4 text-subtitle2 text-weight-bold"
              style="font-size: 0.8em;"
            >
              예상개통일
            </div>
            <div class="col" style="font-size: 0.8em;">
              {{ dealOne.dealDt }}
            </div>
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
      <q-route-tab label="상담진행" :to="'/status/ongoing/' + this.dealno" />
      <q-route-tab label="상담대기" :to="'/status/waiting/' + this.dealno" />
    </q-tabs>
    <div class="q-px-sm q-mb-lg q-pb-xl col">
      <q-card
        flat
        class="q-mt-sm q-mb-md"
        v-for="(sellingOne, index) in sellingList"
        :key="index"
      >
        <q-card-section class="q-pb-none">
          <div class="row items-center">
            <div class="q-mr-xs">
            <q-checkbox
              class="text-subtitle2 text-weight-bold"
              size="xs"
              v-model="checkList"
              :val="sellingOne.bnNo"
              :label="sellingOne.bnNm"
              style="margin: 0 4px 0 -6px;"
            />
            </div>
            <div :class="setRatingClass(sellingOne.bnRtn)" style="font-size: 0.8em;">
              (<q-icon name="star_rate" /> {{ sellingOne.bnRtn | ifNullNumber }} / 5.0)
            </div>
          </div>
        </q-card-section>
        <q-card-section class="q-pt-xs q-pb-sm">
          <div class="row items-center" style="height: 1.2em;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">위치</div>
            <div class="col" style="font-size: 0.8em;">{{ sellingOne.bnLoca }}</div>
          </div>
          <div class="row items-center" style="height: 2em;">
            <div class="col-4 text-subtitle2 text-weight-bold self-center" style="font-size: 0.8em;">실구매가</div>
            <div class="col text-h6 text-weight-bold text-primary" style="font-size: 1.2em;">
              {{ lowestPrice(sellingOne.buyingPriceDv, sellingOne.buyingPriceRt) 
              | showMeTheMoney }}
              </div>
          </div>
          <div class="row items-center q-mb-xs" style="height: 1.2em;">
            <div class="col-4 text-subtitle2 text-weight-bold" style="font-size: 0.8em;">부가서비스</div>
            <div class="col" style="font-size: 0.8em;">{{ sellingOne.pnExtServYn | isIncluded }}</div>
          </div>
        </q-card-section>
        <q-card-section class="row q-col-gutter-xs q-pt-none q-pb-sm">
          <div class="col">
            <q-btn
              unelevated
              rounded
              color="primary"
              icon-right="chevron_right"
              label="리뷰보기"
              class="full-width"
              style="height: 3.2em; font-size: 0.8em;"
              @click="reviewTemp"
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
              @click="locationTemp"
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
                >{{ sellingOne.pnEtc | ifNullString }}</pre>
            </p>
          </div>
        </q-card-section>
      </q-card>
      <q-card flat class="q-py-lg" v-if="this.pageInit && this.sellingList.length < 1">
        <q-card-section class="text-grey-5 content-center">
          <div class="self-center">
            <q-icon name="error" size="xl" class="full-width" />
          </div>
          <div class="q-pt-sm full-width text-center text-subtitle1">
              해당 조건에 맞는 판매조건이 없습니다
          </div>
        </q-card-section>
      </q-card>
      <q-page-sticky position="bottom-left" :offset="[18, 18]">
        <q-btn
          fab
          :color="requestButton.color"
          :text-color="requestButton.textColor"
          label="상담신청"
          icon-right="chevron_right"
          @click="onRequest"
        />
      </q-page-sticky>
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
  </div>
</template>

<script>
import { scroll } from "quasar";
const { getScrollTarget, setScrollPosition } = scroll;

export default {
  name: "PageWaiting",
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
      /** 판매 list */
      sellingList: [],
      /** 체크박스 list */
      checkList: [],
      /** 상담신청 버튼 상태 */
      requestButton: {
        status: false,
        color: "grey-2",
        textColor: "grey-5"
      }
    };
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });
    this.getDealOne();
  },
  watch: {
    checkList(newList) {
      if (newList.length < 1) {
        this.requestButton.status = false;
        this.requestButton.color = "grey-2";
        this.requestButton.textColor = "grey-5";
      } else {
        this.requestButton.status = true;
        this.requestButton.color = "primary";
        this.requestButton.textColor = "white";
      }
    }
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
    /** 평점 클래스 설정 함수 */
    setRatingClass(rating) {
      if (this.$cf.isEmpty(rating)) {
        return 'text-grey-5'
      }
      return 'text-primary'
    },
    /** 리뷰보기 임시 이벤트 */
    reviewTemp() {
      this.$store.commit("setNotification", {
          color: "warning",
          textColor: "dark",
          message: "리뷰보기는 준비중입니다."
        });
    },
    /** 위치보기 임시 이벤트 */
    locationTemp() {
      this.$store.commit("setNotification", {
          color: "warning",
          textColor: "dark",
          message: "위치보기는 준비중입니다."
        });
    },
    /** 상담신청 클릭 이벤트 */
    onRequest() {
      if (this.requestButton.status) {
        let reqno = "";
        for (let n in this.checkList) {
          if (parseInt(n, 10) === 0) {
            reqno += this.checkList[n]
          } else {
            reqno += ("-" + this.checkList[n])
          }
        }
        this.$router.push({ path: "/layer/request/" + this.dealno + "?dis=" + this.dealOne.pnRegDis + "&req=" + reqno });
      }
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
      this.getSelling();
    },
    /** selling 호출 함수 */
    getSelling() {
      this.$cf.call(
        process.env.API + "/api/deal/selling",
        { dealNo: this.dealno },
        this.sellingCb,
        true
      );
    },
    /** selling 콜백 함수 */
    sellingCb(result) {
      this.sellingList = result.sellingList;
      this.pageInit = true;
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
