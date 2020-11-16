<template>
  <div class="column">
    <div class="q-ml-md q-mt-lg q-mb-xs text-subtitle1 text-weight-bold">
      상담현황 리스트
    </div>
    <q-list class="q-mb-xl">
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
              <q-item-label class="text-weight-bold">
                {{ dealOne.pnMdlNm }}
              </q-item-label>
              <q-item-label>
                최저가
                <span class="text-h6 text-weight-bold">
                  <span class="text-primary">
                    {{ dealOne.pnMinPrice | showMeTheMoney }}
                  </span>
                  <q-icon name="delete_outline" color="black" />
                </span>
              </q-item-label>
              <q-item-label caption lines="2" class="text-black">
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
    </q-list>
  </div>
</template>

<script>
export default {
  name: "PageStatus",
  data() {
    return {
      pageInfo: {},
      /** DEAL 리스트 */
      dealList: [],
      /** $cf.call 파라미터 예제 */
      listParam: {
        email: "clientTest@gmail.com",
        page: "1"
      }
    };
  },
  mounted() {
    /** $cf.call 예제 */
    // this.$cf.call(
    //   process.env.API + "/api/deal/list",
    //   this.listParam,
    //   this.listCb,
    //   true
    // );
    // this.getList("1");
  },
  methods: {
    /** > 클릭 이벤트 */
    dealClick(dealNo) {
      this.$router.push({ path: "/ongoing/" + dealNo });
    },
    /** $cf.call 콜백 함수 예제 */
    // cbFunction(response) {
    //   let responseColor = "warning";
    //   let responseTextColor = "dark";
    //   if (response) {
    //     responseColor = "positive";
    //     responseTextColor = "white";
    //   }
    //   this.$store.commit("setNotification", {
    //     color: responseColor,
    //     textColor: responseTextColor,
    //     message: response + "",
    //     caption: "정상적인 결과 출력"
    //   });
    // },
    /** list 호출 함수 */
    getList(page) {
      this.$cf.call(
        process.env.API + "/api/deal/list",
        {
          email: "clientTest@gmail.com",
          page: page
        },
        this.listCb,
        true
      );
    },
    listCb(response) {
      console.log(response);
      this.pageInfo = response.pageInfo;
      for (let n in response.dealList) {
        this.dealList.push(response.dealList[n]);
      }
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
      }, 1);
    }
  }
};
</script>
