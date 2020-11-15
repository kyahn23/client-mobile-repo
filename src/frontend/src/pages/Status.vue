<template>
  <div class="column">
    <div class="q-ml-md q-mt-lg q-mb-xs text-subtitle1 text-weight-bold">
      상담현황 리스트
    </div>
    <q-list class="q-mb-xl">
      <q-separator />
      <div v-for="(dealOne, index) in dealList" :key="index">
        <q-item class="q-py-md">
          <q-item-section avatar>
            <q-avatar square size="4em">
              <img :src="dealOne.pnMdlImg" />
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
    </q-list>
  </div>
</template>

<script>
export default {
  name: "PageStatus",
  data() {
    return {
      /** DEAL 리스트 */
      dealList: [
        {
          dealNo: "100000000001",
          dealDt: "2020-11-11 00:00:00",
          pnMdlImg: "/images/phone_thumbnail.jpg",
          pnMdlNm: "삼성 갤럭시 S20",
          mntCarr: "SKT",
          pnRegDis: "번호이동",
          pnMntRtNm: "5GX 플래티넘",
          pnMinPrice: "250000"
        },
        {
          dealNo: "100000000002",
          dealDt: "2020-11-11 00:00:00",
          pnMdlImg: "/images/phone_thumbnail.jpg",
          pnMdlNm: "삼성 갤럭시 S20",
          mntCarr: "SKT",
          pnRegDis: "번호이동",
          pnMntRtNm: "5GX 플래티넘",
          pnMinPrice: "250000"
        },
        {
          dealNo: "100000000003",
          dealDt: "2020-11-11 00:00:00",
          pnMdlImg: "/images/phone_thumbnail.jpg",
          pnMdlNm: "삼성 갤럭시 S20",
          mntCarr: "SKT",
          pnRegDis: "번호이동",
          pnMntRtNm: "5GX 플래티넘",
          pnMinPrice: "250000"
        },
        {
          dealNo: "100000000004",
          dealDt: "2020-11-11 00:00:00",
          pnMdlImg: "/images/phone_thumbnail.jpg",
          pnMdlNm: "삼성 갤럭시 S20",
          mntCarr: "SKT",
          pnRegDis: "번호이동",
          pnMntRtNm: "5GX 플래티넘",
          pnMinPrice: "250000"
        },
        {
          dealNo: "100000000005",
          dealDt: "2020-11-11 00:00:00",
          pnMdlImg: "/images/phone_thumbnail.jpg",
          pnMdlNm: "삼성 갤럭시 S20",
          mntCarr: "SKT",
          pnRegDis: "번호이동",
          pnMntRtNm: "5GX 플래티넘",
          pnMinPrice: "250000"
        }
      ],
      /** $cf.call 파라미터 예제 */
      paramTest: {
        email: "clientTest@gmail.com",
        page: "1"
      }
    };
  },
  mounted() {
    /** $cf.call 예제 */
    this.$cf.call(
      process.env.API + "/api/deal/list",
      this.paramTest,
      this.cbTest,
      true
    );
  },
  methods: {
    /** > 클릭 이벤트 */
    dealClick(dealNo) {
      this.$router.push({ path: "/ongoing/" + dealNo });
    },
    /** $cf.call 콜백 함수 예제 */
    cbFunction(response) {
      let responseColor = "warning";
      let responseTextColor = "dark";
      if (response) {
        responseColor = "positive";
        responseTextColor = "white";
      }
      this.$store.commit("setNotification", {
        color: responseColor,
        textColor: responseTextColor,
        message: response + "",
        caption: "정상적인 결과 출력"
      });
    },
    cbTest(response) {
      console.log(response);
      this.$store.commit("setNotification", {
        color: "positive",
        textColor: "white",
        message: "SUCC",
        caption: "정상적인 결과 출력"
      });
    }
  }
};
</script>
