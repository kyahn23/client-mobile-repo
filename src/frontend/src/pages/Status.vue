<template>
  <div class="column">
    <div
      id="top"
      class="q-ml-md q-mt-lg q-mb-xs text-subtitle1 text-weight-bold"
    >
      상담현황 리스트
    </div>
    <q-list>
      <q-separator />
      <q-infinite-scroll @load="onScrollLoad" :offset="110">
        <div v-for="(dealOne, index) in dealList" :key="index">
          <q-item class="q-py-md">
            <q-item-section avatar>
              <q-avatar square size="4em">
                <img :src="dealOne.pnImg" />
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label
                class="text-caption text-weight-bold"
                style="font-size: 0.8em;"
              >
                {{ dealOne.pnMdlNm }}
              </q-item-label>
              <q-item-label class="text-caption">
                최저가
                <span class="text-h6 text-weight-bold">
                  <span class="text-primary">
                    {{
                      lowestPrice(dealOne.minPriceDv, dealOne.minPriceRt)
                        | showMeTheMoney
                    }}
                  </span>
                  <q-icon
                    name="delete_outline"
                    color="black"
                    @click="deleteClick(dealOne)"
                  />
                </span>
              </q-item-label>
              <q-item-label
                lines="2"
                class="text-caption"
                style="font-size: 0.6em;"
              >
                {{ dealOne.mntCarr }} / {{ dealOne.pnRegDis }} /
                {{ dealOne.pnMntRtNm }}
              </q-item-label>
            </q-item-section>

            <q-item-section side>
              <q-icon
                name="chevron_right"
                color="black"
                @click="dealClick(dealOne.dealNo)"
              />
            </q-item-section>
          </q-item>
          <q-separator />
        </div>
      </q-infinite-scroll>
      <q-item class="q-py-md" v-if="this.pageInfo.hasNextPage">
        <q-item-section>
          <q-spinner color="primary" size="sm" class="self-center" />
        </q-item-section>
      </q-item>
      <q-item
        clickable
        class="q-py-md"
        v-else-if="this.pageInfo.lastPage && this.pageInfo.totalPages > 1"
        @click="scrollTop"
      >
        <q-item-section>
          <q-item-label class="self-center">
            <q-icon name="expand_less" size="md" />
          </q-item-label>
          <q-item-label class="q-pb-md self-center text-caption">
            맨 위로 돌아가기
          </q-item-label>
        </q-item-section>
      </q-item>
      <q-item class="q-py-lg" v-if="this.pageInit && this.dealList.length < 1">
        <q-item-section class="text-grey-5">
          <q-item-label class="self-center">
            <q-icon name="error" size="xl" />
          </q-item-label>
          <q-item-label class="q-pt-sm self-center text-subtitle1">
            아직 요청한 상담이 없습니다
          </q-item-label>
        </q-item-section>
      </q-item>
    </q-list>
    <q-dialog v-model="deleteDialog" persistent>
      <q-card style="width: 300px">
        <q-card-section>
          <div class="text-subtitle1 text-weight-bold">삭제</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <div class="text-caption text-weight-bold" style="font-size: 1em;">
            {{ deleteTarget.pnMdlNm }}
          </div>
          <div class="text-caption" style="font-size: 0.8em;">
            {{ deleteTarget.mntCarr }} / {{ deleteTarget.pnRegDis }} /
            {{ deleteTarget.pnMntRtNm }}
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          삭제하시겠습니까?
        </q-card-section>

        <q-card-actions align="evenly">
          <q-btn
            unelevated
            rounded
            color="primary"
            label="취소"
            style="width: 48%"
            v-close-popup
          />
          <q-btn
            unelevated
            rounded
            color="primary"
            label="삭제"
            style="width: 48%"
            @click="deleteConfirm"
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
    <q-dialog v-model="dealDateDialog" persistent>
      <q-card style="width: 300px">
        <q-card-section>
          <div class="text-subtitle1 text-weight-bold">DEAL 연장</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <div class="text-caption text-weight-bold" style="font-size: 1em;">
            {{ dealDateOne.pnMdlNm }}
          </div>
          <div class="text-caption" style="font-size: 0.8em;">
            {{ dealDateOne.mntCarr }} / {{ dealDateOne.pnRegDis }} /
            {{ dealDateOne.pnMntRtNm }}
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          DEAL을 1주 연장하시겠습니까?
        </q-card-section>

        <q-card-actions align="evenly">
          <q-btn
            unelevated
            rounded
            color="primary"
            label="취소"
            style="width: 48%"
            @click="iterateDealDateList"
          />
          <q-btn
            unelevated
            rounded
            color="primary"
            label="연장"
            style="width: 48%"
            @click="extendConfirm"
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
  name: "PageStatus",
  data() {
    return {
      /** 페이지 초기화 여부 */
      pageInit: false,
      /** 페이징 처리 정보 */
      pageInfo: {},
      /** deal 리스트 */
      dealList: [],
      /** deal 일시 리스트 */
      dealDateList: [],
      /** deal 임박일시 정보 */
      dealDateOne: {},
      /** deal 임박일시 dialog 표시 여부 */
      dealDateDialog: false,
      /** 삭제 dialog 표시 여부 */
      deleteDialog: false,
      /** 삭제 deal 정보 */
      deleteTarget: {}
    };
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });
    this.getDates();
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
    /** infinite scroll 이벤트 */
    onScrollLoad(index, done) {
      setTimeout(() => {
        if (index === 1) {
          this.getList(index + "");
          done();
        } else if (this.pageInfo.hasNextPage) {
          this.getList(this.pageInfo.nextPage + "");
          done();
        }
      }, 2000);
    },
    /** 최저가 비교 함수 */
    lowestPrice(value1, value2) {
      if (parseInt(value1, 10) <= parseInt(value2, 10)) {
        return value1;
      }
      return value2;
    },
    /** > 클릭 이벤트 */
    dealClick(dealNo) {
      this.$router.push({ path: "/ongoing/" + dealNo });
    },
    /** 삭제 팝업 표시 이벤트 */
    deleteClick(deal) {
      this.deleteTarget = deal;
      this.deleteDialog = true;
    },
    /** deal 일시 list 호출 함수 */
    getDates() {
      this.$cf.call(
        process.env.API + "/api/deal/dates",
        {
          email: "clientTest@gmail.com"
          /** TODO 로그인 구현 후 사용 변경 */
          // email: this.currentUser
        },
        this.datesCb,
        true
      );
    },
    /** deal 일시 list 콜백 함수 */
    datesCb(response) {
      if (!this.pageInit) {
        this.$store.commit("setLoading", { isLoading: true });
      }
      let dealDateList = response.dealDateList;
      const today = new Date();
      const hourInMilliseconds = 3600000;
      for (let n in dealDateList) {
        let dealDate = new Date(dealDateList[n].dealDt);
        let dateDifference = dealDate - today;
        if (dateDifference <= hourInMilliseconds * 24 && dateDifference > 0) {
          this.dealDateList.push(dealDateList[n]);
        }
      }
      if (this.dealDateList.length > 0) {
        this.iterateDealDateList();
      }
    },
    /** deal 임박일시 팝업 표시 함수 */
    iterateDealDateList() {
      this.dealDateDialog = false;
      setTimeout(() => {
        if (this.dealDateList.length > 0) {
          this.dealDateOne = this.dealDateList.pop();
          this.dealDateDialog = true;
        }
      }, 500);
    },
    /** 종료임박 deal 연장 함수 */
    extendConfirm() {
      this.$cf.call(
        process.env.API + "/api/deal/extend",
        {
          email: "clientTest@gmail.com",
          /** TODO 로그인 구현 후 사용 변경 */
          // email: this.currentUser,
          dealNo: this.dealDateOne.dealNo
        },
        this.extendCb,
        true
      );
    },
    /** 종료임박 deal 연장 콜백 함수 */
    extendCb(response) {
      this.dealDateDialog = false;
      this.dealDateOne = {};
      this.dealDateList = [];
      this.pageInit = false;
      this.$store.commit("setLoading", { isLoading: true });
      this.dealList = [];
      this.getList("1");
      this.getDates();
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "해당 DEAL이 연장되었습니다.",
        caption: "구매예정일이 1주일 연장됩니다."
      });
    },
    /** list 호출 함수 */
    getList(page) {
      this.$cf.call(
        process.env.API + "/api/deal/list",
        {
          email: "clientTest@gmail.com",
          /** TODO 로그인 구현 후 사용 변경 */
          // email: this.currentUser,
          page: page
        },
        this.listCb,
        true
      );
    },
    /** list 콜백 함수 */
    listCb(response) {
      console.log(response);
      this.pageInfo = response.pageInfo;
      for (let n in response.dealList) {
        this.dealList.push(response.dealList[n]);
      }
      this.pageInit = true;
    },
    /** 삭제 호출 함수  */
    deleteConfirm() {
      this.$cf.call(
        process.env.API + "/api/deal/delete",
        {
          email: "clientTest@gmail.com",
          /** TODO 로그인 구현 후 사용 변경 */
          // email: this.currentUser,
          dealNo: this.deleteTarget.dealNo
        },
        this.deleteCb,
        true
      );
    },
    /** 삭제 콜백 함수 */
    deleteCb(result) {
      this.deleteDialog = false;
      this.deleteTarget = {};
      this.pageInit = false;
      this.$store.commit("setLoading", { isLoading: true });
      this.dealList = [];
      this.getList("1");
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "해당 항목이 삭제되었습니다."
      });
    }
  },
  computed: {
    /** 현재 로그인 한 사용자 */
    currentUser: {
      get() {
        return this.$store.getters.currentUser;
      }
    }
  }
};
</script>
