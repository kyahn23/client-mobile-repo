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
                  <q-icon name="delete_outline" color="black" />
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
      /** DEAL 리스트 */
      dealList: []
    };
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });
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
    /** > 클릭 이벤트 */
    dealClick(dealNo) {
      this.$router.push({ path: "/ongoing/" + dealNo });
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
