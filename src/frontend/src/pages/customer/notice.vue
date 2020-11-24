<template>
  <div>
    <div id="top" class="q-px-md q-mt-lg q-pt-lg">
      <div class="q-mb-xs">
        <span class="text-subtitle1 text-weight-bold">공지사항</span>
        <span class="q-pa-xs float-right text-subtitle2">총 36건</span>
      </div>
    </div>

    <q-list>
      <q-separator/>
      <q-infinite-scroll @load="onScrollLoad" :offset="110">
        <!--        <div v-for="(dealOne, index) in dealList" :key="index">-->
        <div>
          <q-item>
            <q-item-section class="q-py-xs">
              <q-item-label class="text-subtitle2 text-weight-bold" lines="1">
                <q-btn label="공지" size="xs" color="primary" padding="xs"/>
                펜타에스에 오신것을 환영합니
              </q-item-label>
              <q-item-label caption>2020.09.21 | 시스템관리자</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-item-label>
                <q-icon
                  name="chevron_right"
                  size="sm"
                  @click="noticeClick(1)"/>
              </q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <q-separator/>
        <div>
          <q-item>
            <q-item-section class="q-py-xs">
              <q-item-label class="text-weight-bold" lines="1">
                <q-btn label="공지" size="xs" color="primary" padding="xs"/>
                펜타에스에 오신것을 환영합니다
              </q-item-label>
              <q-item-label caption>2020.09.21 | 시스템관리자</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-item-label>
                <q-icon
                  name="chevron_right"
                  size="sm"
                  @click="noticeClick(1)"/>
              </q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <q-separator/>
        <div>
          <q-item>
            <q-item-section class="q-py-xs">
              <q-item-label class="text-weight-bold" lines="1">
                펜타에스에 오신것을 환영합니다
              </q-item-label>
              <q-item-label caption>2020.09.21 | 시스템관리자</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-item-label>
                <q-icon
                  name="chevron_right"
                  size="sm"
                  @click="noticeClick(1)"/>
              </q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <q-separator/>
        <div>
          <q-item>
            <q-item-section class="q-py-xs">
              <q-item-label class="text-weight-bold" lines="1">
                펜타에스에 오신것을 환영합니다
              </q-item-label>
              <q-item-label caption>2020.09.21 | 시스템관리자</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-item-label>
                <q-icon
                  name="chevron_right"
                  size="sm"
                  @click="noticeClick(1)"/>
              </q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <q-separator/>
        <div>
          <q-item>
            <q-item-section class="q-py-xs">
              <q-item-label class="text-weight-bold" lines="1">
                펜타에스에 오신것을 환영합니다
              </q-item-label>
              <q-item-label caption>2020.09.21 | 시스템관리자</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-item-label>
                <q-icon
                  name="chevron_right"
                  size="sm"
                  @click="noticeClick(1)"/>
              </q-item-label>
            </q-item-section>
          </q-item>
        </div><q-separator/>
        <div>
          <q-item>
            <q-item-section class="q-py-xs">
              <q-item-label class="text-weight-bold" lines="1">
                펜타에스에 오신것을 환영합니다
              </q-item-label>
              <q-item-label caption>2020.09.21 | 시스템관리자</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-item-label>
                <q-icon
                  name="chevron_right"
                  size="sm"
                  @click="noticeClick(1)"/>
              </q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <q-separator/>
      </q-infinite-scroll>

      <q-item class="q-py-md" v-if="this.pageInfo.hasNextPage">
        <q-item-section>
          <q-spinner color="primary" size="sm" class="self-center"/>
        </q-item-section>
      </q-item>

      <q-item
        clickable
        class="q-py-md"
        v-else-if="this.pageInfo.lastPage && this.pageInfo.totalPages > 1"
        @click="scrollTop">
        <q-item-section>
          <q-item-label class="self-center">
            <q-icon name="expand_less" size="md"/>
          </q-item-label>
          <q-item-label class="q-pb-md self-center text-caption">
            맨 위로 돌아가기
          </q-item-label>
        </q-item-section>
      </q-item>
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
      page:'1',
      /** 페이지 초기화 여부 */
      pageInit: false,
      /** 페이징 처리 정보 */
      pageInfo: {},
      /* 공지사항리스트 */
      noticeList: [],
    }
  },
  mounted() {
    this.$store.commit("setLoading", { isLoading: true });


    this.getNotice(this.page);
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
          this.getNotice(index + "");
          done();
        } else if (this.pageInfo.hasNextPage) {
          this.getNotice(this.pageInfo.nextPage + "");
          done();
        }
      }, 2000);
    },
    /** list 호출 함수 */
    getNotice(page) {
      console.log(page)
      this.$cf.call(
        process.env.API + "/api/customer/noticeList",
        {
          // email: "clientTest@gmail.com",
          page: page
        },
        this.getNoticeCB,
        true
      );
    },
    /** list 콜백 함수 */
    getNoticeCB(response) {
      console.log(response);
      this.pageInfo = response.pageInfo;

      this.noticeList = response.noticeList
      // for (let n in response.noticeList) {
      //   this.noticeList.push(response.noticeList[n]);
      // }
    },
    noticeClick(no) {
      console.log(no)
      this.$router.push({ path: "notice/" + no });
    }
  }
}
</script>


<style scoped>

</style>
