<template>
  <div>
    <q-card flat>
      <q-card-section horizontal class="q-ml-md q-my-sm">
        <q-img
          class="col-4"
          style="max-width: 100px;"
          src="/images/phone_thumbnail.jpg"
          contain
        />
        <q-card-section class="self-center full-width q-pl-sm">
          <div class="row">
            <div class="col text-subtitle1 text-weight-bold">
              {{ dealOne.pnMdlNm }}
            </div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">통신사</div>
            <div class="col">{{ dealOne.mntCarr }}</div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">가입유형</div>
            <div class="col">{{ dealOne.pnRegDis }}</div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">요금제</div>
            <div class="col">{{ dealOne.pnMntRtNm }}</div>
          </div>
          <div class="row">
            <div class="col-5 text-subtitle2 text-weight-bold">예상개통일</div>
            <div class="col">{{ dealOne.dealDt }}</div>
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
    >
      <q-route-tab label="상담진행" to="/ongoing/100000000001" />
      <q-route-tab label="상담대기" to="/waiting/100000000001" />
    </q-tabs>
    <div class="col">
      <q-card
        flat
        class="q-mt-sm q-mb-md"
        v-for="(consultOne, index) in consultList"
        :key="index"
      >
        <q-card-section>
          <div class="row self-center">
            <div
              class="q-mr-sm text-subtitle2 text-weight-bold"
              style="font-size: 1rem;"
            >
              {{ consultOne.bnNm }}
            </div>
            <div class="text-primary">
              (<q-icon name="star_rate" /> {{ consultOne.bnRtn }} / 5.0)
            </div>
          </div>
        </q-card-section>
        <q-card-section class="q-py-none">
          <div
            class="row q-py-xs text-caption text-grey-5 justify-evenly"
            style="border: 1px solid gray; border-radius: 4px;"
          >
            <span :class="callStatus(consultOne.callStCd === 'R')">상담접수중</span>
            <span :class="callStatus(consultOne.callStCd === 'A')">방문예정</span>
            <span :class="callStatus(consultOne.callStCd === 'B')">상담완료</span>
            <span :class="callStatus(consultOne.callStCd === 'C')">상담취소</span>
          </div>
        </q-card-section>
        <q-card-section>
          <div class="row">
            <div class="col-4 text-subtitle2 text-weight-bold">위치</div>
            <div class="col">{{ consultOne.bnLoc }}</div>
          </div>
          <div class="row">
            <div class="col-4 text-subtitle2 text-weight-bold">실구매가</div>
            <div class="col">{{ consultOne.realPrice | showMeTheMoney }}</div>
          </div>
          <div class="row">
            <div class="col-4 text-subtitle2 text-weight-bold">부가서비스</div>
            <div class="col">{{ consultOne.extSrvYn | isIncluded }}</div>
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
            />
          </div>
        </q-card-section>
        <q-card-section>
          <div class="row self-center q-mb-sm text-subtitle2 text-weight-bold">
            <div class="q-mr-xs">
              <q-icon name="error" style="font-size: 1.4em;" />
            </div>
            <div>비고</div>
          </div>
          <div class="row bg-grey-2">
            <p class="q-pa-md q-ma-none">
              <pre
                class="q-my-none text-body2"
                style="font-family: inherit !important;" 
                >{{ consultOne.pnEtc }}</pre>
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
            />
          </div>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script>
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
      dealOne: {
        dealNo: "100000000001",
        dealDt: "2020-11-11",
        pnMdlImg: "/images/phone_thumbnail.jpg",
        pnMdlNm: "삼성 갤럭시 S20",
        mntCarr: "SKT",
        pnRegDis: "번호이동",
        pnMntRtNm: "5GX 플래티넘",
        dealReq: "색상 블랙 / 전화는 오후 3시 이후"
      },
      consultList: [
        {
          callNo: "10000000000001",
          bnNo: "100000000001",
          bnNm: "강서대리점",
          bnRtn: "4.0",
          callStCd: "R",
          bnLoc: "서울 강서구",
          realPrice: "250000",
          extSrvYn: "Y",
          pnEtc:
            "요금제 최소 유지기간 - 6개월" +
            "\n회선유지기간 - 6개월" +
            "\n부가서비스 - FLO 앤 데이터" +
            "\n부가서비스 유지기간 - 익월말까지"
        }
      ]
    };
  },
  methods: {
    callStatus(value) {
      if (value) return "text-weight-bold text-primary";
    }
  }
};
</script>
