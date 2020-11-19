<template>
  <div class="q-mt-lg q-pt-lg">
    <div class="full-width full-height">
      <q-stepper
        class="no-box-shadow"
        v-model="step"
        ref="stepper"
        animated
        contracted
        header-class="no-header">
        <q-step
          class="no-padding no-margin"
          :name="1" title="step 1" :done="step > 1"
          style="min-height: 250px">
          <div class="step1content">
            <!-- Your content goes here -->
            <div class="intro-bg q-px-md q-py-xs">
              <p class="text-subtitle1 text-weight-bold">원하는 조건을 입력하시면</p>
              <p class="text-subtitle1 text-weight-bold">
                <span style="color: cornflowerblue;">지역별 시세정보</span>와</p>
              <p class="text-subtitle1 text-weight-bold">
                <span style="color: cornflowerblue;">상담</span>을 받으실 수 있습니다.</p>
            </div>
            <div class="q-px-md">
              <p class="text-body2 text-weight-medium"
                 style="word-break: keep-all;">
                이곳 저곳 방문하지 않고 간단한
                조건 등록을 통해서 원하는 대리점을 추천해 드립니다.
              </p>
            </div>
            <div>
              <div class="q-mx-md text-subtitle1 text-weight-bold">상담등록 절차</div>
              <img class="full-width text-center q-pa-sm" src="/images/intro-icon2.png">
            </div>
          </div>
        </q-step>

        <q-step
          class="no-padding no-margin"
          :name="2"
          title="step2"
          style="min-height: 250px">
          <div class="step2content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">STEP 1. 가입유형</p>
            </div>

            <div class="no-margin">
              <img style="border-bottom: 2px solid lightgrey;" class="full-width text-center q-pa-sm"
                   src="/images/step01-img1.png">
            </div>
            <div class="q-ma-sm">
              <div class="row q-mb-sm">
                <div class="col-3 items-center"><span class="text-subtitle2 text-weight-bold q-pl-sm">가입유형</span></div>
                <div class="col-9 self-center">
                  <q-radio class="text-caption q-mr-xs" style="width: 32%" dense size="xs"
                           v-model="signType"
                           val="newSign"
                           label="신규가입"></q-radio>
                  <q-radio class="text-caption q-mr-xs" style="width: 32%" dense size="xs"
                           v-model="signType"
                           val="moveCarr"
                           label="번호이동"></q-radio>
                  <q-radio class="text-caption" style="width: 32%" dense size="xs"
                           v-model="signType" val="chgDev"
                           label="기기변경"></q-radio>
                </div>
              </div>
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center q-pl-sm">지원구분</span>
                <q-select
                  class="col-9 self-center"
                  outlined
                  v-model="saleType"
                  :options="saleOpts"
                  dense
                  emit-value
                  map-options>
                </q-select>
              </div>
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center q-pl-sm">요금제</span>
                <q-select
                  class="col-9 self-center"
                  outlined
                  v-model="selectedMntRt"
                  :options="mntRtList"
                  dense
                  emit-value
                  map-options>
                </q-select>
              </div>
            </div>
            <div>
              <ul>
                <li><span style="color: lightgray;">데이터 :</span> 무제한</li>
                <li><span style="color: lightgray;">음성 :</span> 무제한</li>
                <li><span style="color: lightgray;">문자 :</span> 무제한</li>
              </ul>
            </div>
          </div>
        </q-step>

        <q-step
          :name="3"
          title="step3"
          style="min-height: 250px">
          <div class="step3content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">STEP 2. 스마트폰 선택</p>
            </div>
            <div class="no-margin">
              <img style="border-bottom: 2px solid lightgrey;" class="full-width text-center q-pa-sm"
                   src="/images/step02-img1.png">
            </div>
            <div class="q-ma-sm">
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center q-pl-sm">제조사</span>
                <q-select
                  class="col-9 self-center" outlined
                  v-model="pnMkr" :options="pnMkrList"
                  dense emit-value map-options>
                </q-select>
              </div>
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center q-pl-sm">모델명</span>
                <q-select
                  class="col-9 self-center" outlined
                  v-model="pnMkr" :options="pnMkrList"
                  dense emit-value map-options>
                </q-select>
              </div>
            </div>
          </div>

        </q-step>

        <q-step
          class="no-border"
          :name="4"
          title="step4"
          style="min-height: 200px">
          <div class="page-content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">STEP 3. 기타 상담정보</p>
            </div>
            <div class="no-margin">
              <img style="border-bottom: 2px solid lightgrey;"
                   src="/images/step03-img1.png"
                   class="full-width text-center q-pa-sm">
            </div>
            <div class="q-ma-sm">
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center text-caption text-center">희망 구매지역</span>
                <q-select
                  class="col-4 self-center" outlined
                  v-model="pnMkr" :options="pnMkrList"
                  label="경상남도"
                  dense emit-value map-options>
                </q-select>
                <q-select
                  class="col-5 self-center q-pl-xs" outlined
                  v-model="pnMkr2" :options="pnMkrList2"
                  label="경상남도"
                  dense emit-value map-options>
                </q-select>
              </div>
              <div class="row q-mb-sm">
                <span class="col-3 text-weight-bold self-center text-caption text-center">예상 구매일</span>
                <q-input class="col-9 self-center input-buy-date" outlined dense v-model="hopeDt" mask="date"
                         :rules="['date']">
                  <template v-slot:append>
                    <q-icon name="event" class="cursor-pointer">
                      <q-popup-proxy ref="qDateProxy" transition-show="scale" transition-hide="scale">
                        <q-date v-model="hopeDt">
                          <div class="row items-center justify-end">
                            <q-btn v-close-popup label="Close" color="primary" flat/>
                          </div>
                        </q-date>
                      </q-popup-proxy>
                    </q-icon>
                  </template>
                </q-input>
              </div>
              <div>
                <span class="q-pl-sm text-caption text-weight-bold full-width">기타 요청사항</span>
                <q-input
                  style="height: 100px"
                  class="full-width"
                  v-model="etc"
                  outlined
                  type="textarea"
                  maxlength="30"
                  :rules="[ val => val.length <= 30 || '요청사항은 30자 이내로 작성해주세요']"
                  hint="요청사항은 30자 이내로 작성해주세요"/>
              </div>
            </div>
          </div>
        </q-step>
        <q-step
          class="no-border"
          :name="5"
          title="step5"
          style="min-height: 250px">
          <div class="page-content">
            <!-- Your content goes here -->
            <div class="q-px-md q-py-xs">
              <p class="text-subtitle2 text-weight-bold no-margin">FINAL. 상담등록 정보 확인/등록</p>
            </div>
            <div class="no-margin">
              <img style="border-bottom: 2px solid lightgrey;"
                   src="/images/final-img1.png"
                   class="full-width text-center q-pa-sm">
            </div>
            <div class="q-pa-sm">
              <table style="width: 100%; border-spacing: 0px;">
                <tr style="height: 2.5rem">
                  <th class="bg-blue-1" style="width: 40%; border-top: 2px solid lightgrey;">통신사</th>
                  <td style="width: 60%; border-top: 2px solid lightgrey;;">
                    <span class="q-pl-md">SKT</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem">
                  <th class="bg-blue-1" style="width: 40%;">가입유형</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">번호이동</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">지원구분</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">공시지원금</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">요금제</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">
                      5GX 프리미엄
                    </span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">선택모델</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">갤럭시 S20</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%;">희망 구매지역</th>
                  <td style="width: 60%;">
                    <span class="q-pl-md">서울시 영등포구</span>
                  </td>
                </tr>
                <tr style="height: 2.5rem;">
                  <th class="bg-blue-1" style="width: 40%; border-bottom: 2px solid lightgrey;">예상 개통일</th>
                  <td style="width: 60%; border-bottom: 2px solid lightgrey;">
                    <span class="q-pl-md">2020-11-11</span>
                  </td>
                </tr>
              </table>
              <div class="q-my-xs" style="margin-bottom: 40px">
                <span class="q-mt-sm q-pl-sm text-weight-bold full-width">기타 요청사항</span>
                <p
                  style="height: 60px; border: 1px solid lightgrey; border-radius: 5px"
                  class="full-width text-caption q-pa-xs q-mb-lg">
                  {{ etc }}
                </p>
              </div>
            </div>
          </div>
        </q-step>

        <template v-slot:navigation>
          <q-stepper-navigation class="no-margin no-padding fixed-bottom" style="margin-bottom: 56px !important;">
            <q-btn v-if="step === 1"
                   @click="$refs.stepper.next()"
                   color="primary"
                   padding="sm"
                   label="SKT 상담등록 시작하기 >>"
                   class="full-width">
            </q-btn>
            <q-btn v-if="step === 2"
                   color="grey"
                   padding="sm"
                   @click="$refs.stepper.previous()"
                   label="취소"
                   style="width: 30%">
            </q-btn>
            <q-btn v-if="step > 2"
                   color="grey"
                   padding="sm"
                   @click="$refs.stepper.previous()"
                   label="이전"
                   style="width: 30%">
            </q-btn>
            <q-btn v-if="step < 5 && step !== 1"
                   @click="nextStep"
                   label="다음"
                   padding="sm"
                   color="primary"
                   style="width: 70%">
            </q-btn>
            <q-btn v-if="step === 5"
                   @click="$refs.stepper.next()"
                   label="등록하기 >>"
                   padding="sm"
                   color="primary"
                   style="width: 70%">
            </q-btn>
          </q-stepper-navigation>
        </template>
      </q-stepper>
    </div>
  </div>
</template>

<script>
export default {
  name: "SKT",
  data() {
    return {
      step: 1,
      carrShort: 'S',
      carrLong: 'SKT',
      hopeDt: '2020/11/21',
      etc: '블랙색상.전화는 오후 3시이후',
      carr: "S",
      signType: null,
      signOpts: [
        {label: "신규가입", value: "newSign"},
        {label: "번호이동", value: "moveCarr"},
        {label: "기기변경", value: "chgDev"}
      ],
      saleType: 'all',
      saleOpts: [
        {label: "선택", value: "all"},
        {label: "공시지원", value: "ofclDv"},
        {label: "요금할인", value: "ofclRt"}
      ],
      selectedMntRt: {label: '선택', pnMntRtNo: 'all'},
      mntRtList: [],
    };
  },
  watch: {},
  mounted() {
    this.getMntRtList()
  },
  methods: {
    getMntRtList() {
      let param = {
        mntCarr: this.carrLong
      }
      this.$cf.call(
        process.env.API + "/api/regCounsel/mntRtList",
        param,
        this.getMntRtListCB,
        true
      )
    },
    getMntRtListCB(response) {
      const obj = {
        label: '선택',
        pnMntRtNo: 'all'
      }
      this.mntRtList.push(obj)
      for (let n in response.mntRtList) {
        response.mntRtList[n].label = response.mntRtList[n].pnMntRtNm.concat(" ", Number(response.mntRtList[n].pnMntAmt).toLocaleString(), "원")
        this.mntRtList.push(response.mntRtList[n])
      }
      // this.mntRtList =  response.mntRtList
    },
    nextStep() {
      // step 1 은 상담 등록 시작화면
      switch (this.step) {
        case 2 :
          let step2Chk = false
          if (this.signType === null) {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "가입유형을 선택해주세요."
            });
            step2Chk = true
          } else if (this.saleType === 'all') {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "지원구분을 선택해주세요."
            });
            step2Chk = true
          } else if (this.selectedMntRt.pnMntRtNo === 'all') {
            this.$store.commit("setNotification", {
              color: "grey-8",
              textColor: "white",
              message: "요금제를 선택해주세요."
            });
            step2Chk = true
          }

          if (!step2Chk) {
            // alert(t)
            this.$refs.stepper.next();
          }
          break
        case 3 :
          break
        case 4 :
          break
        case 5 :
          break
      }

    }
  }
}
</script>
<style>
.no-header {
  display: none;
}

.intro-bg {
  background-image: url("/images/intro-icon1_bg.png");
  background-size: contain;
  background-repeat: no-repeat;
  background-position: right;
}

.q-stepper--horizontal .q-stepper__step-inner {
  padding: unset;
}

.input-buy-date {
  padding-bottom: unset;
}

.q-field__bottom {
  padding: 4px 8px 0;
}

.q-textarea .q-field__native {
  padding-top: 8px;
}
</style>
